import fais.zti.oramus.gomoku.Mark;

public interface BoundaryAdapter {
    Mark get(Mark[][] board, int r, int c);

    boolean isOnBoard(Mark[][] board, int r, int c);

    int countLine(Mark[][] board, int r, int c, int dr, int dc);
}
