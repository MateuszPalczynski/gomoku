import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Position;

import java.util.*;
import java.util.stream.Collectors;

public class ThreatDetector {
    private final BoundaryAdapter adapter;

    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {1, 1}, {1, -1}
    };

    public ThreatDetector(BoundaryAdapter adapter, int boardSize) {
        this.adapter = adapter;
    }

    /**
     * Counts the number of distinct groups of three stones that constitute an "open three" threat.
     * An open three threat is any arrangement of 3 stones that can be made into an open four
     * with one move.
     *
     * @param board      The current game board.
     * @param playerMark The mark of the player whose threats are to be counted.
     * @return The number of unique open three threats.
     */
    public int countOpenThrees(Mark[][] board, Mark playerMark) {
        if (board == null || playerMark == Mark.NULL) {
            return 0;
        }

        Set<String> foundThreats = new HashSet<>();
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    board[r][c] = playerMark; // Temporarily place the mark

                    // Check if this move created an open four
                    if (isCreatingOpenFour(board, playerMark, r, c)) {
                        // It did. Now, find the 3 stones that made this possible.
                        String canonicalKey = getThreatKey(board, playerMark, r, c);
                        if (canonicalKey != null) {
                            foundThreats.add(canonicalKey);
                        }
                    }
                    board[r][c] = Mark.NULL; // Backtrack
                }
            }
        }
        return foundThreats.size();
    }

    /**
     * Finds the three existing stones that, combined with the new stone at (r,c),
     * created an open four, and returns their canonical key.
     */
    private String getThreatKey(Mark[][] board, Mark playerMark, int r, int c) {
        for (int[] dir : DIRECTIONS) {
            List<Position> stones = new ArrayList<>();
            stones.add(new Position(c, r)); // Add the new stone

            // Check forward
            for (int i = 1; i <= 4; i++) {
                if (adapter.get(board, r + i * dir[0], c + i * dir[1]) == playerMark) {
                    stones.add(new Position(c + i * dir[1], r + i * dir[0]));
                }
            }
            // Check backward
            for (int i = 1; i <= 4; i++) {
                if (adapter.get(board, r - i * dir[0], c - i * dir[1]) == playerMark) {
                    stones.add(new Position(c - i * dir[1], r - i * dir[0]));
                }
            }

            // We should have found 4 stones in total that form the line.
            // We want the key for the 3 original stones.
            if (stones.size() == 4) {
                stones.remove(new Position(c, r)); // Remove the new stone to get the original 3
                return canonicalize(stones);
            }
        }
        return null; // Should not happen if isCreatingOpenFour was true
    }

    /**
     * Creates a sorted, unique string key for a list of positions.
     */
    private String canonicalize(List<Position> positions) {
        positions.sort(Comparator.comparingInt(Position::row).thenComparingInt(Position::col));
        return positions.stream()
                .map(p -> p.row() + "," + p.col())
                .collect(Collectors.joining(";"));
    }

    private boolean isCreatingOpenFour(Mark[][] board, Mark playerMark, int r, int c) {
        for (int[] dir : DIRECTIONS) {
            int dr = dir[0], dc = dir[1];
            int pos = countDirection(board, playerMark, r, c, dr, dc);
            int neg = countDirection(board, playerMark, r, c, -dr, -dc);
            int total = 1 + pos + neg;

            if (total == 4) {
                int frontR = r + (pos + 1) * dr;
                int frontC = c + (pos + 1) * dc;
                int backR = r - (neg + 1) * dr;
                int backC = c - (neg + 1) * dc;

                if (adapter.isOnBoard(board, frontR, frontC) && adapter.get(board, frontR, frontC) == Mark.NULL &&
                        adapter.isOnBoard(board, backR, backC) && adapter.get(board, backR, backC) == Mark.NULL) {
                    return true;
                }
            }
        }
        return false;
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
}
