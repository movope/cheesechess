package de.movope.game.piece;

import de.movope.game.*;
import de.movope.game.Color;

import java.awt.*;
import java.util.Arrays;

public class Queen implements Piece {

    private Color color;

    public Queen(Color color) {
        this.color = color;
    }

    java.util.List<Point> directions = Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));

    @Override
    public String printIdentifier() {
        return "Q";
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public MoveEvaluation getMoveEvaluationFor(ChessBoard board, Square square) {
        return MoveEvaluator.with(board)
                            .pieceColor(color)
                            .forDirections(directions)
                            .maximumMoves(7)
                            .analyse(square);
    }
}
