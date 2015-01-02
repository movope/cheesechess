package de.movope.util;

import de.movope.game.ChessBoard;
import de.movope.game.Piece;
import de.movope.game.Square;
import de.movope.web.PieceView;

import java.util.Map;

public class ChessGameUtils {

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

    public static Piece[][] getSquaresOfBoard(ChessBoard boardToPrint) {
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

    public static PieceView[][] getViewOfBoard(ChessBoard boardToPrint) {
        PieceView[][] board = new PieceView[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = PieceView.NULL;
            }
        }

        for (Map.Entry<String, Piece> p : boardToPrint.getPieces().entrySet()) {
            Square position = Square.create(p.getKey());
            board[7 - position.getFile()][position.getRank()] = toPieceView(p.getValue());
        }
        return board;
    }

    private static PieceView toPieceView(Piece piece) {
        return new PieceView(piece.getPieceType(), piece.getColor());
    }
}
