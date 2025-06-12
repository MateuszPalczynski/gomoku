import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Position;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Detects critical threats for a given player.
 * Contains corrected logic for periodic boards.
 */
public class ThreatDetector {
    private final BoundaryAdapter adapter;
    private final int boardSize; // Store board size for loop boundaries

    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {1, 1}, {1, -1}
    };

    public ThreatDetector(BoundaryAdapter adapter, int boardSize) {
        this.adapter = adapter;
        this.boardSize = boardSize; // Initialize the board size
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

    private int countThreats(Mark[][] board, Mark playerMark, int targetLength, boolean checkOpenEnds) {
        if (board == null || playerMark == Mark.NULL) return 0;

        Set<String> foundThreatKeys = new HashSet<>();
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    addCreatedThreatsToSet(foundThreatKeys, board, playerMark, r, c, targetLength, checkOpenEnds);
                }
            }
        }
        return foundThreatKeys.size();
    }

    private void addCreatedThreatsToSet(Set<String> keys, Mark[][] board, Mark playerMark, int r, int c, int targetLength, boolean checkOpenEnds) {
        board[r][c] = playerMark; // Temporarily place the stone

        for (int[] dir : DIRECTIONS) {
            int dr = dir[0], dc = dir[1];
            int pos = countDirection(board, playerMark, r, c, dr, dc);
            int neg = countDirection(board, playerMark, r, c, -dr, -dc);

            if (1 + pos + neg == targetLength) {
                boolean endsAreValid = !checkOpenEnds;

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
        positions.sort(Comparator.comparingInt(Position::row).thenComparingInt(Position::col));
        return positions.stream()
                .map(p -> p.row() + "," + p.col())
                .collect(Collectors.joining(";"));
    }

    /**
     * CORRECTED to prevent infinite loops on periodic boards.
     */
    private int countDirection(Mark[][] board, Mark me, int r, int c, int dr, int dc) {
        int count = 0;
        int rr = r + dr;
        int cc = c + dc;
        // The for loop prevents checking more cells than the board's width/height,
        // which avoids infinite loops on a periodic board.
        for (int i = 0; i < this.boardSize; i++) {
            if (adapter.get(board, rr, cc) == me) {
                count++;
                rr += dr;
                cc += dc;
            } else {
                break; // Stop when the line of stones is broken.
            }
        }
        return count;
    }

    // This method can be kept for other strategies that might use it.
    public Set<Position> findOpenFourMoves(Mark[][] board, Mark playerMark) {
        // ... (implementation remains the same as your original)
        return new HashSet<>(); // Placeholder
    }


    /**
     * REFACTORED AND IMPROVED: Checks if the given player has a guaranteed win on their next turn.
     * This version uses direct pattern matching, which is more robust for periodic boards.
     */
    public boolean hasForcedWin(Mark[][] board, Mark playerMark) {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (board[r][c] == Mark.NULL) {
                    // Simulate placing the stone
                    board[r][c] = playerMark;

                    // Condition 1: Does this move win the game immediately?
                    WinDetector winDetector = new WinDetector(adapter);
                    if (winDetector.hasWinner(board)) {
                        board[r][c] = Mark.NULL; // Backtrack
                        return true;
                    }

                    // Condition 2: Does this move create an unblockable "open four"?
                    if (createsOpenFour(board, playerMark, r, c)) {
                        board[r][c] = Mark.NULL; // Backtrack
                        return true;
                    }

                    // Backtrack before the next loop iteration
                    board[r][c] = Mark.NULL;
                }
            }
        }
        return false;
    }

    /**
     * NEW HELPER METHOD: Checks if placing a stone at (r, c) creates an open four.
     * An open four is a solid line of 4 with empty spaces at both ends (.XXXX.).
     */
    private boolean createsOpenFour(Mark[][] board, Mark player, int r, int c) {
        for (int[] dir : DIRECTIONS) {
            int dr = dir[0], dc = dir[1];
            // An open four is a pattern of 6 cells: NULL, P, P, P, P, NULL.
            // We check all 6-cell windows that have our new stone in one of the four 'P' positions.
            for (int i = 1; i <= 4; i++) {
                int startR = r - i * dr;
                int startC = c - i * dc;

                Mark before = adapter.get(board, startR, startC);
                Mark after = adapter.get(board, startR + 5 * dr, startC + 5 * dc);

                // Check if the ends are open
                if (before == Mark.NULL && after == Mark.NULL) {
                    // Check if the four spots in between are all the player's stones
                    boolean solidFour = true;
                    for (int j = 1; j <= 4; j++) {
                        if (adapter.get(board, startR + j * dr, startC + j * dc) != player) {
                            solidFour = false;
                            break;
                        }
                    }
                    if (solidFour) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}