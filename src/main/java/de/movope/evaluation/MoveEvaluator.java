package de.movope.evaluation;

import de.movope.domain.ChessBoard;
import de.movope.domain.Color;
import de.movope.domain.Direction;
import de.movope.domain.Move;
import de.movope.domain.Piece;
import de.movope.domain.PieceType;
import de.movope.domain.Square;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static de.movope.evaluation.MoveEvaluation.Builder.aMoveEvaluation;

public class MoveEvaluator {

    private final ChessBoard board;
    private PieceType pieceType;
    private Color color;
    private boolean considerKingInCheck = true;

    private MoveEvaluator(ChessBoard board) {
        this.board = ChessBoard.copy(board);
    }

    public static MoveEvaluator on(ChessBoard board) {
        return new MoveEvaluator(board);
    }

    public MoveEvaluation analyse(Color color) {
        MoveEvaluation result = MoveEvaluation.empty();
        board.getSquaresWithPiece(color).stream()
                .map(square -> analyse(Square.create(square)))
                .forEach(result::addAll);

        if(considerKingInCheck) {
            result.filterMovesBy(IsKingNotInCheck.forPlayer(color).on(board));
        }

        return result;
    }

    public MoveEvaluator ignoreKingInCheck() {
        considerKingInCheck = false;
        return this;
    }

    public MoveEvaluation analyse(Square square) {
        Piece piece = board.getPieceAt(square);
        pieceType = piece.getPieceType();
        color = piece.getColor();

        final MoveEvaluation.Builder forAllDirections = aMoveEvaluation();

        directions().stream()
                .map(dir -> possibleMoves(dir, square))
                .forEach(result -> forAllDirections
                                        .addMoves(result.possibleTargets())
                                        .addAttacks(result.possibleAttacks()));

        return forAllDirections.create();
    }

    private MoveEvaluation possibleMoves(Direction dir, Square start) {
        if (pieceType == PieceType.PAWN) {
            return possibleMovesForPawn(dir, start);
        }
        final MoveEvaluation.Builder forOneDirection = aMoveEvaluation();

        Square target = Square.copy(start);

        for (int i = 0; i < pieceType.getMaximumMoves(); i++) {
            target = target.move(dir.x(), dir.y());
            if (board.canPieceBeMovedTo(target)) {
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
        final MoveEvaluation.Builder forOneDirection = aMoveEvaluation();
        Square target = Square.copy(start);

        for (int i = 0; i < maximumMovesForPawn(start); i++) {
            target = target.move(dir.x(), dir.y());
            if (board.canPieceBeMovedTo(target)) {
                forOneDirection.addMove(Move.create(start, target));
            }
        }
        List<Direction> attackDirections = Arrays.asList(Direction.create(-1, dir.y()), Direction.create(1, dir.y()));
        attackDirections.stream()
                .map(direction -> (Square.copy(start)).move(direction.x(), direction.y()))
                .filter(attack -> board.occupiedFromEnemy(attack, color.invert()))
                .forEach(attack -> forOneDirection.addAttack(Move.create(start, attack)));

        return forOneDirection.create();
    }

    private int maximumMovesForPawn(Square squareWithPawn) {
        return firstMoveForPawn(squareWithPawn) ? 2 : 1;
    }

    private boolean firstMoveForPawn(Square start) {
        return ((color == Color.BLACK && start.getFile() == 6)) ||
                ((color == Color.WHITE) && start.getFile() == 1);
    }

    private List<Direction> directions() {
        if (pieceType == PieceType.PAWN) {
            if (color == Color.WHITE) {
                return Collections.singletonList(Direction.create(0, 1));
            } else {
                return Collections.singletonList(Direction.create(0, -1));
            }
        }
        return pieceType.getDirections();
    }

    public boolean isPlayerGameOver(Color color) {
        return ! analyse(color).isMovePossible();
    }

    public Move selectRandomMoveForColor(Color color) {
        MoveEvaluation evaluation = analyse(color);
        return evaluation.selectMove();
    }
}
