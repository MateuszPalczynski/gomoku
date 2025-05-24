package gomoku;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;

import java.util.Set;

/**
 * Tworzy reprezentację planszy na podstawie zestawu ruchów.
 */
public class BoardModel {
    /**
     * Buduje tablicę Mark[][] o zadanym rozmiarze na podstawie ruchów.
     * Puste pola przyjmują wartość Mark.NULL.
     *
     * @param size rozmiar planszy (liczba kolumn i wierszy)
     * @param moves zbiór wykonanych ruchów
     * @return tablica Mark[size][size]
     */
    public static Mark[][] fromMoves(int size, Set<Move> moves) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        Mark[][] board = new Mark[size][size];
        // wypełnij pustymi
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                board[r][c] = Mark.NULL;
            }
        }
        // nanieś ruchy
        for (Move m : moves) {
            Position p = m.position();
            int c = p.col();
            int r = p.row();
            if (r < 0 || r >= size || c < 0 || c >= size) {
                throw new IllegalArgumentException(
                        String.format("Move out of bounds: (%d,%d)", c, r));
            }
            board[r][c] = m.mark();
        }
        return board;
    }
}