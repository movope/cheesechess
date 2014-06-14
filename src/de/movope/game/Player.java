package de.movope.game;

import java.util.Collection;
import java.util.Iterator;

public class Player {

    private ChessBoard board;
    private Color color;
    private boolean gameOver = false;

    public Player(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void makeRandomMove() {
        MoveEvaluation evaluation = MoveEvaluation.empty();

        if (checkmateOf(color)) {
            tryToMoveKing();
            return;
        } else {
            while (!evaluation.isMovePossible()) {
                Square randomSquare = Square.create(getRandomSquareWithPiece());
                evaluation = MoveEvaluator.on(board).analyse(randomSquare);
            }
        }
        Move move;
        if (evaluation.possibleAttacks().size() > 0) {
            move = chooseOneOf(evaluation.possibleAttacks());
        } else {
            move = chooseOneOf(evaluation.possibleTargets());
        }
        board.move(move.getFrom(), move.getTo());

    }

    public boolean checkmateOf(Color color) {
        MoveEvaluation evaluation = getMoveEvaluationOfAllPieces(color.invert());
        for (Move move : evaluation.possibleAttacks()) {
            if (board.getPieceAt(move.getTo()).getPieceType() == PieceType.KING) {
                return true;
            }
        }
        return false;
    }

    private void tryToMoveKing() {
        Square squareWithKing = board.getSquareOfKing(color);
        MoveEvaluation evaluation = MoveEvaluator.on(board).analyse(squareWithKing);
        if (!evaluation.isMovePossible()) {
            gameOverFor(color);
        } else {
            for (Move move : evaluation.possibleTargets()) {
                MoveEvaluation evaluationOfAllPieces = getMoveEvaluationOfAllPieces(color.invert());
                if (!evaluationOfAllPieces.possibleTargets().contains(move.getTo())) {
                    board.move(evaluation.getStart(), move.getTo());
                    return;
                }
            }
        }
        gameOverFor(color);
    }

    private void gameOverFor(Color color) {
        System.out.println(color + " lost! ");
        gameOver = true;
    }

    private Move chooseOneOf(Collection<Move> possibleTargets) {
        Move[] targets = possibleTargets.toArray(new Move[possibleTargets.size()]);
        int rand = (int) (possibleTargets.size() * Math.random());
        return targets[rand];
    }

    private String getRandomSquareWithPiece() {
        Collection<String> squares = board.getSquaresWithPiece(color);
        int randomInt = (int) (Math.random() * squares.size());
        Iterator<String> it = squares.iterator();
        for (int i = 0; i < randomInt - 1; i++) {
            it.next();
        }
        return it.next();
    }

    public MoveEvaluation getMoveEvaluationOfAllPieces(Color color) {
        MoveEvaluation evaluation = MoveEvaluation.empty();
        for (String square : board.getSquaresWithPiece(color)) {
            evaluation = evaluation.join(MoveEvaluator.on(board).analyse(Square.create(square)));
        }
        return evaluation;
    }

}
