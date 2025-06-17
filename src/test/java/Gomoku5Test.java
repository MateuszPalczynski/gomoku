import fais.zti.oramus.gomoku.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Gomoku5Test {

    @Test
    public void throwsWrongBoardStateExceptionWhenBoardPositionIsOutOfBounds() {
        for (int rot = 0; rot < 4; rot++) {
            throwsWrongBoardStateExceptionWhenBoardPositionIsOutOfBounds(rot);
        }
    }

    public void throwsWrongBoardStateExceptionWhenBoardPositionIsOutOfBounds(int rot) {
        try {
            Set<Move> moves = new HashSet<>();

            moves.add(new Move(new Position(7, 0), Mark.CROSS));
            moves.add(new Move(new Position(1, 1), Mark.CROSS));
            moves.add(new Move(new Position(6, 1), Mark.CROSS));
            moves.add(new Move(new Position(8, 1), Mark.CROSS));
            moves.add(new Move(new Position(2, 2), Mark.CROSS));
            moves.add(new Move(new Position(7, 2), Mark.CROSS));
            moves.add(new Move(new Position(2, 4), Mark.CROSS));
            moves.add(new Move(new Position(1, 5), Mark.CROSS));
            moves.add(new Move(new Position(4, 6), Mark.CROSS));
            moves.add(new Move(new Position(5, 6), Mark.CROSS));
            moves.add(new Move(new Position(3, 7), Mark.CROSS));
            moves.add(new Move(new Position(5, 7), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 5;
            gomoku.size(size);
            gomoku.nextMove(moves, Mark.CROSS);

            Move next = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Assertions.fail("Expected WrongBoardStateException to be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void throwsWrongBoardStateExceptionWhenOneMarkHasMoreMovesAllRotations() {
        for (int rot = 0; rot < 4; rot++) {
            throwsWrongBoardStateExceptionWhenOneMarkHasMoreMoves(rot);
        }
    }

    public void throwsWrongBoardStateExceptionWhenOneMarkHasMoreMoves(int rot) {
        try {
            Set<Move> moves = new HashSet<>();

            moves.add(new Move(new Position(7, 0), Mark.CROSS));
            moves.add(new Move(new Position(1, 1), Mark.CROSS));
            moves.add(new Move(new Position(6, 1), Mark.CROSS));
            moves.add(new Move(new Position(8, 1), Mark.CROSS));
            moves.add(new Move(new Position(2, 2), Mark.CROSS));
            moves.add(new Move(new Position(7, 2), Mark.CROSS));
            moves.add(new Move(new Position(2, 4), Mark.CROSS));
            moves.add(new Move(new Position(1, 5), Mark.CROSS));
            moves.add(new Move(new Position(4, 6), Mark.CROSS));
            moves.add(new Move(new Position(5, 6), Mark.CROSS));
            moves.add(new Move(new Position(3, 7), Mark.CROSS));
            moves.add(new Move(new Position(5, 7), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move next = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Assertions.fail("Expected WrongBoardStateException to be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void throwsWrongBoardStateExceptionWhenBothMarksAtTheSamePosition() {
        for (int rot = 0; rot < 4; rot++) {
            throwsWrongBoardStateExceptionWhenBothMarksAtTheSamePosition(rot);
        }
    }

    public void throwsWrongBoardStateExceptionWhenBothMarksAtTheSamePosition(int rot) {
        try {
            Set<Move> moves = new HashSet<>();

            moves.add(new Move(new Position(5, 7), Mark.CROSS));
            moves.add(new Move(new Position(5, 7), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Assertions.fail("Expected WrongBoardStateException to be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void throwsTheWinnerIsExceptionWhenCurrentMarkWins() {
        for (int rot = 0; rot < 4; rot++) {
            throwsTheWinnerIsExceptionWhenCurrentMarkWins(rot);
        }
    }

    public void throwsTheWinnerIsExceptionWhenCurrentMarkWins(int rot) {
        try {
            Set<Move> moves = new HashSet<>();

            moves.add(new Move(new Position(1, 0), Mark.CROSS));
            moves.add(new Move(new Position(1, 1), Mark.CROSS));
            moves.add(new Move(new Position(1, 2), Mark.CROSS));
            moves.add(new Move(new Position(1, 3), Mark.CROSS));
            moves.add(new Move(new Position(1, 4), Mark.CROSS));
            moves.add(new Move(new Position(7, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 0), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);
            Assertions.fail("Expected TheWinnerIsException to be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            assertEquals(Mark.CROSS, e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void throwsTheWinnerIsExceptionWhenOpponentWins() {
        for (int rot = 0; rot < 4; rot++) {
            throwsTheWinnerIsExceptionWhenOpponentWins(rot);
        }
    }

    public void throwsTheWinnerIsExceptionWhenOpponentWins(int rot) {
        try {
            Set<Move> moves = new HashSet<>();

            moves.add(new Move(new Position(1, 0), Mark.CROSS));
            moves.add(new Move(new Position(1, 1), Mark.CROSS));
            moves.add(new Move(new Position(1, 2), Mark.CROSS));
            moves.add(new Move(new Position(1, 3), Mark.CROSS));
            moves.add(new Move(new Position(1, 4), Mark.CROSS));
            moves.add(new Move(new Position(7, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 0), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            int size = 10;
            gomoku.size(size);
            gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.NOUGHT);
            Assertions.fail("Expected TheWinnerIsException to be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            assertEquals(Mark.CROSS, e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void returnsNextMoveWhenInLineThreeSpaceOneMarks() {
        for (int rot = 0; rot < 4; rot++) {
            returnsNextMoveWhenInLineThreeSpaceOneMarks(rot);
        }
    }


    public void returnsNextMoveWhenInLineThreeSpaceOneMarks(int rot) {
//        XXX_X
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', 'X', '.', 'O', '.', '.', '.', 'X', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', 'X', 'X', 'X', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', 'X', '.', '.'},
                    {'.', '.', '.', 'O', '.', '.', '.', '.', '.', 'O'},
                    {'.', '.', 'X', '.', '.', '.', '.', 'X', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', 'O', '.', '.'},
                    {'.', '.', '.', '.', 'X', '.', '.', '.', '.', '.'},
                    {'O', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'O', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'O', '.', 'O', '.', '.', '.', 'O', '.', 'O', 'O'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);
            Move expected = new Move(new Position(7, 3), Mark.CROSS);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void returnsNextMoveWhenInLineTwoSpaceTwoMarks() {
        for (int rot = 0; rot < 4; rot++) {
            returnsNextMoveWhenInLineTwoSpaceTwoMarks(rot);
        }
    }

    public void returnsNextMoveWhenInLineTwoSpaceTwoMarks(int rot) {
//        XX_XX
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', 'O', '.', '.', '.', 'X', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', 'X', 'X', 'X', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', '.', 'O'},
                    {'.', '.', '.', '.', '.', '.', '.', 'X', '.', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', 'X', '.', 'O'},
                    {'.', 'X', '.', '.', '.', '.', '.', 'O', '.', '.'},
                    {'.', '.', '.', '.', 'X', '.', '.', '.', '.', '.'},
                    {'O', '.', '.', 'X', '.', '.', '.', '.', '.', 'O'},
                    {'.', 'O', '.', '.', '.', '.', '.', 'O', '.', '.'},
                    {'O', '.', 'O', '.', '.', '.', 'O', '.', '.', 'O'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);
            Move expected = new Move(new Position(7, 2), Mark.CROSS);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void throwsWinnerExceptionWhenBoardIsEquallyFull() {
        for (int rot = 0; rot < 4; rot++) {
            throwsWinnerExceptionWhenBoardIsEquallyFull(rot);
        }
    }

    public void throwsWinnerExceptionWhenBoardIsEquallyFull(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                    {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                    {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                    {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                    {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                    {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Assertions.fail("Should have thrown TheWinnerIsException");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            assertEquals(Mark.CROSS, e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void throwsResignExceptionWhenOpponentHasFourInLineAndOpenNodes() {
        for (int rot = 0; rot < 4; rot++) {
            throwsResignExceptionWhenOpponentHasFourInLineAndOpenNodes(rot);
        }
    }

    public void throwsResignExceptionWhenOpponentHasFourInLineAndOpenNodes(int rot) {
//        OO_O
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'O', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', 'X', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'X', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expected = new Move(new Position(8, 2), Mark.CROSS);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.assertTrue(true);
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void blockMoveWhenOpponentCanHaveDoubleThrees() {
        for (int rot = 0; rot < 4; rot++) {
            blockMoveWhenOpponentCanHaveDoubleThrees(rot);
        }
    }

    public void blockMoveWhenOpponentCanHaveDoubleThrees(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', 'O', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.NOUGHT);

            Move expected = new Move(new Position(3, 3), Mark.NOUGHT);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException should be thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown" + e.getLocalizedMessage());
        }
    }

    @Test
    public void returnsFourthStepForCurrentMarkWhenOpenNodes() {
        for (int rot = 0; rot < 4; rot++) {
            returnsFourthStepForCurrentMarkWhenOpenNodes(rot);
        }
    }

    public void returnsFourthStepForCurrentMarkWhenOpenNodes(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', 'X', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'O', '.', '.', '.', '.', '.', '.', 'X', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', 'O', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expected = new Move(new Position(4, 4), Mark.CROSS);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void blocksFourthStepForOpponentMarkWhenClosedNode() {
        for (int rot = 0; rot < 4; rot++) {
            blocksFourthStepForOpponentMarkWhenClosedNode(rot);
        }
    }

    public void blocksFourthStepForOpponentMarkWhenClosedNode(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'O', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', 'X', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'O', '.', '.', '.', '.', '.', '.', 'X', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.NOUGHT);

            Move expected = new Move(new Position(4, 4), Mark.NOUGHT);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void returnsFourthStepForDoubleThreesVersion1() {
        for (int rot = 0; rot < 4; rot++) {
            returnsFourthStepForDoubleThreesVersion1(rot);
        }
    }

    public void returnsFourthStepForDoubleThreesVersion1(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', 'O', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expected = new Move(new Position(3, 3), Mark.CROSS);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void returnsStepForDoubleThreesVersion2() {
        for (int rot = 0; rot < 4; rot++) {
            returnsStepForDoubleThreesVersion2(rot);
        }
    }

    public void returnsStepForDoubleThreesVersion2(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'O', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', 'X', '.', 'X', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expected = new Move(new Position(2, 2), Mark.CROSS);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void returnsStepForDoubleThreesVersion3() {
        for (int rot = 0; rot < 4; rot++) {
            returnsStepForDoubleThreesVersion3(rot);
        }
    }

    public void returnsStepForDoubleThreesVersion3(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'O', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', 'X', 'X', '.', '.', '.', '.', '.'},
                    {'.', '.', 'X', '.', 'X', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expected = new Move(new Position(4, 5), Mark.CROSS);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void returnsNextMoveWhenInLineThreeSpaceOneMarksAndPeriodic() {
        for (int rot = 0; rot < 4; rot++) {
            returnsNextMoveWhenInLineThreeSpaceOneMarksAndPeriodic(rot);
        }
    }

    public void returnsNextMoveWhenInLineThreeSpaceOneMarksAndPeriodic(int rot) {
//        XXX_X
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'O', '.', '.', '.', '.', '.', '.', 'X', 'O', '.'},
                    {'.', '.', '.', '.', 'O', '.', '.', 'X', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', 'O', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'O', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', 'X', 'X', '.', '.', '.', '.', '.'},
                    {'.', '.', 'X', '.', 'X', '.', '.', '.', 'O', '.'},
                    {'.', 'O', '.', '.', '.', '.', '.', 'X', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', 'X', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            gomoku.periodicBoundaryConditionsInUse();
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            List<Move> expected = List.of(
                    new Move(new Position(7, 7), Mark.CROSS),
                    new Move(new Position(7, 2), Mark.CROSS));
            boolean isAnyValidMove = false;
            for (Move move : expected) {
                Move exp = TestUtil.rotate(move, rot, size);
                if (exp.equals(nextMove)) {
                    isAnyValidMove = true;
                }
            }

            Assertions.assertTrue(isAnyValidMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown" + e.getLocalizedMessage());
        }
    }

//    @Test
//    public void returnsNextMoveWhenInLineThreeSpaceOneMarksAndNotPeriodic() {
//        for (int rot = 0; rot < 4; rot++) {
//            returnsNextMoveWhenInLineThreeSpaceOneMarksAndNotPeriodic(rot);
//        }
//    }

    public void returnsNextMoveWhenInLineThreeSpaceOneMarksAndNotPeriodic(int rot) {
//        XXX_X
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'O', '.', '.', '.', '.', '.', '.', 'X', 'O', '.'},
                    {'.', '.', '.', '.', 'O', '.', '.', 'X', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', 'O', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'O', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', 'X', 'X', '.', '.', '.', '.', '.'},
                    {'.', '.', 'X', '.', 'X', '.', '.', '.', 'O', '.'},
                    {'.', 'O', '.', '.', '.', '.', '.', 'X', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', 'X', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);
            Move expectedMoveOption1 = new Move(new Position(4, 5), Mark.CROSS);
            Move exp = TestUtil.rotate(expectedMoveOption1, rot, size);
            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown" + e.getLocalizedMessage());
        }
    }

    @Test
    public void throwsResignExceptionWhenOpponentHasFourInLineAndOpenNodesAndPeriodic() {
        for (int rot = 0; rot < 4; rot++) {
            throwsResignExceptionWhenOpponentHasFourInLineAndOpenNodesAndPeriodic(rot);
        }
    }

    public void throwsResignExceptionWhenOpponentHasFourInLineAndOpenNodesAndPeriodic(int rot) {

        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'O', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', 'X', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', 'X', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'X', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.periodicBoundaryConditionsInUse();
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expectedMoveOption1 = new Move(new Position(8, 0), Mark.CROSS);
            Move exp = TestUtil.rotate(expectedMoveOption1, rot, size);
            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.assertTrue(true);
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void blocksFourthStepForOpponentMarkWhenClosedNodeAndPeriodic() {
        for (int rot = 0; rot < 4; rot++) {
            blocksFourthStepForOpponentMarkWhenClosedNodeAndPeriodic(rot);
        }
    }

    public void blocksFourthStepForOpponentMarkWhenClosedNodeAndPeriodic(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'X', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'O', '.', '.', '.', '.', '.', '.', 'X', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', 'X'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.periodicBoundaryConditionsInUse();
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.NOUGHT);

            Move expectedMoveOption1 = new Move(new Position(2, 2), Mark.NOUGHT);
            Move exp = TestUtil.rotate(expectedMoveOption1, rot, size);
            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void CaseThereIsInvalidPosition() {
        try {
            final Mark firstMark = Mark.CROSS;
            final int boardSize = 8;
            final boolean isPeriodic = false;
            var boardState = new HashSet<Move>();
            var mark = firstMark;
            for (int i = 0; i <= boardSize; ++i) {
                boardState.add(new Move(new Position(i, i), mark));
                if (mark == Mark.CROSS) mark = Mark.NOUGHT;
                else mark = Mark.CROSS;
            }
            new BoardStateValidatorTestData(boardState, firstMark, Mark.CROSS, boardSize, isPeriodic);

            Assertions.fail("WrongBoardStateException should be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void CaseThereIsTooManyPositionsInSet() {
        try {

            final Mark firstMark = Mark.CROSS;
            final boolean isPeriodic = false;
            final int boardSize = 8;
            var boardState = new HashSet<Move>();
            var mark = firstMark;
            for (int i = 0; i <= boardSize * boardSize; ++i) {
                boardState.add(new Move(new Position(i, i), mark));
                if (mark == Mark.CROSS) mark = Mark.NOUGHT;
                else mark = Mark.CROSS;
            }
            new BoardStateValidatorTestData(boardState, firstMark, Mark.CROSS, boardSize, isPeriodic);

            Assertions.fail("WrongBoardStateException should be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void CaseThereIsDuplicate() {
        try {
            final Mark firstMark = Mark.CROSS;
            final int boardSize = 8;
            final boolean isPeriodic = false;
            var boardState = new HashSet<Move>();
            boardState.add(new Move(new Position(0, 0), Mark.CROSS));
            boardState.add(new Move(new Position(0, 0), Mark.NOUGHT));
            new BoardStateValidatorTestData(boardState, firstMark, Mark.NOUGHT, boardSize, isPeriodic);

            Assertions.fail("WrongBoardStateException should be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void CaseThereIsImpossibleNumberOfMoves1() {
        try {
            final int boardSize = 8;
            final boolean isPeriodic = false;
            var boardState = new HashSet<Move>();
            for (int i = 0; i < 3; ++i) {
                boardState.add(new Move(new Position(i, 0), Mark.CROSS));
            }
            for (int i = 3; i < 5; ++i) {
                boardState.add(new Move(new Position(i, 0), Mark.NOUGHT));
            }
            new BoardStateValidatorTestData(boardState, Mark.CROSS, Mark.CROSS, boardSize, isPeriodic);

            Assertions.fail("WrongBoardStateException should be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void CaseThereIsImpossibleNumberOfMoves2() {
        try {
            final int boardSize = 8;
            final boolean isPeriodic = false;
            var boardState = new HashSet<Move>();
            for (int i = 0; i < 3; ++i) {
                boardState.add(new Move(new Position(i, 0), Mark.NOUGHT));
            }
            for (int i = 3; i < 5; ++i) {
                boardState.add(new Move(new Position(i, 0), Mark.CROSS));
            }
            new BoardStateValidatorTestData(boardState, Mark.CROSS, Mark.NOUGHT, boardSize, isPeriodic);

            Assertions.fail("WrongBoardStateException should be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void CaseSomeoneAllreadyWon1() {
        try {
            final int boardSize = 10;
            final boolean isPeriodic = true;
            var boardState = new HashSet<Move>();
            int[] cols = {7, 8, 9, 0, 1};
            for (int col : cols)
                boardState.add(new Move(new Position(col, 0), Mark.CROSS));
            for (int i = 0; i < 4; i++)
                boardState.add(new Move(new Position(i, 9), Mark.NOUGHT));
            new BoardStateValidatorTestData(boardState, Mark.CROSS, Mark.NOUGHT, boardSize, isPeriodic);

            Assertions.fail("TheWinnerIsException should be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void CaseSomeoneAllreadyWon2() {
        try {
            final int boardSize = 10;
            final boolean isPeriodic = true;
            var boardState = new HashSet<Move>();
            boardState.add(new Move(new Position(0, 0), Mark.CROSS));
            boardState.add(new Move(new Position(1, 1), Mark.CROSS));
            boardState.add(new Move(new Position(2, 2), Mark.CROSS));
            boardState.add(new Move(new Position(3, 3), Mark.CROSS));
            boardState.add(new Move(new Position(4, 4), Mark.CROSS));

            boardState.add(new Move(new Position(0, 9), Mark.NOUGHT));
            boardState.add(new Move(new Position(1, 9), Mark.NOUGHT));
            boardState.add(new Move(new Position(2, 9), Mark.NOUGHT));
            boardState.add(new Move(new Position(3, 9), Mark.NOUGHT));
            new BoardStateValidatorTestData(boardState, Mark.CROSS, Mark.NOUGHT, boardSize, isPeriodic);

            Assertions.fail("TheWinnerIsException should be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void CaseSomeoneAllreadyWon3() {
        try {
            final int boardSize = 10;
            final boolean isPeriodic = true;
            var boardState = new HashSet<Move>();
            boardState.add(new Move(new Position(2, 4), Mark.CROSS));
            boardState.add(new Move(new Position(3, 3), Mark.CROSS));
            boardState.add(new Move(new Position(4, 2), Mark.CROSS));
            boardState.add(new Move(new Position(5, 1), Mark.CROSS));
            boardState.add(new Move(new Position(6, 0), Mark.CROSS));

            boardState.add(new Move(new Position(0, 9), Mark.NOUGHT));
            boardState.add(new Move(new Position(1, 9), Mark.NOUGHT));
            boardState.add(new Move(new Position(2, 9), Mark.NOUGHT));
            boardState.add(new Move(new Position(3, 9), Mark.NOUGHT));
            new BoardStateValidatorTestData(boardState, Mark.CROSS, Mark.NOUGHT, boardSize, isPeriodic);

            Assertions.fail("TheWinnerIsException should be thrown");
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void doubleThrees() {
        for (int rot = 0; rot < 4; rot++) {
            doubleThrees(rot);
        }
    }

    public void doubleThrees(int rot) {
        try {
            Set<Move> moves = Set.of(
                    new Move(new Position(1, 1), Mark.CROSS),
                    new Move(new Position(5, 0), Mark.NOUGHT),
                    new Move(new Position(3, 3), Mark.CROSS),
                    new Move(new Position(5, 1), Mark.NOUGHT),
                    new Move(new Position(2, 4), Mark.CROSS),
                    new Move(new Position(5, 4), Mark.NOUGHT),
                    new Move(new Position(1, 5), Mark.CROSS),
                    new Move(new Position(5, 5), Mark.NOUGHT)
            );

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expectedMoveOption1 = new Move(new Position(2, 2), Mark.CROSS);
            Move exp = TestUtil.rotate(expectedMoveOption1, rot, size);
            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }
//
//    @Test
//    public void doubleThrees90() {
//        for (int rot = 0; rot < 4; rot++) {
//            doubleThrees90(rot);
//        }
//    }

    public void doubleThrees90(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', 'X', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', 'X', '.', '.', '.', 'O', '.'},
                    {'.', 'X', '.', '.', '.', 'X', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', 'O', '.', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expectedMoveOption1 = new Move(new Position(2, 7), Mark.CROSS);
            Move exp = TestUtil.rotate(expectedMoveOption1, rot, size);
            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException was thrown");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void dcTest() {
        for (int rot = 0; rot < 4; rot++) {
            dcTest(rot);
        }
    }

    public void dcTest(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', 'O', '.', '.', 'O', '.', '.', '.'},
                    {'.', '.', '.', 'O', '.', '.', 'O', '.', '.', '.'},
                    {'.', 'O', 'O', '.', '.', '.', '.', 'O', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', 'X', '.', 'X', '.', '.', 'X'},
                    {'.', 'X', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', 'X', '.', 'X', '.', '.', 'X', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expectedMoveOption1 = new Move(new Position(2, 7), Mark.CROSS);
            Move exp = TestUtil.rotate(expectedMoveOption1, rot, size);
            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.assertTrue(true);
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void dc2Test() {
        for (int rot = 0; rot < 4; rot++) {
            dc2Test(rot);
        }
    }

    public void dc2Test(int rot) {
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', 'O', 'O', 'O', 'X', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'X', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', 'O', 'O', 'O', 'X', '.', '.', 'X', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            //todo shouldnt be thrown without periodci

            Assertions.assertTrue(true);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.fail("ResignException should be thrown");
        } catch (TheWinnerIsException e) {
            Assertions.fail("ResignException should be thrown");
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }


    @Test
    public void test() {
        for (int rot = 0; rot < 4; rot++) {
            test(rot);
        }
    }

    public void test(int rot) {
//        OO_O
        try {
            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'O', '.', 'O', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', 'O', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', 'X', 'X', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', 'X', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', 'X', 'X', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', 'O', '.'}
            });

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            int size = 10;
            gomoku.size(size);
            Move nextMove = gomoku.nextMove(TestUtil.rotateMoves(moves, rot, size), Mark.CROSS);

            Move expected = new Move(new Position(6, 6), Mark.CROSS);
            Move exp = TestUtil.rotate(expected, rot, size);

            assertEquals(exp, nextMove);
        } catch (WrongBoardStateException e) {
            Assertions.fail("WrongBoardStateException was thrown");
        } catch (ResignException e) {
            Assertions.assertTrue(true);
        } catch (TheWinnerIsException e) {
            Assertions.fail("TheWinnerIsException was thrown" + e.mark);
        } catch (Exception e) {
            Assertions.fail("Exception was thrown");
        }
    }

    private static class BoardStateValidatorTestData {
        public BoardStateValidatorTestData(HashSet<Move> boardState, Mark firstMark, Mark currentMoveMark, int boardSize, boolean isPeriodic) throws WrongBoardStateException, TheWinnerIsException, ResignException {
            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(firstMark);
            gomoku.size(boardSize);
            if (isPeriodic) {
                gomoku.periodicBoundaryConditionsInUse();
            }
            Move nextMove = gomoku.nextMove(boardState, currentMoveMark);
            System.out.println("nextMove: " + nextMove);
            Assertions.fail();
        }
    }
}