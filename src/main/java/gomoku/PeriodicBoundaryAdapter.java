package gomoku;

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
        // w trybie periodic dla każdego pola != NULL zwracamy pełną długość planszy
        return board.length;
    }
}
