package gomoku;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;

/**
 * Interfejs strategii AI.
 */
public interface Strategy {
    void setNext(Strategy next);
    Move decide(Mark[][] board, Mark me) throws ResignException;
}
