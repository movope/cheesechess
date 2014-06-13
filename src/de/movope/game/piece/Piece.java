package de.movope.game.piece;

import de.movope.game.ChessBoard;
import de.movope.game.Color;
import de.movope.game.MoveEvaluation;
import de.movope.game.Square;

public interface Piece {

    public String printIdentifier();
    public Color getColor();
    public MoveEvaluation getMoveEvaluationFor(ChessBoard board, Square square);
    public int getMaximumMoves();

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
        public MoveEvaluation getMoveEvaluationFor(ChessBoard board, Square square) {
            return null;
        }

        @Override
        public int getMaximumMoves() {
            return 0;
        }
    };
}
