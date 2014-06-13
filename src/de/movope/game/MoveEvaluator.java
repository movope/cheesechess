package de.movope.game;

import de.movope.game.piece.Piece;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MoveEvaluator {

    ChessBoard board;

    boolean forPawn = false;
    private List<Point> directions;
    private int maximumMoves;

    private MoveEvaluator(ChessBoard board) {
        this.board = board;
    }

    public static MoveEvaluator with(ChessBoard board) {
        return new MoveEvaluator(board);
    }

    public MoveEvaluator forPawn() {
        forPawn = true;
        return this;
    }

    public  MoveEvaluator forDirections(List<Point> directions) {
        this.directions = directions;
        return this;
    }

    public MoveEvaluator maximumMoves(int maximumMoves) {
        this.maximumMoves = maximumMoves;
        return this;
    }

    public MoveEvaluation analyse(Square square) {
        Piece piece = board.getPieceAt(square);
        if (piece == Piece.NULL) {
            return null;
        }
        return determinePossibleTargets(square, piece);
    }

    public MoveEvaluation analyse(String square) {
       return analyse(Square.create(square));
    }


    private MoveEvaluation determinePossibleTargets(final Square start, final Piece piece) {

        final MoveEvaluation.Builder builder = MoveEvaluation.Builder.startAt(start);
        directions.stream()
                  .map(dir -> possibleMoves(dir, start, piece))
                  .forEach(result -> builder.addMoves(result.getMoves())
                          .addAttacks(result.getAttacks()));

        return builder.create();
    }

    private EvaluationResult possibleMoves(Point dir, Square start, Piece piece) {
        if (forPawn) {
            return possibleMovesForPawn(dir, start, piece);
        }
        EvaluationResult result = new EvaluationResult();
        Square target = (Square) start.clone();

        for (int i = 0; i < maximumMoves; i++) {
            target = target.move(dir.x, dir.y);
            if (board.canPieceMoveTo(target)) {
                result.addMove(target);
            } else {
                if (board.occupiedFromEnemy(target, piece.getColor().invert())) {
                    result.addPossibleAttack(target);
                }
                break;
            }
        }
        return result;
    }

    private EvaluationResult possibleMovesForPawn(Point dir, Square start, Piece pawn) {
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
                                .filter(attack -> board.occupiedFromEnemy(attack, pawn.getColor().invert()))
                                .forEach(result::addPossibleAttack);

        return result;
    }
}
