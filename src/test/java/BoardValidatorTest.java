import static org.junit.jupiter.api.Assertions.*;

import fais.zti.oramus.gomoku.*;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class BoardValidatorTest {
    @Test
    public void testInvalidCount() {
        Set<Move> moves = Set.of(
                new Move(new Position(0,0), Mark.CROSS),
                new Move(new Position(1,0), Mark.CROSS),
                new Move(new Position(2,0), Mark.CROSS)
        );
        // Specify firstMark explicitly (CROSS)
        BoardValidator v = new BoardValidator(new BoundedAdapter(), Mark.CROSS);
        assertThrows(WrongBoardStateException.class, () -> v.validate(5, moves, Mark.CROSS));
    }

    @Test
    public void testThrowWinner() {
        Set<Move> moves = Set.of(
                new Move(new Position(0,0), Mark.NOUGHT),
                new Move(new Position(0,1), Mark.NOUGHT),
                new Move(new Position(0,2), Mark.NOUGHT),
                new Move(new Position(0,3), Mark.NOUGHT),
                new Move(new Position(0,4), Mark.NOUGHT)
        );
        // Winner detection occurs before count, firstMark can be CROSS or NOUGHT
        BoardValidator v = new BoardValidator(new BoundedAdapter(), Mark.CROSS);
        TheWinnerIsException ex = assertThrows(
                TheWinnerIsException.class,
                () -> v.validate(5, moves, Mark.CROSS)
        );
        assertEquals(Mark.NOUGHT, ex.mark);
    }
}
