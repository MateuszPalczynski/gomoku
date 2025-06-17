import fais.zti.oramus.gomoku.Mark;

public class ThreatAssessor {
    private final GeneralThreatCounter generalThreatCounter;
    private final DoubleThreeThreatDetector doubleThreeThreatDetector;
    private final ForcedWinDetector forcedWinDetector;

    public ThreatAssessor(BoundaryAdapter adapter, int boardSize) {
        this.generalThreatCounter = new GeneralThreatCounter(adapter, boardSize);
        this.doubleThreeThreatDetector = new DoubleThreeThreatDetector(adapter, boardSize);
        this.forcedWinDetector = new ForcedWinDetector(adapter, boardSize);
    }

    public int countOpenThrees(Mark[][] board, Mark playerMark) {
        return generalThreatCounter.countThreats(board, playerMark, 4, true);
    }

    public int countFours(Mark[][] board, Mark playerMark) {
        return generalThreatCounter.countThreats(board, playerMark, 5, false);
    }

    public int countDoubleThreeThreats(Mark[][] board, Mark playerMark) {
        return doubleThreeThreatDetector.count(board, playerMark);
    }

    public boolean hasForcedWin(Mark[][] board, Mark playerMark) {
        return forcedWinDetector.hasForcedWin(board, playerMark);
    }
}