package fais.zti.oramus.gomoku;

/** Szuka linii czterech z dwóch otwartych końców. */
public class OpenFourStrategy extends AbstractStrategy {
    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        int n = board.length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] != Mark.NULL) continue;
                board[r][c] = me;
                BoundaryAdapter a = new BoundedAdapter();
                int[][] dirs = {{0,1},{1,0},{1,1},{1,-1}};
                for(int[] dir : dirs) {
                    int len = a.countLine(board, r, c, dir[0], dir[1]);
                    if (len == 4) {
                        if (a.get(board, r - dir[0], c - dir[1]) == Mark.NULL &&
                                a.get(board, r + dir[0]*4 + dir[0], c + dir[1]*4 + dir[1]) == Mark.NULL) {
                            board[r][c] = Mark.NULL;
                            return new Move(new Position(c, r), me);
                        }
                    }
                }
                board[r][c] = Mark.NULL;
            }
        }
        return null;
    }
}
