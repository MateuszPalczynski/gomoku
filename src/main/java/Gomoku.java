import fais.zti.oramus.gomoku.*;
import java.util.Set;

public class Gomoku implements Game {
    private final int size;
    private final Mark firstMark; // Though not used in methods shown, good to keep if part of Game interface
    private final BoundaryAdapter adapter;
    private final BoardValidator validator;
    private final MoveSelector selector;
    private final ThreatDetector threatDetector; // Added ThreatDetector

    @Override
    public void firstMark(Mark first) { /* ... */ }
    @Override
    public void size(int size) { /* ... */ }
    @Override
    public void periodicBoundaryConditionsInUse() { /* ... */ }

    public Gomoku(int size, Mark firstMark, boolean periodic) {
        this.size = size;
        this.firstMark = firstMark;
        this.adapter = periodic
                ? new PeriodicBoundaryAdapter()
                : new BoundedAdapter();
        this.validator = new BoardValidator(adapter); // Assuming BoardValidator might need adapter
        this.selector = new MoveSelector(adapter);
        this.threatDetector = new ThreatDetector(adapter, size); // Initialize ThreatDetector
    }

    public Gomoku() {
        this(15, Mark.CROSS, false);
    }

    @Override
    public Move nextMove(Set<Move> boardState, Mark nextMoveMark)
            throws ResignException, TheWinnerIsException, WrongBoardStateException {

        // 0) Create current board representation
        // This should be done once at the beginning.
        // 'pre' was used for temporary modifications, 'currentBoardModel' is the actual state.
        Mark[][] currentBoardModel = BoardModel.fromMoves(size, boardState);

        // 1) Full board => resignation
        if (boardState.size() >= size * size) {
            throw new ResignException();
        }

        // 2) State validation (detects existing winner on the board)
        validator.validate(size, boardState); // This might throw TheWinnerIsException

        Mark opp = (nextMoveMark == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);

        // 3) Resignation Condition: Opponent has two or more open threes
        // This check is on the current board state 'currentBoardModel'.
        if (threatDetector.countOpenThrees(currentBoardModel, opp) >= 2) {
            throw new ResignException();
        }

        // 4) Resignation Condition: Opponent has multiple immediate winning moves (5-in-a-row)
        // This loop simulates the opponent placing one stone in each empty spot
        // and checks if that single move results in a win.
        WinDetector oppWinDetector = new WinDetector(adapter);
        int immediateWinThreats = 0;
        // Create a mutable copy for simulation if 'currentBoardModel' should remain pristine
        // For this loop, 'currentBoardModel' is modified and reverted.
        for (int r_idx = 0; r_idx < size; r_idx++) {
            for (int c_idx = 0; c_idx < size; c_idx++) {
                if (currentBoardModel[r_idx][c_idx] == Mark.NULL) {
                    currentBoardModel[r_idx][c_idx] = opp;
                    if (oppWinDetector.hasWinner(currentBoardModel)) {
                        immediateWinThreats++;
                    }
                    currentBoardModel[r_idx][c_idx] = Mark.NULL; // Revert simulation

                    // Optimization: if threats > 1, no need to check further
                    if (immediateWinThreats > 1) {
                        break;
                    }
                }
            }
            if (immediateWinThreats > 1) {
                break;
            }
        }
        if (immediateWinThreats > 1) {
            throw new ResignException();
        }

        // 5) Check for immediate win for current player (nextMoveMark)
        // This also simulates placing one stone.
        WinDetector ourWinDetector = new WinDetector(adapter);
        for (int r_idx = 0; r_idx < size; r_idx++) {
            for (int c_idx = 0; c_idx < size; c_idx++) {
                if (currentBoardModel[r_idx][c_idx] == Mark.NULL) {
                    currentBoardModel[r_idx][c_idx] = nextMoveMark;
                    if (ourWinDetector.hasWinner(currentBoardModel)) {
                        // We have an immediate winning move.
                        // Revert for safety, though we are returning.
                        currentBoardModel[r_idx][c_idx] = Mark.NULL;
                        return new Move(new Position(c_idx, r_idx), nextMoveMark);
                    }
                    currentBoardModel[r_idx][c_idx] = Mark.NULL; // Revert simulation
                }
            }
        }

        // 6) If no immediate win for us, and no resignation, proceed to AI move selection
        // The selector needs the pristine current board state.
        // Note: BoardModel.fromMoves needs to be called again if currentBoardModel was modified
        // and not perfectly reverted by all prior steps.
        // However, selector usually takes Mark[][], so currentBoardModel if pristine is fine.
        // Let's ensure currentBoardModel is the state *before* any simulation for selector.
        // The simulations above for win checks did revert.
        // For safety, or if selector logic might also mutate, it could take a fresh copy.
        // Mark[][] boardForSelector = BoardModel.fromMoves(size, boardState);
        return selector.decide(currentBoardModel, nextMoveMark);
    }
}