package de.movope.game;

import java.util.Collection;
import java.util.Iterator;

public class Player {

    private ChessBoard board;
    private Color color;

    public Player(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    public void makeRandomMove() {
        MoveEvaluation evaluation = MoveEvaluation.empty();
        while (!evaluation.isMovePossible()) {
            String randomSquare = getRandomSquareWithPiece();
            evaluation = MoveEvaluator.with(board).analyse(randomSquare);
        }
        Square target;
        if (evaluation.possibleAttacks().size() > 0) {
            target = chooseOneOf(evaluation.possibleAttacks());
        } else {
            target = chooseOneOf(evaluation.possibleTargets());
        }
        board.move(evaluation.getStart(), target);

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

}
