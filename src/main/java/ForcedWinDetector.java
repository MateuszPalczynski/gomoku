import fais.zti.oramus.gomoku.Mark;

public class ForcedWinDetector extends AbstractThreatDetector {

    public ForcedWinDetector(BoundaryAdapter adapter, int boardSize) {
        super(adapter, boardSize);
    }

    public boolean hasForcedWin(Mark[][] board, Mark playerMark) {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (board[r][c] == Mark.NULL) {
                    board[r][c] = playerMark;

                    WinDetector winDetector = new WinDetector(adapter);
                    if (winDetector.hasWinner(board)) {
                        board[r][c] = Mark.NULL; // Backtrack
                        return true;
                    }

                    if (createsOpenFour(board, playerMark, r, c)) {
                        board[r][c] = Mark.NULL; // Backtrack
                        return true;
                    }

                    board[r][c] = Mark.NULL;
                }
            }
        }
        return false;
    }

    private boolean createsOpenFour(Mark[][] board, Mark player, int r, int c) {
        for (int[] dir : DIRECTIONS) {
            int dr = dir[0], dc = dir[1];
            for (int i = 1; i <= 4; i++) {
                int startR = r - i * dr;
                int startC = c - i * dc;

                Mark before = adapter.get(board, startR, startC);
                Mark after = adapter.get(board, startR + 5 * dr, startC + 5 * dc);

                if (before == Mark.NULL && after == Mark.NULL) {
                    boolean solidFour = true;
                    for (int j = 1; j <= 4; j++) {
                        if (adapter.get(board, startR + j * dr, startC + j * dc) != player) {
                            solidFour = false;
                            break;
                        }
                    }
                    if (solidFour) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
