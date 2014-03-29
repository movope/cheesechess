package de.movope.game.pieces;

import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;

public class Rook extends Piece {

    public Rook(Color color, String position) {
        super(color, position);
        directions = Arrays.asList(new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));
        maximumMoves = 7;
        id = "R";
    }

}
