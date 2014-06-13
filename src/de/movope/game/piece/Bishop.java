package de.movope.game.piece;


import de.movope.game.*;
import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Bishop implements Piece {

    private Color color;

    List<Point> directions = Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1));

    public Bishop(Color color) {
        this.color = color;
    }

    @Override
    public String printIdentifier() {
        return "B";
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public MoveEvaluation getMoveEvaluationFor(ChessBoard board, Square square) {
        return MoveEvaluator.with(board)
                            .forDirections(directions)
                            .maximumMoves(7)
                            .analyse(square);
    }
}
