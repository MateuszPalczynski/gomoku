import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Position;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Detects critical threats for a given player.
 * A threat is either an "Open Three" or a "Four".
 */
public class ThreatDetector {
    private final BoundaryAdapter adapter;

    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {1, 1}, {1, -1}
    };

    public ThreatDetector(BoundaryAdapter adapter, int boardSize) {
        this.adapter = adapter;
    }

    /**
     * Counts unique "open three" threats. An open three is an arrangement of 3 stones
     * that can become an open four in one move.
     */
    public int countOpenThrees(Mark[][] board, Mark playerMark) {
        return countThreats(board, playerMark, 4, true);
    }

    /**
     * Counts unique "four" threats. A four is an arrangement of 4 stones
     * that can become a winning five-in-a-row in one move.
     */
    public int countFours(Mark[][] board, Mark playerMark) {
        return countThreats(board, playerMark, 5, false);
    }

    public int countDoubleThreeThreats(Mark[][] board, Mark playerMark) {
        if (board == null || playerMark == Mark.NULL) return 0;

        int doubleThreeMoveCount = 0;
        int n = board.length;

        // Iterate over every empty cell to see if playing there creates a double three
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {

                    board[r][c] = playerMark; // Temporarily place the stone

                    int openThreesCreated = 0;
                    // For this one move, check in all 4 directions how many open threes were formed
                    for (int[] dir : DIRECTIONS) {
                        int dr = dir[0], dc = dir[1];
                        int pos = countDirection(board, playerMark, r, c, dr, dc);
                        int neg = countDirection(board, playerMark, r, c, -dr, -dc);

                        // Check for an open line of THREE: .XXX.
                        if (1 + pos + neg == 3) {
                            int frontR = r + (pos + 1) * dr;
                            int frontC = c + (pos + 1) * dc;
                            int backR = r - (neg + 1) * dr;
                            int backC = c - (neg + 1) * dc;

                            if (adapter.isOnBoard(board, frontR, frontC) && adapter.get(board, frontR, frontC) == Mark.NULL &&
                                    adapter.isOnBoard(board, backR, backC) && adapter.get(board, backR, backC) == Mark.NULL) {
                                openThreesCreated++;
                            }
                        }
                    }

                    board[r][c] = Mark.NULL; // Backtrack

                    if (openThreesCreated >= 2) {
                        doubleThreeMoveCount++;
                    }
                }
            }
        }
        return doubleThreeMoveCount;
    }
    /**
     * A generic method to count threats of a certain type by checking every possible move.
     */
    private int countThreats(Mark[][] board, Mark playerMark, int targetLength, boolean checkOpenEnds) {
        if (board == null || playerMark == Mark.NULL) return 0;

        Set<String> foundThreatKeys = new HashSet<>();
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    // This helper will check all directions for the move (r,c)
                    // and add any found threat keys to the set.
                    addCreatedThreatsToSet(foundThreatKeys, board, playerMark, r, c, targetLength, checkOpenEnds);
                }
            }
        }
        return foundThreatKeys.size();
    }

    /**
     * Checks a single potential move at (r,c) and adds the canonical keys of any
     * threats it creates to the provided set.
     */
    private void addCreatedThreatsToSet(Set<String> keys, Mark[][] board, Mark playerMark, int r, int c, int targetLength, boolean checkOpenEnds) {
        board[r][c] = playerMark; // Temporarily place the stone

        for (int[] dir : DIRECTIONS) {
            int dr = dir[0], dc = dir[1];
            int pos = countDirection(board, playerMark, r, c, dr, dc);
            int neg = countDirection(board, playerMark, r, c, -dr, -dc);

            // Check if a line of the target length was formed
            if (1 + pos + neg == targetLength) {
                boolean endsAreValid = !checkOpenEnds; // If we don't need to check, ends are valid.

                if (checkOpenEnds) {
                    int frontR = r + (pos + 1) * dr;
                    int frontC = c + (pos + 1) * dc;
                    int backR = r - (neg + 1) * dr;
                    int backC = c - (neg + 1) * dc;
                    if (adapter.isOnBoard(board, frontR, frontC) && adapter.get(board, frontR, frontC) == Mark.NULL &&
                            adapter.isOnBoard(board, backR, backC) && adapter.get(board, backR, backC) == Mark.NULL) {
                        endsAreValid = true;
                    }
                }

                if (endsAreValid) {
                    // A valid threat was found. Collect the ORIGINAL stones.
                    List<Position> originalStones = new ArrayList<>();
                    for (int i = 1; i <= neg; i++) {
                        originalStones.add(new Position(c - i * dc, r - i * dr));
                    }
                    for (int i = 1; i <= pos; i++) {
                        originalStones.add(new Position(c + i * dc, r + i * dr));
                    }
                    keys.add(canonicalize(originalStones));
                }
            }
        }
        board[r][c] = Mark.NULL; // Backtrack
    }

    private String canonicalize(List<Position> positions) {
        // This sorting is critical for generating a unique key for a set of stones
        positions.sort(Comparator.comparingInt(Position::row).thenComparingInt(Position::col));
        return positions.stream()
                .map(p -> p.row() + "," + p.col())
                .collect(Collectors.joining(";"));
    }

    private int countDirection(Mark[][] board, Mark me, int r, int c, int dr, int dc) {
        int count = 0;
        int rr = r + dr;
        int cc = c + dc;
        while (adapter.get(board, rr, cc) == me) {
            count++;
            rr += dr;
            cc += dc;
        }
        return count;
    }

    public Set<Position> findOpenFourMoves(Mark[][] board, Mark playerMark) {
        if (board == null || playerMark == Mark.NULL) {
            return Collections.emptySet();
        }

        Set<Position> threateningMoves = new HashSet<>();
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    // Temporarily make the move
                    board[r][c] = playerMark;

                    // Check if this move created an open four
                    for (int[] dir : DIRECTIONS) {
                        int dr = dir[0], dc = dir[1];
                        // Check backwards from the new stone
                        int neg = countDirection(board, playerMark, r, c, -dr, -dc);
                        // Check forwards from the new stone
                        int pos = countDirection(board, playerMark, r, c, dr, dc);

                        // A line of 4 (_XXXX_) is formed if the new stone is part of a 4-in-a-row
                        // with open ends.
                        // We check all possible sub-segments of length 4 that include the new stone.
                        for (int i = 0; i <= 3; i++) {
                            int startOffset = -i;
                            int endOffset = 3 - i;

                            // Check if the segment is composed of 4 of our stones
                            // The segment is from (r + startOffset*dr) to (r + endOffset*dr)
                            int r_start = r + startOffset * dr;
                            int c_start = c + startOffset * dc;

                            // We need to verify that all 4 positions in this window are `playerMark`
                            boolean isSolidFour = true;
                            for(int k=0; k<4; k++){
                                if(adapter.get(board, r_start + k*dr, c_start + k*dc) != playerMark){
                                    isSolidFour = false;
                                    break;
                                }
                            }

                            if(isSolidFour){
                                // Now check if the ends are open
                                int frontR = r_start + 4 * dr;
                                int frontC = c_start + 4 * dc;
                                int backR = r_start - 1 * dr;
                                int backC = c_start - 1 * dc;

                                if (adapter.get(board, frontR, frontC) == Mark.NULL &&
                                        adapter.get(board, backR, backC) == Mark.NULL) {
                                    threateningMoves.add(new Position(c, r));
                                    break; // Found a threat in this direction, move to the next direction
                                }
                            }
                        }
                        if (threateningMoves.contains(new Position(c, r))) {
                            break; // Move to next direction
                        }
                    }
                    // Backtrack
                    board[r][c] = Mark.NULL;
                }
            }
        }
        return threateningMoves;
    }


    /**
     * Checks if the given player has a guaranteed win on their next turn.
     * A guaranteed win is defined as either:
     * 1. An immediate winning move (creates 5-in-a-row).
     * 2. A move that creates an "open four" (_XXXX_), which is unblockable.
     *
     * @param board      The current game board.
     * @param playerMark The player to check.
     * @return True if a forced win exists, false otherwise.
     */
    public boolean hasForcedWin(Mark[][] board, Mark playerMark) {
        int n = board.length;
        // Check for any move that results in a win or an open four
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    // Temporarily make the move
                    board[r][c] = playerMark;

                    // 1. Did this move win the game?
                    WinDetector winDetector = new WinDetector(adapter);
                    if (winDetector.hasWinner(board)) {
                        board[r][c] = Mark.NULL; // Backtrack
                        return true; // Found an immediate winning move
                    }

                    // 2. Did this move create an "open four"?
                    // (Using a simplified check here for clarity, can reuse findOpenFourMoves logic)
                    for (int[] dir : DIRECTIONS) {
                        int dr = dir[0], dc = dir[1];
                        int consecutive = 1 + countDirection(board, playerMark, r, c, dr, dc) + countDirection(board, playerMark, r, c, -dr, -dc);

                        if (consecutive >= 5) { // This case is caught by WinDetector above
                            continue;
                        }

                        if (consecutive == 4) {
                            // We have a line of 4. Check if it's open.
                            // Find the two ends of the line of 4.
                            int count_fwd = countDirection(board, playerMark, r, c, dr, dc);
                            int r_end1 = r + (count_fwd + 1) * dr;
                            int c_end1 = c + (count_fwd + 1) * dc;

                            int count_bwd = countDirection(board, playerMark, r, c, -dr, -dc);
                            int r_end2 = r - (count_bwd + 1) * dr;
                            int c_end2 = c - (count_bwd + 1) * dc;

                            if (adapter.get(board, r_end1, c_end1) == Mark.NULL && adapter.get(board, r_end2, c_end2) == Mark.NULL) {
                                board[r][c] = Mark.NULL; // Backtrack
                                return true; // Found a move that creates an open four
                            }
                        }
                    }

                    // Backtrack
                    board[r][c] = Mark.NULL;
                }
            }
        }
        return false; // No forced win found
    }
}