package fais.zti.oramus.gomoku;

import static org.junit.jupiter.api.Assertions.*;

import gomoku.BoardModel;
import org.junit.jupiter.api.Test;
import java.util.Set;

/**
 * Testy jednostkowe dla BoardModel.
 */
public class BoardModelTest {
    @Test
    public void testFromMovesEmpty() {
        Mark[][] board = BoardModel.fromMoves(3, Set.of());
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertEquals(Mark.NULL, board[r][c], "Pole powinno być NULL");
            }
        }
    }

    @Test
    public void testFromMovesWithMoves() {
        Set<Move> moves = Set.of(
                new Move(new Position(0, 0), Mark.CROSS),
                new Move(new Position(1, 2), Mark.NOUGHT)
        );
        Mark[][] board = BoardModel.fromMoves(3, moves);
        assertEquals(Mark.CROSS, board[0][0]);
        assertEquals(Mark.NOUGHT, board[2][1]);
        assertEquals(Mark.NULL, board[1][1]);
    }

    @Test
    public void testFromMovesOutOfBounds() {
        Set<Move> moves = Set.of(
                new Move(new Position(5, 5), Mark.CROSS)
        );
        assertThrows(IllegalArgumentException.class,
                () -> BoardModel.fromMoves(3, moves));
    }
}
