package de.movope.game.piece;

import de.movope.game.*;
import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Pawn implements Piece {

    private Color color;
    private MoveEvaluator evaluator;

    public Pawn(Color color) {
        this.color = color;
        List<Point> directions;
        if (color == de.movope.game.Color.WHITE) {
            directions = Arrays.asList(new Point(0, 1));
        } else {
            directions = Arrays.asList(new Point(0, -1));
        }
        evaluator = MoveEvaluator.Builder.forDirections(directions)
                                           .forPawn()
                                           .pieceColor(color)
                                           .create();
    }

    @Override
    public String printIdentifier() {
        return "p";
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
