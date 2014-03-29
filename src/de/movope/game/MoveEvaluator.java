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
        removeUnreachableTargets();
        return result;
    }


    private void determinePossibleTargetsOnBoard(Square start, Piece piece) {
        Iterator<Point> it = piece.directions();
        while (it.hasNext()) {
            Point dir = it.next();
            Square target = (Square) start.clone();
            target = target.move(dir);
            for (int i = 0; i < piece.getMaximumMoves() && target.onBoard(); i++) {
                result.add(target);
                target = target.move(dir);
            }
            result.nextDirection();
        }
    }


    private void removeUnreachableTargets() {
        Iterator<MoveVector> vectorIterator = result.getVectorIterator();
        while (vectorIterator.hasNext()) {
            MoveVector moveVector = vectorIterator.next();
            Iterator<Square> moveIterator = moveVector.iterator();
            boolean deleteFollowing = false;
            while (moveIterator.hasNext()) {
                Square target = moveIterator.next();
                if ((board.getPieceAt(target) != Piece.NULL) || deleteFollowing) {
                    moveIterator.remove();
                    deleteFollowing = true;
                }
            }
        }
        result.deleteEmptyVectors();
    }
}
