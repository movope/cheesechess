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

    public MoveEvaluation analyse(Color color) {
        return analyse(color, true);
    }

    private MoveEvaluation analyse(Color color, boolean checkmateCheck) {
        MoveEvaluation evaluation = getMoveEvaluationForAllPiecesOf(color);
        if (checkmateCheck && kingInCheckFor(color)) {
            evaluation.filterMovesBy(move -> !kingInCheckAfter(move, color));
        }
        return evaluation;
    }

    private MoveEvaluation getMoveEvaluationForAllPiecesOf(Color color) {
        MoveEvaluation evaluation = MoveEvaluation.empty();
        for (String square : board.getSquaresWithPiece(color)) {
            evaluation = evaluation.join(analyse(Square.create(square)));
        }
        return evaluation;
    }

    private boolean kingInCheckFor(Color color) {
        MoveEvaluation ofEnemy = analyse(color.invert(), false);
        for (Move move : ofEnemy.possibleAttacks()) {
            if (board.getPieceAt(move.getTo()).getPieceType() == PieceType.KING) {
                return true;
            }
        }
        return false;
    }

    private boolean kingInCheckAfter(Move move, Color color) {
        board.move(move.getFrom(), move.getTo());
        boolean checkmate = kingInCheckFor(color);
        board.move(move.getTo(), move.getFrom());
        return checkmate;
    }

    protected MoveEvaluation analyse(Square square) {
        Piece piece = board.getPieceAt(square);
        pieceType = piece.getPieceType();
        color = piece.getColor();

        final MoveEvaluation.Builder forAllDirections = new MoveEvaluation.Builder();

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
        final MoveEvaluation.Builder forOneDirection = new MoveEvaluation.Builder();

        Square target = (Square) start.clone();

        for (int i = 0; i < pieceType.getMaximumMoves(); i++) {
            target = target.move(dir.x(), dir.y());
            if (board.canPieceMoveTo(target)) {
                forOneDirection.addMove(Move.create(start, target));
            } else {
                if (board.occupiedFromEnemy(target, color.invert())) {
                    forOneDirection.addAttack(Move.create(start, target));
                }
                break;
            }
        }
        return forOneDirection.create();
    }

    private MoveEvaluation possibleMovesForPawn(Direction dir, Square start) {
        final MoveEvaluation.Builder forOneDirection = new MoveEvaluation.Builder();
        Square target = (Square) start.clone();

        boolean firstMove = ((color == Color.BLACK && start.getFile() == 6)) ||
                ((color == Color.WHITE) && start.getFile() == 1);

        int maximumMoves = firstMove ? 2 : 1;

        for (int i = 0; i < maximumMoves; i++) {
            target = target.move(dir.x(), dir.y());
            if (board.canPieceMoveTo(target)) {
                forOneDirection.addMove(Move.create(start, target));
            }
        }
        List<Direction> attackDirections = Arrays.asList(Direction.create(-1, dir.y()), Direction.create(1, dir.y()));
        attackDirections.stream()
                        .map(direction -> ((Square) start.clone()).move(direction.x(), direction.y()))
                        .filter(attack -> board.occupiedFromEnemy(attack, color.invert()))
                        .forEach(attack -> forOneDirection.addAttack(Move.create(start, attack)));

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
