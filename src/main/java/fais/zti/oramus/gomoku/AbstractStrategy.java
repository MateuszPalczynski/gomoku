package fais.zti.oramus.gomoku;

/**
 * Abstrakcyjna klasa do łańczenia strategii.
 */
public abstract class AbstractStrategy implements Strategy {
    private Strategy next;

    @Override
    public void setNext(Strategy next) {
        this.next = next;
    }

    @Override
    public Move decide(Mark[][] board, Mark me) throws ResignException {
        Move m = findMove(board, me);
        if (m != null) {
            return m;
        }
        if (next != null) {
            return next.decide(board, me);
        }
        throw new ResignException();
    }

    protected abstract Move findMove(Mark[][] board, Mark me);
}
