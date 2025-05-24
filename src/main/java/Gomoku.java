import fais.zti.oramus.gomoku.*;
import gomoku.*;

import java.util.Set;

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
        // stub: nieużywana w tej implementacji
    }

    @Override
    public void size(int size) {
        // stub: nieużywana w tej implementacji
    }

    @Override
    public void periodicBoundaryConditionsInUse() {
        // stub: nieużywana w tej implementacji
    }

    /**
     * Konstruktor pakietowy używany przez GomokuBuilder.
     */
    Gomoku(int size, Mark firstMark, boolean periodic) {
        this.size = size;
        this.firstMark = firstMark;
        this.adapter = periodic ? new PeriodicBoundaryAdapter() : new BoundedAdapter();
        this.validator = new BoardValidator(adapter);
        this.selector = new MoveSelector();
    }

    /**
     * Publiczny konstruktor bezparametrowy z domyślną konfiguracją.
     */
    public Gomoku() {
        this(15, Mark.CROSS, false);
    }

    @Override
    public Move nextMove(Set<Move> boardState, Mark nextMoveMark)
            throws ResignException, TheWinnerIsException, WrongBoardStateException {
        // pełna plansza => rezygnacja
        if (boardState.size() >= size * size) {
            throw new ResignException();
        }
        validator.validate(size, boardState);
        Mark[][] board = BoardModel.fromMoves(size, boardState);
        return selector.decide(board, nextMoveMark);
    }
}
