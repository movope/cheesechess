package de.movope.game.piece;

import de.movope.game.Color;

import java.awt.*;
import java.util.List;



public class Piece {

    PieceType pieceType;
    Color color;

    public Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public static final Piece NULL = new Piece(PieceType.NULL, Color.UNDEFINED);

    public String printIdentifier() {
        return pieceType.print();
    }

    public Color getColor() {
        return color;
    }

    public List<Point> getDirections() {
        return pieceType.getDirections();
    }

    public int getMaximumMoves() {
        return pieceType.getMaximumMoves();
    }

    public PieceType getPieceType() {
        return pieceType ;
    }
}
