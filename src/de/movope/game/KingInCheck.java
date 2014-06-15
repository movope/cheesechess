package de.movope.game;

import java.util.function.Predicate;

public class KingInCheck implements Predicate<ChessBoard> {

    private MoveEvaluation enemyMoves;

    public KingInCheck(MoveEvaluation enemyMoves) {
        this.enemyMoves = enemyMoves;
    }

    @Override
    public boolean test(ChessBoard board) {
        for (Move attack : enemyMoves.possibleAttacks()) {
            if (board.getPieceAt(attack.getTo()).getPieceType() == PieceType.KING) {
                return true;
            }
        }
        return false;
    }
}
