package de.movope.game;


import de.movope.game.pieces.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Board {

    Set<Piece> whitePieces = new HashSet<>();
    Set<Piece> blackPieces = new HashSet<>();

    public void initPieces() {
        initPieces(Color.WHITE, whitePieces);
        initPieces(Color.BLACK, blackPieces);
    }

    private void initPieces(Color color, Set<Piece> pieces) {
        int row;
        if (color == Color.WHITE) {
            row = 1;
        } else {
            row = 8;
        }
        pieces.add(new Rook(color, "A" + row));
        pieces.add(new Rook(color, "H" + row));
        pieces.add(new Knight(color, "B" + row));
        pieces.add(new Knight(color, "G" + row));
        pieces.add(new Bishop(color, "C" + row));
        pieces.add(new Bishop(color, "F" + row));
        pieces.add(new Queen(color, "D" + row));
        pieces.add(new King(color, "E" + row));

        if (color == Color.WHITE) {
            row++;
        } else {
            row--;
        }

        String[] files = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(color, files[i] + row));
        }
    }


    public void print() {
        String[][] board = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = "-";
            }
        }

        for (Piece p : blackPieces) {
            Square position = p.getPosition();
            board[position.getFile()][position.getRank()] = p.printIdentifier();
        }

        for (Piece p : whitePieces) {
            Square position = p.getPosition();
            board[position.getFile()][position.getRank()] = p.printIdentifier();
        }


        System.out.println("----------------------");
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");

            }
            System.out.println();

        }
        System.out.print("  A B C D E F G H");
        System.out.println();
        System.out.println();
    }

    public Collection<Piece> getPieces(Color color) {
        if (color == Color.WHITE)
            return whitePieces;
        return blackPieces;
    }

    public Piece getPieceAt(String square) {
        for (Piece p : whitePieces) {
            if (p.getPosition().equals(Square.create(square))) {
                return p;
            }
        }
        for (Piece p : blackPieces) {
            if (p.getPosition().equals(Square.create(square))) {
                return p;
            }
        }

        return Piece.NULL;
    }


}
