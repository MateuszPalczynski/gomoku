package fais.zti.oramus.gomoku;

import static org.junit.jupiter.api.Assertions.*;

import GomokuBuilder;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;

public class GomokuTest {
    @Test
    public void testWinnerDetected() {
        Game g = new GomokuBuilder().size(5).firstMark(Mark.CROSS).build();
        Set<Move> moves = new HashSet<>();
        moves.add(new Move(new Position(0,0), Mark.CROSS));
        moves.add(new Move(new Position(1,0), Mark.CROSS));
        moves.add(new Move(new Position(2,0), Mark.CROSS));
        moves.add(new Move(new Position(3,0), Mark.CROSS));
        moves.add(new Move(new Position(4,0), Mark.CROSS));
        assertThrows(TheWinnerIsException.class, () -> g.nextMove(moves, Mark.NOUGHT));
    }

    @Test
    public void testResignOnFullBoard() {
        Game g = new GomokuBuilder().size(3).firstMark(Mark.CROSS).build();
        Set<Move> moves = new HashSet<>();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                moves.add(new Move(new Position(c, r), Mark.CROSS));
            }
        }
        assertThrows(ResignException.class, () -> g.nextMove(moves, Mark.NOUGHT));
    }
}
