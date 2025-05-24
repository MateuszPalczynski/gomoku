
import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;

/**
 * Abstrakcyjna klasa do łańczenia strategii.
 */
public abstract class AbstractStrategy implements Strategy {
    protected final BoundaryAdapter adapter;
    private Strategy next;

    /**
     * Konstruktor przyjmujący adapter brzegów.
     */
    protected AbstractStrategy(BoundaryAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Domyślny konstruktor: używa adaptera BoundedAdapter.
     */
    protected AbstractStrategy() {
        this(new BoundedAdapter());
    }

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