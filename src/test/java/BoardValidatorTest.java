package fais.zti.oramus.gomoku;

import static org.junit.jupiter.api.Assertions.*;

import gomoku.BoardValidator;
import gomoku.BoundedAdapter;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class BoardValidatorTest {
    @Test
    public void testInvalidCount() {
        Set<Move> moves = Set.of(
                new Move(new Position(0,0),Mark.CROSS),
                new Move(new Position(1,0),Mark.CROSS),
                new Move(new Position(2,0),Mark.CROSS)
        );
        BoardValidator v = new BoardValidator(new BoundedAdapter());
        assertThrows(WrongBoardStateException.class, () -> v.validate(5,moves));
    }

    @Test
    public void testThrowWinner() {
        Set<Move> moves = Set.of(
                new Move(new Position(0,0),Mark.NOUGHT),
                new Move(new Position(0,1),Mark.NOUGHT),
                new Move(new Position(0,2),Mark.NOUGHT),
                new Move(new Position(0,3),Mark.NOUGHT),
                new Move(new Position(0,4),Mark.NOUGHT)
        );
        BoardValidator v = new BoardValidator(new BoundedAdapter());
        TheWinnerIsException ex = assertThrows(TheWinnerIsException.class,
                () -> v.validate(5,moves));
        assertEquals(Mark.NOUGHT, ex.mark);
    }
}
