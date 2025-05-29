import static org.junit.jupiter.api.Assertions.*;

import fais.zti.oramus.gomoku.Mark;
import org.junit.jupiter.api.Test;

public class BoundaryAdapterTest {
    @Test
    public void testBoundedAdapter() {
        Mark[][] board = {{Mark.CROSS}};
        BoundaryAdapter a = new BoundedAdapter();
        assertEquals(Mark.CROSS, a.get(board,0,0));
        assertEquals(Mark.NULL, a.get(board,-1,0));
        assertEquals(1, a.countLine(board,0,0,0,1));
    }
/*
    @Test
    public void testPeriodicAdapter() {
        Mark[][] board = {
                {Mark.NOUGHT, Mark.CROSS},
                {Mark.CROSS, Mark.NULL}
        };
        BoundaryAdapter a = new PeriodicBoundaryAdapter();
        assertEquals(Mark.NOUGHT, a.get(board,0,2));
        // diagonal from (0,0) to (1,1) wrapping
        assertEquals(2, a.countLine(board,0,0,1,1));
    }
    */

}
