package gomoku;

import fais.zti.oramus.gomoku.Mark;

/**
 * Adapter obsługujący dostęp do granic planszy.
 */
public interface BoundaryAdapter {
    /** Pobiera wartość z tablicy, odpowiednio obsługując granice. */
    Mark get(Mark[][] board, int r, int c);
    /** Liczy długość linii tego samego znaku w kierunku dr, dc od punktu (r,c). */
    int countLine(Mark[][] board, int r, int c, int dr, int dc);
}
