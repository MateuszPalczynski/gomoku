import static org.junit.jupiter.api.Assertions.*;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class WinDetectorTest {
    private final BoundaryAdapter boundedAdapter = new BoundedAdapter();

    @Test
    public void testNoWinner() {
        Mark[][] board = BoardModel.fromMoves(5, Set.of());
        WinDetector d = new WinDetector(boundedAdapter);  // Pass adapter to constructor
        assertFalse(d.hasWinner(board));  // Remove adapter parameter
    }

    @Test
    public void testHorizontalWin() {
        Set<Move> moves = Set.of(
                new Move(new Position(0,0), Mark.CROSS),
                new Move(new Position(1,0), Mark.CROSS),
                new Move(new Position(2,0), Mark.CROSS),
                new Move(new Position(3,0), Mark.CROSS),
                new Move(new Position(4,0), Mark.CROSS)
        );
        Mark[][] board = BoardModel.fromMoves(5, moves);
        WinDetector d = new WinDetector(boundedAdapter);  // Pass adapter to constructor
        assertTrue(d.hasWinner(board));  // Remove adapter parameter
        assertEquals(Mark.CROSS, d.getWinner());
    }
}