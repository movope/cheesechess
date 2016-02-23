package de.movope.domain;

public class Player {

    private boolean gameOver = false;
    private boolean isComputer = false;

    private Player() {
    }

    public static Player computer() {
        Player player = new Player();
        player.isComputer = true;
        return  player;
    }

    public static Player newPlayer() {
        return  new Player();
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
