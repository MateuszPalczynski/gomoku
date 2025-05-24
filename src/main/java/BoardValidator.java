import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.TheWinnerIsException;
import fais.zti.oramus.gomoku.WrongBoardStateException;

import java.util.Set;

/** Kompozyt walidatorów planszy. */
public class BoardValidator {
    private final BoundaryAdapter adapter;
    private final WinDetector winDetector = new WinDetector();

    public BoardValidator(BoundaryAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Waliduje stan planszy.
     * @throws TheWinnerIsException gdy pięć w rzędzie już istnieje
     * @throws WrongBoardStateException gdy niepoprawny stan (różnica ruchów >1)
     */
    public void validate(int size, Set<Move> moves)
            throws TheWinnerIsException, WrongBoardStateException {
        Mark[][] board = BoardModel.fromMoves(size, moves);
        if (winDetector.hasWinner(board, adapter)) {
            throw new TheWinnerIsException(winDetector.getWinner());
        }
        long crossCount = moves.stream().filter(m -> m.mark() == Mark.CROSS).count();
        long noughtCount = moves.stream().filter(m -> m.mark() == Mark.NOUGHT).count();
        if (Math.abs(crossCount - noughtCount) > 1) {
            throw new WrongBoardStateException();
        }
    }
}