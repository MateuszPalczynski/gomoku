import fais.zti.oramus.gomoku.*;

import java.util.HashSet;
import java.util.Set;

public class ForcedDefenseStrategy extends AbstractStrategy {

    private final ThreatAssessor threatAssessor;

    public ForcedDefenseStrategy(BoundaryAdapter adapter, ThreatAssessor threatAssessor) {
        super(adapter);
        if (threatAssessor == null) {
            throw new IllegalArgumentException("ThreatDetector cannot be null");
        }
        this.threatAssessor = threatAssessor;
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) throws ResignException {
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        int size = board.length;

        int openThreeThreats = threatAssessor.countOpenThrees(board, opponent);
        int fourThreats = threatAssessor.countFours(board, opponent);
        int potentialThreeThreats = threatAssessor.countDoubleThreeThreats(board, opponent);

        if (potentialThreeThreats + openThreeThreats >= 2 || potentialThreeThreats + fourThreats >= 2) {
            throw new ResignException();
        }

        Set<Position> opponentWinningMoves = findOpponentWinningMoves(board, opponent);

        if (opponentWinningMoves.size() >= 2) {
            throw new ResignException();
        }

        Set<Position> ourCandidateMoves = new HashSet<>();
        if (opponentWinningMoves.size() == 1) {
            ourCandidateMoves.addAll(opponentWinningMoves);
        } else {
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (board[r][c] == Mark.NULL) {
                        ourCandidateMoves.add(new Position(c, r));
                    }
                }
            }
        }

        if (ourCandidateMoves.isEmpty()) {
            throw new ResignException();
        }

        boolean isGameLost = true;
        for (Position ourMove : ourCandidateMoves) {
            board[ourMove.row()][ourMove.col()] = me;
            boolean opponentCanForceWin = threatAssessor.hasForcedWin(board, opponent);
            board[ourMove.row()][ourMove.col()] = Mark.NULL; // Backtrack

            if (!opponentCanForceWin) {
                isGameLost = false;
                break;
            }
        }

        if (isGameLost) {
            throw new ResignException();
        }

        if (opponentWinningMoves.size() == 1) {
            return new Move(opponentWinningMoves.iterator().next(), me);
        } else {
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
                    board[r][c] = opponent;
                    if (oppWinDet.hasWinner(board)) {
                        winningMoves.add(new Position(c, r));
                    }
                    board[r][c] = Mark.NULL;
                }
            }
        }
        return winningMoves;
    }
}
