import fais.zti.oramus.gomoku.*;

import java.util.Set;

public class Gomoku implements Game {
    private int size;
    private Mark firstMark;
    private boolean periodic;

    private BoundaryAdapter adapter;
    private BoardValidator validator;
    private MoveSelector selector;

    public Gomoku() {
        this.size = 10;
        this.firstMark = Mark.CROSS;
        this.periodic = false;
        reinit();
    }

    public Gomoku(int size, Mark firstMark, boolean periodic) {
        this.size = size;
        this.firstMark = firstMark;
        this.periodic = periodic;
        reinit();
    }

    @Override
    public void firstMark(Mark first) {
        if (first == null) {
            throw new IllegalArgumentException("First mark cannot be null");
        }
        this.firstMark = first;
        reinit();
    }

    @Override
    public void size(int size) {
        if (size < 10 || size > 15) {
            throw new IllegalArgumentException("Size must be between 10 and 15 (inclusive)");
        }
        this.size = size;
        reinit();
    }

    @Override
    public void periodicBoundaryConditionsInUse() {
        this.periodic = true;
        reinit();
    }

    private void reinit() {
        this.adapter = periodic
                ? new PeriodicBoundaryAdapter()
                : new BoundedAdapter();
        this.validator = new BoardValidator(adapter, firstMark);
        ThreatAssessor threatAssessor = new ThreatAssessor(adapter, size);
        this.selector = new MoveSelector(adapter, threatAssessor);

    }

    @Override
    public Move nextMove(Set<Move> boardState, Mark nextMoveMark)
            throws ResignException, TheWinnerIsException, WrongBoardStateException {
        // 0) Build current board
        Mark[][] board = BoardModel.fromMoves(size, boardState);

        // 1) Full board => WrongBoardStateException
        if (boardState.size() >= size * size) {
            throw new WrongBoardStateException();
        }

        // 2) Validate state
        validator.validate(size, boardState, nextMoveMark);

        // 3) Immediate win check for us
        WinDetector ourWinDet = new WinDetector(adapter);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == Mark.NULL) {
                    board[r][c] = nextMoveMark;
                    if (ourWinDet.hasWinner(board)) {
                        board[r][c] = Mark.NULL;
                        return new Move(new Position(c, r), nextMoveMark);
                    }
                    board[r][c] = Mark.NULL;
                }
            }
        }

        // 4) Delegate to strategy chain
        return selector.decide(board, nextMoveMark);
    }
}
