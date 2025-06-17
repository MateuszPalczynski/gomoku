import fais.zti.oramus.gomoku.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class Gomoku4Test {

    /* periodic = false */
    @Test
    public void validator1() {
        /* kolejność ruchów */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(0, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(0, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 5), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.CROSS);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("2");
        } catch (TheWinnerIsException e) {
            Assertions.fail("3");
        }
    }

    @Test
    public void validator2() {
        /* kolejność ruchów */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(0, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(0, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.CROSS);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("2");
        } catch (TheWinnerIsException e) {
            Assertions.fail("3");
        }
    }

    @Test
    public void validator3() {
        /* kolejność ruchów */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(0, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(0, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("2");
        } catch (TheWinnerIsException e) {
            Assertions.fail("3");
        }
    }

    // #################################################

    @Test
    public void validator4() {
        /* wymiary planszy */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(0, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(0, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 15), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("2");
        } catch (TheWinnerIsException e) {
            Assertions.fail("3");
        }
    }

    @Test
    public void validator5() {
        /* wymiary planszy */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(0, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(0, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(15, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("2");
        } catch (TheWinnerIsException e) {
            Assertions.fail("3");
        }
    }

    @Test
    public void validator6() {
        /* wymiary planszy */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(0, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(0, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(-1, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("2");
        } catch (TheWinnerIsException e) {
            Assertions.fail("3");
        }
    }

    @Test
    public void validator7() {
        /* wymiary planszy */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(0, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(0, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(0, -1), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("2");
        } catch (TheWinnerIsException e) {
            Assertions.fail("3");
        }
    }

    // ############################

    /* 2 takie same pozycje */
    @Test
    public void validator8() {
        /* wymiary planszy */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(0, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(0, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("2");
        } catch (TheWinnerIsException e) {
            Assertions.fail("3");
        }
    }

    @Test
    public void validator9() {
        /* wymiary planszy */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(0, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 5), Mark.CROSS));
            moves.add(new Move(new Position(0, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.assertTrue(true);
        } catch (ResignException e) {
            Assertions.fail("2");
        } catch (TheWinnerIsException e) {
            Assertions.fail("3");
        }
    }

    // ##############################

    @Test
    public void validator10() {
        /* wygrana pozycja na dzień dobry ukos1*/
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(7, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 1), Mark.CROSS));
            moves.add(new Move(new Position(7, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 2), Mark.CROSS));
            moves.add(new Move(new Position(7, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 3), Mark.CROSS));
            moves.add(new Move(new Position(7, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(4, 4), Mark.CROSS));
            moves.add(new Move(new Position(8, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(5, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void validator11() {
        /* wygrana pozycja na dzień dobry ukos2*/
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(8, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 5), Mark.CROSS));
            moves.add(new Move(new Position(7, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 4), Mark.CROSS));
            moves.add(new Move(new Position(7, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 3), Mark.CROSS));
            moves.add(new Move(new Position(7, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(4, 2), Mark.CROSS));
            moves.add(new Move(new Position(7, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(5, 1), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void validator12() {
        /* wygrana pozycja na dzień dobry pionowo*/
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(8, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 5), Mark.CROSS));
            moves.add(new Move(new Position(7, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 5), Mark.CROSS));
            moves.add(new Move(new Position(7, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 5), Mark.CROSS));
            moves.add(new Move(new Position(7, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(4, 5), Mark.CROSS));
            moves.add(new Move(new Position(7, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(5, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void validator13() {
        /* wygrana pozycja na dzień dobry poziomo*/
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(8, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 1), Mark.CROSS));
            moves.add(new Move(new Position(7, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 2), Mark.CROSS));
            moves.add(new Move(new Position(7, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 3), Mark.CROSS));
            moves.add(new Move(new Position(7, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 4), Mark.CROSS));
            moves.add(new Move(new Position(7, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        }
    }

    // ##### przeciwnik

    @Test
    public void validator14() {
        /* wygrana pozycja na dzień dobry ukos1*/
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(7, 4), Mark.CROSS));
            moves.add(new Move(new Position(1, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 3), Mark.CROSS));
            moves.add(new Move(new Position(2, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 2), Mark.CROSS));
            moves.add(new Move(new Position(3, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 1), Mark.CROSS));
            moves.add(new Move(new Position(4, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(8, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.CROSS);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void validator15() {
        /* wygrana pozycja na dzień dobry ukos2*/
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(8, 3), Mark.CROSS));
            moves.add(new Move(new Position(4, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 1), Mark.CROSS));
            moves.add(new Move(new Position(1, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 2), Mark.CROSS));
            moves.add(new Move(new Position(2, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 3), Mark.CROSS));
            moves.add(new Move(new Position(3, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.CROSS);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void validator16() {
        /* wygrana pozycja na dzień dobry pionowo*/
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(8, 3), Mark.CROSS));
            moves.add(new Move(new Position(1, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 1), Mark.CROSS));
            moves.add(new Move(new Position(2, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 2), Mark.CROSS));
            moves.add(new Move(new Position(3, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 3), Mark.CROSS));
            moves.add(new Move(new Position(4, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.CROSS);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void validator17() {
        /* wygrana pozycja na dzień dobry poziomo*/
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(8, 3), Mark.CROSS));
            moves.add(new Move(new Position(1, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 1), Mark.CROSS));
            moves.add(new Move(new Position(1, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 2), Mark.CROSS));
            moves.add(new Move(new Position(1, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 3), Mark.CROSS));
            moves.add(new Move(new Position(1, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(7, 4), Mark.CROSS));
            moves.add(new Move(new Position(1, 5), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, Mark.CROSS);

            Assertions.fail("1");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.assertTrue(true);
        }
    }

    // koniec poczatkowej walidacji
    // ###########################

    @Test
    public void validator18() {
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 1), Mark.CROSS));
            moves.add(new Move(new Position(6, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 2), Mark.CROSS));
            moves.add(new Move(new Position(7, 2), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 3), Mark.CROSS));
            moves.add(new Move(new Position(6, 3), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 4), Mark.CROSS));
            moves.add(new Move(new Position(8, 3), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.CROSS);

            Assertions.assertEquals(new Move(new Position(4, 5), Mark.CROSS), next);

        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    // ###

    @Test
    public void validator19() {
        // zablokuj otwartą trojke przeciwnika 1
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 1), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 2), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.assertEquals(new Move(new Position(3, 3), Mark.NOUGHT), next);

        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator20() {
        // zablokuj otwarta trojke przeciwnika 2
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 5), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.assertEquals(new Move(new Position(3, 3), Mark.NOUGHT), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator21() { // todo CHECK
        // zablokuj otwarta trojke przeciwnika 3
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

//            Assertions.assertEquals(new Move(new Position(4, 5), Mark.NOUGHT), next);
            Assertions.assertEquals(new Move(new Position(2, 4), Mark.NOUGHT), next); //todo should be 2, 4
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    // ####

    @Test
    public void validator22() {
        // zagraj otwartą trojke 1
        try {
//            Set<Move> moves = new HashSet<>();
//            moves.add(new Move(new Position(1, 1), Mark.CROSS));
//            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
//            moves.add(new Move(new Position(2, 2), Mark.CROSS));
//            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
//            moves.add(new Move(new Position(2, 4), Mark.CROSS));
//            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
//            moves.add(new Move(new Position(1, 5), Mark.CROSS));
//            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));

            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', 'X', '.', '.', '.', 'X', '.', '.', '.', '.'},
                    {'.', '.', 'X', '.', 'X', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'O', 'O', '.', '.', 'O', 'O', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });


            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.CROSS);

            Assertions.assertEquals(new Move(new Position(3, 3), Mark.CROSS), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator23() {
        // zagraj otwarta trojke 2
        try {
//            Set<Move> moves = new HashSet<>();
//            moves.add(new Move(new Position(2, 4), Mark.CROSS));
//            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
//            moves.add(new Move(new Position(3, 4), Mark.CROSS));
//            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
//            moves.add(new Move(new Position(1, 5), Mark.CROSS));
//            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
//            moves.add(new Move(new Position(3, 5), Mark.CROSS));
//            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));

            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', 'X', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', 'X', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', 'X', 'X', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'O', 'O', '.', '.', 'O', 'O', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });


            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.CROSS);

            Assertions.assertEquals(new Move(new Position(3, 3), Mark.CROSS), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator24() {
        // zagraj otwarta trojke 3
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(2, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 5), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.CROSS);

            Assertions.assertEquals(new Move(new Position(2, 4), Mark.CROSS), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }


    @Test
    public void validator25() { //todo CHECk
        // gra O, poddaj otwarta 3, 1
        try {
//            Set<Move> moves = new HashSet<>();
//            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
//            moves.add(new Move(new Position(1, 4), Mark.CROSS));
//            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
//            moves.add(new Move(new Position(1, 5), Mark.CROSS));
//            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
//            moves.add(new Move(new Position(2, 5), Mark.CROSS));
//            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
//            moves.add(new Move(new Position(1, 6), Mark.CROSS));
//            moves.add(new Move(new Position(5, 6), Mark.NOUGHT));
//            moves.add(new Move(new Position(3, 6), Mark.CROSS));

            Set<Move> moves = TestUtil.tableToSet(new char[][]{
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', 'X', 'X', 'X', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', 'X', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', 'X', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'O', 'O', '.', '.', 'O', 'O', 'O', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
            });


            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.assertEquals(new Move(new Position(5, 3), Mark.NOUGHT), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }


    @Test
    public void validator26() { //todo CHECK - should resign
        // gra O, zablokuj otwarta 4
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 5), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 6), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(4, 7), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("3");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.assertTrue(true);
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator27() {
        // gra X, zagraj otwarta 4
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(1, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 5), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 6), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(4, 7), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.CROSS);

//            Assertions.assertEquals(new Move(new Position(5, 8), Mark.NOUGHT), next); next mark is cross
            Assertions.assertEquals(new Move(new Position(5, 8), Mark.CROSS), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator28() {
        // gra O, poddaj, bo otwarta 4
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 5), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 6), Mark.CROSS));
            moves.add(new Move(new Position(5, 6), Mark.NOUGHT));
            moves.add(new Move(new Position(4, 7), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.fail("3");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.assertTrue(true);
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator29() {
        // gra X, najlepszy ruch zwiekszyc do 4
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 5), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 6), Mark.CROSS));
            moves.add(new Move(new Position(5, 6), Mark.NOUGHT));
            moves.add(new Move(new Position(4, 7), Mark.CROSS));
            moves.add(new Move(new Position(5, 8), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, Mark.CROSS);

            Assertions.assertEquals(new Move(new Position(5, 7), Mark.CROSS), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    // ######################################################################################################
    // ######################################################################################################
    // ######################################################################################################
    // ######################################################################################################
    // ### periodyczne warunki brzegowe...


    @Test
    public void validator30() { // todo CHECK
        // gra X, wygrana x
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(8, 0), Mark.CROSS));
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(9, 1), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 2), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 8), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.periodicBoundaryConditionsInUse();
            gomoku.size(10);
            Move next = gomoku.nextMove(moves, Mark.CROSS);

//            Assertions.assertEquals(new Move(new Position(2, 4), Mark.CROSS), next);
            Assertions.assertEquals(new Move(new Position(7, 9), Mark.CROSS), next); //todo could be more moves
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    // ###

    @Test
    public void validator31() { // ??
        // gra X, zagraj otwartą trojke 1
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(9, 1), Mark.CROSS));
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 2), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(9, 5), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.periodicBoundaryConditionsInUse();
            gomoku.size(10);
            Move next = gomoku.nextMove(moves, Mark.CROSS);

            Assertions.assertEquals(new Move(new Position(1, 3), Mark.CROSS), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator32() {
        // gra X, zagraj trojke open 2
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(9, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(8, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.periodicBoundaryConditionsInUse();
            gomoku.size(10);
            Move next = gomoku.nextMove(moves, Mark.CROSS);

            Assertions.assertEquals(new Move(new Position(0, 2), Mark.CROSS), next);

        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator33() {
        // gra X, zagraj otwarta trojke 3
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 2), Mark.CROSS));
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(9, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.CROSS);
            gomoku.size(10);
            gomoku.periodicBoundaryConditionsInUse();
            Move next = gomoku.nextMove(moves, Mark.CROSS);

//            Assertions.assertEquals(new Move(new Position(0, 4), Mark.CROSS), next);
            Assertions.assertEquals(new Move(new Position(0, 3), Mark.CROSS), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    // ####

    @Test
    public void validator34() {
        // gra O, zablokuj otwartą trojke 1
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(9, 1), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 2), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(9, 5), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(10);
            gomoku.periodicBoundaryConditionsInUse();
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.assertEquals(new Move(new Position(1, 3), Mark.NOUGHT), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator35() {
        // gra X, zablokuj otwarta trojke 2
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(9, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 4), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(8, 4), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(10);
            gomoku.periodicBoundaryConditionsInUse();
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.assertEquals(new Move(new Position(0, 2), Mark.NOUGHT), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator36() {
        // gra O, zablokuj otwarta trojke 3
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 2), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(9, 3), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(0, 4), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(10);
            gomoku.periodicBoundaryConditionsInUse();
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.assertEquals(new Move(new Position(0, 3), Mark.NOUGHT), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    // ######### inne periodic trójki
    // ######### inne periodic trójki
    // ######### inne periodic trójki
    // ######### inne periodic trójki


    @Test
    public void validator42() {
        // gra O, zablokuj otwarta trojke 1 gora-dol
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 0), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 1), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 7), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 8), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(10);
            gomoku.periodicBoundaryConditionsInUse();
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.assertEquals(new Move(new Position(3, 9), Mark.NOUGHT), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator43() { // ??
        // gra O, zablokuj otwarta trojke 2 gora-dol
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(1, 0), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 0), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 9), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 9), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(10);
            gomoku.periodicBoundaryConditionsInUse();
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.assertEquals(new Move(new Position(3, 8), Mark.NOUGHT), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator44() {
        // gra O, zablokuj otwarta trojke 3 gora-dol
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 0), Mark.CROSS));
            moves.add(new Move(new Position(5, 1), Mark.NOUGHT));
            moves.add(new Move(new Position(3, 8), Mark.CROSS));
            moves.add(new Move(new Position(5, 4), Mark.NOUGHT));
            moves.add(new Move(new Position(2, 9), Mark.CROSS));
            moves.add(new Move(new Position(5, 5), Mark.NOUGHT));
            moves.add(new Move(new Position(4, 9), Mark.CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(Mark.NOUGHT);
            gomoku.size(10);
            gomoku.periodicBoundaryConditionsInUse();
            Move next = gomoku.nextMove(moves, Mark.NOUGHT);

            Assertions.assertEquals(new Move(new Position(3, 9), Mark.NOUGHT), next);
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    // ######### inne periodic trójki koniec
    // ######### inne periodic trójki koniec
    // ######### inne periodic trójki koniec
    // ######### inne periodic trójki koniec
    // ######### inne periodic trójki koniec


}
