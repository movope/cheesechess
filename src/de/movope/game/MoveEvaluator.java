package de.movope.game;

import de.movope.game.pieces.Piece;

import java.awt.*;
import java.util.Iterator;

public class MoveEvaluator {

    MoveEvaluationResult result;
    Board board;

    public MoveEvaluationResult analyse(Board board, String aSquare) {

        this.board = board;
        Square square = Square.create(aSquare);
        if (!square.onBoard()) {
            return null;
        }
        Piece piece = board.getPieceAt(aSquare);
        if (piece == Piece.NULL) {
            return null;
        }

        result = new MoveEvaluationResult();
        determinePossibleTargetsOnBoard(square, piece);
        return result;
    }


    private void determinePossibleTargetsOnBoard(Square start, Piece piece) {

        Iterator<Point> it = piece.directions();
        while (it.hasNext()) {
            Point dir = it.next();
            Square target = (Square) start.clone();
            target = target.move(dir);
            int max = piece.getMaximumMoves();
            for (int i = 0; i < max && canPieceMoveTo(target); i++) {
                result.add(target);
                target = target.move(dir);
            }
            if (occupiedFromEnemy(target, piece.getColor().invert())) {
                result.addPossibleAttack(target);
            }
            result.nextDirection();
        }
    }

    private boolean occupiedFromEnemy(Square target, Color enemyColor) {
        return board.getPieceAt(target).getColor() == enemyColor;
    }

    private boolean canPieceMoveTo(Square target) {
        return target.onBoard() && board.getPieceAt(target) == Piece.NULL;
    }
}
