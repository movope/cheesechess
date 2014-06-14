package de.movope.game.piece;

import de.movope.game.ChessBoard;
import de.movope.game.Color;
import de.movope.game.MoveEvaluation;
import de.movope.game.Square;

import java.awt.*;
import java.util.List;

public interface Piece {

    public String printIdentifier();
    public Color getColor();
    public List<Point> getDirections();
    int getMaximumMoves();

    public MoveEvaluation getMoveEvaluationFor(ChessBoard board, Square square);

    public static Piece NULL = new Piece() {
        @Override
        public String printIdentifier() {
            return "-";
        }

        @Override
        public Color getColor() {
            return null;
        }

        @Override
        public List<Point> getDirections() {
            return null;
        }

        @Override
        public int getMaximumMoves() {
            return 0;
        }

        @Override
        public MoveEvaluation getMoveEvaluationFor(ChessBoard board, Square square) {
            return null;
        }
    };
}
