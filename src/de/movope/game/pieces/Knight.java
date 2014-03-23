package de.movope.game.pieces;

import de.movope.game.Color;
import java.awt.*;

public class Knight extends Piece {

    public Knight(Color color, String position) {
        super(color, position);
        maximumMoves = 1;
        directions = new Point[]{new Point(2, 1), new Point(2, -1), new Point(-2, 1), new Point(-2, -1),
                new Point(1, 2), new Point(1, -2), new Point(-1, 2), new Point(-1, -2)};

        id = "P";
    }
}
