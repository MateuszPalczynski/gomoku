import fais.zti.oramus.gomoku.*;

import java.util.HashSet;
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
        int fourThreats = threatDetector.countFours(board, opponent);
        int potentialThreeThreats = threatDetector.countDoubleThreeThreats(board, opponent);

        /*
        if (fourThreats >= 2 || openThreeThreats >= 2) {
            throw new ResignException();
        }
        */
        if (potentialThreeThreats +  openThreeThreats >= 2) {
            throw new ResignException();
        }
        if (potentialThreeThreats +  fourThreats >= 2) {
            throw new ResignException();
        }

        Set<Position> opponentWinningMoves = new HashSet<>();
        WinDetector oppWinDet = new WinDetector(adapter);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == Mark.NULL) {
                    board[r][c] = opponent;
                    if (oppWinDet.hasWinner(board)) {
                        opponentWinningMoves.add(new Position(c, r));
                    }
                    board[r][c] = Mark.NULL; // Backtrack
                }
            }
        }

        // If opponent has 2+ immediate wins, it's unblockable. Resign.
        if (opponentWinningMoves.size() >= 2) {
            throw new ResignException();
        }

// Define our candidate moves. What are the moves we absolutely MUST consider?
        Set<Position> ourCandidateMoves = new HashSet<>();
        if (opponentWinningMoves.size() == 1) {
            // If opponent has exactly one winning move, our only rational move is to block it.
            ourCandidateMoves.addAll(opponentWinningMoves);
        } else {
            // If no immediate threat, we must consider all possible moves.
            // (For performance, this could be pruned to moves near existing stones, but for correctness, check all).
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (board[r][c] == Mark.NULL) {
                        ourCandidateMoves.add(new Position(c, r));
                    }
                }
            }
        }

// If there are no moves left to make (unlikely but possible), resign.
        if (ourCandidateMoves.isEmpty()){
            throw new ResignException();
        }

        boolean isGameLost = true;
// Now, for every candidate move we can make...
        for (Position ourMove : ourCandidateMoves) {
            // Simulate our move
            board[ourMove.row()][ourMove.col()] = nextMoveMark;

            // ...check if the opponent STILL has a forced win.
            boolean opponentCanForceWin = threatDetector.hasForcedWin(board, opponent);

            // Backtrack our move
            board[ourMove.row()][ourMove.col()] = Mark.NULL;

            if (!opponentCanForceWin) {
                // We found a "savior move"! If we play here, the opponent does not have a forced win.
                // Therefore, the game is NOT lost. We can break and proceed to the strategy chain.
                isGameLost = false;
                break;
            }
        }

        // If after checking all our essential moves, we found that every single one
        // leads to a forced win for the opponent, the game is truly lost.
        if (isGameLost) {
            throw new ResignException();
        }

        // 6) Delegate to strategy chain
        return selector.decide(board, nextMoveMark);
    }
}
