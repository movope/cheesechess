package de.movope.game.piece;

import de.movope.game.*;
import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;

public class Rook implements Piece {

    private Color color;
    private MoveEvaluator evaluator;

    public Rook(Color color) {
        this.color = color;
        java.util.List<Point> directions = Arrays.asList(new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));
        evaluator = MoveEvaluator.Builder.forDirections(directions)
                                        .maximumMoves(7)
                                        .pieceColor(color)
                                        .create();
    }


    @Override
    public String printIdentifier() {
        return "R";
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public java.util.List<Point> getDirections() {
        return Arrays.asList(new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));
    }

    @Override
    public int getMaximumMoves() {
        return 7;
    }

    @Override
    public MoveEvaluation getMoveEvaluationFor(ChessBoard board, Square square) {
        return evaluator.on(board).analyse(square);
    }
}
