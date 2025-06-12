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

    protected abstract Move findMove(Mark[][] board, Mark me) throws ResignException;

    /** Zlicza kolejno pionki kierunku (dr,dc). */
    protected int countDirection(Mark[][] board, Mark me, int r, int c, int dr, int dc) {
        int count = 0;
        int rr = r + dr;
        int cc = c + dc;
        while (adapter.get(board, rr, cc) == me) {
            count++;
            rr += dr;
            cc += dc;
        }
        return count;
    }
}