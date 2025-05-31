import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;

/**
 * Strategy to block the opponent.
 * It first attempts to block an immediate win (5-in-a-row) for the opponent.
 * If no such threat exists, it attempts to block the opponent from creating an "open four".
 */
public class BlockStrategy extends AbstractStrategy {

    // Directions to check: Horizontal, Vertical, Diagonal Down-Right, Diagonal Up-Right
    // These are primary directions; the open four check will explore lines along these.
    private static final int[][] DIRECTIONS = {
            {0, 1},  // Horizontal
            {1, 0},  // Vertical
            {1, 1},  // Diagonal Down-Right (\)
            {1, -1}  // Diagonal Up-Right / Anti-Diagonal (/)
    };

    public BlockStrategy() {
        super(); // Assumes AbstractStrategy handles null adapter or has default
    }

    public BlockStrategy(BoundaryAdapter adapter) {
        super(adapter);
        if (adapter == null) {
            // Or handle as AbstractStrategy does. For robustness:
            throw new IllegalArgumentException("BoundaryAdapter cannot be null for BlockStrategy.");
        }
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        int n = board.length;

        // 1. Block opponent's immediate win (5-in-a-row)
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (adapter.get(board, r, c) == Mark.NULL) { // Use adapter for periodic checks if (r,c) is a valid empty spot
                    board[r][c] = opponent; // Simulate opponent's move
                    WinDetector winDet = new WinDetector(this.adapter); // Use the instance's adapter
                    if (winDet.hasWinner(board)) {
                        board[r][c] = Mark.NULL; // Revert
                        return new Move(new Position(c, r), me); // Block by playing here
                    }
                    board[r][c] = Mark.NULL; // Revert
                }
            }
        }

        // 2. Block opponent from creating an open four
        // An open four is _OOOO_ (four opponent stones with empty ends)
        // We need to find a move for 'me' that prevents 'opponent' from making such a formation.
        // This means checking if 'opponent' playing at an empty spot (r,c) would create an open four.
        // If so, 'me' should play at (r,c).
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (adapter.get(board, r, c) == Mark.NULL) {
                    board[r][c] = opponent; // Simulate opponent playing at (r,c)
                    if (createsOpenFour(board, opponent, r, c)) {
                        board[r][c] = Mark.NULL; // Revert
                        return new Move(new Position(c, r), me); // 'me' plays here to block
                    }
                    board[r][c] = Mark.NULL; // Revert
                }
            }
        }
        return null; // No critical block found
    }

    /**
     * Checks if placing 'playerMark' at (r_move, c_move) creates an open four for that player.
     * An open four is a line of exactly 4 of playerMark's stones with empty cells at both ends.
     * Example: _ M M M M _
     *
     * @param board      The board state *after* playerMark has been placed at (r_move, c_move).
     * @param playerMark The mark of the player whose move is being checked.
     * @param r_move     The row of the last move.
     * @param c_move     The column of the last move.
     * @return True if an open four is created, false otherwise.
     */
    private boolean createsOpenFour(Mark[][] board, Mark playerMark, int r_move, int c_move) {
        if (adapter.get(board, r_move, c_move) != playerMark) {
            // This should not happen if board was correctly updated before calling
            return false;
        }

        for (int[] dir : DIRECTIONS) {
            int dr = dir[0];
            int dc = dir[1];

            // Count stones in the positive direction (from r_move, c_move inclusive)
            int countForward = adapter.countLine(board, r_move, c_move, dr, dc);

            // Count stones in the negative direction (from r_move, c_move inclusive)
            int countBackward = adapter.countLine(board, r_move, c_move, -dr, -dc);

            int totalLength = countForward + countBackward - 1;

            if (totalLength == 4) {
                // Found a line of 4. Now check if it's open at both ends.
                // End point in positive direction: (r_move + dr*(countForward-1), c_move + dc*(countForward-1))
                // Cell after positive end:
                int r_after_fwd = r_move + dr * countForward;
                int c_after_fwd = c_move + dc * countForward;

                // End point in negative direction: (r_move - dr*(countBackward-1), c_move - dc*(countBackward-1))
                // Cell after negative end (which is before the start of countBackward's path from r_move,c_move):
                int r_after_bwd = r_move - dr * countBackward;
                int c_after_bwd = c_move - dc * countBackward;

                if (adapter.get(board, r_after_fwd, c_after_fwd) == Mark.NULL &&
                        adapter.get(board, r_after_bwd, c_after_bwd) == Mark.NULL) {
                    return true; // It's an open four
                }
            }
        }
        return false;
    }
}