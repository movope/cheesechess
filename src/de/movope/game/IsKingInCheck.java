package de.movope.game;

import java.util.function.Predicate;

public class IsKingInCheck implements Predicate<ChessBoard> {

    private static Color color;

    public IsKingInCheck() {
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

    public IsKingInCheck forPlayer(Color color) {
        IsKingInCheck.color = color;
        return this;
    }
}
