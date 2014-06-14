package de.movope.game.piece;

import de.movope.game.*;
import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class King implements Piece {

    private Color color;
    private MoveEvaluator evaluator;

    public King(Color color) {
        this.color = color;
        List<Point> directions = Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));
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
    public List<Point> getDirections() {
        return Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));
    }

    @Override
    public int getMaximumMoves() {
        return 1;
    }

    @Override
    public MoveEvaluation getMoveEvaluationFor(ChessBoard board, Square square) {
        return evaluator.on(board).analyse(square);

    }
}
