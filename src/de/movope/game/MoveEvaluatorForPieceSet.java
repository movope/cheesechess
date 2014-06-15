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

    private MoveEvaluation getMoveEvaluationForAllPiecesOf(Color color) {
        MoveEvaluation evaluation = MoveEvaluation.empty();
        for (String square : board.getSquaresWithPiece(color)) {
            evaluation = evaluation.join(analyse(Square.create(square)));
        }
        return evaluation;
    }

    private boolean kingInCheckFor(Color color) {
        MoveEvaluation ofEnemy = getMoveEvaluationForAllPiecesOf(color.invert());
        for (Move move : ofEnemy.possibleAttacks()) {
            if (board.getPieceAt(move.getTo()).getPieceType() == PieceType.KING) {
                return true;
            }
        }
        return false;
    }

    private boolean kingInCheckAfter(Move move, Color color) {
        board.move(move.getFrom(), move.getTo());
        boolean checkmate = kingInCheckFor(color);
        board.move(move.getTo(), move.getFrom());
        return checkmate;
    }

    protected MoveEvaluation analyse(Square square) {
        return MoveEvaluatorForPiece.on(board).analyse(square);
    }
}
