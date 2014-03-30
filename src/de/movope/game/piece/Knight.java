package de.movope.game.piece;

import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
        maximumMoves = 1;
        directions = Arrays.asList(new Point(2, 1), new Point(2, -1), new Point(-2, 1), new Point(-2, -1),
                new Point(1, 2), new Point(1, -2), new Point(-1, 2), new Point(-1, -2));

        id = "K";
    }
}
