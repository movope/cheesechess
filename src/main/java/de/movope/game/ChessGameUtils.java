package de.movope.game;

import java.util.Map;

public class ChessGameUtils {

    public static void print(ChessBoard boardToPrint) {

        Piece[][] board = getSquaresOfBoard(boardToPrint);

            System.out.println("----------------------");
            for (int i = 7; i >= 0; i--) {
                System.out.print(i + 1 + " ");
                for (int j = 0; j < 8; j++) {
                    System.out.print(board[i][j].printIdentifier() + " ");
                }
                System.out.println();

            }
            System.out.print("  A  B  C  D  E  F  G  H");
            System.out.println();
            System.out.println();
    }

    public static Piece[][] getSquaresOfBoard(ChessBoard boardToPrint) {
        Piece[][] board = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = Piece.NULL;
            }
        }

        for (Map.Entry<String, Piece> p : boardToPrint.pieces.entrySet()) {
            Square position = Square.create(p.getKey());
            board[position.getFile()][position.getRank()] = p.getValue();
        }
        return board;
    }
}
