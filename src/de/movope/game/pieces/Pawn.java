package de.movope.game.pieces;

import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(Color color) {
        super(color);
        id = "P";
        maximumMoves = 2;
        if (color == de.movope.game.Color.WHITE)
            directions = Arrays.asList(new Point(0, 1));
        if (color == de.movope.game.Color.BLACK)
            directions = Arrays.asList(new Point(0,-1));
    }

    @Override
    public List<Point> getAttackDirections() {
        if (color != Color.WHITE) {
            return Arrays.asList(new Point(-1, 1), new Point(1, 1));
        }
        return Arrays.asList(new Point(-1, -1), new Point(1, -1));
    }
}
