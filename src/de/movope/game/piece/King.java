package de.movope.game.piece;

import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class King implements Piece {

    private Color color;

    public King(Color color) {
        this.color = color;
    }

    @Override
    public String printIdentifier() {
        return "K";
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public List<Point> getDirections() {
        return Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));
    }

    @Override
    public int getMaximumMoves() {
        return 1;
    }
}
