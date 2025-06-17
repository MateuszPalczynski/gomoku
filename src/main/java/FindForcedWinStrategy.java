import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;
import fais.zti.oramus.gomoku.ResignException;

public class FindForcedWinStrategy extends AbstractStrategy {

    public FindForcedWinStrategy(BoundaryAdapter adapter) {
        super(adapter);
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) throws ResignException {
        int n = board.length;
        Mark opponent = (me == Mark.CROSS) ? Mark.NOUGHT : Mark.CROSS;

        if (opponentHasWinningThreat(board, opponent)) {
            return null; // Let ForcedDefenseStrategy handle it.
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    board[r][c] = me;

                    if (countWinningSpots(board, me) >= 2) {
                        board[r][c] = Mark.NULL; // Backtrack
                        return new Move(new Position(c, r), me);
                    }

                    board[r][c] = Mark.NULL;
                }
            }
        }

        return null;
    }

    private int countWinningSpots(Mark[][] board, Mark player) {
        int winningSpotCount = 0;
        int n = board.length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    board[r][c] = player;
                    WinDetector winDetector = new WinDetector(adapter);
                    if (winDetector.hasWinner(board)) {
                        winningSpotCount++;
                    }
                    board[r][c] = Mark.NULL; // Backtrack
                }
            }
        }
        return winningSpotCount;
    }

    private boolean opponentHasWinningThreat(Mark[][] board, Mark opponent) {
        return countWinningSpots(board, opponent) > 0;
    }
}