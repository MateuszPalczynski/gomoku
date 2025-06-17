import fais.zti.oramus.gomoku.Mark;

public class DoubleThreeThreatDetector extends AbstractThreatDetector {

    public DoubleThreeThreatDetector(BoundaryAdapter adapter, int boardSize) {
        super(adapter, boardSize);
    }

    public int count(Mark[][] board, Mark playerMark) {
        if (board == null || playerMark == Mark.NULL) return 0;

        int doubleThreeMoveCount = 0;
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {

                    board[r][c] = playerMark;

                    int openThreesCreated = 0;

                    for (int[] dir : DIRECTIONS) {
                        int dr = dir[0], dc = dir[1];
                        int pos = countDirection(board, playerMark, r, c, dr, dc);
                        int neg = countDirection(board, playerMark, r, c, -dr, -dc);

                        if (1 + pos + neg == 3) {
                            int frontR = r + (pos + 1) * dr;
                            int frontC = c + (pos + 1) * dc;
                            int backR = r - (neg + 1) * dr;
                            int backC = c - (neg + 1) * dc;

                            if (adapter.isOnBoard(board, frontR, frontC) && adapter.get(board, frontR, frontC) == Mark.NULL &&
                                    adapter.isOnBoard(board, backR, backC) && adapter.get(board, backR, backC) == Mark.NULL) {
                                openThreesCreated++;
                            }
                        }
                    }

                    board[r][c] = Mark.NULL; // Backtrack

                    if (openThreesCreated >= 2) {
                        doubleThreeMoveCount++;
                    }
                }
            }
        }
        return doubleThreeMoveCount;
    }
}