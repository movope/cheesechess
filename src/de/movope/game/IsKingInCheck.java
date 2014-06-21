package de.movope.game;

import java.util.function.Predicate;

public class IsKingInCheck implements Predicate<Move> {

    private static Color color;
    private ChessBoard board;

    private IsKingInCheck(Color color) {
        this.color = color;
    }

    @Override
    public boolean test(Move move) {
        ChessBoard boardAfterMove = new ChessBoard(board);
        boardAfterMove.execute(move);
        MoveEvaluation enemyMoves = MoveEvaluatorForPiece.on(boardAfterMove).analyse(color.invert());
        for (Move attack : enemyMoves.possibleAttacks()) {
            if (boardAfterMove.getPieceAt(attack.to()).getPieceType() == PieceType.KING) {
                return true;
            }
        }
        return false;
    }

    public static IsKingInCheck forPlayer(Color color) {
        return new IsKingInCheck(color);
    }

    public IsKingInCheck on(ChessBoard board) {
        this.board = board;
        return this;
    }
}
