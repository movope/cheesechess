package de.movope.game;

import java.util.Collection;

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
        MoveEvaluation evaluation = MoveEvaluatorForPieceSet.on(board).analyse(color);
        if (!evaluation.isMovePossible()) {
            gameOver();
            return;
        }

        Move move;
        if (evaluation.possibleAttacks().size() > 0) {
            move = chooseOneOf(evaluation.possibleAttacks());
        } else {
            move = chooseOneOf(evaluation.possibleTargets());
        }
        board.movePiece(move.getFrom(), move.getTo());
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
}
