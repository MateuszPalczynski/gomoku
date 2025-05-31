import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreatDetector {
    private final BoundaryAdapter adapter;
    private final int boardSize;

    // Primary directions to check for sequences
    private static final int[][] DIRECTIONS = {
            {0, 1},  // Horizontal
            {1, 0},  // Vertical
            {1, 1},  // Diagonal Down-Right (\)
            {1, -1}  // Diagonal Up-Right / Anti-Diagonal (/)
    };

    public ThreatDetector(BoundaryAdapter adapter, int boardSize) {
        if (adapter == null) {
            throw new IllegalArgumentException("BoundaryAdapter cannot be null.");
        }
        this.adapter = adapter;
        this.boardSize = boardSize;
    }

    /**
     * Counts the number of distinct "open threes" for a given player.
     * An open three is defined as three consecutive stones of the player's mark,
     * with empty cells at both ends of the sequence (_MMM_).
     *
     * @param board      The current game board.
     * @param playerMark The mark of the player whose open threes are to be counted.
     * @return The number of distinct open threes.
     */
    public int countOpenThrees(Mark[][] board, Mark playerMark) {
        if (board == null || board.length != boardSize || playerMark == Mark.NULL) {
            return 0;
        }

        Set<String> foundOpenThrees = new HashSet<>();

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                // Consider (r,c) as the potential start of an MMM sequence
                if (adapter.get(board, r, c) == playerMark) {
                    for (int[] dir : DIRECTIONS) {
                        int dr = dir[0];
                        int dc = dir[1];

                        // Check for M M M sequence starting at (r,c)
                        // M1 is at (r, c)
                        Mark m2 = adapter.get(board, r + dr, c + dc);
                        Mark m3 = adapter.get(board, r + 2 * dr, c + 2 * dc);

                        if (m2 == playerMark && m3 == playerMark) {
                            // Found MMM. Now check if it's open: _MMM_
                            Mark spaceBefore = adapter.get(board, r - dr, c - dc);
                            Mark spaceAfter = adapter.get(board, r + 3 * dr, c + 3 * dc);

                            if (spaceBefore == Mark.NULL && spaceAfter == Mark.NULL) {
                                // This is an open three _M M M_
                                // M1 at (r,c), M2 at (r+dr, c+dc), M3 at (r+2dr, c+2dc)
                                // Create a canonical key for this open three to avoid duplicates.
                                List<Position> stonePositions = new ArrayList<>();
                                stonePositions.add(normalizePosition(r, c));
                                stonePositions.add(normalizePosition(r + dr, c + dc));
                                stonePositions.add(normalizePosition(r + 2 * dr, c + 2 * dc));

                                // Sort positions to ensure canonical order
                                // Use record accessor methods: row() and col()
                                Collections.sort(stonePositions, Comparator
                                        .comparingInt(Position::row) // Corrected: Position::row
                                        .thenComparingInt(Position::col)); // Corrected: Position::col

                                String key = "OT:" +
                                        stonePositions.get(0).row() + "," + stonePositions.get(0).col() + ":" + // Corrected
                                        stonePositions.get(1).row() + "," + stonePositions.get(1).col() + ":" + // Corrected
                                        stonePositions.get(2).row() + "," + stonePositions.get(2).col();      // Corrected
                                foundOpenThrees.add(key);
                            }
                        }
                    }
                }
            }
        }
        return foundOpenThrees.size();
    }

    /**
     * Normalizes a position's coordinates to be within [0, boardSize-1].
     * Useful for creating canonical keys, especially with periodic boundaries.
     * The Position record constructor is Position(int col, int row).
     */
    private Position normalizePosition(int r_param, int c_param) {
        int normalizedRow = ((r_param % boardSize) + boardSize) % boardSize;
        int normalizedCol = ((c_param % boardSize) + boardSize) % boardSize;
        return new Position(normalizedCol, normalizedRow); // Position record is (col, row)
    }
}