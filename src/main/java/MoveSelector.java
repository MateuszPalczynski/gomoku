import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;

/** Buduje łańcuch strategii i zwraca ruch. */
public class MoveSelector {
    private final Strategy chain;

    public MoveSelector(BoundaryAdapter adapter) {
        // Create strategies with injected adapter
        ImmediateWinStrategy s1 = new ImmediateWinStrategy(adapter);
        BlockStrategy s2 = new BlockStrategy(adapter);
        OpenFourStrategy s3 = new OpenFourStrategy(adapter);
        DoubleThreatStrategy s4 = new DoubleThreatStrategy(adapter);

        s1.setNext(s2);
        s2.setNext(s3);
        s3.setNext(s4);

        this.chain = s1;
    }

    public Move decide(Mark[][] board, Mark me) throws ResignException {
        return chain.decide(board, me);
    }
}