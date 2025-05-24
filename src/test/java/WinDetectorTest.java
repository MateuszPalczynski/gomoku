package fais.zti.oramus.gomoku;

import static org.junit.jupiter.api.Assertions.*;

import gomoku.BoardModel;
import gomoku.BoundedAdapter;
import gomoku.WinDetector;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class WinDetectorTest {
    @Test
    public void testNoWinner() {
        Mark[][] board = BoardModel.fromMoves(5, Set.of());
        WinDetector d = new WinDetector();
        assertFalse(d.hasWinner(board,new BoundedAdapter()));
    }

    @Test
    public void testHorizontalWin() {
        Set<Move> moves = Set.of(
                new Move(new Position(0,0),Mark.CROSS),
                new Move(new Position(1,0),Mark.CROSS),
                new Move(new Position(2,0),Mark.CROSS),
                new Move(new Position(3,0),Mark.CROSS),
                new Move(new Position(4,0),Mark.CROSS)
        );
        Mark[][] board = BoardModel.fromMoves(5,moves);
        WinDetector d = new WinDetector();
        assertTrue(d.hasWinner(board,new BoundedAdapter()));
        assertEquals(Mark.CROSS,d.getWinner());
    }
}