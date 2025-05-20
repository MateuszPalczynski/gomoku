package fais.zti.oramus.gomoku;

/** Szuka ruchu prowadzącego do natychmiastowego zwycięstwa. */
public class ImmediateWinStrategy extends AbstractStrategy {
    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        int n = board.length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] != Mark.NULL) continue;
                board[r][c] = me;
                WinDetector d = new WinDetector();
                if (d.hasWinner(board, new BoundedAdapter())) {
                    board[r][c] = Mark.NULL;
                    return new Move(new Position(c, r), me);
                }
                board[r][c] = Mark.NULL;
            }
        }
        return null;
    }
}
