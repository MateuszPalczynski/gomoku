import static org.junit.jupiter.api.Assertions.*;

import fais.zti.oramus.gomoku.*;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;

public class GomokuTest {
    @Test
    public void testWinnerDetected() {
        Game g = new GomokuBuilder().size(15).firstMark(Mark.CROSS).build();
        Set<Move> moves = new HashSet<>();
        moves.add(new Move(new Position(0,0), Mark.CROSS));
        moves.add(new Move(new Position(7,0), Mark.NOUGHT));
        moves.add(new Move(new Position(1,0), Mark.CROSS));
        moves.add(new Move(new Position(7,2), Mark.NOUGHT));
        moves.add(new Move(new Position(2,0), Mark.CROSS));
        moves.add(new Move(new Position(7,5), Mark.NOUGHT));
        moves.add(new Move(new Position(3,0), Mark.CROSS));
        moves.add(new Move(new Position(8,0), Mark.NOUGHT));
        moves.add(new Move(new Position(4,0), Mark.CROSS));
        assertThrows(TheWinnerIsException.class, () -> g.nextMove(moves, Mark.NOUGHT));
    }

    @Test
    public void testResignOnFullBoard() {
        Game g = new GomokuBuilder().size(13).firstMark(Mark.CROSS).build();
        Set<Move> moves = new HashSet<>();
        moves.add(new Move(new Position(4,0), Mark.CROSS));
        moves.add(new Move(new Position(7,0), Mark.NOUGHT));
        moves.add(new Move(new Position(1,0), Mark.CROSS));
        moves.add(new Move(new Position(7,2), Mark.NOUGHT));
        moves.add(new Move(new Position(2,0), Mark.CROSS));
        moves.add(new Move(new Position(7,5), Mark.NOUGHT));
        moves.add(new Move(new Position(3,0), Mark.CROSS));
        assertThrows(ResignException.class, () -> g.nextMove(moves, Mark.NOUGHT));
    }
}
