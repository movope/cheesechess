package de.movope.cheesechess.domain;

import com.google.common.base.Objects;

public class Piece {

    private final PieceType pieceType;
    private final Color color;

    public Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public static final Piece NULL = new Piece(PieceType.NULL, Color.UNDEFINED);

    public String printIdentifier() {
        return color.print() + pieceType.print();
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType ;
    }

    @Override
    public String toString() {
        return "{" + pieceType + " " + color + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType &&
                color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pieceType, color);
    }
}
