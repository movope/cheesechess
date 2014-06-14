package de.movope.game.piece;

import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Pawn implements Piece {

    private Color color;

    public Pawn(Color color) {
        this.color = color;
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
    public List<Point> getDirections() {
        if (color == de.movope.game.Color.WHITE) {
            return Arrays.asList(new Point(0, 1));
        } else {
            return Arrays.asList(new Point(0, -1));
        }
    }

    @Override
    public int getMaximumMoves() {
        return 2;
    }
}
