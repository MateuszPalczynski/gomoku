import fais.zti.oramus.gomoku.*;

public class BlockWinStrategy extends AbstractStrategy {

    public BlockWinStrategy(BoundaryAdapter adapter) {
        super(adapter);
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (adapter.get(board, r, c) == Mark.NULL) {
                    board[r][c] = opponent;
                    WinDetector winDet = new WinDetector(this.adapter);
                    if (winDet.hasWinner(board)) {
                        board[r][c] = Mark.NULL;
                        return new Move(new Position(c, r), me);
                    }
                    board[r][c] = Mark.NULL;
                }
            }
        }
        return null;
    }
}