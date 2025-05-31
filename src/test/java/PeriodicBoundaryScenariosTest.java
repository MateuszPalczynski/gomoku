import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import fais.zti.oramus.gomoku.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PeriodicBoundaryScenariosTest {

    private static Stream<Arguments> scenarios() {
        return Stream.of(
                // 1. Winner is
                Arguments.of(new String[]{
                                "...x....o.",
                                "...x......",
                                "...o......",
                                "..........",
                                ".....o....",
                                ".....o....",
                                ".....o....",
                                "...x......",
                                "...x......",
                                "...x......"
                        },
                        Mark.CROSS,
                        null,
                        TheWinnerIsException.class,
                        null),

                // 2. Immediate win via horizontal wrap on 10×10
                Arguments.of(new String[]{
                                "xxxxo.....",
                                ".........o",
                                ".........o",
                                ".........o",
                                ".........o",
                                "..........",
                                "..........",
                                "...x......",
                                "..........",
                                ".........."
                        },
                        Mark.CROSS,
                        new Position(9, 0),
                        null,
                        null),

                // 3. Block opponent's vertical wrap on 10×10
                Arguments.of(new String[]{
                                ".........o",
                                ".........o",
                                "..x......o",
                                "..x......o",
                                "..x......x",
                                "..........",
                                "..........",
                                "..........",
                                "..........",
                                ".........."
                        },
                        Mark.CROSS,
                        new Position(9, 9),
                        null,
                        null),

                // 4. Open‐four diagonal wrap on 10×10
                Arguments.of(new String[]{
                                "..........",
                                "x....o....",
                                ".x........",
                                "..x....o..",
                                ".......o..",
                                ".......o..",
                                ".......o..",
                                "..........",
                                ".........x",
                                ".........x"
                        },
                        Mark.CROSS,
                        new Position(3, 4),
                        null,
                        null),

                // 5. Create double‐threat via wrap on 10×10
                Arguments.of(new String[]{
                                "..x......o",
                                "..x.......",
                                "..........",
                                "........o.",
                                "..........",
                                "..........",
                                ".........o",
                                ".........o",
                                "..........",
                                ".x.x......"
                        },
                        Mark.CROSS,
                        new Position(2, 9),
                        null,
                        null),
                // 6. Standard on periodic
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
                        null)
        );
    }

    @ParameterizedTest(name = "[{index}] mark={1}")
    @MethodSource("scenarios")
    void testPeriodicScenarios(String[] boardRows,
                               Mark nextMoveMark,
                               Position expectedPos,
                               Class<? extends Exception> expectedException,
                               Mark expectedExceptionMark) {
        int size = boardRows.length;
        // always enable periodic
        Gomoku game = new GomokuBuilder()
                .size(size)
                .firstMark(Mark.CROSS)
                .periodic()
                .build();

        Set<Move> moves = toMoves(boardRows);

        if (expectedException != null) {
            assertThrows(expectedException, () ->
                    game.nextMove(moves, nextMoveMark)
            );
        } else {
            Move move = assertDoesNotThrow(() ->
                    game.nextMove(moves, nextMoveMark)
            );
            assertNotNull(move);
            // may have two possible wrap endpoints for the first scenario:
            if (expectedPos != null && boardRows.length == 1 && boardRows[0].equals(".xxxx..")) {
                assertTrue(move.position().equals(new Position(6, 0))
                                || move.position().equals(new Position(0, 0)),
                        "Expected wrap-around endpoint");
            } else {
                assertEquals(expectedPos, move.position());
            }
            assertEquals(nextMoveMark, move.mark());
        }
    }

    /** Helper to convert String[] to Set<Move> */
    private static Set<Move> toMoves(String[] rows) {
        Set<Move> moves = new HashSet<>();
        for (int r = 0; r < rows.length; r++) {
            for (int c = 0; c < rows[r].length(); c++) {
                char ch = rows[r].charAt(c);
                if (ch == 'x') moves.add(new Move(new Position(c, r), Mark.CROSS));
                else if (ch == 'o') moves.add(new Move(new Position(c, r), Mark.NOUGHT));
            }
        }
        return moves;
    }
}
