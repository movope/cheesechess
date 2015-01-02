package de.movope.game;

import java.util.Collection;

public class Player {

    private Color color;
    private boolean gameOver = false;
    private boolean isComputer = false;

    public Player(Color color) {
        this.color = color;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void gameOver() {
        System.out.println(color + " lost! ");
        gameOver = true;
    }

    private Move chooseOneOf(Collection<Move> moves) {
        Move[] move = moves.toArray(new Move[moves.size()]);
        int rand = (int) (moves.size() * Math.random());
        return move[rand];
    }

    public Move getRandomMove(ChessBoard board) {
        MoveEvaluation evaluation = MoveEvaluator.on(board)
                .considerKingInCheck()
                .analyse(color);
        if (!evaluation.isMovePossible()) {
            gameOver();
            return null;
        }

        Move move;
        if (evaluation.possibleAttacks().size() > 0) {
            move = chooseOneOf(evaluation.possibleAttacks());
        } else {
            move = chooseOneOf(evaluation.possibleTargets());
        }
        return move;
    }

    public void activateComputer() {
        isComputer = true;
    }

    public boolean isControlledByComputer() {
        return isComputer;
    }
}
