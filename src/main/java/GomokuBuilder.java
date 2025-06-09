import fais.zti.oramus.gomoku.Mark;
/**
 * Budowniczy konfiguracji gry Gomoku.
 */
public class GomokuBuilder {
    private int size = 10;
    private Mark first = Mark.CROSS;
    private boolean periodic = false;

    public GomokuBuilder size(int size) {
        if (size < 10 || size > 15) {
            throw new IllegalArgumentException("Size must be between 10 and 15 (inclusive)");
        }
        this.size = size;
        return this;
    }

    public GomokuBuilder firstMark(Mark first) {
        if (first == null) {
            throw new IllegalArgumentException("First mark cannot be null");
        }
        this.first = first;
        return this;
    }

    public GomokuBuilder periodic() {
        this.periodic = true;
        return this;
    }

    public Gomoku build() {
        return new Gomoku(size, first, periodic);
    }
}