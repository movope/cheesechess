package de.movope.web;

import de.movope.domain.Color;
import de.movope.domain.PieceType;

public class PieceView {

    public static final PieceView NULL = new PieceView();

    private PieceType pieceType;
    private Color color;

    public PieceView() {
    }

    public PieceView(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceView)) return false;

        PieceView pieceView = (PieceView) o;

        if (color != pieceView.color) return false;
        if (pieceType != pieceView.pieceType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }
}
