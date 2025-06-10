import fais.zti.oramus.gomoku.Mark;

/** Pozwala na wrap-around (tylko na kwadratowej planszy). */
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
        // Handle zero-direction case (no movement)
        if (dr == 0 && dc == 0) {
            return 1;
        }

        int n = board.length;
        int count = 0;
        int rr = r;
        int cc = c;

        // Traverse at most 'n' cells to avoid infinite loops and overcounting
        for (int i = 0; i < n; i++) {
            Mark current = get(board, rr, cc);
            if (current != target) {
                break; // Stop at first non-matching mark
            }
            count++;
            // Move to next position (wrapping handled in get())
            rr += dr;
            cc += dc;
        }
        return count;
    }

    @Override
    public boolean isOnBoard(Mark[][] board, int r, int c) {
        return true; // On a periodic board, every coordinate maps to a valid cell.
    }
}