import fais.zti.oramus.gomoku.Mark;

/**
 * Detects if there is a winner on the board.
 * This version uses bidirectional line counting for accuracy.
 */
public class WinDetector {
    private Mark winner = Mark.NULL;
    private final BoundaryAdapter adapter;

    // Directions to check: Horizontal, Vertical, Diagonal Down-Right, Diagonal Up-Right
    private static final int[][] DIRECTIONS = {
            {0, 1},  // Horizontal
            {1, 0},  // Vertical
            {1, 1},  // Diagonal Down-Right (\)
            {1, -1}  // Diagonal Up-Right / Anti-Diagonal (/)
    };

    public WinDetector(BoundaryAdapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("BoundaryAdapter cannot be null.");
        }
        this.adapter = adapter;
    }

    /**
     * Checks if a player has won on the given board.
     * A win is defined as 5 or more consecutive stones.
     *
     * @param board The current state of the game board.
     * @return True if a winner is found, false otherwise.
     */
    public boolean hasWinner(Mark[][] board) {
        this.winner = Mark.NULL; // Reset winner state for this check
        if (board == null || board.length == 0) {
            return false;
        }
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                Mark currentMark = adapter.get(board, r, c);
                if (currentMark == Mark.NULL) {
                    continue;
                }

                for (int[] dir : DIRECTIONS) {
                    // Count stones in the positive direction (from r,c inclusive)
                    int countForward = adapter.countLine(board, r, c, dir[0], dir[1]);

                    // Count stones in the negative direction (from r,c inclusive)
                    // Note: countLine counts from (r,c) in the given direction.
                    // So, for the "opposite" part of the line, we count from (r,c) in -dir.
                    int countBackward = adapter.countLine(board, r, c, -dir[0], -dir[1]);

                    // The stone at (r,c) is counted in both countForward and countBackward,
                    // so subtract 1 to get the total distinct stones in the line through (r,c).
                    int totalLength = countForward + countBackward - 1;

                    if (totalLength >= 5) {
                        this.winner = currentMark;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the mark of the winning player.
     * Call this method after hasWinner() returns true.
     *
     * @return The Mark of the winner (CROSS or NOUGHT), or NULL if no winner yet or hasWinner was not called/returned false.
     */
    public Mark getWinner() {
        return this.winner;
    }
}