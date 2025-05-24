import fais.zti.oramus.gomoku.Mark;

/** Nie pozwala na wrap-around; wyjście poza tablicę zwraca Mark.NULL. */
public class BoundedAdapter implements BoundaryAdapter {
    @Override
    public Mark get(Mark[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board.length) {
            return Mark.NULL;
        }
        return board[r][c];
    }

    @Override
    public int countLine(Mark[][] board, int r, int c, int dr, int dc) {
        Mark target = get(board, r, c);
        if (target == Mark.NULL) {
            return 0;
        }
        int count = 0;
        int rr = r, cc = c;
        while (get(board, rr, cc) == target) {
            count++;
            rr += dr;
            cc += dc;
        }
        return count;
    }
}
