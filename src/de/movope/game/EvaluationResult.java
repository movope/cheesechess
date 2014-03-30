package de.movope.game;

import java.util.ArrayList;
import java.util.List;

public class EvaluationResult {

    List<Square> moves = new ArrayList<>();
    Square attack;

    public List<Square> getMoves() {
        return moves;
    }

    public void addMove(Square move) {
        moves.add(move);
    }

    public Square getAttack() {
        return attack;
    }

    public void setAttack(Square attack) {
        this.attack = attack;
    }
}
