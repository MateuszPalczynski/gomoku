import fais.zti.oramus.gomoku.Mark;

public class BoundedAdapter implements BoundaryAdapter {
    @Override
    public Mark get(Mark[][] board, int r, int c) {
        if (!isOnBoard(board, r, c)) { // Use our new method
            return Mark.NULL;
        }
        return board[r][c];
    }

    @Override
    public boolean isOnBoard(Mark[][] board, int r, int c) {
        if (board == null) return false;
        int n = board.length;
        return r >= 0 && r < n && c >= 0 && c < n;
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
