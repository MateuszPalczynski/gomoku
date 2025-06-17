import fais.zti.oramus.gomoku.*;

public class BlockDoubleThreatStrategy extends AbstractStrategy {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

    public BlockDoubleThreatStrategy(BoundaryAdapter adapter) {
        super(adapter);
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (adapter.get(board, r, c) == Mark.NULL) {
                    board[r][c] = opponent;
                    int createdThreats = countCreatedOpenThrees(board, opponent, r, c);
                    board[r][c] = Mark.NULL; // Backtrack

                    if (createdThreats >= 2) {
                        return new Move(new Position(c, r), me);
                    }
                }
            }
        }
        return null;
    }

    private int countCreatedOpenThrees(Mark[][] board, Mark player, int r, int c) {
        int threats = 0;
        for (int[] dir : DIRECTIONS) {
            int dr = dir[0], dc = dir[1];
            int positive = countDirection(board, player, r, c, dr, dc);
            int negative = countDirection(board, player, r, c, -dr, -dc);

            if (1 + positive + negative == 3) {
                int frontR = r + (positive + 1) * dr;
                int frontC = c + (positive + 1) * dc;
                int backR = r - (negative + 1) * dr;
                int backC = c - (negative + 1) * dc;

                if (adapter.isOnBoard(board, frontR, frontC) && adapter.get(board, frontR, frontC) == Mark.NULL &&
                        adapter.isOnBoard(board, backR, backC) && adapter.get(board, backR, backC) == Mark.NULL) {
                    threats++;
                }
            }
        }
        return threats;
    }
}