package de.movope.game;


public class ChessGamePlayer {

    public static void main(String[] arg) {
        ChessGame game = new ChessGame();
        game.init();

        Player white = game.getWhitePlayer();
        Player black = game.getBlackPlayer();

        game.print();

        for (int i = 0; i < 4; i++) {
            white.makeRandomMove();
            game.print();
            black.makeRandomMove();
            game.print();
        }
    }
}
