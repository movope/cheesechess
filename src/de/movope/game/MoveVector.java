package de.movope.game;

import java.util.ArrayList;
import java.util.List;

class MoveVector {

    public List<Square> moves = new ArrayList<>();
    public Square attack;

    public List<Square> getMoves() {
        return moves;
    }

    void add(Square target) {
        moves.add(target);
    }

    public int size() {
        return moves.size();
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[ ");
        for (Square move: moves) {
            buffer.append(move).append(" ");
        }
        buffer.append("] ");
        return buffer.toString();
    }

    public void addAttack(Square target) {
        attack = target;
    }
}
