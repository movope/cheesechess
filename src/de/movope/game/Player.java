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
        Square target;
        if (evaluation.possibleAttacks().size() > 0) {
            target = chooseOneOf(evaluation.possibleAttacks());
        } else {
            target = chooseOneOf(evaluation.possibleTargets());
        }
        board.move(evaluation.getStart(), target);

    }

    public boolean checkmateOf(Color color) {
        MoveEvaluation evaluation = getMoveEvaluationOfAllPieces(color.invert());
        for (Square attack : evaluation.possibleAttacks()) {
            if (board.getPieceAt(attack).getPieceType() == PieceType.KING) {
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
            for (Square target : evaluation.possibleTargets()) {
                MoveEvaluation evaluationOfAllPieces = getMoveEvaluationOfAllPieces(color.invert());
                if (!evaluationOfAllPieces.possibleTargets().contains(target)) {
                    board.move(evaluation.getStart(), target);
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

    private Square chooseOneOf(Collection<Square> possibleTargets) {
        Square[] targets = possibleTargets.toArray(new Square[possibleTargets.size()]);
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
