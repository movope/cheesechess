package de.movope.game;

import java.util.Arrays;
import java.util.List;

public class MoveEvaluator {

    private ChessBoard board;
    private PieceType pieceType;
    private Color color;

    public MoveEvaluator(ChessBoard board) {
        this.board = board;
    }

    public static MoveEvaluator on(ChessBoard board) {
        return new MoveEvaluator(board);
    }

    public MoveEvaluation analyse(Square square) {
        Piece piece = board.getPieceAt(square);
        pieceType = piece.getPieceType();
        color = piece.getColor();

        final MoveEvaluation.Builder forAllDirections = MoveEvaluation.Builder.startAt(square);

        directions().stream()
                .map(dir -> possibleMoves(dir, square))
                .forEach(result -> forAllDirections.addMoves(result.possibleTargets())
                                          .addAttacks(result.possibleAttacks()));

        return forAllDirections.create();
    }

    private MoveEvaluation possibleMoves(Direction dir, Square start) {
        if (pieceType == PieceType.PAWN) {
            return possibleMovesForPawn(dir, start);
        }
        final MoveEvaluation.Builder forOneDirection = MoveEvaluation.Builder.startAt(start);

        Square target = (Square) start.clone();

        for (int i = 0; i < pieceType.getMaximumMoves(); i++) {
            target = target.move(dir.x(), dir.y());
            if (board.canPieceMoveTo(target)) {
                forOneDirection.addMove(target);
            } else {
                if (board.occupiedFromEnemy(target, color.invert())) {
                    forOneDirection.addAttack(target);
                }
                break;
            }
        }
        return forOneDirection.create();
    }

    private MoveEvaluation possibleMovesForPawn(Direction dir, Square start) {
        final MoveEvaluation.Builder forOneDirection = MoveEvaluation.Builder.startAt(start);
        Square target = (Square) start.clone();

        boolean firstMove = ((color == Color.BLACK && start.getFile() == 6)) ||
                ((color == Color.WHITE) && start.getFile() == 1);

        int maximumMoves = firstMove ? 2 : 1;

        for (int i = 0; i < maximumMoves; i++) {
            target = target.move(dir.x(), dir.y());
            if (board.canPieceMoveTo(target)) {
                forOneDirection.addMove(target);
            }
        }
        List<Direction> attackDirections = Arrays.asList(Direction.create(-1, dir.y()), Direction.create(1, dir.y()));
        attackDirections.stream().map(direction -> ((Square) start.clone()).move(direction.x(), direction.y()))
                .filter(attack -> board.occupiedFromEnemy(attack, color.invert()))
                .forEach(forOneDirection::addAttack);

        return forOneDirection.create();
    }

    private List<Direction> directions() {
        if (pieceType == PieceType.PAWN) {
            if (color == Color.WHITE) {
                return Arrays.asList(Direction.create(0, 1));
            } else {
                return Arrays.asList(Direction.create(0, -1));
            }
        }
        return pieceType.getDirections();
    }
}
