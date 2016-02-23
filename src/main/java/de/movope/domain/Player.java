package de.movope.domain;

public class Player {

    private boolean gameOver = false;
    private boolean isComputer = false;

    public boolean isGameOver() {
        return gameOver;
    }

    public void gameOver() {
        gameOver = true;
    }

    public void activateComputer() {
        isComputer = true;
    }

    public boolean isControlledByComputer() {
        return isComputer;
    }
}
