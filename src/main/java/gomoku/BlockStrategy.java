package gomoku;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;

/** Blokuje ruch przeciwnika. */
public class BlockStrategy extends AbstractStrategy {
    @Override
    protected Move findMove(Mark[][] board, Mark me) {
        // blokujemy ruch przeciwnika: znajdź, gdzie przeciwnik wygra w następnym i postaw tam
        Mark opponent = (me == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
        ImmediateWinStrategy im = new ImmediateWinStrategy();
        Move oppWin = im.findMove(board, opponent);
        if (oppWin != null) {
            // blokujemy
            return new Move(oppWin.position(), me);
        }
        return null;
    }
}