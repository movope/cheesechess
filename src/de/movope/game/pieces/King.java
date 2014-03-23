package de.movope.game.pieces;

import de.movope.game.Color;

import java.awt.*;

public class King extends Piece {

    public King(Color color, String position) {
        super(color, position);
        maximumMoves = 1;
        directions = new Point[]{new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)};
        id = "K";
    }
}