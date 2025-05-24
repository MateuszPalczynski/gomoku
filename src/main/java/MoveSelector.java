import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;

/** Buduje łańcuch strategii i zwraca ruch. */
public class MoveSelector {
    private final Strategy chain;

    public MoveSelector() {
        ImmediateWinStrategy s1 = new ImmediateWinStrategy();
        OpenFourStrategy s2 = new OpenFourStrategy();
        DoubleThreatStrategy s3 = new DoubleThreatStrategy();
        BlockStrategy s4 = new BlockStrategy();
        s1.setNext(s2);
        s2.setNext(s3);
        s3.setNext(s4);
        this.chain = s1;
    }

    public Move decide(Mark[][] board, Mark me) throws ResignException {
        return chain.decide(board, me);
    }
}
