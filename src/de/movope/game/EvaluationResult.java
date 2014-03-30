package de.movope.game;

import java.util.ArrayList;
import java.util.List;

public class EvaluationResult {

    List<Square> moves = new ArrayList<>();
    List<Square> attacks = new ArrayList<>();

    public List<Square> getAttacks() {
        return attacks;
    }

    public List<Square> getMoves() {
        return moves;
    }

    public void addMove(Square move) {
        moves.add(move);
    }

    public void addPossibleAttack(Square attack) {
        attacks.add(attack);
    }
}
