package de.movope.game;

import de.movope.game.pieces.Pawn;
import de.movope.game.pieces.Piece;

import java.awt.*;
import java.util.Iterator;

public class MoveEvaluator {

    ChessBoard board;

    public MoveEvaluation analyse(ChessBoard board, String aSquare) {

        this.board = board;
        Square square = Square.create(aSquare);
        if (!square.onBoard()) {
            return null;
        }
        Piece piece = board.getPieceAt(aSquare);
        if (piece == Piece.NULL) {
            return null;
        }


        return determinePossibleTargetsOnBoard(square, piece);
    }


    private MoveEvaluation determinePossibleTargetsOnBoard(Square start, Piece piece) {

        MoveEvaluation.Builder builder = new MoveEvaluation.Builder();
        builder.startAt(start);
        Iterator<Point> it = piece.directions();
        while (it.hasNext()) {
            Point dir = it.next();
            Square target = (Square) start.clone();
            int max = piece.getMaximumMoves();
            for (int i = 0; i < max; i++) {
                target = target.move(dir);
                if (canPieceMoveTo(target)) {
                    builder.addMove(target);
                } else {
                    break;
                }
            }
            if (!(piece instanceof Pawn)) {
                if (occupiedFromEnemy(target, piece.getColor().invert())) {
                    builder.addAttack(target);
                }
            }
        }
        if (piece instanceof Pawn) {
            for (Point dir : piece.getAttackDirections()) {
                Square target = (Square) start.clone();
                target = target.move(dir);
                if (occupiedFromEnemy(target, piece.getColor().invert())) {
                    builder.addAttack(target);
                }
            }
        }
        return builder.create();
    }

    private boolean occupiedFromEnemy(Square target, Color enemyColor) {
        if ((target == null) || !target.onBoard()) {
            return false;
        }
        return board.getPieceAt(target).getColor() == enemyColor;
    }

    private boolean canPieceMoveTo(Square target) {
        return target.onBoard() && board.getPieceAt(target) == Piece.NULL;
    }
}
