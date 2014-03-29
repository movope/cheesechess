package de.movope.game.pieces;

import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;

public class Queen extends Piece {

    public Queen(Color color, String position) {
        super(color, position);
        maximumMoves = 7;
        directions = Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));
        id = "Q";
    }
}
