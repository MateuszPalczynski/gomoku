import fais.zti.oramus.gomoku.*;
import java.util.Set;

public class Gomoku implements Game {
    private final int size;
    private final Mark firstMark;
    private final BoundaryAdapter adapter;
    private final BoardValidator validator;
    private final MoveSelector selector;

    @Override
    public void firstMark(Mark first) {
        // Implementation not needed since firstMark is set in constructor
    }

    @Override
    public void size(int size) {
        // Implementation not needed since size is set in constructor
    }

    @Override
    public void periodicBoundaryConditionsInUse() {
        // Implementation not needed since boundary type is set in constructor
    }

    Gomoku(int size, Mark firstMark, boolean periodic) {
        this.size = size;
        this.firstMark = firstMark;
        this.adapter = periodic
                ? new PeriodicBoundaryAdapter()
                : new BoundedAdapter();
        this.validator = new BoardValidator(adapter);
        this.selector = new MoveSelector(adapter);  // Pass adapter to selector
    }

    @Override
    public Move nextMove(Set<Move> boardState, Mark nextMoveMark)
            throws ResignException, TheWinnerIsException, WrongBoardStateException {
        // 1) full board => resignation
        if (boardState.size() >= size * size) {
            throw new ResignException();
        }

        // 2) state validation (detects existing winner)
        validator.validate(size, boardState);

        Mark[][] pre = BoardModel.fromMoves(size, boardState);

        // 3) Check for immediate win for current player
        WinDetector ourWinDetector = new WinDetector(adapter);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (pre[r][c] == Mark.NULL) {
                    pre[r][c] = nextMoveMark;
                    if (ourWinDetector.hasWinner(pre)) {
                        // We have a winning move - skip threat detection
                        return new Move(new Position(c, r), nextMoveMark);
                    }
                    pre[r][c] = Mark.NULL;
                }
            }
        }

        // 4) multiple threats from the opponent => resignation
        Mark opp = (nextMoveMark == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        WinDetector det = new WinDetector(adapter);
        int threats = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (pre[r][c] == Mark.NULL) {
                    pre[r][c] = opp;
                    if (det.hasWinner(pre)) {
                        threats++;
                        if (threats > 1) {
                            throw new ResignException();
                        }
                    }
                    pre[r][c] = Mark.NULL;
                }
            }
        }

        // 5) board construction and move selection
        Mark[][] board = BoardModel.fromMoves(size, boardState);
        return selector.decide(board, nextMoveMark);
    }
}