package de.movope.game.domain;

import de.movope.game.Color;
import java.awt.*;

public class Rook extends Piece {

    public Rook(Color color, String position) {
        super(color, position);
        directions = new Point[]{new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)};
        maximumMoves = 7;
        id = "R";
    }

}
