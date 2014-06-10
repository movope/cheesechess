package de.movope.game.piece;

import de.movope.game.ChessBoard;
import de.movope.game.Color;
import de.movope.game.MoveEvaluation;
import de.movope.game.MoveEvaluator;

import java.awt.*;
import java.util.Arrays;

public class Knight implements Piece {

    private Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public java.util.List<Point> directions() {
        return Arrays.asList(new Point(2, 1), new Point(2, -1), new Point(-2, 1), new Point(-2, -1),
                new Point(1, 2), new Point(1, -2), new Point(-1, 2), new Point(-1, -2));
    }

    @Override
    public String printIdentifier() {
        return "K";
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public MoveEvaluation getMoveEvaluationFor(ChessBoard board, String square) {
        return MoveEvaluator.with(board).analyse(square.toString());
    }

    @Override
    public int getMaximumMoves() {
        return 1;
    }
}
