import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;

/** Buduje łańcuch strategii i zwraca ruch. */
public class MoveSelector {
    private final Strategy chain;

    public MoveSelector(BoundaryAdapter adapter, ThreatDetector threatDetector) {
        // Create strategies with injected adapter
        Strategy s1 = new ForcedDefenseStrategy(adapter, threatDetector);
        Strategy s2 = new BlockWinStrategy(adapter);
        Strategy s3 = new OpenFourStrategy(adapter);
        Strategy s4 = new BlockOpenFourStrategy(adapter);
        Strategy s5 = new DoubleThreatStrategy(adapter);
        Strategy s6 = new BlockDoubleThreatStrategy(adapter);

        // Set the new chain of command
        s1.setNext(s2);
        s2.setNext(s3);
        s3.setNext(s4);
        s4.setNext(s5);
        s5.setNext(s6);

        this.chain = s1;
    }

    public Move decide(Mark[][] board, Mark me) throws ResignException {
        return chain.decide(board, me);
    }
}