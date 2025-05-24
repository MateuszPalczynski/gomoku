import static org.junit.jupiter.api.Assertions.*;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;
import fais.zti.oramus.gomoku.ResignException;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class StrategyTest {
    private static final int SIZE = 5;

    @Test
    public void testImmediateWin() throws Exception {
        Mark[][] board = BoardModel.fromMoves(SIZE, Set.of(
                new Move(new Position(0,0),Mark.CROSS),
                new Move(new Position(1,0),Mark.CROSS),
                new Move(new Position(2,0),Mark.CROSS),
                new Move(new Position(3,0),Mark.CROSS)
        ));
        Move m = new MoveSelector().decide(board, Mark.CROSS);
        assertEquals(new Position(4,0), m.position());
    }

    @Test
    public void testBlock() throws Exception {
        Mark[][] board = BoardModel.fromMoves(SIZE, Set.of(
                new Move(new Position(0,1),Mark.NOUGHT),
                new Move(new Position(1,1),Mark.NOUGHT),
                new Move(new Position(2,1),Mark.NOUGHT),
                new Move(new Position(3,1),Mark.NOUGHT)
        ));
        Move m = new MoveSelector().decide(board, Mark.CROSS);
        assertEquals(new Position(4,1), m.position());
    }

    @Test
    public void testResign() {
        Mark[][] board = new Mark[SIZE][SIZE];
        for(int r=0;r<SIZE;r++) for(int c=0;c<SIZE;c++) board[r][c]=Mark.CROSS;
        assertThrows(ResignException.class, () -> new MoveSelector().decide(board, Mark.CROSS));
    }
}