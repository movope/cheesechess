package de.movope.domain;

public class Player {

    private boolean gameOver = false;
    private final boolean isComputer;

    private Player(boolean isComputer) {
        this.isComputer = isComputer;
    }

    public static Player computer() {
        Player player = new Player(true);
        return  player;
    }

    public static Player newPlayer() {
        return  new Player(false);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void gameOver() {
        gameOver = true;
    }

    public boolean isControlledByComputer() {
        return isComputer;
    }
}
