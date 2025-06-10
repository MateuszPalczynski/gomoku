import fais.zti.oramus.gomoku.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FinalTest {
    @Nested
    class validation {
        @Test
        void emptyBoardWrongMove() {
            String board = """
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.NOUGHT;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(WrongBoardStateException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void givenBothWinners() {
            String board = """
                X . . . . . . . . .
                . X . . . . . . . .
                . . X . . . . . . .
                . . . X . . . . . .
                . . . . X . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . O O O O O
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(TheWinnerIsException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void givenTwoWinnerSequencesForSingleMark() {
            String board = """
                X . . . . . . . . .
                . X . . . . . . . .
                . . X . . . . . . .
                . . . X . . . . . X
                . . . . . . . . . X
                . . . . . . . X X X
                . . . . . . . . . .
                . . . . . O O O O O
                . . . . . . . . . .
                . . . . . O O O O O
                """;
            Mark firstMark=Mark.NOUGHT;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(TheWinnerIsException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void theWinnerIsZigzag() {
            String board = """
                X . . X . X . X . X
                . X . . . . . . . .
                . . X . . . . . . .
                . . . X . . . X . .
                . . . . . . . . . .
                . . X . . O . X . O
                . . X . . O . . . O
                . . . . . O . . . O
                . . . . . O . . . O
                . . . . . O O O O O
                """;
            Mark firstMark=Mark.NOUGHT;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(TheWinnerIsException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

    }

    @Nested
    class theWinnerIs {
        @Test
        void theWinnerIs() {
            String board = """
                X . . . . . . . . .
                . X . . . . . . . .
                . . X . . . . . . .
                . . . X . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . O O O O O
                """;
            Mark firstMark=Mark.NOUGHT;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(TheWinnerIsException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void theWinnerHasMultiple5SequencesWithJointPoint() {
            String board = """
                X . . X . X . X . X
                . X . . . . . . . .
                . . X . . . . . . .
                . . . X . . . X . .
                . . . . . . . . . .
                . . X . . O . X . O
                . . X . . . O . . O
                . . . . . . . O . O
                . . . . . . . . O O
                . . . . . O O O O O
                """;
            Mark firstMark=Mark.NOUGHT;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(TheWinnerIsException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void theWinnerIsWithBoundary() {
            String board = """
                X . . . . . . . . .
                . X . . . . . . . .
                . . X . . . . . . .
                . . . X . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . O O O O X
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.NOUGHT;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(TheWinnerIsException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }
    }

    @Nested
    class resign {

        @Test
        void givenOpponentWith4SequenceAndOpenEnds() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                    X . . . . . . . . .
                    . X . . . . . . . O
                    . . X . . . . . . O
                    . . . . . . . . . O
                    . . . . . . . . . O
                    . . . . . X . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    """;
            Mark firstMark = Mark.CROSS;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(ResignException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void givenOpponentWithHorizontal4SequenceAndOpenEnds() {
            String board = """
                    X . . . . . . . . .
                    . X . . . . . . . .
                    . . X . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . O O O O .
                    """;
            Mark firstMark = Mark.NOUGHT;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(ResignException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void givenOpponentWithCrossAndOpenEnds() {
            String board = """
                    . . . . . . O . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . X . . . . . . .
                    . X . X . . . . . .
                    . . X . . . . . . .
                    . . . . . . O . . .
                    . . . . . O O O . .
                    """;
            Mark firstMark = Mark.NOUGHT;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(ResignException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void givenOpponentWith4SequenceAndOpenEndsAndBoundary() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                    . . . . . O . . . .
                    . . . . . O . . . .
                    . . . . . O . . . .
                    . . . . . . . . O O
                    . . . . . . . . . .
                    . . X . . . . . . .
                    . X X X . . . . . .
                    . . X . . . . . . .
                    . . . . . . . . . .
                    . . . . . O . . . .
                    """;
            Mark firstMark = Mark.NOUGHT;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(ResignException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void resign4WhenCanMove() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                    . . . . . O . . . .
                    . . . . . O . . . .
                    . . . . . O . . . .
                    . . . . . . . . . .
                    . . X . . . . . . .
                    . . . . . . . . . .
                    . . X . . . . . . .
                    . . X . . . . . . .
                    . . . . . . . . . .
                    . . . . . O . . . .
                    """;
            Mark firstMark = Mark.NOUGHT;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(ResignException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void resign3WhenCanMove() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                    . . . . . O . . . .
                    . . . . O O O . . .
                    . . . . . O . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . X X . . . . .
                    . . X . . . . . . .
                    . . X . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    """;
            Mark firstMark = Mark.NOUGHT;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            assertThrows(ResignException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }
    }

    @Nested
    class nextWinMove {

        @Nested
        class creates5Sequence {
            @Test
            void fifthMarkInMiddle() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                X . . . . . . . . O
                . X . . . . . . . O
                . . X . . . . . . O
                . . . . . . . . . O
                . . . . X . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
                Mark firstMark=Mark.CROSS;
                Mark nextMoveMark=Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = false;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(3, 3), Mark.CROSS), move);
            }

            @Test
            void fifthMarkAtEnd() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                X . . . . . . . . O
                . X . . . . . . . O
                . . X . . . . . . O
                . . . X . . . . . O
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
                Mark firstMark=Mark.CROSS;
                Mark nextMoveMark=Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = false;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(4, 4), Mark.CROSS), move);
            }
            @Test
            void fifthMarkAtEitherEnd() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                . . . . . . . . . O
                . X . . . . . . . O
                . . X . . . . . . O
                . . . X . . . . . O
                . . . . X . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
                Mark firstMark=Mark.CROSS;
                Mark nextMoveMark=Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = false;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertTrue(move.equals(new Move(new Position(0, 0), Mark.CROSS)) ||
                        move.equals(new Move(new Position(5, 5), Mark.CROSS)));
            }

            @Test
            void forthMarkAtTheEn() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                X . . . . . . . . O
                . X . . . . . . . O
                . . X . . . . . . O
                . . . X . . . . . O
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . O . .
                . . . . . . . . . .
                """;
                Mark firstMark=Mark.NOUGHT;
                Mark nextMoveMark=Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = true;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(4, 4), Mark.CROSS), move);
            }
        }

        @Nested
        class creates4Sequence {
            @Test
            void forthMarkInMiddle() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                . . . . . . . . . O
                . . . . . . . O . .
                . . . . . . . . . O
                . . . . . X . . . .
                . . . . X . . . . .
                . . . . . . . . . .
                . . X . . . . . . .
                . . . . . . . . . .
                O . . . . . . . . .
                . . . . . . . . . .
                """;
                Mark firstMark=Mark.NOUGHT;
                Mark nextMoveMark=Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = false;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(3, 5), Mark.CROSS), move);
            }

            @Test
            void forthMarkAtTheEnd() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                        X . . . . . . . . O
                        . X . . . . . . . .
                        . . X . . . . . . O
                        . . . . . . . . . O
                        . . . . . . . . . .
                        . . . . . . . . . .
                        . . . . . . . . . .
                        . . . . . . . . . .
                        . . . . . . . O . .
                        . . . . . . . . . .
                        """;
                Mark firstMark = Mark.NOUGHT;
                Mark nextMoveMark = Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = true;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(3, 3), Mark.CROSS), move);
            }
            @Test
            void given3SequenceWithClosedEnd_thenShouldNotAdd4() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                        O . . . . . . . . .
                        . X . . . . . . . .
                        . . X . . . . . . O
                        . . . X . . . . . O
                        . . . . . . . . . .
                        . . . . . . . . . O
                        . . . . . . . . . .
                        . . . . . . . . . .
                        . . . . . . . . . .
                        . . . . . . . . . .
                        """;
                Mark firstMark = Mark.NOUGHT;
                Mark nextMoveMark = Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = false;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(9, 4), Mark.CROSS), move);
            }

            @Test
            void crossWithClosedEnd() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                        O . . . . . . . . .
                        . X . X . . . . . .
                        . . . . . . . . . O
                        . X . X . . . . . O
                        . . . . . . . O O .
                        . . . . . . . . . .
                        . . . . . . . . . .
                        . . . . . . . . . .
                        . . . . . . . . . .
                        . . . . . . . . . .
                        """;
                Mark firstMark = Mark.NOUGHT;
                Mark nextMoveMark = Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = true;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(9, 4), Mark.CROSS), move);
            }
        }

        @Nested
        class createsTwo3Sequences {

            @Test
            void cross() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                . . . . . . . . . .
                . . . . . . . . . .
                . . X . X . . . . O
                . . . . . . . . . O
                . . X . X . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . O O .
                . . . . . . . . . O
                """;
                Mark firstMark=Mark.NOUGHT;
                Mark nextMoveMark=Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = false;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(3, 3), Mark.CROSS), move);
            }

            @Test
            void g() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                . . . . . . . . . .
                . . . . . . . . . .
                . . . X X . . . . O
                . . X . . . . . . O
                . . X . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . O O .
                . . . . . . . . . O
                """;
                Mark firstMark=Mark.NOUGHT;
                Mark nextMoveMark=Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = false;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(2, 2), Mark.CROSS), move);
            }

            @Test
            void gOutOfBounds() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                . . X X . . . . . .
                . X . . . . . . . .
                . X . . . . O . . .
                . . . . . . O . . .
                . . . . . . . O O .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . O
                """;
                Mark firstMark=Mark.NOUGHT;
                Mark nextMoveMark=Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = false;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertEquals(new Move(new Position(6, 4), Mark.CROSS), move);
            }

            @Test
            void doNotResignWithG() throws TheWinnerIsException, ResignException, WrongBoardStateException {
                String board = """
                X . . . . O . . . .
                . X . . . O . . . .
                . . . . . . . . . .
                . . . . . . . . O O
                . . . . . . . . . .
                . . X . . . . . . O
                . X X X . . . . . .
                . . X . . . . . . .
                . . . . . . . . . .
                . . . . . O O O . .
                """;
                Mark firstMark=Mark.NOUGHT;
                Mark nextMoveMark=Mark.CROSS;
                int size = 10;
                boolean periodicBoundaryConditionsInUse = true;
                Set<Move> moves = parseBoard(board);

                Game gomoku = new Gomoku();
                gomoku.firstMark(firstMark);
                gomoku.size(size);
                if (periodicBoundaryConditionsInUse) {
                    gomoku.periodicBoundaryConditionsInUse();
                }
                Move move = gomoku.nextMove(moves, nextMoveMark);
                assertTrue(
                        move.equals(new Move(new Position(2, 8), Mark.CROSS)) ||
                                move.equals(new Move(new Position(2, 4), Mark.CROSS)),
                        "The move should be one of two expected moves"
                );

            }
        }

        @Test
        void mustBuild4InsteadOfProtectFromCross() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                X . . . . . . . . .
                . X . . . . . . . .
                . . X . . . . . . .
                . . . . . . . . . .
                . . . . . . . . O .
                . . . . . . . O . O
                . . . . . . . . O .
                . . X . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(3, 3), Mark.CROSS), move);
        }

    }

    @Nested
    class protect {

        @Test
        void fifthMarkAtSpecificEnd() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                X . . . . . . . . O
                . X . . . . . . . O
                . . X . . . . . . O
                . . . . . . . . . O
                . . . . . . . . . .
                . . . . . X . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(9, 4), Mark.CROSS), move);
        }

        @Test
        void fifthMarkAtMiddleOfFour() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                X . . . . . . . . O
                . X . . . . . . . O
                . . X . . . . . . O
                . . . . . . . . . .
                . . . . . . . . . O
                . . . . . X . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(9, 3), Mark.CROSS), move);
        }

        @Test
        void mustProtectFromFour() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                X . . . . . . . . .
                . X . . . . . . . O
                . . X . . . . . . O
                . . . . . . . . . .
                . . . . . . . . . O
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(9, 3), Mark.CROSS), move);
        }

        @Test
        void mustProtectFromFourThreeInARow() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                X . . . . . . . . .
                . X . . . . . . . .
                . . . . . . . . . O
                . . X . . . . . . O
                . . . . . . . . . O
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertTrue(
                    move.equals(new Move(new Position(9, 5), Mark.CROSS)) ||
                            move.equals(new Move(new Position(9, 1), Mark.CROSS)),
                    "Ruch powinien być jednym z dwóch oczekiwanych ruchów"
            );

        }

        @Test
        void mustProtectFromFourWithoutBoundaries() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                X . . . . . . . . O
                . X . . . . . . . O
                . . . . . . . . . O
                . . X . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(9, 3), Mark.CROSS), move);
        }

        @Test
        void mustProtectFromThree() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                    X . . . . . . . . .
                    . X . . . . . . . .
                    . . X . . . O . . .
                    . . . . . . . O . .
                    . X . . . . . . . .
                    . . . . . . . . O .
                    . . . . . . . . O .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    . . . . . . . . . .
                    """;
            Mark firstMark = Mark.CROSS;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(8, 4), Mark.CROSS), move);
        }

        @Test
        void mustProtectFromCross() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                X . . . . . . . . .
                . X . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . X . . . . . . O .
                . X . . . . . O . O
                . . . . . . . . O .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(8, 5), Mark.CROSS), move);
        }

        @Test
        void mustProtectFromFive() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                . . . . . . . . . .
                . X . . . . . . . .
                . . X . . . . . O .
                . . . X . . . . O .
                . X . . . . . . . .
                . . . . . . . . O .
                . . . . . . . . O .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(8, 4), Mark.CROSS), move);
        }

        @Test
        void mustProtectFromFiveFourInRow() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                . . . . . . . . . .
                . X . . . . . . . .
                . . X . . . . . . .
                . . . X . . . . O .
                . . . . . . . . O .
                . . . . . . . . O .
                . . . . . . . . O .
                . . . . . . . . X .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = false;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(8, 2), Mark.CROSS), move);
        }

        @Test
        void mustProtectFromFiveWithBoundaryConditions() throws TheWinnerIsException, ResignException, WrongBoardStateException {
            String board = """
                . . . . . . . . O .
                . X . . . . . . O .
                . . X . . . . . O .
                . . . X . . . . O .
                . . . . . . . . X .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                """;
            Mark firstMark=Mark.CROSS;
            Mark nextMoveMark=Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if (periodicBoundaryConditionsInUse) {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);
            assertEquals(new Move(new Position(8, 9), Mark.CROSS), move);
        }
    }

    @Nested
    class addedbyOramus {
        @Test
        void mustHaveEnoughSpaceToWin() throws TheWinnerIsException, ResignException, WrongBoardStateException
        {

            String board = """
                . . . . . . . . . .
                . . . . . . . . . .
                . . X . . . . . . .
                . . . X . . . . O .
                . . . . . . . . O .
                . . . . . . . . O .
                . . O O . . . . . .
                . . X X . . . . . .
                . . . . . . . . X .
                . . . . . . . . . .
                """;
            Mark firstMark = Mark.CROSS;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if(periodicBoundaryConditionsInUse)
            {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);

            assertEquals(new Move(new Position(8, 2),Mark.CROSS),move);
        }


        @Test
        void multipleAttacks() throws TheWinnerIsException, ResignException, WrongBoardStateException
        {

            String board = """
                . . . . . . . . . .
                . . . . . . . . X .
                . . X . . . . . O .
                . . . X . . . . O .
                . . . . . . . . O .
                . . . . . . O O O .
                . . O O . . . . . .
                . X X X . O . . X .
                . . . . . . . X X .
                . . . . . . . . . .
                """;
            Mark firstMark = Mark.CROSS;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if(periodicBoundaryConditionsInUse)
            {
                gomoku.periodicBoundaryConditionsInUse();
            }

            //Move move = gomoku.nextMove(moves, nextMoveMark);

            assertThrows(ResignException.class, () -> gomoku.nextMove(moves, nextMoveMark));
        }

        @Test
        void BestAttack() throws TheWinnerIsException, ResignException, WrongBoardStateException
        {
            String board = """
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . . .
                . . . . . . . . O .
                . . . . . . . . O .
                . . . . . . . . O .
                . . . . . . . . . .
                O . X X . X . O . .
                . . . . . . . . X .
                . . . . . . . . X .
                """;
            Mark firstMark = Mark.CROSS;
            Mark nextMoveMark = Mark.CROSS;
            int size = 10;
            boolean periodicBoundaryConditionsInUse = true;
            Set<Move> moves = parseBoard(board);

            Game gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(size);
            if(periodicBoundaryConditionsInUse)
            {
                gomoku.periodicBoundaryConditionsInUse();
            }

            Move move = gomoku.nextMove(moves, nextMoveMark);

            assertEquals(new Move(new Position(4, 7),Mark.CROSS),move);
        }


    }





    public static Set<Move> parseBoard(String board) {
        Set<Move> moves = new HashSet<>();
        String[] lines = board.strip().split("\n");

        for (int col = 0; col < lines.length; col++) {
            String[] tokens = lines[col].trim().split("\\s+");
            for (int row = 0; row < tokens.length; row++) {
                String token = tokens[row];
                switch (token) {
                    case "X" -> moves.add(new Move(new Position(row, col), Mark.CROSS));
                    case "O" -> moves.add(new Move(new Position(row, col), Mark.NOUGHT));
                }
            }
        }
        return moves;
    }
}