import fais.zti.oramus.gomoku.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static fais.zti.oramus.gomoku.Mark.CROSS;
import static fais.zti.oramus.gomoku.Mark.NOUGHT;


public class Gomoku2Test {
    private Position rotate(Position pos, int rotation, int size) {
        int x = pos.col();
        int y = pos.row();
        return switch (rotation % 4) {
            case 1 -> // 90°
                    new Position(y, size - 1 - x);
            case 2 -> // 180°
                    new Position(size - 1 - x, size - 1 - y);
            case 3 -> // 270°
                    new Position(size - 1 - y, x);
            default -> // 0°
                    pos;
        };
    }

    private Move rotate(Move move, int rotation, int size) {
        Position p = rotate(move.position(), rotation, size);
        return new Move(p, move.mark());
    }

    private Set<Move> rotateMoves(Set<Move> moves, int rotation, int size) {
        Set<Move> rotated = new HashSet<>();
        for (Move m : moves) rotated.add(rotate(m, rotation, size));
        System.out.println(rotated);
        return rotated;
    }

    /* periodic = false */
    @Test
    public void validator1() {
        /* kolejność ruchów */
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(0, 1), CROSS));
            moves.add(new Move(new Position(0, 2), NOUGHT));
            moves.add(new Move(new Position(0, 3), CROSS));
            moves.add(new Move(new Position(0, 4), NOUGHT));
            moves.add(new Move(new Position(0, 5), NOUGHT));

            for (int rot = 0; rot < 4; rot++) {
                Gomoku gomoku = new Gomoku();
                gomoku.firstMark(CROSS);
                gomoku.size(15);
                gomoku.nextMove(moves, CROSS);
            }

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
            moves.add(new Move(new Position(0, 1), CROSS));
            moves.add(new Move(new Position(0, 2), NOUGHT));
            moves.add(new Move(new Position(0, 3), CROSS));
            moves.add(new Move(new Position(0, 4), NOUGHT));
            moves.add(new Move(new Position(0, 5), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, CROSS);

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
            moves.add(new Move(new Position(0, 1), CROSS));
            moves.add(new Move(new Position(0, 2), NOUGHT));
            moves.add(new Move(new Position(0, 3), CROSS));
            moves.add(new Move(new Position(0, 4), NOUGHT));
            moves.add(new Move(new Position(0, 5), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(0, 1), CROSS));
            moves.add(new Move(new Position(0, 2), NOUGHT));
            moves.add(new Move(new Position(0, 3), CROSS));
            moves.add(new Move(new Position(0, 4), NOUGHT));
            moves.add(new Move(new Position(0, 15), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(0, 1), CROSS));
            moves.add(new Move(new Position(0, 2), NOUGHT));
            moves.add(new Move(new Position(0, 3), CROSS));
            moves.add(new Move(new Position(0, 4), NOUGHT));
            moves.add(new Move(new Position(15, 5), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(0, 1), CROSS));
            moves.add(new Move(new Position(0, 2), NOUGHT));
            moves.add(new Move(new Position(0, 3), CROSS));
            moves.add(new Move(new Position(0, 4), NOUGHT));
            moves.add(new Move(new Position(-1, 5), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(0, 1), CROSS));
            moves.add(new Move(new Position(0, 2), NOUGHT));
            moves.add(new Move(new Position(0, 3), CROSS));
            moves.add(new Move(new Position(0, 4), NOUGHT));
            moves.add(new Move(new Position(0, -1), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(0, 1), CROSS));
            moves.add(new Move(new Position(0, 2), NOUGHT));
            moves.add(new Move(new Position(0, 3), CROSS));
            moves.add(new Move(new Position(0, 5), NOUGHT));
            moves.add(new Move(new Position(0, 5), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(0, 1), CROSS));
            moves.add(new Move(new Position(0, 2), NOUGHT));
            moves.add(new Move(new Position(0, 5), CROSS));
            moves.add(new Move(new Position(0, 4), NOUGHT));
            moves.add(new Move(new Position(0, 5), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(7, 4), NOUGHT));
            moves.add(new Move(new Position(1, 1), CROSS));
            moves.add(new Move(new Position(7, 3), NOUGHT));
            moves.add(new Move(new Position(2, 2), CROSS));
            moves.add(new Move(new Position(7, 2), NOUGHT));
            moves.add(new Move(new Position(3, 3), CROSS));
            moves.add(new Move(new Position(7, 1), NOUGHT));
            moves.add(new Move(new Position(4, 4), CROSS));
            moves.add(new Move(new Position(8, 3), NOUGHT));
            moves.add(new Move(new Position(5, 5), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(8, 3), NOUGHT));
            moves.add(new Move(new Position(1, 5), CROSS));
            moves.add(new Move(new Position(7, 1), NOUGHT));
            moves.add(new Move(new Position(2, 4), CROSS));
            moves.add(new Move(new Position(7, 2), NOUGHT));
            moves.add(new Move(new Position(3, 3), CROSS));
            moves.add(new Move(new Position(7, 3), NOUGHT));
            moves.add(new Move(new Position(4, 2), CROSS));
            moves.add(new Move(new Position(7, 4), NOUGHT));
            moves.add(new Move(new Position(5, 1), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(8, 3), NOUGHT));
            moves.add(new Move(new Position(1, 5), CROSS));
            moves.add(new Move(new Position(7, 1), NOUGHT));
            moves.add(new Move(new Position(2, 5), CROSS));
            moves.add(new Move(new Position(7, 2), NOUGHT));
            moves.add(new Move(new Position(3, 5), CROSS));
            moves.add(new Move(new Position(7, 3), NOUGHT));
            moves.add(new Move(new Position(4, 5), CROSS));
            moves.add(new Move(new Position(7, 4), NOUGHT));
            moves.add(new Move(new Position(5, 5), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(8, 3), NOUGHT));
            moves.add(new Move(new Position(1, 1), CROSS));
            moves.add(new Move(new Position(7, 1), NOUGHT));
            moves.add(new Move(new Position(1, 2), CROSS));
            moves.add(new Move(new Position(7, 2), NOUGHT));
            moves.add(new Move(new Position(1, 3), CROSS));
            moves.add(new Move(new Position(7, 3), NOUGHT));
            moves.add(new Move(new Position(1, 4), CROSS));
            moves.add(new Move(new Position(7, 4), NOUGHT));
            moves.add(new Move(new Position(1, 5), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(NOUGHT);
            gomoku.size(15);
            gomoku.nextMove(moves, NOUGHT);

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
            moves.add(new Move(new Position(7, 4), CROSS));
            moves.add(new Move(new Position(1, 1), NOUGHT));
            moves.add(new Move(new Position(7, 3), CROSS));
            moves.add(new Move(new Position(2, 2), NOUGHT));
            moves.add(new Move(new Position(7, 2), CROSS));
            moves.add(new Move(new Position(3, 3), NOUGHT));
            moves.add(new Move(new Position(7, 1), CROSS));
            moves.add(new Move(new Position(4, 4), NOUGHT));
            moves.add(new Move(new Position(8, 3), CROSS));
            moves.add(new Move(new Position(5, 5), NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, CROSS);

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
            moves.add(new Move(new Position(8, 3), CROSS));
            moves.add(new Move(new Position(4, 2), NOUGHT));
            moves.add(new Move(new Position(7, 1), CROSS));
            moves.add(new Move(new Position(1, 5), NOUGHT));
            moves.add(new Move(new Position(7, 2), CROSS));
            moves.add(new Move(new Position(2, 4), NOUGHT));
            moves.add(new Move(new Position(7, 3), CROSS));
            moves.add(new Move(new Position(3, 3), NOUGHT));
            moves.add(new Move(new Position(7, 4), CROSS));
            moves.add(new Move(new Position(5, 1), NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, CROSS);

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
            moves.add(new Move(new Position(8, 3), CROSS));
            moves.add(new Move(new Position(1, 5), NOUGHT));
            moves.add(new Move(new Position(7, 1), CROSS));
            moves.add(new Move(new Position(2, 5), NOUGHT));
            moves.add(new Move(new Position(7, 2), CROSS));
            moves.add(new Move(new Position(3, 5), NOUGHT));
            moves.add(new Move(new Position(7, 3), CROSS));
            moves.add(new Move(new Position(4, 5), NOUGHT));
            moves.add(new Move(new Position(7, 4), CROSS));
            moves.add(new Move(new Position(5, 5), NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, CROSS);

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
            moves.add(new Move(new Position(8, 3), CROSS));
            moves.add(new Move(new Position(1, 1), NOUGHT));
            moves.add(new Move(new Position(7, 1), CROSS));
            moves.add(new Move(new Position(1, 2), NOUGHT));
            moves.add(new Move(new Position(7, 2), CROSS));
            moves.add(new Move(new Position(1, 3), NOUGHT));
            moves.add(new Move(new Position(7, 3), CROSS));
            moves.add(new Move(new Position(1, 4), NOUGHT));
            moves.add(new Move(new Position(7, 4), CROSS));
            moves.add(new Move(new Position(1, 5), NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            gomoku.nextMove(moves, CROSS);

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
    public void validator27() {
        // gra X, zagraj otwarta 4
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(5, 0), NOUGHT));
            moves.add(new Move(new Position(1, 4), CROSS));
            moves.add(new Move(new Position(5, 1), NOUGHT));
            moves.add(new Move(new Position(2, 5), CROSS));
            moves.add(new Move(new Position(5, 4), NOUGHT));
            moves.add(new Move(new Position(3, 6), CROSS));
            moves.add(new Move(new Position(5, 5), NOUGHT));
            moves.add(new Move(new Position(4, 7), CROSS));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(CROSS);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, CROSS);

            Assertions.assertEquals(next, new Move(new Position(5, 8), CROSS));
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }

    @Test
    public void validator18AllRotations() {
        Set<Move> base = new HashSet<>();
        base.add(new Move(new Position(5, 0), NOUGHT));
        base.add(new Move(new Position(0, 1), CROSS));
        base.add(new Move(new Position(6, 1), NOUGHT));
        base.add(new Move(new Position(1, 2), CROSS));
        base.add(new Move(new Position(7, 2), NOUGHT));
        base.add(new Move(new Position(2, 3), CROSS));
        base.add(new Move(new Position(6, 3), NOUGHT));
        base.add(new Move(new Position(3, 4), CROSS));
        base.add(new Move(new Position(8, 3), NOUGHT));
        Move expected = new Move(new Position(4, 5), CROSS);
        int size = 10;
        for (int rot = 0; rot < 4; rot++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size);
            try {
//                System.out.println(g);
                Move next = g.nextMove(rotateMoves(base, rot, size), CROSS);
                Move exp = rotate(expected, rot, size);
                System.out.println(exp);
                Assertions.assertEquals(exp, next, "Rotation " + rot * 90 + "° failed");
            } catch (Exception e) {
                Assertions.fail("Unexpected " + e.getClass().getSimpleName() + " at rotation=" + rot);
            }
        }
    }

    @Test
    public void validator19AllRotations() {
        Set<Move> base = new HashSet<>();
        base.add(new Move(new Position(5, 0), NOUGHT));
        base.add(new Move(new Position(1, 1), CROSS));
        base.add(new Move(new Position(5, 1), NOUGHT));
        base.add(new Move(new Position(2, 2), CROSS));
        base.add(new Move(new Position(5, 4), NOUGHT));
        base.add(new Move(new Position(2, 4), CROSS));
        base.add(new Move(new Position(5, 5), NOUGHT));
        base.add(new Move(new Position(1, 5), CROSS));
        Move expected = new Move(new Position(3, 3), NOUGHT);
        int size = 10;
        for (int rot = 0; rot < 4; rot++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size);
            try {
                Move next = g.nextMove(rotateMoves(base, rot, size), NOUGHT);
                Move exp = rotate(expected, rot, size);
                Assertions.assertEquals(exp, next, "Rotation " + rot * 90 + "° failed");
            } catch (Exception e) {
                Assertions.fail("Unexpected " + e.getClass().getSimpleName() + " at rotation=" + rot);
            }
        }
    }

    @Test
    public void validator20AllRotations() {
        Set<Move> base = new HashSet<>();
        base.add(new Move(new Position(5, 0), NOUGHT));
        base.add(new Move(new Position(2, 4), CROSS));
        base.add(new Move(new Position(5, 1), NOUGHT));
        base.add(new Move(new Position(3, 4), CROSS));
        base.add(new Move(new Position(5, 4), NOUGHT));
        base.add(new Move(new Position(1, 5), CROSS));
        base.add(new Move(new Position(5, 5), NOUGHT));
        base.add(new Move(new Position(3, 5), CROSS));
        Move expected = new Move(new Position(3, 3), NOUGHT);
        int size = 10;
        for (int rot = 0; rot < 4; rot++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size);
            try {
                Move next = g.nextMove(rotateMoves(base, rot, size), NOUGHT);
                Move exp = rotate(expected, rot, size);
                Assertions.assertEquals(exp, next, "Rotation " + rot * 90 + "° failed");
            } catch (Exception e) {
                Assertions.fail("Unexpected " + e.getClass().getSimpleName() + " at rotation=" + rot);
            }
        }
    }

    @Test
    public void validator21AllRotations() {
        Set<Move> base = new HashSet<>();
        base.add(new Move(new Position(5, 0), NOUGHT));
        base.add(new Move(new Position(2, 3), CROSS));
        base.add(new Move(new Position(5, 1), NOUGHT));
        base.add(new Move(new Position(1, 4), CROSS));
        base.add(new Move(new Position(5, 4), NOUGHT));
        base.add(new Move(new Position(3, 4), CROSS));
        base.add(new Move(new Position(5, 5), NOUGHT));
        base.add(new Move(new Position(2, 5), CROSS));
        Move expected = new Move(new Position(2, 4), NOUGHT);
        int size = 10;
        for (int rot = 0; rot < 4; rot++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size);
            try {
                Move next = g.nextMove(rotateMoves(base, rot, size), NOUGHT);
                Move exp = rotate(expected, rot, size);
                Assertions.assertEquals(exp, next, "Rotation " + rot * 90 + "° failed");
            } catch (Exception e) {
                Assertions.fail("Unexpected " + e.getClass().getSimpleName() + " at rotation=" + rot);
            }
        }
    }

    @Test
    public void validator22AllRotations() {
        Set<Move> base = new HashSet<>();
        base.add(new Move(new Position(1, 1), CROSS));
        base.add(new Move(new Position(5, 0), NOUGHT));
        base.add(new Move(new Position(2, 2), CROSS));
        base.add(new Move(new Position(5, 1), NOUGHT));
        base.add(new Move(new Position(2, 4), CROSS));
        base.add(new Move(new Position(5, 4), NOUGHT));
        base.add(new Move(new Position(1, 5), CROSS));
        base.add(new Move(new Position(5, 5), NOUGHT));
        Move expected = new Move(new Position(3, 3), CROSS);
        int size = 10;
        for (int rot = 0; rot < 4; rot++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.size(size);
            try {
                Move next = g.nextMove(rotateMoves(base, rot, size), CROSS);
                Move exp = rotate(expected, rot, size);
                Assertions.assertEquals(exp, next, "Rotation " + rot * 90 + "° failed");
            } catch (Exception e) {
                Assertions.fail("Unexpected " + e.getClass().getSimpleName() + " at rotation=" + rot);
            }
        }
    }

    @Test
    public void validator23AllRotations() {
        Set<Move> base = new HashSet<>();
        base.add(new Move(new Position(2, 4), CROSS));
        base.add(new Move(new Position(5, 0), NOUGHT));
        base.add(new Move(new Position(3, 4), CROSS));
        base.add(new Move(new Position(5, 1), NOUGHT));
        base.add(new Move(new Position(1, 5), CROSS));
        base.add(new Move(new Position(5, 4), NOUGHT));
        base.add(new Move(new Position(3, 5), CROSS));
        base.add(new Move(new Position(5, 5), NOUGHT));
        Move expected = new Move(new Position(3, 3), CROSS);
        int size = 10;
        for (int rot = 0; rot < 4; rot++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.size(size);
            try {
                Move next = g.nextMove(rotateMoves(base, rot, size), CROSS);
                Move exp = rotate(expected, rot, size);
                Assertions.assertEquals(exp, next, "Rotation " + rot * 90 + "° failed");
            } catch (Exception e) {
                Assertions.fail("Unexpected " + e.getClass().getSimpleName() + " at rotation=" + rot);
            }
        }
    }

    @Test
    public void validator24AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(2, 3), CROSS), new Move(new Position(5, 0), NOUGHT),
                new Move(new Position(1, 4), CROSS), new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(3, 4), CROSS), new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(2, 5), CROSS), new Move(new Position(5, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(2, 4), CROSS);
        int size24 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.size(size24);
            Move got = g.nextMove(rotateMoves(base, r, size24), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size24), got);
        }
    }

    @Test
    public void validator25AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(5, 0), NOUGHT), new Move(new Position(1, 4), CROSS),
                new Move(new Position(5, 1), NOUGHT), new Move(new Position(1, 5), CROSS),
                new Move(new Position(5, 4), NOUGHT), new Move(new Position(2, 5), CROSS),
                new Move(new Position(5, 5), NOUGHT), new Move(new Position(1, 6), CROSS),
                new Move(new Position(5, 6), NOUGHT), new Move(new Position(3, 6), CROSS)
        );
        Move exp0 = new Move(new Position(5, 7), NOUGHT);
        Move exp1 = new Move(new Position(5, 3), NOUGHT);
        int size25 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size25);
            Move got = g.nextMove(rotateMoves(base, r, size25), NOUGHT);
            Assertions.assertTrue(
                    rotate(exp0, r, size25).equals(got) || rotate(exp1, r, size25).equals(got),
                    "Expected got to match either exp0 or exp1 after rotation");
        }
    }

    @Test
    public void validator26AllRotations() {
        Set<Move> base = Set.of(
                new Move(new Position(5, 0), NOUGHT), new Move(new Position(1, 4), CROSS),
                new Move(new Position(5, 1), NOUGHT), new Move(new Position(2, 5), CROSS),
                new Move(new Position(5, 4), NOUGHT), new Move(new Position(3, 6), CROSS),
                new Move(new Position(5, 5), NOUGHT), new Move(new Position(4, 7), CROSS)
        );
        int size26 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size26);
            int finalR = r;
            Assertions.assertThrows(ResignException.class, () ->
                    g.nextMove(rotateMoves(base, finalR, size26), NOUGHT)
            );
        }
    }

    //    @Test public void validator27AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base=Set.of(
//                new Move(new Position(5,0),NOUGHT), new Move(new Position(1,4),CROSS),
//                new Move(new Position(5,1),NOUGHT), new Move(new Position(2,5),CROSS),
//                new Move(new Position(5,4),NOUGHT), new Move(new Position(3,6),CROSS),
//                new Move(new Position(5,5),NOUGHT), new Move(new Position(4,7),CROSS)
//        ); Move exp0=new Move(new Position(0,3),CROSS); int size27=10; //todo 15
//        for(int r=0;r<4;r++){ Gomoku g=new Gomoku(); g.firstMark(CROSS); g.size(size27);
//            Move got=g.nextMove(rotateMoves(base,r,size27),CROSS);
//            Assertions.assertEquals(rotate(exp0,r,size27),got);
//        }
//    }
    @Test
    public void validator28AllRotations() {
        Set<Move> base = Set.of(
                new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(5, 5), NOUGHT),
                new Move(new Position(5, 6), NOUGHT),
                new Move(new Position(2, 5), CROSS),
                new Move(new Position(1, 4), CROSS),
                new Move(new Position(3, 6), CROSS),
                new Move(new Position(4, 7), CROSS)
        );
        int size28 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size28);
            int finalR = r;
            Assertions.assertThrows(ResignException.class, () ->
                    g.nextMove(rotateMoves(base, finalR, size28), NOUGHT)
            );
        }
    }

    @Test
    public void validator29AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(5, 1), NOUGHT), new Move(new Position(2, 5), CROSS),
                new Move(new Position(5, 5), NOUGHT), new Move(new Position(3, 6), CROSS),
                new Move(new Position(5, 6), NOUGHT), new Move(new Position(4, 7), CROSS),
                new Move(new Position(5, 8), NOUGHT)
        );
        Move exp0 = new Move(new Position(5, 7), CROSS);
        int size29 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size29);
            Move got = g.nextMove(rotateMoves(base, r, size29), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size29), got);
        }
    }

    @Test
    public void validator30AllRotations() {
        Set<Move> base = Set.of(
                new Move(new Position(8, 0), CROSS), new Move(new Position(5, 0), NOUGHT),
                new Move(new Position(9, 1), CROSS), new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(0, 2), CROSS), new Move(new Position(5, 5), NOUGHT),
                new Move(new Position(1, 3), CROSS), new Move(new Position(5, 8), NOUGHT)
        );
        Move exp0 = new Move(new Position(2, 4), CROSS);
        Move exp1 = new Move(new Position(7, 9), CROSS);
        int size30 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.periodicBoundaryConditionsInUse();
            g.size(size30);
            System.out.println("analyzing rotation " + r * 90);
            try {
                Move got = g.nextMove(rotateMoves(base, r, size30), CROSS);
                System.out.println(got);
                Assertions.assertTrue(
                        rotate(exp0, r, size30).equals(got) || rotate(exp1, r, size30).equals(got),
                        "Expected got to match either exp0 or exp1 after rotation"
                );
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Test
    public void validator31AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(9, 1), CROSS), new Move(new Position(5, 0), NOUGHT),
                new Move(new Position(0, 2), CROSS), new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(0, 4), CROSS), new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(9, 5), CROSS), new Move(new Position(5, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(1, 3), CROSS);
        int size31 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.periodicBoundaryConditionsInUse();
            g.size(size31);
            Move got = g.nextMove(rotateMoves(base, r, size31), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size31), got);
        }
    }

    @Test
    public void validator32AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(0, 3), CROSS), new Move(new Position(5, 0), NOUGHT),
                new Move(new Position(9, 3), CROSS), new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(0, 4), CROSS), new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(8, 4), CROSS), new Move(new Position(5, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(0, 2), CROSS);
        int size32 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.periodicBoundaryConditionsInUse();
            g.size(size32);
            Move got = g.nextMove(rotateMoves(base, r, size32), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size32), got);
        }
    }

    @Test
    public void validator33AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(0, 2), CROSS), new Move(new Position(5, 0), NOUGHT),
                new Move(new Position(1, 3), CROSS), new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(9, 3), CROSS), new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(0, 4), CROSS), new Move(new Position(5, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(0, 3), CROSS);
        int size33 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.periodicBoundaryConditionsInUse();
            g.size(size33);
            Move got = g.nextMove(rotateMoves(base, r, size33), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size33), got);
        }
    }

    @Test
    public void validator34AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(5, 0), NOUGHT), new Move(new Position(9, 1), CROSS),
                new Move(new Position(5, 1), NOUGHT), new Move(new Position(0, 2), CROSS),
                new Move(new Position(5, 4), NOUGHT), new Move(new Position(0, 4), CROSS),
                new Move(new Position(5, 5), NOUGHT), new Move(new Position(9, 5), CROSS)
        );
        Move exp0 = new Move(new Position(1, 3), NOUGHT);
        int size34 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.periodicBoundaryConditionsInUse();
            g.size(size34);
            Move got = g.nextMove(rotateMoves(base, r, size34), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size34), got);
        }
    }

    @Test
    public void validator35AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(5, 0), NOUGHT), new Move(new Position(0, 3), CROSS),
                new Move(new Position(5, 1), NOUGHT), new Move(new Position(9, 3), CROSS),
                new Move(new Position(5, 4), NOUGHT), new Move(new Position(0, 4), CROSS),
                new Move(new Position(5, 5), NOUGHT), new Move(new Position(8, 4), CROSS)
        );
        Move exp0 = new Move(new Position(0, 2), NOUGHT);
        int size35 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.periodicBoundaryConditionsInUse();
            g.size(size35);
            Move got = g.nextMove(rotateMoves(base, r, size35), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size35), got);
        }
    }

    @Test
    public void validator36AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(5, 0), NOUGHT), new Move(new Position(0, 2), CROSS),
                new Move(new Position(5, 1), NOUGHT), new Move(new Position(1, 3), CROSS),
                new Move(new Position(5, 4), NOUGHT), new Move(new Position(9, 3), CROSS),
                new Move(new Position(5, 5), NOUGHT), new Move(new Position(0, 4), CROSS)
        );
        Move exp0 = new Move(new Position(0, 3), NOUGHT);
        int size36 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.periodicBoundaryConditionsInUse();
            g.size(size36);
            Move got = g.nextMove(rotateMoves(base, r, size36), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size36), got);
        }
    }

    @Test
    public void validator42AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(5, 0), NOUGHT), new Move(new Position(2, 0), CROSS),
                new Move(new Position(5, 4), NOUGHT), new Move(new Position(1, 1), CROSS),
                new Move(new Position(5, 1), NOUGHT), new Move(new Position(1, 7), CROSS),
                new Move(new Position(5, 5), NOUGHT), new Move(new Position(2, 8), CROSS)
        );
        Move exp0 = new Move(new Position(3, 9), NOUGHT);
        int size42 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.periodicBoundaryConditionsInUse();
            g.size(size42);
            Move got = g.nextMove(rotateMoves(base, r, size42), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size42), got);
        }
    }

    @Test
    public void validator43AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(5, 0), NOUGHT), new Move(new Position(1, 0), CROSS),
                new Move(new Position(5, 1), NOUGHT), new Move(new Position(3, 0), CROSS),
                new Move(new Position(5, 4), NOUGHT), new Move(new Position(2, 9), CROSS),
                new Move(new Position(5, 5), NOUGHT), new Move(new Position(3, 9), CROSS)
        );
        Move exp0 = new Move(new Position(3, 8), NOUGHT);
        int size43 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.periodicBoundaryConditionsInUse();
            g.size(size43);
            Move got = g.nextMove(rotateMoves(base, r, size43), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size43), got);
        }
    }

    @Test
    public void validator44AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(5, 0), NOUGHT), new Move(new Position(3, 0), CROSS),
                new Move(new Position(5, 1), NOUGHT), new Move(new Position(3, 8), CROSS),
                new Move(new Position(5, 4), NOUGHT), new Move(new Position(2, 9), CROSS),
                new Move(new Position(5, 5), NOUGHT), new Move(new Position(4, 9), CROSS)
        );
        Move exp0 = new Move(new Position(3, 9), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    // ############ nowe testy 20.05.2025
    // ############
    // ############

    @Test
    public void validator45AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(5, 0), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(2, 4), CROSS),
                new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(1, 5), CROSS),
                new Move(new Position(5, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(0, 6), CROSS);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    @Test
    public void validator46AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(6, 0), NOUGHT),
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(6, 1), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(2, 4), CROSS),
                new Move(new Position(6, 4), NOUGHT),
                new Move(new Position(1, 5), CROSS),
                new Move(new Position(6, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(0, 6), CROSS);
        Move exp1 = new Move(new Position(4, 2), CROSS);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
            Assertions.assertTrue(
                    rotate(exp0, r, size44).equals(got) || rotate(exp1, r, size44).equals(got),
                    "Expected got to match either exp0 or exp1 after rotation"
            );
//            Assertions.assertEquals(rotate(exp0,r,size44),got);
        }
    }

    @Test
    public void validator47AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(6, 0), NOUGHT),
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(6, 1), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(2, 4), CROSS),
                new Move(new Position(6, 4), NOUGHT),
                new Move(new Position(1, 5), CROSS),
                new Move(new Position(6, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(0, 6), CROSS);
        Move exp1 = new Move(new Position(4, 2), CROSS);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
            Assertions.assertTrue(
                    rotate(exp0, r, size44).equals(got) || rotate(exp1, r, size44).equals(got),
                    "Expected got to match either exp0 or exp1 after rotation"
            );
//            Assertions.assertEquals(rotate(exp0,r,size44),got);
        }
    }

    @Test
    public void validator48AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(5, 0), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(2, 4), CROSS),
                new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(1, 5), CROSS),
                new Move(new Position(5, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(2, 2), CROSS);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }


    //----
    //---- blokowanko
    //----

    @Test
    public void validator495AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(2, 4), CROSS),
                new Move(new Position(1, 5), CROSS),
                new Move(new Position(5, 0), NOUGHT),
                new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(5, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(0, 6), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    @Test
    public void validator50AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(6, 0), NOUGHT),
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(6, 1), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(2, 4), CROSS),
                new Move(new Position(6, 4), NOUGHT),
                new Move(new Position(1, 5), CROSS),
                new Move(new Position(6, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(0, 6), NOUGHT);
        Move exp1 = new Move(new Position(4, 2), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertTrue(
                    rotate(exp0, r, size44).equals(got) || rotate(exp1, r, size44).equals(got),
                    "Expected got to match either exp0 or exp1 after rotation"
            );
//            Assertions.assertEquals(rotate(exp0,r,size44),got);
        }
    }

    @Test
    public void validator51AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(6, 0), NOUGHT),
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(6, 1), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(2, 4), CROSS),
                new Move(new Position(6, 4), NOUGHT),
                new Move(new Position(1, 5), CROSS),
                new Move(new Position(6, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(0, 6), NOUGHT);
        Move exp1 = new Move(new Position(4, 2), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertTrue(
                    rotate(exp0, r, size44).equals(got) || rotate(exp1, r, size44).equals(got),
                    "Expected got to match either exp0 or exp1 after rotation"
            );
//            Assertions.assertEquals(rotate(exp0,r,size44),got);
        }
    }

    @Test
    public void validator52AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(5, 0), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(5, 1), NOUGHT),
                new Move(new Position(2, 4), CROSS),
                new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(1, 5), CROSS),
                new Move(new Position(5, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(2, 2), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    //
    //
    // nowe testy 24.05.2025
    //

    // blokuj kolkiem L
    @Test
    public void validator53AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(6, 0), NOUGHT),
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(6, 1), NOUGHT),
                new Move(new Position(1, 2), CROSS),
                new Move(new Position(2, 3), CROSS),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(6, 4), NOUGHT),
                new Move(new Position(6, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(1, 3), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    // zaatakuj x L
    @Test
    public void validator54AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(6, 0), NOUGHT),
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(6, 1), NOUGHT),
                new Move(new Position(1, 2), CROSS),
                new Move(new Position(2, 3), CROSS),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(6, 4), NOUGHT),
                new Move(new Position(6, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(1, 3), CROSS);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    // zaatakuj x _/
    @Test
    public void validator55AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(6, 0), NOUGHT),
                new Move(new Position(4, 1), CROSS),
                new Move(new Position(6, 1), NOUGHT),
                new Move(new Position(3, 2), CROSS),
                new Move(new Position(1, 3), CROSS),
                new Move(new Position(2, 3), CROSS),
                new Move(new Position(6, 4), NOUGHT),
                new Move(new Position(6, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(1, 4), CROSS);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    // blokuj o _/
    // todo problematyczny przypadek

//        ['.', '.', '.', '.', '.', '.', 'O', '.', '.', '.'],
//        ['.', '.', '.', '.', 'X', '.', 'O', '.', '.', '.'],
//        ['.', '.', '.', 'X', '.', '.', '.', '.', '.', '.'],
//        ['.', 'X', 'X', '.', '.', '.', '.', '.', '.', '.'],
//        ['.', '.', '.', '.', '.', '.', 'O', '.', '.', '.'],
//        ['.', '.', '.', '.', '.', '.', 'O', '.', '.', '.'],
//        ['.', '.', '.', '.', '.', '.', '.', '.', '.', '.'],
//        ['.', '.', '.', '.', '.', '.', '.', '.', '.', '.'],
//        ['.', '.', '.', '.', '.', '.', '.', '.', '.', '.'],
//        ['.', '.', '.', '.', '.', '.', '.', '.', '.', '.']

    @Test
    public void validator56AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(4, 1), CROSS),
                new Move(new Position(3, 2), CROSS),
                new Move(new Position(1, 3), CROSS),
                new Move(new Position(2, 3), CROSS),
                new Move(new Position(6, 0), NOUGHT),
                new Move(new Position(6, 1), NOUGHT),
                new Move(new Position(6, 4), NOUGHT),
                new Move(new Position(6, 5), NOUGHT)
        );
        Move exp0 = new Move(new Position(1, 4), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    @Test
    public void validator25teststest() {
        // gra O, poddaj otwarta 3, 1
        try {
            Set<Move> moves = new HashSet<>();
            moves.add(new Move(new Position(1, 4), CROSS));
            moves.add(new Move(new Position(1, 5), CROSS));
            moves.add(new Move(new Position(2, 5), CROSS));
            moves.add(new Move(new Position(1, 6), CROSS));
            moves.add(new Move(new Position(3, 6), CROSS));
            moves.add(new Move(new Position(5, 0), NOUGHT));
            moves.add(new Move(new Position(5, 1), NOUGHT));
            moves.add(new Move(new Position(5, 4), NOUGHT));
            moves.add(new Move(new Position(5, 5), NOUGHT));
            moves.add(new Move(new Position(5, 6), NOUGHT));

            Gomoku gomoku = new Gomoku();
            gomoku.firstMark(NOUGHT);
            gomoku.size(15);
            Move next = gomoku.nextMove(moves, NOUGHT);

            Assertions.assertTrue(
                    next.equals(new Move(new Position(5, 7), NOUGHT)) ||
                            next.equals(new Move(new Position(5, 3), NOUGHT))
            );
//            Assertions.fail("3");
        } catch (WrongBoardStateException e) {
            Assertions.fail("2");
        } catch (ResignException e) {
            Assertions.fail("3");
            //            Assertions.assertTrue(true);
        } catch (TheWinnerIsException e) {
            Assertions.fail("1");
        }
    }


    @Test
    public void validatorTooEarlyResign1AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), NOUGHT),
                new Move(new Position(1, 2), NOUGHT),
                new Move(new Position(1, 3), NOUGHT),
                new Move(new Position(1, 7), NOUGHT),
                new Move(new Position(2, 2), CROSS),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(4, 4), CROSS),
                new Move(new Position(5, 5), CROSS)
        );
        Move exp0 = new Move(new Position(6, 6), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    @Test
    public void validatorTooEarlyResign2AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), NOUGHT),
                new Move(new Position(1, 2), NOUGHT),
                new Move(new Position(2, 2), CROSS),
                new Move(new Position(3, 2), NOUGHT),
                new Move(new Position(1, 3), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(3, 4), CROSS),
                new Move(new Position(4, 4), CROSS),
                new Move(new Position(3, 5), CROSS),
                new Move(new Position(5, 5), CROSS),
                new Move(new Position(1, 7), NOUGHT),
                new Move(new Position(7, 8), NOUGHT)
        );
        Move exp0 = new Move(new Position(6, 6), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }


    @Test
    public void validatorTooEarlyResign3AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), NOUGHT),
                new Move(new Position(1, 2), NOUGHT),
                new Move(new Position(2, 2), CROSS),
                new Move(new Position(3, 2), NOUGHT),
                new Move(new Position(1, 3), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(5, 3), CROSS),
                new Move(new Position(4, 4), CROSS),
                new Move(new Position(5, 5), CROSS),
                new Move(new Position(1, 7), NOUGHT)
        );
        Move exp0 = new Move(new Position(6, 6), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    @Test
    public void validatorResign1AllRotations() throws TheWinnerIsException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 2), NOUGHT),
                new Move(new Position(2, 2), CROSS),
                new Move(new Position(3, 2), NOUGHT),
                new Move(new Position(1, 3), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(5, 3), CROSS),
                new Move(new Position(4, 4), CROSS),
                new Move(new Position(5, 5), CROSS),
                new Move(new Position(5, 6), NOUGHT),
                new Move(new Position(1, 7), NOUGHT)
        );

        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size44);
            try {
                Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
                System.out.println(got);
                // If no exception is thrown, fail the test
                Assertions.fail("Expected ResignException to be thrown (rotation " + r + ")");
            } catch (ResignException e) {
                // Expected exception — test passes for this rotation
                Assertions.assertTrue(true);
            }
        }
    }

    @Test
    public void validatorX11AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), NOUGHT),
                new Move(new Position(1, 2), NOUGHT),
                new Move(new Position(2, 2), CROSS),
                new Move(new Position(1, 3), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(4, 4), CROSS),
                new Move(new Position(5, 5), CROSS),
                new Move(new Position(1, 6), NOUGHT)
        );
        Move exp0 = new Move(new Position(6, 6), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    @Test
    public void validatorX12AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), NOUGHT),
                new Move(new Position(2, 2), CROSS),
                new Move(new Position(7, 2), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(7, 3), NOUGHT),
                new Move(new Position(8, 3), NOUGHT),
                new Move(new Position(4, 4), CROSS),
                new Move(new Position(7, 4), NOUGHT),
                new Move(new Position(9, 4), NOUGHT),
                new Move(new Position(5, 5), CROSS),
                new Move(new Position(2, 7), CROSS),
                new Move(new Position(3, 7), CROSS)
        );
        Move exp0 = new Move(new Position(6, 6), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

    @Test
    public void validatorX13AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(2, 2), NOUGHT),
                new Move(new Position(7, 2), CROSS),
                new Move(new Position(3, 3), NOUGHT),
                new Move(new Position(7, 3), CROSS),
                new Move(new Position(8, 3), CROSS),
                new Move(new Position(4, 4), NOUGHT),
                new Move(new Position(7, 4), CROSS),
                new Move(new Position(9, 4), CROSS),
                new Move(new Position(5, 5), NOUGHT),
                new Move(new Position(2, 7), NOUGHT),
                new Move(new Position(3, 7), NOUGHT)
        );
        Move exp0 = new Move(new Position(6, 6), CROSS);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
        }
    }

//    @Test
//    public void validatorX14AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(1, 1), Mark.CROSS),
//                new Move(new Position(2, 2), Mark.NOUGHT),
//                new Move(new Position(7, 2), Mark.CROSS),
//                new Move(new Position(3, 3), Mark.NOUGHT),
//                new Move(new Position(7, 3), Mark.CROSS),
//                new Move(new Position(8, 3), Mark.CROSS),
//                new Move(new Position(3, 4), Mark.NOUGHT),
//                new Move(new Position(4, 4), Mark.NOUGHT),
//                new Move(new Position(7, 4), Mark.CROSS),
//                new Move(new Position(9, 4), Mark.CROSS),
//                new Move(new Position(5, 5), Mark.NOUGHT),
//                new Move(new Position(3, 6), Mark.NOUGHT),
//                new Move(new Position(2, 7), Mark.NOUGHT),
//                new Move(new Position(7, 7), Mark.CROSS)
//        );
//        Move exp0 = new Move(new Position(6, 6), CROSS);
//        int size44 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(CROSS);

    /// /            g.periodicBoundaryConditionsInUse();
//            g.size(size44);
//            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
//            Assertions.assertEquals(rotate(exp0, r, size44), got);
//        }
//    }
    @Test
    public void validatorX14AllRotations() throws TheWinnerIsException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(1, 1), CROSS),
                new Move(new Position(2, 2), NOUGHT),
                new Move(new Position(7, 2), CROSS),
                new Move(new Position(3, 3), NOUGHT),
                new Move(new Position(7, 3), CROSS),
                new Move(new Position(8, 3), CROSS),
                new Move(new Position(3, 4), NOUGHT),
                new Move(new Position(4, 4), NOUGHT),
                new Move(new Position(7, 4), CROSS),
                new Move(new Position(9, 4), CROSS),
                new Move(new Position(5, 5), NOUGHT),
                new Move(new Position(3, 6), NOUGHT),
                new Move(new Position(2, 7), NOUGHT),
                new Move(new Position(7, 7), CROSS)
        );

        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.size(size44);
            try {
                Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
//                System.out.println(got);
                // If no exception is thrown, fail the test
                Assertions.fail("Expected ResignException to be thrown (rotation " + r + ")");
            } catch (ResignException e) {
                // Expected exception — test passes for this rotation
                Assertions.assertTrue(true);
            }
        }
    }

    public static void printBoard(Set<Move> moves) {
        // Ustalenie rozmiaru planszy
        int maxRow = 0, maxCol = 0;
        for (Move move : moves) {
            maxCol = Math.max(maxCol, move.position().col());
            maxRow = Math.max(maxRow, move.position().row());
        }

        maxCol = 10;
        maxRow = 10;
        // Tworzymy pustą planszę
        char[][] board = new char[maxRow + 1][maxCol + 1];
        for (int y = 0; y <= maxRow; y++) {
            for (int x = 0; x <= maxCol; x++) {
                board[y][x] = '.';
            }
        }
        // Uzupełniamy ruchy
        for (Move move : moves) {
            char c = move.mark() == CROSS ? 'X' : 'O';
            board[move.position().row()][move.position().col()] = c;
        }
        // Drukujemy planszę
        for (int y = 0; y <= maxRow; y++) {
            for (int x = 0; x <= maxCol; x++) {
                System.out.print(board[y][x] + "  ");
            }
            System.out.println();
        }
    }

    @Test
    public void validatorX15AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(4, 5), Mark.CROSS),
//                new Move(new Position(5, 5), Mark.CROSS),
//                new Move(new Position(6, 5), Mark.CROSS),
//                new Move(new Position(8, 5), Mark.CROSS),
//                new Move(new Position(6, 6), Mark.NOUGHT),
//                new Move(new Position(7, 6), Mark.NOUGHT),
//                new Move(new Position(8, 6), Mark.NOUGHT),
//                new Move(new Position(7, 8), Mark.NOUGHT)
//        );

        Set<Move> base = TestUtil.tableToSet(new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'X', 'X', 'X', '.', 'X', '.'},
                {'.', '.', '.', '.', '.', '.', 'O', 'O', 'O', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'O', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
        });



        Move exp0 = new Move(new Position(7, 5), NOUGHT);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {

            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
//            g.periodicBoundaryConditionsInUse();
            g.size(size44);

            var rotated = rotateMoves(new HashSet<>(base), r, size44);
            printBoard(rotated);
            Move got = g.nextMove(rotated, NOUGHT);
            Assertions.assertEquals(rotate(exp0, r, size44), got);
            //System.out.println(rotate(exp0, r, size44).equals(got));
        }
    }


    @Test
    public void validatorDoubleThreat1AllRotations() throws TheWinnerIsException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(4, 2), CROSS),
                new Move(new Position(5, 2), CROSS),
                new Move(new Position(6, 2), CROSS),
                new Move(new Position(4, 5), CROSS),
                new Move(new Position(5, 5), CROSS),
                new Move(new Position(6, 5), CROSS),
                new Move(new Position(7, 6), NOUGHT),
                new Move(new Position(8, 6), NOUGHT),
                new Move(new Position(3, 7), NOUGHT),
                new Move(new Position(5, 7), NOUGHT),
                new Move(new Position(7, 8), NOUGHT),
                new Move(new Position(1, 9), NOUGHT)
        );

        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size44);
            try {
                Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
//                System.out.println(got);
                // If no exception is thrown, fail the test
                Assertions.fail("Expected ResignException to be thrown (rotation " + r + ")");
            } catch (ResignException e) {
                // Expected exception — test passes for this rotation
                Assertions.assertTrue(true);
            }
        }
    }

    @Test
    public void validatorkonsultacjeprzyklAllRotations() throws TheWinnerIsException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(2, 1), NOUGHT),
                new Move(new Position(4, 2), CROSS),
                new Move(new Position(5, 2), CROSS),
                new Move(new Position(8, 2), NOUGHT),
                new Move(new Position(2, 3), NOUGHT),
                new Move(new Position(3, 3), CROSS),
                new Move(new Position(6, 3), CROSS),
                new Move(new Position(3, 4), CROSS),
                new Move(new Position(6, 4), CROSS),
                new Move(new Position(4, 5), CROSS),
                new Move(new Position(5, 5), CROSS),
                new Move(new Position(8, 6), NOUGHT),
                new Move(new Position(1, 7), NOUGHT),
                new Move(new Position(4, 7), NOUGHT),
                new Move(new Position(5, 7), NOUGHT),
                new Move(new Position(8, 8), NOUGHT)
        );

        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(NOUGHT);
            g.size(size44);
            try {
                Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
//                System.out.println(got);
                // If no exception is thrown, fail the test
                Assertions.fail("Expected ResignException to be thrown (rotation " + r + ")");
            } catch (ResignException e) {
                // Expected exception — test passes for this rotation
                Assertions.assertTrue(true);
            }
        }
    }

    // $$$$$$$

//    @Test
//    public void validator12121230AllRotations() {
//        Set<Move> base = Set.of(
//                new Move(new Position(8, 0), CROSS), new Move(new Position(5, 0), NOUGHT),
//                new Move(new Position(9, 1), CROSS), new Move(new Position(5, 1), NOUGHT),
//                new Move(new Position(0, 2), CROSS), new Move(new Position(5, 5), NOUGHT),
//                new Move(new Position(1, 3), CROSS), new Move(new Position(5, 8), NOUGHT)
//        );
//        Move exp0 = new Move(new Position(2, 4), CROSS);
//        Move exp1 = new Move(new Position(7, 9), CROSS);
//        int size30 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(CROSS);
////            g.periodicBoundaryConditionsInUse();
//            g.size(size30);
//            System.out.println("analyzing rotation " + r * 90);
//            try {
//                Move got = g.nextMove(rotateMoves(base, r, size30), CROSS);
//                System.out.println(got);
//                Assertions.assertTrue(
//                        rotate(exp0, r, size30).equals(got) || rotate(exp1, r, size30).equals(got),
//                        "Expected got to match either exp0 or exp1 after rotation"
//                );
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//    }
//
//    @Test
//    public void validatorz2222AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(9, 1), CROSS), new Move(new Position(5, 0), NOUGHT),
//                new Move(new Position(0, 2), CROSS), new Move(new Position(5, 1), NOUGHT),
//                new Move(new Position(0, 4), CROSS), new Move(new Position(5, 4), NOUGHT),
//                new Move(new Position(9, 5), CROSS), new Move(new Position(5, 5), NOUGHT)
//        );
//        Move exp0 = new Move(new Position(1, 3), CROSS);
//        int size31 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(CROSS);
////            g.periodicBoundaryConditionsInUse();
//            g.size(size31);
//            Move got = g.nextMove(rotateMoves(base, r, size31), CROSS);
//            Assertions.assertEquals(rotate(exp0, r, size31), got);
//        }
//    }
//
//    @Test
//    public void validatorx111AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(0, 3), CROSS), new Move(new Position(5, 0), NOUGHT),
//                new Move(new Position(9, 3), CROSS), new Move(new Position(5, 1), NOUGHT),
//                new Move(new Position(0, 4), CROSS), new Move(new Position(5, 4), NOUGHT),
//                new Move(new Position(8, 4), CROSS), new Move(new Position(5, 5), NOUGHT)
//        );
//        Move exp0 = new Move(new Position(0, 2), CROSS);
//        int size32 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(CROSS);
////            g.periodicBoundaryConditionsInUse();
//            g.size(size32);
//            Move got = g.nextMove(rotateMoves(base, r, size32), CROSS);
//            Assertions.assertEquals(rotate(exp0, r, size32), got);
//        }
//    }
//
//    @Test
//    public void validatorx10AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(0, 2), CROSS), new Move(new Position(5, 0), NOUGHT),
//                new Move(new Position(1, 3), CROSS), new Move(new Position(5, 1), NOUGHT),
//                new Move(new Position(9, 3), CROSS), new Move(new Position(5, 4), NOUGHT),
//                new Move(new Position(0, 4), CROSS), new Move(new Position(5, 5), NOUGHT)
//        );
//        Move exp0 = new Move(new Position(0, 3), CROSS);
//        int size33 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(CROSS);
////            g.periodicBoundaryConditionsInUse();
//            g.size(size33);
//            Move got = g.nextMove(rotateMoves(base, r, size33), CROSS);
//            Assertions.assertEquals(rotate(exp0, r, size33), got);
//        }
//    }
//
//    @Test
//    public void validatorz8AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(5, 0), NOUGHT), new Move(new Position(9, 1), CROSS),
//                new Move(new Position(5, 1), NOUGHT), new Move(new Position(0, 2), CROSS),
//                new Move(new Position(5, 4), NOUGHT), new Move(new Position(0, 4), CROSS),
//                new Move(new Position(5, 5), NOUGHT), new Move(new Position(9, 5), CROSS)
//        );
//        Move exp0 = new Move(new Position(1, 3), NOUGHT);
//        int size34 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(NOUGHT);
////            g.periodicBoundaryConditionsInUse();
//            g.size(size34);
//            Move got = g.nextMove(rotateMoves(base, r, size34), NOUGHT);
//            Assertions.assertEquals(rotate(exp0, r, size34), got);
//        }
//    }
//
//    @Test
//    public void validatorx7AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(5, 0), NOUGHT), new Move(new Position(0, 3), CROSS),
//                new Move(new Position(5, 1), NOUGHT), new Move(new Position(9, 3), CROSS),
//                new Move(new Position(5, 4), NOUGHT), new Move(new Position(0, 4), CROSS),
//                new Move(new Position(5, 5), NOUGHT), new Move(new Position(8, 4), CROSS)
//        );
//        Move exp0 = new Move(new Position(0, 2), NOUGHT);
//        int size35 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(NOUGHT);
////            g.periodicBoundaryConditionsInUse();
//            g.size(size35);
//            Move got = g.nextMove(rotateMoves(base, r, size35), NOUGHT);
//            Assertions.assertEquals(rotate(exp0, r, size35), got);
//        }
//    }
//
//    @Test
//    public void validatorx5AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(5, 0), NOUGHT), new Move(new Position(0, 2), CROSS),
//                new Move(new Position(5, 1), NOUGHT), new Move(new Position(1, 3), CROSS),
//                new Move(new Position(5, 4), NOUGHT), new Move(new Position(9, 3), CROSS),
//                new Move(new Position(5, 5), NOUGHT), new Move(new Position(0, 4), CROSS)
//        );
//        Move exp0 = new Move(new Position(0, 3), NOUGHT);
//        int size36 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(NOUGHT);
////            g.periodicBoundaryConditionsInUse();
//            g.size(size36);
//            Move got = g.nextMove(rotateMoves(base, r, size36), NOUGHT);
//            Assertions.assertEquals(rotate(exp0, r, size36), got);
//        }
//    }
//
//    @Test
//    public void validatorx4AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(5, 0), NOUGHT), new Move(new Position(2, 0), CROSS),
//                new Move(new Position(5, 4), NOUGHT), new Move(new Position(1, 1), CROSS),
//                new Move(new Position(5, 1), NOUGHT), new Move(new Position(1, 7), CROSS),
//                new Move(new Position(5, 5), NOUGHT), new Move(new Position(2, 8), CROSS)
//        );
//        Move exp0 = new Move(new Position(3, 9), NOUGHT);
//        int size42 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(NOUGHT);
////            g.periodicBoundaryConditionsInUse();
//            g.size(size42);
//            Move got = g.nextMove(rotateMoves(base, r, size42), NOUGHT);
//            Assertions.assertEquals(rotate(exp0, r, size42), got);
//        }
//    }
//
//    @Test
//    public void validatorx2AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(5, 0), NOUGHT), new Move(new Position(1, 0), CROSS),
//                new Move(new Position(5, 1), NOUGHT), new Move(new Position(3, 0), CROSS),
//                new Move(new Position(5, 4), NOUGHT), new Move(new Position(2, 9), CROSS),
//                new Move(new Position(5, 5), NOUGHT), new Move(new Position(3, 9), CROSS)
//        );
//        Move exp0 = new Move(new Position(3, 8), NOUGHT);
//        int size43 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(NOUGHT);
////            g.periodicBoundaryConditionsInUse();
//            g.size(size43);
//            Move got = g.nextMove(rotateMoves(base, r, size43), NOUGHT);
//            Assertions.assertEquals(rotate(exp0, r, size43), got);
//        }
//    }
//
//    @Test
//    public void validatorx1AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        Set<Move> base = Set.of(
//                new Move(new Position(5, 0), NOUGHT), new Move(new Position(3, 0), CROSS),
//                new Move(new Position(5, 1), NOUGHT), new Move(new Position(3, 8), CROSS),
//                new Move(new Position(5, 4), NOUGHT), new Move(new Position(2, 9), CROSS),
//                new Move(new Position(5, 5), NOUGHT), new Move(new Position(4, 9), CROSS)
//        );
//        Move exp0 = new Move(new Position(3, 9), NOUGHT);
//        int size44 = 10;
//        for (int r = 0; r < 4; r++) {
//            Gomoku g = new Gomoku();
//            g.firstMark(NOUGHT);
    ////            g.periodicBoundaryConditionsInUse();
//            g.size(size44);
//            Move got = g.nextMove(rotateMoves(base, r, size44), NOUGHT);
//            Assertions.assertEquals(rotate(exp0, r, size44), got);
//        }
//    }


    @Test
    public void validatorDo243423ubleThreat1AllRotations() throws TheWinnerIsException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(0, 2), CROSS),
                new Move(new Position(1, 2), CROSS),
                new Move(new Position(2, 2), CROSS),
                new Move(new Position(8, 2), CROSS),
                new Move(new Position(9, 2), CROSS),
                new Move(new Position(5, 3), NOUGHT),
                new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(5, 5), NOUGHT),
                new Move(new Position(5, 6), NOUGHT),
                new Move(new Position(8, 7), NOUGHT)
        );

        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.size(size44);
            try {
                Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
//                System.out.println(got);
                // If no exception is thrown, fail the test
                Assertions.fail("Expected ResignException to be thrown (rotation " + r + ")");
            } catch (ResignException e) {
                // Expected exception — test passes for this rotation
                Assertions.assertTrue(true);
            }
        }
    }


    @Test
    public void validatorX42342313AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(0, 2), CROSS),
                new Move(new Position(1, 2), CROSS),
                new Move(new Position(2, 2), CROSS),
                new Move(new Position(9, 2), CROSS),
                new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(5, 5), NOUGHT),
                new Move(new Position(6, 5), NOUGHT),
                new Move(new Position(5, 6), NOUGHT),
                new Move(new Position(7, 6), NOUGHT),
                new Move(new Position(2, 8), CROSS)
        );
        Move exp0 = new Move(new Position(3, 2), CROSS);
        Move exp1 = new Move(new Position(8, 2), CROSS);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);

            boolean match0 = rotate(exp0, r, size44).equals(got);
            boolean match1 = rotate(exp1, r, size44).equals(got);
            Assertions.assertTrue(match0 || match1);

        }
    }


    @Test
    public void validator243432X42342313AllRotations() throws TheWinnerIsException, ResignException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(0, 2), CROSS),
                new Move(new Position(1, 2), CROSS),
                new Move(new Position(9, 2), CROSS),
                new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(5, 5), NOUGHT),
                new Move(new Position(6, 5), NOUGHT),
                new Move(new Position(3, 6), CROSS),
                new Move(new Position(5, 6), NOUGHT),
                new Move(new Position(7, 6), NOUGHT),
                new Move(new Position(2, 8), CROSS)
        );
        Move exp0 = new Move(new Position(2, 2), CROSS);
        Move exp1 = new Move(new Position(8, 2), CROSS);
        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.periodicBoundaryConditionsInUse();
            g.size(size44);
            Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);

            boolean match0 = rotate(exp0, r, size44).equals(got);
            boolean match1 = rotate(exp1, r, size44).equals(got);
            Assertions.assertTrue(match0 || match1);

        }
    }

    @Test
    public void validatorDo24224343423ubleThreat1AllRotations() throws TheWinnerIsException, WrongBoardStateException {
        Set<Move> base = Set.of(
                new Move(new Position(0, 2), CROSS),
                new Move(new Position(1, 2), CROSS),
                new Move(new Position(9, 2), CROSS),
                new Move(new Position(5, 4), NOUGHT),
                new Move(new Position(5, 5), NOUGHT),
                new Move(new Position(6, 5), NOUGHT),
                new Move(new Position(3, 6), CROSS),
                new Move(new Position(5, 6), NOUGHT),
                new Move(new Position(7, 6), NOUGHT),
                new Move(new Position(2, 8), CROSS)
        );

        int size44 = 10;
        for (int r = 0; r < 4; r++) {
            Gomoku g = new Gomoku();
            g.firstMark(CROSS);
            g.size(size44);
            try {
                Move got = g.nextMove(rotateMoves(base, r, size44), CROSS);
//                System.out.println(got);
                // If no exception is thrown, fail the test
                Assertions.fail("Expected ResignException to be thrown (rotation " + r + ")");
            } catch (ResignException e) {
                // Expected exception — test passes for this rotation
                Assertions.assertTrue(true);
            }
        }
    }
}
