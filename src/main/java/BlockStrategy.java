import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;

/** Blokuje ruch przeciwnika. */
public class BlockStrategy extends AbstractStrategy {
    public BlockStrategy() {
        super();
    }

    public BlockStrategy(BoundaryAdapter adapter) {
        super(adapter);
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] != Mark.NULL) continue;
                board[r][c] = opponent;
                WinDetector d = new WinDetector(adapter);  // Use injected adapter
                if (d.hasWinner(board)) {
                    board[r][c] = Mark.NULL;
                    return new Move(new Position(c, r), me);
                }
                board[r][c] = Mark.NULL;
            }
        }
        return null;
    }
}