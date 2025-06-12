import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;
import fais.zti.oramus.gomoku.ResignException;

/**
 * A powerful offensive strategy that replaces the simple OpenFourStrategy.
 * It searches for any move that creates a "forced win" scenario, defined as
 * creating two or more immediate winning threats (i.e., spots that would
 * create a 5-in-a-row) simultaneously.
 *
 * It also includes a crucial safety check to ensure it doesn't foolishly
 * attack when the opponent is one move away from winning.
 */
public class FindForcedWinStrategy extends AbstractStrategy {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

    public FindForcedWinStrategy(BoundaryAdapter adapter) {
        super(adapter);
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) throws ResignException {
        int n = board.length;
        Mark opponent = (me == Mark.CROSS) ? Mark.NOUGHT : Mark.CROSS;

        // Safety Check (The "Advantage" Clause): If the opponent can win now, don't attack.
        if (opponentHasWinningThreat(board, opponent)) {
            return null; // Let ForcedDefenseStrategy handle it.
        }

        // Search for a move that creates a forced win for us.
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    // Simulate our move
                    board[r][c] = me;

                    // After our move, do we have 2+ winning spots?
                    if (countWinningSpots(board, me) >= 2) {
                        board[r][c] = Mark.NULL; // Backtrack
                        return new Move(new Position(c, r), me);
                    }

                    // Backtrack
                    board[r][c] = Mark.NULL;
                }
            }
        }

        return null;
    }

    /**
     * Counts how many empty squares on the board, if filled by the player,
     * would result in a 5-in-a-row win.
     */
    private int countWinningSpots(Mark[][] board, Mark player) {
        int winningSpotCount = 0;
        int n = board.length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    // Check if placing a stone here wins the game
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

    /**
     * Safety check: Scans if the opponent has a line of 4 that they can
     * complete to win on their next turn.
     */
    private boolean opponentHasWinningThreat(Mark[][] board, Mark opponent) {
        return countWinningSpots(board, opponent) > 0;
    }
}