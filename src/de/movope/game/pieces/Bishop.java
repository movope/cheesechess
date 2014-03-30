package de.movope.game.pieces;


import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
        maximumMoves = 7;
        directions = Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1));
        id = "B";
    }
}
