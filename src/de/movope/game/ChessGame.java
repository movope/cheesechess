package de.movope.game;


public class ChessGame {

    Board board = new Board();
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
}