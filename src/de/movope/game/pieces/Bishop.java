package de.movope.game.pieces;


import de.movope.game.Color;

import java.awt.*;

public class Bishop extends Piece {

    public Bishop(Color color, String position) {
        super(color,position);
        maximumMoves = 7;
        directions = new Point[]{new Point(1, 1), new Point(-1, -1), new Point(1, -1)};
        id = "B";
    }
}
