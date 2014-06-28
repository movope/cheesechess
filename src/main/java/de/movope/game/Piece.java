package de.movope.game;

public class Piece {

    private PieceType pieceType;
    private Color color;

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
}
