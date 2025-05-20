package fais.zti.oramus.gomoku;

/**
 * Interfejs strategii AI.
 */
public interface Strategy {
    void setNext(Strategy next);
    Move decide(Mark[][] board, Mark me) throws ResignException;
}
