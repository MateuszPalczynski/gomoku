import fais.zti.oramus.gomoku.*;
import java.util.Set;

public class Gomoku implements Game {
    private int size;
    private Mark firstMark;
    private boolean periodic;

    private BoundaryAdapter adapter;
    private BoardValidator validator;
    private MoveSelector selector;
    private ThreatDetector threatDetector;

    /**
     * Public no-arg constructor: uses defaults (15×15, CROSS, bounded).
     */
    public Gomoku() {
        this.size = 10;
        this.firstMark = Mark.CROSS;
        this.periodic = false;
        reinit();
    }

    /**
     * Internal constructor used by GomokuBuilder.
     */
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

    /**
     * Reinitialize boundary adapter, validator, selector, and threat detector whenever config changes.
     */
    private void reinit() {
        this.adapter = periodic
                ? new PeriodicBoundaryAdapter()
                : new BoundedAdapter();
        this.validator = new BoardValidator(adapter, firstMark);
        this.selector  = new MoveSelector(adapter);
        this.threatDetector = new ThreatDetector(adapter, size);
    }

    @Override
    public Move nextMove(Set<Move> boardState, Mark nextMoveMark)
            throws ResignException, TheWinnerIsException, WrongBoardStateException {
        // 0) Build current board
        Mark[][] board = BoardModel.fromMoves(size, boardState);

        // 1) Full board => Resign
        if (boardState.size() >= size * size) {
            throw new ResignException();
        }

        // 2) Validate state (existing winner or wrong turn)
        validator.validate(size, boardState, nextMoveMark);

        // 3) IMMEDIATE WIN CHECK FOR US (MOVED UP)
        // This is the highest priority. If we can win, we do it.
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

        Mark opponent = (nextMoveMark == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);

        // 4) FINAL CORRECTED RESIGN LOGIC
        int openThreeThreats = threatDetector.countOpenThrees(board, opponent);
        //int fourThreats = threatDetector.countFours(board, opponent);
        //int potentialThreeThreats = threatDetector.countDoubleThreeThreats(board, opponent);
        if (openThreeThreats>= 2) {
            throw new ResignException();
        }

        // 5) Opponent multiple immediate wins => resign (another resign condition)
        WinDetector oppWinDet = new WinDetector(adapter);
        int oppWins = 0;
        for (int r = 0; r < size && oppWins <= 1; r++) {
            for (int c = 0; c < size && oppWins <= 1; c++) {
                if (board[r][c] == Mark.NULL) {
                    board[r][c] = opponent;
                    if (oppWinDet.hasWinner(board)) {
                        oppWins++;
                    }
                    board[r][c] = Mark.NULL;
                }
            }
        }
        if (oppWins > 1) {
            throw new ResignException();
        }

        // 6) Delegate to strategy chain
        return selector.decide(board, nextMoveMark);
    }
}
