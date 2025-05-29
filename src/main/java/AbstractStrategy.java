import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;

public abstract class AbstractStrategy implements Strategy {
    protected Strategy next;
    protected BoundaryAdapter adapter;

    public AbstractStrategy() {
        this.adapter = new BoundedAdapter();
    }

    public AbstractStrategy(BoundaryAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void setNext(Strategy next) {
        this.next = next;
    }

    @Override
    public Move decide(Mark[][] board, Mark me) throws ResignException {
        Move move = findMove(board, me);
        if (move != null) {
            return move;
        } else if (next != null) {
            return next.decide(board, me);
        } else {
            throw new ResignException();
        }
    }

    protected abstract Move findMove(Mark[][] board, Mark me);
}