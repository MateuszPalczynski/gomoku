import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;

public class MoveSelector {
    private final Strategy chain;

    public MoveSelector(BoundaryAdapter adapter, ThreatAssessor threatAssessor) {
        Strategy s1 = new FindForcedWinStrategy(adapter);
        Strategy s2 = new ForcedDefenseStrategy(adapter, threatAssessor);
        Strategy s3 = new BlockWinStrategy(adapter);
        Strategy s4 = new BlockOpenFourStrategy(adapter);
        Strategy s5 = new DoubleThreatStrategy(adapter);
        Strategy s6 = new BlockDoubleThreatStrategy(adapter);

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