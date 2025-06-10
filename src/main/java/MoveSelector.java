import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;

/** Buduje łańcuch strategii i zwraca ruch. */
public class MoveSelector {
    private final Strategy chain;

    public MoveSelector(BoundaryAdapter adapter) {
        // Create strategies with injected adapter

        Strategy s2 = new BlockWinStrategy(adapter);
        Strategy s3 = new OpenFourStrategy(adapter);
        Strategy s4 = new BlockOpenFourStrategy(adapter);
        Strategy s5 = new DoubleThreatStrategy(adapter);

        s2.setNext(s3);
        s3.setNext(s4);
        s4.setNext(s5);

        this.chain = s2;
    }

    public Move decide(Mark[][] board, Mark me) throws ResignException {
        return chain.decide(board, me);
    }
}