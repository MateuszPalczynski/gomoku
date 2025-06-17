import fais.zti.oramus.gomoku.Mark;

public class PeriodicBoundaryAdapter implements BoundaryAdapter {
    @Override
    public Mark get(Mark[][] board, int r, int c) {
        int n = board.length;
        int rr = ((r % n) + n) % n;
        int cc = ((c % n) + n) % n;
        return board[rr][cc];
    }

    @Override
    public int countLine(Mark[][] board, int r, int c, int dr, int dc) {
        Mark target = get(board, r, c);
        if (target == Mark.NULL) {
            return 0;
        }
        if (dr == 0 && dc == 0) {
            return 1;
        }

        int n = board.length;
        int count = 0;
        int rr = r;
        int cc = c;

        for (int i = 0; i < n; i++) {
            Mark current = get(board, rr, cc);
            if (current != target) {
                break;
            }
            count++;
            rr += dr;
            cc += dc;
        }
        return count;
    }

    @Override
    public boolean isOnBoard(Mark[][] board, int r, int c) {
        return true;
    }
}