import fais.zti.oramus.gomoku.*;

/**
 * Fasada silnika Gomoku.
 */
public class Gomoku implements Game {
    private final int size;
    private final Mark firstMark;
    private final BoundaryAdapter adapter;
    private final BoardValidator validator;
    private final MoveSelector selector;

    @Override
    public void firstMark(Mark first) {

    }

    @Override
    public void size(int size) {

    }

    @Override
    public void periodicBoundaryConditionsInUse() {

    }

    /**
     * Konstruktor pakietowy używany przez GomokuBuilder.
     */
    Gomoku(int size, Mark firstMark, boolean periodic) {
        this.size = size;
        this.firstMark = firstMark;
        this.adapter = periodic
                ? new PeriodicBoundaryAdapter()
                : new BoundedAdapter();
        this.validator = new BoardValidator(adapter);
        this.selector = new MoveSelector();
    }

    @Override
    public Move nextMove(java.util.Set<Move> boardState, Mark nextMoveMark)
            throws ResignException, TheWinnerIsException, WrongBoardStateException {
        // 1) pełna plansza => rezygnacja
        if (boardState.size() >= size * size) {
            throw new ResignException();
        }

        // 2) walidacja stanu (wykrywa istniejącego zwycięzcę)
        validator.validate(size, boardState);

        // 3) wielokrotne zagrożenia przeciwnika => rezygnacja
        Mark[][] pre = BoardModel.fromMoves(size, boardState);
        Mark opp = (nextMoveMark == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        WinDetector det = new WinDetector();
        int threats = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (pre[r][c] == Mark.NULL) {
                    pre[r][c] = opp;
                    if (det.hasWinner(pre, adapter) && ++threats > 1) {
                        throw new ResignException();
                    }
                    pre[r][c] = Mark.NULL;
                }
            }
        }

        // 4) budowa tablicy i wybór ruchu
        Mark[][] board = BoardModel.fromMoves(size, boardState);
        return selector.decide(board, nextMoveMark);
    }
}
