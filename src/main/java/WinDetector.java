import fais.zti.oramus.gomoku.Mark;

/** Wykrywa, czy na planszy jest zwycięzca. */
public class WinDetector {
    private Mark winner = Mark.NULL;
    private final BoundaryAdapter adapter;

    public WinDetector(BoundaryAdapter adapter) {
        this.adapter = adapter;
    }

    public boolean hasWinner(Mark[][] board) {
        // Reset winner state before each detection
        winner = Mark.NULL;

        int n = board.length;
        int[][] dirs = {{0,1},{1,0},{1,1},{1,-1}};
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                Mark m = adapter.get(board, r, c);
                if (m == Mark.NULL) continue;
                for (int[] d : dirs) {
                    if (adapter.countLine(board, r, c, d[0], d[1]) >= 5) {
                        winner = m;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Mark getWinner() {
        return winner;
    }
}