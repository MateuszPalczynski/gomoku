package gomoku;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;

/** Tworzy podwójne zagrożenie (dwa otwarte cztery). */
public class DoubleThreatStrategy extends AbstractStrategy {
    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        int n = board.length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] != Mark.NULL) continue;
                board[r][c] = me;
                BoundaryAdapter a = new BoundedAdapter();
                int threats = 0;
                int[][] dirs = {{0,1},{1,0},{1,1},{1,-1}};
                for(int[] dir : dirs) {
                    if (a.countLine(board, r, c, dir[0], dir[1]) == 4) threats++;
                }
                board[r][c] = Mark.NULL;
                if (threats >= 2) {
                    return new Move(new Position(c, r), me);
                }
            }
        }
        return null;
    }
}
