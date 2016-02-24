package de.movope;

import de.movope.cheesechess.domain.ChessBoard;
import de.movope.cheesechess.domain.Piece;
import de.movope.cheesechess.domain.Square;

import java.util.Map;

public class ChessGamePrinter {

    public static void print(ChessBoard boardToPrint) {

        Piece[][] board = getSquaresOfBoard(boardToPrint);

            System.out.println("----------------------");
            for (int i = 0; i <= 7; i++) {
                System.out.print( 8 - i  + " ");
                for (int j = 0; j < 8; j++) {
                    System.out.print(board[i][j].printIdentifier() + " ");
                }
                System.out.println();

            }
            System.out.print("  A  B  C  D  E  F  G  H");
            System.out.println();
            System.out.println();
    }

    private static Piece[][] getSquaresOfBoard(ChessBoard boardToPrint) {
        Piece[][] board = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = Piece.NULL;
            }
        }

        for (Map.Entry<String, Piece> p : boardToPrint.getPieces().entrySet()) {
            Square position = Square.create(p.getKey());
            board[7 - position.getFile()][position.getRank()] = p.getValue();
        }
        return board;
    }

}
