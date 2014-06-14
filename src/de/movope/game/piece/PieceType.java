package de.movope.game.piece;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PieceType {
    NULL("+", 0,
          Collections.<Point>emptyList()),
    PAWN("B", 0,
          Collections.<Point>emptyList()),
    ROOK("T", 7,
          Arrays.asList(new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1))),
    BISHOP("L", 7,
           Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1))),
    QUEEN("Q", 7,
           Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                    new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1))),
    KNIGHT("P", 1,
            Arrays.asList(new Point(2, 1), new Point(2, -1), new Point(-2, 1), new Point(-2, -1),
                    new Point(1, 2), new Point(1, -2), new Point(-1, 2), new Point(-1, -2))),
    KING("K", 1,
          Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                    new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)));

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