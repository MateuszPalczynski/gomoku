import fais.zti.oramus.gomoku.*;

import java.util.HashSet;
import java.util.Set;

public class TestUtil {

    public static Set<Move> tableToSet(char[][] board) {
        Set<Move> moves = new HashSet<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                char ch = board[r][c];
                if (ch != '.') {
                    Mark mark = fromChar(ch);
                    moves.add(new Move(new Position(c, r), mark));
                }
            }
        }
        return moves;
    }

    public static Mark[][] tableToMatrix(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        Mark[][] matrix = new Mark[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                char c = board[y][x];
                matrix[y][x] = (c == '.') ? Mark.NULL : fromChar(c);
            }
        }
        return matrix;
    }

    private static Mark fromChar(char c) {
        return switch (c) {
            case 'X' -> Mark.CROSS;
            case 'O' -> Mark.NOUGHT;
            case '.' -> Mark.NULL;
            default -> throw new IllegalArgumentException("Unknown symbol: " + c);
        };
    }

    private static Position rotate(Position pos, int rotation, int size) {
        int x = pos.col();
        int y = pos.row();
        return switch (rotation % 4) {
            case 1 -> new Position(y, size - 1 - x);         // 90°
            case 2 -> new Position(size - 1 - x, size - 1 - y); // 180°
            case 3 -> new Position(size - 1 - y, x);         // 270°
            default -> pos;                                  // 0°
        };
    }

    static Move rotate(Move move, int rotation, int size) {
        Position p = rotate(move.position(), rotation, size);
        return new Move(p, move.mark());
    }

    static Set<Move> rotateMoves(Set<Move> moves, int rotation, int size) {
        Set<Move> rotated = new HashSet<>();
        for (Move m : moves) rotated.add(rotate(m, rotation, size));
        return rotated;
    }

    /**
     * Zamienia macierz Mark[][] na czytelny String z X, O i . (kropka).
     */
    public static String boardToString(Mark[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (Mark[] row : matrix) {
            for (Mark m : row) {
                sb.append(m == Mark.CROSS ? 'X'
                        : m == Mark.NOUGHT ? 'O'
                        : '.');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Przykład: konwersja Set<Move> na tekstową reprezentację tablicy char[][],
     * przydatne do wypisywania w testach.
     */
    public static String convertSetToStrBoard(Set<Move> moveSet) {
        // Znajdź maksymalne wymiary planszy na podstawie pozycji ruchów
        int maxRow = 0;
        int maxCol = 0;
        for (Move move : moveSet) {
            maxRow = Math.max(maxRow, move.position().row());
            maxCol = Math.max(maxCol, move.position().col());
        }

        int boardHeight = maxRow + 1;
        int boardWidth = maxCol + 1;

        char[][] board = new char[boardHeight][boardWidth];
        for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = '.';
            }
        }

        for (Move move : moveSet) {
            int row = move.position().row();
            int col = move.position().col();
            board[row][col] = markToChar(move.mark());
        }

        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (int r = 0; r < boardHeight; r++) {
            result.append("    {");
            for (int c = 0; c < boardWidth; c++) {
                result.append('\'').append(board[r][c]).append('\'');
                if (c < boardWidth - 1) {
                    result.append(", ");
                }
            }
            result.append("}");
            if (r < boardHeight - 1) {
                result.append(",");
            }
            result.append("\n");
        }
        result.append("}");
        return result.toString();
    }

    private static char markToChar(Mark mark) {
        return switch (mark) {
            case CROSS -> 'X';
            case NOUGHT -> 'O';
            case NULL -> '.';
            default -> '.';
        };
    }
}
