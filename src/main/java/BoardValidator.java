import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.TheWinnerIsException;
import fais.zti.oramus.gomoku.WrongBoardStateException;

import java.util.Set;

public class BoardValidator {
    private final BoundaryAdapter adapter;
    private final Mark firstMark;

    public BoardValidator(BoundaryAdapter adapter, Mark firstMark) {
        this.adapter   = adapter;
        this.firstMark = firstMark;
    }

    public void validate(int size,
                         Set<Move> boardState,
                         Mark nextMoveMark)
            throws TheWinnerIsException, WrongBoardStateException {

        Mark[][] board = BoardModel.fromMoves(size, boardState);
        WinDetector detector = new WinDetector(adapter);
        if (detector.hasWinner(board)) {
            throw new TheWinnerIsException(detector.getWinner());
        }

        long crossCount  = boardState.stream()
                .filter(m -> m.mark() == Mark.CROSS).count();
        long noughtCount = boardState.stream()
                .filter(m -> m.mark() == Mark.NOUGHT).count();

        if (firstMark == Mark.CROSS) {
            if (!(crossCount == noughtCount || crossCount == noughtCount + 1)) {
                throw new WrongBoardStateException();
            }
        } else {
            if (!(noughtCount == crossCount || noughtCount == crossCount + 1)) {
                throw new WrongBoardStateException();
            }
        }

        boolean crossesTurn = (crossCount == noughtCount);
        if (firstMark == Mark.NOUGHT) {
            crossesTurn = !crossesTurn;
        }
        if ( (nextMoveMark == Mark.CROSS) != crossesTurn ) {
            throw new WrongBoardStateException();
        }
    }
}
