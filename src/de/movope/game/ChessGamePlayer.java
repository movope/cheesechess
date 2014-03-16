package de.movope.game;


public class ChessGamePlayer {

    public static void main(String[] arg) {
        ChessGame game = new ChessGame();
        game.init();

        Player white = game.getWhitePlayer();

        game.print();

        for (int i = 0; i < 30; i++) {
            white.makeRandomMove();
            game.print();
        }
    }
}
