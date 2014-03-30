package de.movope.game.piece;

import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;

public class King extends Piece {

    public King(Color color) {
        super(color);
        maximumMoves = 1;
        directions = Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));
        id = "K";
    }
}
