package de.movope.game.piece;

import de.movope.game.ChessBoard;
import de.movope.game.Color;
import de.movope.game.MoveEvaluation;
import de.movope.game.MoveEvaluator;

import java.awt.*;
import java.util.Arrays;

public class Rook implements Piece {

    private Color color;

    public Rook(Color color) {
        this.color = color;
    }

    @Override
    public java.util.List<Point> directions() {
        return Arrays.asList(new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1));
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
    public MoveEvaluation getMoveEvaluationFor(ChessBoard board, String square) {
        return MoveEvaluator.with(board).analyse(square.toString());
    }

    @Override
    public int getMaximumMoves() {
        return 7;
    }

}
