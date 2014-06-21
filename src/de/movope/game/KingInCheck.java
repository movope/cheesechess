package de.movope.game;

import java.util.function.Predicate;

public class KingInCheck implements Predicate<ChessBoard> {

    private static Color color;

    public KingInCheck() {
    }

    @Override
    public boolean test(ChessBoard board) {
        MoveEvaluation enemyMoves = MoveEvaluatorForPiece.on(board).analyse(color.invert());
        for (Move attack : enemyMoves.possibleAttacks()) {
            if (board.getPieceAt(attack.to()).getPieceType() == PieceType.KING) {
                return true;
            }
        }
        return false;
    }

    public KingInCheck forPlayer(Color color) {
        KingInCheck.color = color;
        return this;
    }
}
