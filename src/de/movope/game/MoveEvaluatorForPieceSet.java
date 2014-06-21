package de.movope.game;

public class MoveEvaluatorForPieceSet {

    private ChessBoard board;

    public MoveEvaluatorForPieceSet(ChessBoard board) {
        this.board = board;
    }

    public static MoveEvaluatorForPieceSet on(ChessBoard board) {
        return new MoveEvaluatorForPieceSet(board);
    }

    public MoveEvaluation analyse(Color color) {
        MoveEvaluation evaluation = MoveEvaluatorForPiece.on(board).analyse(color);
        return evaluation.filterMovesBy(move -> !kingInCheckAfter(move, color));
    }

    private boolean kingInCheckAfter(Move move, Color color) {
        ChessBoard boardAfterMove = new ChessBoard(board);
        boardAfterMove.execute(move);
        MoveEvaluation enemyMoves = MoveEvaluatorForPiece.on(boardAfterMove).analyse(color.invert());
        return KingInCheck.forEnemyMoves(enemyMoves)
                          .test(boardAfterMove);
    }
}
