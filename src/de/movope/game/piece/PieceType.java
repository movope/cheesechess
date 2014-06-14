package de.movope.game.piece;

import de.movope.game.Direction;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public enum PieceType {
    NULL("+", 0,
          Collections.<Point>emptyList()),
    PAWN("B", 0,
          Collections.<Point>emptyList()),
    ROOK("T", 7,
            Direction.Builder.create().parallel().directions()),
    BISHOP("L", 7,
            Direction.Builder.create().diagonal().directions()),
    QUEEN("Q", 7,
            Direction.Builder.create().parallel().diagonal().directions()),
    KNIGHT("P", 1,
            Direction.Builder.create().knight().directions()),
    KING("K", 1,
            Direction.Builder.create().parallel().diagonal().directions());

    final private String identifier;
    final private List<Point> directions;
    final private int maximumMoves;


    PieceType(String identifier, int maximumMoves, List<Point> points) {
        this.identifier = identifier;
        this.maximumMoves = maximumMoves;
        this.directions = points;
    }

    public String print() {
        return identifier;
    }

    public List<Point> getDirections() {
        return directions;
    }

    public int getMaximumMoves() {
        return maximumMoves;
    }


}