import static org.junit.jupiter.api.Assertions.*;

import fais.zti.oramus.gomoku.*; // Import all necessary classes
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class StrategyTest {
    private static final int SIZE = 10;
    private BoundaryAdapter boundedAdapter;
    private ThreatDetector threatDetector;

    // It's good practice to set up common objects here
    @BeforeEach
    public void setUp() {
        this.boundedAdapter = new BoundedAdapter();
        this.threatDetector = new ThreatDetector(boundedAdapter, SIZE);
    }

    @Test
    public void testBlock() throws Exception {
        Mark[][] board = BoardModel.fromMoves(SIZE, Set.of(
                new Move(new Position(0, 1), Mark.NOUGHT),
                new Move(new Position(1, 1), Mark.NOUGHT),
                new Move(new Position(2, 1), Mark.NOUGHT),
                new Move(new Position(3, 1), Mark.NOUGHT)
        ));

        // CORRECTED: Pass both the adapter and the threat detector
        MoveSelector selector = new MoveSelector(boundedAdapter, threatDetector);
        Move m = selector.decide(board, Mark.CROSS);

        assertEquals(new Position(4, 1), m.position());
    }

    @Test
    public void testResign() {
        Mark[][] board = new Mark[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = Mark.CROSS;
            }
        }

        // CORRECTED: Pass both the adapter and the threat detector
        MoveSelector selector = new MoveSelector(boundedAdapter, threatDetector);

        assertThrows(ResignException.class,
                () -> selector.decide(board, Mark.CROSS));
    }
}