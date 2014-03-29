package de.movope.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MoveVector {

    public List<Square> moves = new ArrayList<>();

    public List<Square> getMoves() {
        return moves;
    }

    void add(Square target) {
        moves.add(target);
    }

    public int size() {
        return moves.size();
    }

    public Iterator<Square> iterator() {
        return moves.iterator();
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[ ");
        for (Square move: moves) {
            buffer.append(move + " ");
        }
        buffer.append("] ");
        return buffer.toString();
    }

}
