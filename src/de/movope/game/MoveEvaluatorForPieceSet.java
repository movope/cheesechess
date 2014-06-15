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
        evaluation.filterMovesBy(move -> !kingInCheckAfter(move, color));
        return evaluation;
    }

    private boolean kingInCheckAfter(Move move, Color color) {
        ChessBoard copy = new ChessBoard(board);
        copy.move(move.getFrom(), move.getTo());
        return new KingInCheck(getMoveEvaluationForAllPiecesOf(copy, color.invert()))
                                .on(copy)
                                .test(move);
    }

    private MoveEvaluation getMoveEvaluationForAllPiecesOf(ChessBoard board, Color color) {
        MoveEvaluation evaluation = MoveEvaluation.empty();
        for (String square : board.getSquaresWithPiece(color)) {
            MoveEvaluation ofSquare = MoveEvaluatorForPiece.on(board).analyse(Square.create(square));
            evaluation = evaluation.join(ofSquare);
        }
        return evaluation;
    }
}
