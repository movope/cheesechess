package de.movope.game;

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

    public void gameOver() {
        System.out.println(color + " lost! ");
        gameOver = true;
    }

    public Move getRandomMove(ChessBoard board) {
        return MoveEvaluator.on(board)
                .considerKingInCheck()
                .analyse(color)
                .selectMove();
    }

    public void activateComputer() {
        isComputer = true;
    }

    public boolean isControlledByComputer() {
        return isComputer;
    }
}
