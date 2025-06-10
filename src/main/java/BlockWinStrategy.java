import fais.zti.oramus.gomoku.*;

/**
 * Strategy to block an opponent's immediate winning move (a line of 4
 * that would become 5).
 */
public class BlockWinStrategy extends AbstractStrategy {

    public BlockWinStrategy(BoundaryAdapter adapter) {
        super(adapter);
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        int n = board.length;

        // Iterate through all empty cells
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (adapter.get(board, r, c) == Mark.NULL) {
                    // If the opponent played here, would they win?
                    board[r][c] = opponent;
                    WinDetector winDet = new WinDetector(this.adapter);
                    if (winDet.hasWinner(board)) {
                        // If so, we must block it.
                        board[r][c] = Mark.NULL; // backtrack
                        return new Move(new Position(c, r), me);
                    }
                    board[r][c] = Mark.NULL; // backtrack
                }
            }
        }
        return null; // No immediate win for the opponent was found
    }
}