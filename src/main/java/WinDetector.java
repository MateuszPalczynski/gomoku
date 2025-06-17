import fais.zti.oramus.gomoku.Mark;

public class WinDetector {
    private Mark winner = Mark.NULL;
    private final BoundaryAdapter adapter;

    private static final int[][] DIRECTIONS = {
            {0, 1},
            {1, 0},
            {1, 1},
            {1, -1}
    };

    public WinDetector(BoundaryAdapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("BoundaryAdapter cannot be null.");
        }
        this.adapter = adapter;
    }

    public boolean hasWinner(Mark[][] board) {
        this.winner = Mark.NULL;
        if (board == null || board.length == 0) {
            return false;
        }
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                Mark currentMark = adapter.get(board, r, c);
                if (currentMark == Mark.NULL) {
                    continue;
                }

                for (int[] dir : DIRECTIONS) {
                    int countForward = adapter.countLine(board, r, c, dir[0], dir[1]);
                    int countBackward = adapter.countLine(board, r, c, -dir[0], -dir[1]);
                    int totalLength = countForward + countBackward - 1;

                    if (totalLength >= 5) {
                        this.winner = currentMark;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Mark getWinner() {
        return this.winner;
    }
}