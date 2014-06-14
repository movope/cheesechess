package de.movope.game.piece;


import de.movope.game.*;
import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Bishop implements Piece {

    private Color color;
    private MoveEvaluator evaluator;

    public Bishop(Color color) {
        this.color = color;
        List<Point> directions = Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1));
        evaluator = MoveEvaluator.Builder.forDirections(directions)
                                        .maximumMoves(7)
                                        .pieceColor(color)
                                        .create();
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
    public List<Point> getDirections() {
        return Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1));
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
