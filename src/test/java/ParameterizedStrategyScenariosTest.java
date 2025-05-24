import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import fais.zti.oramus.gomoku.*;
import org.junit.jupiter.api.Assertions;
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
                                ".......o..",
                                "..xxx.....",
                                "..x.......",
                                "..x.......",
                                ".o........",
                                "..........",
                                "......o...",
                                ".o....o...",
                                "..........",
                                ".........."
                        }, Mark.NOUGHT,
                        null,
                        ResignException.class,
                        null),

                // 5. Rezygnacja - dwie czwórki
                Arguments.of(new String[]{
                                "........o.",
                                ".oxxxx....",
                                "..x.......",
                                "..x.......",
                                ".ox.......",
                                "..........",
                                "......o...",
                                ".o....o...",
                                "..........",
                                "...o......"
                        }, Mark.NOUGHT,
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
                        null),

                // 8. Rezygnacja przy 4
                Arguments.of(new String[]{
                                "..........",
                                "..x...o...",
                                "...x......",
                                "....x.....",
                                "...o.x....",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.NOUGHT,
                        null,
                        ResignException.class,
                        null),

                // 9. Rezygnacja przy 3.1
                Arguments.of(new String[]{
                                "..........",
                                "..xxx.o...",
                                "..x.......",
                                "..x.......",
                                "...o......",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.NOUGHT,
                        null,
                        ResignException.class,
                        null),

                // 10. Rezygnacja przy 3.2
                Arguments.of(new String[]{
                                "..........",
                                "...x..o...",
                                "..xxx.....",
                                "...x......",
                                ".....o....",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.NOUGHT,
                        null,
                        ResignException.class,
                        null),

                // 11. Rezygnacja przy 3.3
                Arguments.of(new String[]{
                                "..........",
                                "...x..o...",
                                "...xx.....",
                                "...x.x....",
                                ".....o....",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.NOUGHT,
                        null,
                        ResignException.class,
                        null),

                // 12. Zablokowanie 4
                Arguments.of(new String[]{
                                "..o.......",
                                "...x..o...",
                                "....x.....",
                                ".....x....",
                                "......x...",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.NOUGHT,
                        new Position(7, 5),
                        null,
                        null),

                // 13. Wygrana
                Arguments.of(new String[]{
                                "..o.......",
                                "...x..o...",
                                "....x.....",
                                "..........",
                                "......x...",
                                ".......x..",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.CROSS,
                        new Position(5, 3),
                        null,
                        null),

                // 14. Uzyskanie 4.1
                Arguments.of(new String[]{
                                "..........",
                                "...x..o...",
                                "..........",
                                "...x......",
                                "...x.o....",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.CROSS,
                        new Position(3, 2),
                        null,
                        null),

                // 15. Uzyskanie 4.2
                Arguments.of(new String[]{
                                "..........",
                                "...x..o...",
                                "....x.....",
                                "..........",
                                "......x...",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.CROSS,
                        new Position(5, 3),
                        null,
                        null),

                // 16. Zrobienie 3.1
                Arguments.of(new String[]{
                                "..........",
                                "...xx.o...",
                                "..x.......",
                                "..x.......",
                                "...o......",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.CROSS,
                        new Position(2, 1),
                        null,
                        null),

                // 17. Zrobienie 3.2
                Arguments.of(new String[]{
                                "..........",
                                "...x..o...",
                                "..x.x.....",
                                "...x......",
                                ".....o....",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.CROSS,
                        new Position(3, 2),
                        null,
                        null),

                // 18. Zrobienie 3.3
                Arguments.of(new String[]{
                                "..........",
                                "......o...",
                                "...xx.....",
                                "...x.x....",
                                ".....o....",
                                "..........",
                                ".......o..",
                                "..........",
                                "..........",
                                "...o......"
                        }, Mark.CROSS,
                        new Position(3, 1),
                        null,
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
            Exception ex = Assertions.assertThrows(expectedException, () ->
                    game.nextMove(moves, nextMoveMark)
            );
            if (ex instanceof TheWinnerIsException && expectedExceptionMark != null) {
                Assertions.assertEquals(expectedExceptionMark, ((TheWinnerIsException) ex).mark);
            }
        } else {
            Move move = Assertions.assertDoesNotThrow(() ->
                    game.nextMove(moves, nextMoveMark)
            );
            Assertions.assertNotNull(move);
            Assertions.assertEquals(expectedPos, move.position());
            Assertions.assertEquals(nextMoveMark, move.mark());
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