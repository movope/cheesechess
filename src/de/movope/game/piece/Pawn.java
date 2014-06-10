package de.movope.game.piece;

import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;

public class Pawn implements Piece {

    private Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public java.util.List<Point> directions() {
        if (color == de.movope.game.Color.WHITE)
            return Arrays.asList(new Point(0, 1));
        if (color == de.movope.game.Color.BLACK)
            return Arrays.asList(new Point(0,-1));
        throw new IllegalArgumentException("Unknown Color.");
    }

    @Override
    public String printIdentifier() {
        return "p";
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getMaximumMoves() {
        return 2;
    }
}
