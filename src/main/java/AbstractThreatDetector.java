import fais.zti.oramus.gomoku.Mark;

public abstract class AbstractThreatDetector {
    protected final BoundaryAdapter adapter;
    protected final int boardSize;

    protected static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {1, 1}, {1, -1}
    };

    public AbstractThreatDetector(BoundaryAdapter adapter, int boardSize) {
        this.adapter = adapter;
        this.boardSize = boardSize;
    }

    protected int countDirection(Mark[][] board, Mark me, int r, int c, int dr, int dc) {
        int count = 0;
        int rr = r + dr;
        int cc = c + dc;


        for (int i = 0; i < this.boardSize; i++) {
            if (adapter.get(board, rr, cc) == me) {
                count++;
                rr += dr;
                cc += dc;
            } else {
                break;
            }
        }
        return count;
    }
}
