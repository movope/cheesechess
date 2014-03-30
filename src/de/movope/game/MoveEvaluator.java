package de.movope.game;

import de.movope.game.pieces.Piece;

import java.awt.*;

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


    private MoveEvaluation determinePossibleTargetsOnBoard(final Square start, final Piece piece) {

        final MoveEvaluation.Builder builder = new MoveEvaluation.Builder();
        builder.startAt(start);

        piece.directions().stream()
                .map(dir -> possibleMoves(dir, start, piece))
                .forEach(result -> {
                    result.getMoves().stream().forEach(builder::addMove);
                });

        piece.directions().stream()
                .map(dir -> possibleMoves(dir, start, piece))
                .filter(result -> occupiedFromEnemy(result.getAttack(), piece.getColor().invert()))
                .forEach(result -> builder.addAttack(result.getAttack()));

        return builder.create();
    }

    private EvaluationResult possibleMoves(Point dir, Square start, Piece piece) {
        EvaluationResult result = new EvaluationResult();
        Square target = (Square) start.clone();

        for (int i = 0; i < piece.getMaximumMoves(); i++) {
            target = target.move(dir);
            if (canPieceMoveTo(target)) {
                result.addMove(target);
            } else {
                result.setAttack(target);
                break;
            }
        }
        return result;
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
