import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.TheWinnerIsException;
import fais.zti.oramus.gomoku.WrongBoardStateException;

import java.util.Set;

/** Kompozyt walidatorów planszy. */
public class BoardValidator {
    private final BoundaryAdapter adapter;
    private final Mark firstMark;

    /**
     * @param adapter   boundary adapter (bounded or periodic)
     * @param firstMark which mark moved first (CROSS or NOUGHT)
     */
    public BoardValidator(BoundaryAdapter adapter, Mark firstMark) {
        this.adapter   = adapter;
        this.firstMark = firstMark;
    }

    /**
     * Validate that the current board state is legal:
     * - No existing five-in-a-row (throws TheWinnerIsException)
     * - The turn (nextMoveMark) matches the counts & starter
     * - Move-count difference never exceeds 1
     *
     * @param size           board dimension
     * @param boardState     set of all moves so far
     * @param nextMoveMark   mark of the player about to move
     */
    public void validate(int size,
                         Set<Move> boardState,
                         Mark nextMoveMark)
            throws TheWinnerIsException, WrongBoardStateException {

        // 1) Rebuild the grid and check for an existing winner
        Mark[][] board = BoardModel.fromMoves(size, boardState);
        WinDetector detector = new WinDetector(adapter);
        if (detector.hasWinner(board)) {
            throw new TheWinnerIsException(detector.getWinner());
        }

        // 2) Count pieces
        long crossCount  = boardState.stream()
                .filter(m -> m.mark() == Mark.CROSS).count();
        long noughtCount = boardState.stream()
                .filter(m -> m.mark() == Mark.NOUGHT).count();

        // 3) Check counts relative to firstMark
        //    Starter may be one ahead, but never more
        if (firstMark == Mark.CROSS) {
            if (!(crossCount == noughtCount || crossCount == noughtCount + 1)) {
                throw new WrongBoardStateException();
            }
        } else {
            if (!(noughtCount == crossCount || noughtCount == crossCount + 1)) {
                throw new WrongBoardStateException();
            }
        }

        // 4) Ensure the declared nextMoveMark matches whose turn it is
        //    If crossCount==noughtCount then it's firstMark's turn
        boolean crossesTurn = (crossCount == noughtCount);
        //    If NOUGHT started, they actually move on even counts
        if (firstMark == Mark.NOUGHT) {
            crossesTurn = !crossesTurn;
        }
        if ( (nextMoveMark == Mark.CROSS) != crossesTurn ) {
            throw new WrongBoardStateException();
        }
    }
}
