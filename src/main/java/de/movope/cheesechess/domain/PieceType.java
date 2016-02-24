package de.movope.cheesechess.domain;

import java.util.Collections;
import java.util.List;

import static de.movope.cheesechess.domain.Direction.Builder.directions;

public enum PieceType {
    NULL("+", 0,
          Collections.<Direction>emptyList()),
    PAWN("B", 0,
          Collections.<Direction>emptyList()),
    ROOK("T", 7,
            directions().parallel().create()),
    BISHOP("L", 7,
            directions().diagonal().create()),
    QUEEN("Q", 7,
            directions().parallel().diagonal().create()),
    KNIGHT("P", 1,
            directions().knight().create()),
    KING("K", 1,
            directions().parallel().diagonal().create());

    final private String identifier;
    final private List<Direction> directions;
    final private int maximumMoves;


    PieceType(String identifier, int maximumMoves, List<Direction> points) {
        this.identifier = identifier;
        this.maximumMoves = maximumMoves;
        this.directions = points;
    }

    public String print() {
        return identifier;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public int getMaximumMoves() {
        return maximumMoves;
    }


}