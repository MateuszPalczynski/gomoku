package fais.zti.oramus.gomoku;

import java.util.Set;

/**
 * Fasada silnika Gomoku. (Stub implementacji)
 */
public abstract class Gomoku implements Game {
    /**
     * Konstruktor bezparametrowy.
     */
    public Gomoku() {
        // TODO: inicjalizacja komponentów
    }

    @Override
    public Move nextMove(Set<Move> boardState, Mark nextMoveMark)
            throws ResignException, TheWinnerIsException, WrongBoardStateException {
        // TODO: deleguj do BoardModel, validatora i selektora ruchów
        throw new UnsupportedOperationException("Not yet implemented");
    }
}