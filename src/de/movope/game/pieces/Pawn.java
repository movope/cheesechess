package de.movope.game.pieces;

import de.movope.game.Color;
import de.movope.game.Square;

import java.awt.*;
import java.util.Arrays;

public class Pawn extends Piece{

    boolean isFirstMove = true;

    public Pawn(Color color, String position) {
        super(color, position);
        id = "P";
        maximumMoves = 2;
        if (color == de.movope.game.Color.WHITE)
            directions = Arrays.asList(new Point(0, 1));
        if (color == de.movope.game.Color.BLACK)
            directions = Arrays.asList(new Point(0,-1));
    }

    @Override
    public void moveTo(Square square) {
        if (isFirstMove) {
            maximumMoves = 1;
            isFirstMove = false;
        }
        super.moveTo(square);
    }
}
