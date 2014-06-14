package de.movope.game;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MoveEvaluator {

    private ChessBoard board;
    private boolean forPawn = false;
    private List<Point> directions;
    private int maximumMoves;
    private Color color;

    private MoveEvaluator(ChessBoard board) {
        this.board = board;
    }

    public MoveEvaluator(List<Point> directions, int maximumMoves, Color color, boolean forPawn) {
        this.directions = directions;
        this.maximumMoves = maximumMoves;
        this.color = color;
        this.forPawn = forPawn;
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

    public MoveEvaluator pieceColor(Color color) {
        this.color = color;
        return this;
    }

    public MoveEvaluation analyse(Square square) {
        final MoveEvaluation.Builder builder = MoveEvaluation.Builder.startAt(square);
        directions.stream()
                  .map(dir -> possibleMoves(dir, square))
                  .forEach(result -> builder.addMoves(result.getMoves())
                                            .addAttacks(result.getAttacks()));

        return builder.create();
    }

    private EvaluationResult possibleMoves(Point dir, Square start) {
        if (forPawn) {
            return possibleMovesForPawn(dir, start);
        }
        EvaluationResult result = new EvaluationResult();
        Square target = (Square) start.clone();

        for (int i = 0; i < maximumMoves; i++) {
            target = target.move(dir.x, dir.y);
            if (board.canPieceMoveTo(target)) {
                result.addMove(target);
            } else {
                if (board.occupiedFromEnemy(target, color.invert())) {
                    result.addPossibleAttack(target);
                }
                break;
            }
        }
        return result;
    }

    private EvaluationResult possibleMovesForPawn(Point dir, Square start) {
        EvaluationResult result = new EvaluationResult();
        Square target = (Square) start.clone();

        boolean firstMove = ((color == Color.BLACK && start.getFile() == 6)) ||
                ((color == Color.WHITE) && start.getFile() == 1);

        int maximumMoves = firstMove ? 2 : 1;

        for (int i = 0; i < maximumMoves; i++) {
            target = target.move(dir.x, dir.y);
            if (board.canPieceMoveTo(target)) {
                result.addMove(target);
            }
        }
        List<Point> attackDirections = Arrays.asList(new Point(-1, dir.y), new Point(1, dir.y));
        attackDirections.stream().map(direction -> ((Square) start.clone()).move(direction.x, direction.y))
                                .filter(attack -> board.occupiedFromEnemy(attack, color.invert()))
                                .forEach(result::addPossibleAttack);

        return result;
    }

    public MoveEvaluator on(ChessBoard board) {
        this.board = board;
        return this;
    }

    public static class Builder {

        private List<Point> directions;
        private int maximumMoves;
        private Color color;
        private boolean forPawn = false;

        public static Builder forDirections(List<Point> directions) {
            Builder builder = new Builder();
            builder.directions = directions;
            return builder;
        }

        public Builder maximumMoves(int maximumMoves) {
            this.maximumMoves = maximumMoves;
            return this;
        }

        public Builder pieceColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder forPawn() {
            this.forPawn = true;
            return this;
        }

        public MoveEvaluator create() {
            return new MoveEvaluator(directions, maximumMoves, color, forPawn);
        }

    }
}
