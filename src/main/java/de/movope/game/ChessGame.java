package main.java.de.movope.game;


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
        ChessGameUtils.print(board);
        System.out.println();
        System.out.println();
        System.out.println();
    }
}