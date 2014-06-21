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
        MoveEvaluation evaluation = getMoveEvaluationForAllPiecesOf(board, color);
        return evaluation.filterMovesBy(move -> !kingInCheckAfter(move, color));
    }

    private boolean kingInCheckAfter(Move move, Color color) {
        ChessBoard boardAfterMove = new ChessBoard(board);
        boardAfterMove.movePiece(move.getFrom(), move.getTo());
        MoveEvaluation enemyMoves = getMoveEvaluationForAllPiecesOf(boardAfterMove, color.invert());
        return KingInCheck.forEnemyMoves(enemyMoves)
                          .test(boardAfterMove);
    }


    private MoveEvaluation getMoveEvaluationForAllPiecesOf(ChessBoard board, Color color) {
        MoveEvaluation result = MoveEvaluation.empty();
        board.getSquaresWithPiece(color).stream()
                    .map(square -> MoveEvaluatorForPiece.on(board).analyse(Square.create(square)))
                    .forEach(result::addAll);
        return result;
    }
}
