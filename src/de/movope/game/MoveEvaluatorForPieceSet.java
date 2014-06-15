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
        MoveEvaluation evaluation = getMoveEvaluationForAllPiecesOf(color);
        evaluation.filterMovesBy(move -> !kingInCheckAfter(move, color));
        return evaluation;
    }

    private boolean kingInCheckAfter(Move move, Color color) {
        board.move(move.getFrom(), move.getTo());
        boolean checkmate = new KingInCheck(getMoveEvaluationForAllPiecesOf(color.invert())).test(board);
        board.move(move.getTo(), move.getFrom());
        return checkmate;
    }

    private MoveEvaluation getMoveEvaluationForAllPiecesOf(Color color) {
        MoveEvaluation evaluation = MoveEvaluation.empty();
        for (String square : board.getSquaresWithPiece(color)) {
            MoveEvaluation ofSquare = MoveEvaluatorForPiece.on(board).analyse(Square.create(square));
            evaluation = evaluation.join(ofSquare);
        }
        return evaluation;
    }
}
