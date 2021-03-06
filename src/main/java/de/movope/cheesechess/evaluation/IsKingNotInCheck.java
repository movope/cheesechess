package de.movope.cheesechess.evaluation;

import de.movope.cheesechess.domain.ChessBoard;
import de.movope.cheesechess.domain.Color;
import de.movope.cheesechess.domain.Move;
import de.movope.cheesechess.domain.PieceType;

import java.util.function.Predicate;

class IsKingNotInCheck implements Predicate<Move> {

    private final Color color;
    private ChessBoard board;

    private IsKingNotInCheck(Color color) {
        this.color = color;
    }

    @Override
    public boolean test(Move move) {
        ChessBoard boardAfterMove = ChessBoard.copy(board);
        boardAfterMove.execute(move);
        MoveEvaluation enemyMoves = MoveEvaluator.on(boardAfterMove).ignoreKingInCheck().analyse(color.invert());
        for (Move attack : enemyMoves.possibleAttacks()) {
            if (boardAfterMove.getPieceAt(attack.to()).getPieceType() == PieceType.KING) {
                return false;
            }
        }
        return true;
    }

    public static IsKingNotInCheck forPlayer(Color color) {
        return new IsKingNotInCheck(color);
    }

    public IsKingNotInCheck on(ChessBoard board) {
        this.board = board;
        return this;
    }
}
