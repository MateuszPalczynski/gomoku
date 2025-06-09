import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;

/**
 * Strategy to block the opponent.
 * It first attempts to block an immediate win (5-in-a-row) for the opponent.
 * If no such threat exists, it attempts to block the opponent from creating an "open four".
 */
public class BlockStrategy extends AbstractStrategy {

    // Directions to check: Horizontal, Vertical, Diagonal Down-Right, Diagonal Up-Right
    // These are primary directions; the open four check will explore lines along these.
    private static final int[][] DIRECTIONS = {{0,1},{1,0},{1,1},{1,-1}};

    public BlockStrategy() { super(); }
    public BlockStrategy(BoundaryAdapter adapter) {
        super(adapter);
        if (adapter == null) throw new IllegalArgumentException("BoundaryAdapter cannot be null");
    }

    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        int n = board.length;

        // 1) Block immediate win (unchanged)
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (adapter.get(board, r, c) == Mark.NULL) {
                    board[r][c] = opponent;
                    WinDetector winDet = new WinDetector(this.adapter);
                    if (winDet.hasWinner(board)) {
                        board[r][c] = Mark.NULL;
                        return new Move(new Position(c, r), me);
                    }
                    board[r][c] = Mark.NULL;
                }
            }
        }

        // 2) Block open-four creation
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (adapter.get(board, r, c) == Mark.NULL) {
                    board[r][c] = opponent;
                    int[][] dirs = DIRECTIONS;
                    boolean createdOpen4 = false;
                    for (int[] dir : dirs) {
                        int dr = dir[0], dc = dir[1];
                        int forward = adapter.countLine(board, r, c, dr, dc);
                        int backward = adapter.countLine(board, r, c, -dr, -dc);
                        int total = forward + backward - 1;
                        if (total == 4) {
                            int rF = r + dr * forward;
                            int cF = c + dc * forward;
                            int rB = r - dr * backward;
                            int cB = c - dc * backward;
                            // on-board checks
                            boolean fIn = rF >=0 && rF < n && cF >=0 && cF < n;
                            boolean bIn = rB >=0 && rB < n && cB >=0 && cB < n;
                            if (fIn && bIn
                                    && adapter.get(board, rF, cF) == Mark.NULL
                                    && adapter.get(board, rB, cB) == Mark.NULL) {
                                createdOpen4 = true;
                                break;
                            }
                        }
                    }
                    board[r][c] = Mark.NULL;
                    if (createdOpen4) {
                        return new Move(new Position(c, r), me);
                    }
                }
            }
        }
        return null;
    }
}