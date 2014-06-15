package de.movope.game;

import java.util.function.Predicate;

public class KingInCheck implements Predicate <ChessBoard> {

    private MoveEvaluation forAllEnemyPieces;

    public KingInCheck(MoveEvaluation forAllEnemyPieces) {
        this.forAllEnemyPieces = forAllEnemyPieces;
    }

    @Override
    public boolean test(ChessBoard board) {
        for (Move move : forAllEnemyPieces.possibleAttacks()) {
            if (board.getPieceAt(move.getTo()).getPieceType() == PieceType.KING) {
                return true;
            }
        }
        return false;
    }
}
