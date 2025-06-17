import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Position;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralThreatCounter extends AbstractThreatDetector {

    public GeneralThreatCounter(BoundaryAdapter adapter, int boardSize) {
        super(adapter, boardSize);
    }

    public int countThreats(Mark[][] board, Mark playerMark, int targetLength, boolean checkOpenEnds) {
        if (board == null || playerMark == Mark.NULL) return 0;

        Set<String> foundThreatKeys = new HashSet<>();
        int n = board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == Mark.NULL) {
                    addCreatedThreatsToSet(foundThreatKeys, board, playerMark, r, c, targetLength, checkOpenEnds);
                }
            }
        }
        return foundThreatKeys.size();
    }

    private void addCreatedThreatsToSet(Set<String> keys, Mark[][] board, Mark playerMark, int r, int c, int targetLength, boolean checkOpenEnds) {
        board[r][c] = playerMark;

        for (int[] dir : DIRECTIONS) {
            int dr = dir[0], dc = dir[1];
            int pos = countDirection(board, playerMark, r, c, dr, dc);
            int neg = countDirection(board, playerMark, r, c, -dr, -dc);

            if (1 + pos + neg == targetLength) {
                boolean endsAreValid = !checkOpenEnds;

                if (checkOpenEnds) {
                    int frontR = r + (pos + 1) * dr;
                    int frontC = c + (pos + 1) * dc;
                    int backR = r - (neg + 1) * dr;
                    int backC = c - (neg + 1) * dc;
                    if (adapter.isOnBoard(board, frontR, frontC) && adapter.get(board, frontR, frontC) == Mark.NULL &&
                            adapter.isOnBoard(board, backR, backC) && adapter.get(board, backR, backC) == Mark.NULL) {
                        endsAreValid = true;
                    }
                }

                if (endsAreValid) {
                    List<Position> originalStones = new ArrayList<>();
                    for (int i = 1; i <= neg; i++) {
                        originalStones.add(new Position(c - i * dc, r - i * dr));
                    }
                    for (int i = 1; i <= pos; i++) {
                        originalStones.add(new Position(c + i * dc, r + i * dr));
                    }
                    keys.add(canonicalize(originalStones));
                }
            }
        }
        board[r][c] = Mark.NULL;
    }

    private String canonicalize(List<Position> positions) {
        positions.sort(Comparator.comparingInt(Position::row).thenComparingInt(Position::col));
        return positions.stream()
                .map(p -> p.row() + "," + p.col())
                .collect(Collectors.joining(";"));
    }
}
