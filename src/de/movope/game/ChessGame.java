package de.movope.game;


public class ChessGame {

    ChessBoard board = new ChessBoard();
    Player white = new Player(board, Color.WHITE);
    Player black = new Player(board, Color.BLACK);

    public void init() {
        board.initPieces();
    }


    public Player getWhitePlayer() {
        return white;
    }

    public Player getBlackPlayer() {
        return black;
    }

    public void print() {
        board.print();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void main(String[] arg) {
        ChessGame game = new ChessGame();
        game.init();

        Player white = game.getWhitePlayer();
        Player black = game.getBlackPlayer();

        game.print();

        for (int i = 0; i < 40; i++) {
            white.makeRandomMove();
            game.print();
            black.makeRandomMove();
            game.print();
        }
    }
}