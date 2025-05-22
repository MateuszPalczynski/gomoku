package fais.zti.oramus.gomoku;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParameterizedStrategyScenariosTest {

    private static Stream<Arguments> scenarios() {
        return Stream.of(
                // 1. Natychmiastowa wygrana (X stawia piątkę)
                Arguments.of(new String[]{
                                "..........",
                                "..xxxx....",
                                ".....o....",
                                "......o...",
                                "..........",
                                "..........",
                                "....o.....",
                                "....o.....",
                                "..........",
                                ".........."
                        }, Mark.CROSS,
                        new Position(1, 1),
                        null,
                        null),

                // 2. Blokowanie (O musi zablokować X przed piątką)
                Arguments.of(new String[]{
                                "..........",
                                ".xxxxo....",
                                "..........",
                                "..........",
                                "....o.....",
                                "....o.....",
                                "....o.....",
                                "..........",
                                "..........",
                                ".........."
                        }, Mark.NOUGHT,
                        new Position(0, 1),
                        null,
                        null),

                // 3. Otwarta czwórka (X tworzy 4 w rzędzie z dwoma pustymi końcami)
                Arguments.of(new String[]{
                                "..........",
                                "...xxx....",
                                "..........",
                                "....o.....",
                                "..........",
                                "..........",
                                "..........",
                                ".....o....",
                                ".....o....",
                                ".........."
                        }, Mark.CROSS,
                        new Position(2, 1),
                        null,
                        null),

                // 4. Podwójne zagrożenie (X jednocześnie tworzy dwa 4-w-rzędzie)
                Arguments.of(new String[]{
                                "..........",
                                "..xx.x....",
                                "..x.x.....",
                                "..........",
                                ".o........",
                                "..........",
                                "......o...",
                                ".o....o...",
                                "..........",
                                ".........."
                        }, Mark.CROSS,
                        new Position(3, 1),
                        null,
                        null),

                // 5. Rezygnacja (pełna plansza bez zwycięzcy)
                Arguments.of(new String[]{
                                "xxooxxooxx",
                                "ooxxooxxoo",
                                "xxooxxooxx",
                                "ooxxooxxoo",
                                "xxooxxooxx",
                                "ooxxooxxoo",
                                "xxooxxooxx",
                                "ooxxooxxoo",
                                "xxooxxooxx",
                                "ooxxooxxoo"
                        }, Mark.CROSS,
                        null,
                        ResignException.class,
                        null),

                // 6. TheWinnerIsException (już jest pięć w rzędzie)
                Arguments.of(new String[]{
                                "..........",
                                ".xxxxx....",
                                "..........",
                                "...o..o...",
                                "...o......",
                                "...o......",
                                ".......o..",
                                "..........",
                                "..........",
                                ".........."
                        }, Mark.CROSS,
                        null,
                        TheWinnerIsException.class,
                        Mark.CROSS),

                // 7. WrongBoardStateException (zła liczba znaków)
                Arguments.of(new String[]{
                                "..........",
                                ".xxx......",
                                "..........",
                                "..........",
                                "..........",
                                "..........",
                                "..........",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.CROSS,
                        null,
                        WrongBoardStateException.class,
                        null)
        );
    }

    @ParameterizedTest(name = "[{index}] mark={1}")
    @MethodSource("scenarios")
    void testScenarios(String[] boardRows,
                       Mark nextMoveMark,
                       Position expectedPos,
                       Class<? extends Exception> expectedException,
                       Mark expectedExceptionMark) {
        int size = boardRows.length;
        Gomoku game = new GomokuBuilder()
                .size(size)
                .firstMark(Mark.CROSS)
                .build();

        Set<Move> moves = toMoves(boardRows);

        if (expectedException != null) {
            Exception ex = assertThrows(expectedException, () ->
                    game.nextMove(moves, nextMoveMark)
            );
            if (ex instanceof TheWinnerIsException && expectedExceptionMark != null) {
                assertEquals(expectedExceptionMark, ((TheWinnerIsException) ex).mark);
            }
        } else {
            Move move = assertDoesNotThrow(() ->
                    game.nextMove(moves, nextMoveMark)
            );
            assertNotNull(move);
            assertEquals(expectedPos, move.position());
            assertEquals(nextMoveMark, move.mark());
        }
    }

    /** Pomocnicza metoda konwertująca String[] na Set<Move> */
    private static Set<Move> toMoves(String[] rows) {
        Set<Move> moves = new HashSet<>();
        for (int r = 0; r < rows.length; r++) {
            String row = rows[r];
            for (int c = 0; c < row.length(); c++) {
                char ch = row.charAt(c);
                if (ch == 'x') {
                    moves.add(new Move(new Position(c, r), Mark.CROSS));
                } else if (ch == 'o') {
                    moves.add(new Move(new Position(c, r), Mark.NOUGHT));
                }
                // '.' oznacza puste pole, pomijamy
            }
        }
        return moves;
    }
}