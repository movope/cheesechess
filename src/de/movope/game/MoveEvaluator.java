package de.movope.game;

import de.movope.game.piece.Pawn;
import de.movope.game.piece.Piece;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

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
                .forEach(result -> result.getMoves().stream().forEach(builder::addMove));

        piece.directions().stream()
                .map(dir -> possibleMoves(dir, start, piece))
                .forEach(result -> result.getAttacks().stream()
                        .filter(attack -> board.occupiedFromEnemy(attack, piece.getColor().invert()))
                        .forEach(builder::addAttack));

        return builder.create();
    }

    private EvaluationResult possibleMoves(Point dir, Square start, Piece piece) {
        if (piece instanceof Pawn) {
            return possibleMoves(dir, start, (Pawn) piece);
        }
        EvaluationResult result = new EvaluationResult();
        Square target = (Square) start.clone();

        for (int i = 0; i < piece.getMaximumMoves(); i++) {
            target = target.move(dir.x, dir.y);
            if (board.canPieceMoveTo(target)) {
                result.addMove(target);
            } else {
                if (target.onBoard()) {
                    result.addPossibleAttack(target);
                }
                break;
            }
        }
        return result;
    }

    private EvaluationResult possibleMoves(Point dir, Square start, Pawn pawn) {
        EvaluationResult result = new EvaluationResult();
        Square target = (Square) start.clone();

        boolean firstMove = ((pawn.getColor() == Color.BLACK && start.getFile() == 6)) ||
                ((pawn.getColor() == Color.WHITE) && start.getFile() == 1);

        int maximumMoves = firstMove ? 2 : 1;

        for (int i = 0; i < maximumMoves; i++) {
            target = target.move(dir.x, dir.y);
            if (board.canPieceMoveTo(target)) {
                result.addMove(target);
            }
        }
        List<Point> attackDirections = Arrays.asList(new Point(-1, dir.y), new Point(1, dir.y));
        attackDirections.stream().map(direction -> ((Square) start.clone()).move(direction.x, direction.y))
                                .filter(Square::onBoard)
                                .forEach(result::addPossibleAttack);

        return result;
    }
}
