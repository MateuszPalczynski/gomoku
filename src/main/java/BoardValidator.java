import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.TheWinnerIsException;
import fais.zti.oramus.gomoku.WrongBoardStateException;

import java.util.Set;

/** Kompozyt walidatorów planszy. */
public class BoardValidator {
    private final BoundaryAdapter adapter;

    public BoardValidator(BoundaryAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Waliduje stan planszy.
     * @throws TheWinnerIsException gdy pięć w rzędzie już istnieje
     * @throws WrongBoardStateException gdy niepoprawny stan (różnica ruchów >1)
     */
    public void validate(int size, Set<Move> boardState)
            throws TheWinnerIsException, WrongBoardStateException {
        Mark[][] board = BoardModel.fromMoves(size, boardState);
        WinDetector detector = new WinDetector(adapter);

        if (detector.hasWinner(board)) {
            throw new TheWinnerIsException(detector.getWinner());
        }

        // Fixed: use boardState instead of undefined 'moves'
        // Fixed: use mark() method of Move objects
        long crossCount = boardState.stream()
                .filter(m -> m.mark() == Mark.CROSS)
                .count();
        long noughtCount = boardState.stream()
                .filter(m -> m.mark() == Mark.NOUGHT)
                .count();

        if (Math.abs(crossCount - noughtCount) > 1) {
            throw new WrongBoardStateException();
        }
    }
}