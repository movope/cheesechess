package de.movope.game;

import java.util.function.Predicate;

public class KingInCheck implements Predicate<Move> {

    private MoveEvaluation forAllEnemyPieces;
    private ChessBoard board;

    public KingInCheck(MoveEvaluation forAllEnemyPieces) {
        this.forAllEnemyPieces = forAllEnemyPieces;
    }

    @Override
    public boolean test(Move move) {
        for (Move attack : forAllEnemyPieces.possibleAttacks()) {
            if (board.getPieceAt(attack.getTo()).getPieceType() == PieceType.KING) {
                return true;
            }
        }
        return false;
    }

    public KingInCheck on(ChessBoard board) {
        this.board = board;
        return this;
    }

}
