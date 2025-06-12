import fais.zti.oramus.gomoku.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A high-priority strategy that handles all forced defensive situations.
 * It performs three key checks:
 * 1. If the opponent has an immediate winning move, it finds the single move to block it.
 * 2. If the opponent has two or more immediate wins, it's unblockable, so it resigns.
 * 3. If there are no immediate wins, it performs a deeper analysis to see if every possible
 * move leads to a forced win for the opponent. If so, it resigns.
 *
 * This strategy either returns a single blocking move, throws a ResignException, or returns
 * null to pass control to the next strategy in the chain.
 */
public class ForcedDefenseStrategy extends AbstractStrategy {

    private final ThreatDetector threatDetector;

    public ForcedDefenseStrategy(BoundaryAdapter adapter, ThreatDetector threatDetector) {
        super(adapter);
        if (threatDetector == null) {
            throw new IllegalArgumentException("ThreatDetector cannot be null");
        }
        this.threatDetector = threatDetector;
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) throws ResignException {
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        int size = board.length;

        // === Step 1: Check for unblockable threat combinations (as in original) ===
        int openThreeThreats = threatDetector.countOpenThrees(board, opponent);
        int fourThreats = threatDetector.countFours(board, opponent);
        int potentialThreeThreats = threatDetector.countDoubleThreeThreats(board, opponent);

        if (potentialThreeThreats + openThreeThreats >= 2 || potentialThreeThreats + fourThreats >= 2) {
            throw new ResignException();
        }

        // === Step 2: Find opponent's immediate winning moves ===
        Set<Position> opponentWinningMoves = findOpponentWinningMoves(board, opponent);

        if (opponentWinningMoves.size() >= 2) {
            // Unblockable due to multiple immediate wins.
            throw new ResignException();
        }

        // === Step 3: Determine the EXACT set of candidate moves for our lookahead ===
        Set<Position> ourCandidateMoves = new HashSet<>();
        if (opponentWinningMoves.size() == 1) {
            // If opponent has one winning move, our ONLY candidate is to block it.
            ourCandidateMoves.addAll(opponentWinningMoves);
        } else {
            // If no immediate threat, we must consider all empty squares.
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (board[r][c] == Mark.NULL) {
                        ourCandidateMoves.add(new Position(c, r));
                    }
                }
            }
        }

        if (ourCandidateMoves.isEmpty()) {
            // No moves left to make.
            throw new ResignException();
        }

        // === Step 4: Perform the 1-ply lookahead on the correct candidate moves ===
        boolean isGameLost = true;
        for (Position ourMove : ourCandidateMoves) {
            // Simulate our candidate move
            board[ourMove.row()][ourMove.col()] = me;
            boolean opponentCanForceWin = threatDetector.hasForcedWin(board, opponent);
            board[ourMove.row()][ourMove.col()] = Mark.NULL; // Backtrack

            if (!opponentCanForceWin) {
                // We found a "savior move"! The game is NOT lost.
                isGameLost = false;
                break;
            }
        }

        if (isGameLost) {
            // After checking all our essential moves, every single one leads to a forced win for the opponent.
            throw new ResignException();
        }

        // === Step 5: If the game is NOT lost, then decide on the action ===
        // At this point, we know the game is playable.

        if (opponentWinningMoves.size() == 1) {
            // We survived the lookahead, which means blocking this single threat is a valid, non-losing move.
            // This is our only logical move.
            return new Move(opponentWinningMoves.iterator().next(), me);
        } else {
            // The game is not lost, and there's no single forced move to make.
            // Pass control to the next (likely offensive) strategy in the chain.
            return null;
        }
    }

    private Set<Position> findOpponentWinningMoves(Mark[][] board, Mark opponent) {
        Set<Position> winningMoves = new HashSet<>();
        int size = board.length;
        WinDetector oppWinDet = new WinDetector(adapter);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == Mark.NULL) {
                    board[r][c] = opponent; // Simulate opponent's move
                    if (oppWinDet.hasWinner(board)) {
                        winningMoves.add(new Position(c, r));
                    }
                    board[r][c] = Mark.NULL; // Backtrack
                }
            }
        }
        return winningMoves;
    }
}
