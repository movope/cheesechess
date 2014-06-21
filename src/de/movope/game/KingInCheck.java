package de.movope.game;

import java.util.function.Predicate;

public class KingInCheck implements Predicate<ChessBoard> {

    private MoveEvaluation enemyMoves;

    private KingInCheck(MoveEvaluation enemyMoves) {
        this.enemyMoves = enemyMoves;
    }

    static public KingInCheck forEnemyMoves(MoveEvaluation enemyMoves) {
        return new KingInCheck(enemyMoves);
    }

    @Override
    public boolean test(ChessBoard board) {
        for (Move attack : enemyMoves.possibleAttacks()) {
            if (board.getPieceAt(attack.to()).getPieceType() == PieceType.KING) {
                return true;
            }
        }
        return false;
    }
}
