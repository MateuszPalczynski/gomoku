import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;

/** Tworzy podwójne zagrożenie – dwa otwarte trójki. */
public class DoubleThreatStrategy extends AbstractStrategy {
    public DoubleThreatStrategy() {
        super();
    }

    public DoubleThreatStrategy(BoundaryAdapter adapter) {
        super(adapter);
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        int n = board.length;
        int[][] dirs = {{0,1},{1,0},{1,1},{1,-1}};
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] != Mark.NULL) continue;
                board[r][c] = me;
                int threats = 0;
                for (int[] dir : dirs) {
                    int dr = dir[0], dc = dir[1];
                    int neg = countDirection(board, me, r, c, -dr, -dc);
                    int pos = countDirection(board, me, r, c, dr, dc);
                    int total = neg + 1 + pos;
                    if (total == 3) {
                        int backR = r - (neg + 1) * dr;
                        int backC = c - (neg + 1) * dc;
                        int frontR = r + (pos + 1) * dr;
                        int frontC = c + (pos + 1) * dc;
                        if (adapter.get(board, backR, backC) == Mark.NULL &&
                                adapter.get(board, frontR, frontC) == Mark.NULL) {
                            threats++;
                        }
                    }
                }
                board[r][c] = Mark.NULL;
                if (threats >= 2) {
                    return new Move(new Position(c, r), me);
                }
            }
        }
        return null;
    }

    /** Zlicza kolejne pionki w kierunku (dr,dc). */
    private int countDirection(Mark[][] board, Mark me, int r, int c, int dr, int dc) {
        int count = 0;
        int rr = r + dr, cc = c + dc;
        while (adapter.get(board, rr, cc) == me) {
            count++;
            rr += dr;
            cc += dc;
        }
        return count;
    }
}