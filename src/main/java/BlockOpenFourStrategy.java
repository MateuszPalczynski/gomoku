import fais.zti.oramus.gomoku.*;

/**
 * Strategy to block the opponent from creating an "open four" threat.
 */
public class BlockOpenFourStrategy extends AbstractStrategy {

    private static final int[][] DIRECTIONS = {{0,1},{1,0},{1,1},{1,-1}};

    public BlockOpenFourStrategy(BoundaryAdapter adapter) {
        super(adapter);
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (adapter.get(board, r, c) == Mark.NULL) {
                    // If opponent plays here, do they create an open four?
                    board[r][c] = opponent;
                    boolean createdOpen4 = false;
                    for (int[] dir : DIRECTIONS) {
                        int dr = dir[0], dc = dir[1];
                        int positive = countDirection(board, opponent, r, c, dr, dc);
                        int negative = countDirection(board, opponent, r, c, -dr, -dc);
                        int total = 1 + positive + negative;

                        if (total == 4) {
                            int rF = r + (positive + 1) * dr;
                            int cF = c + (positive + 1) * dc;
                            int rB = r - (negative + 1) * dr;
                            int cB = c - (negative + 1) * dc;
                            if (adapter.get(board, rF, cF) == Mark.NULL &&
                                    adapter.get(board, rB, cB) == Mark.NULL) {
                                createdOpen4 = true;
                                break;
                            }
                        }
                    }
                    board[r][c] = Mark.NULL; // backtrack
                    if (createdOpen4) {
                        return new Move(new Position(c, r), me);
                    }
                }
            }
        }
        return null;
    }
}