package de.movope.game.piece;

import de.movope.game.*;
import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Knight implements Piece {

    private Color color;
    private MoveEvaluator evaluator;

    public Knight(Color color) {
        this.color = color;
        List<Point> directions = Arrays.asList(new Point(2, 1), new Point(2, -1), new Point(-2, 1), new Point(-2, -1),
                new Point(1, 2), new Point(1, -2), new Point(-1, 2), new Point(-1, -2));
        evaluator = MoveEvaluator.Builder.forDirections(directions)
                                        .maximumMoves(1)
                                        .pieceColor(color)
                                        .create();
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
    public MoveEvaluation getMoveEvaluationFor(ChessBoard board, Square square) {
        return evaluator.on(board).analyse(square);
    }
}
