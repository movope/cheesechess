package de.movope.cheesechess.web.api;

import de.movope.cheesechess.domain.Color;
import de.movope.cheesechess.domain.PieceType;

public class PieceView {

    public static final PieceView NULL = new PieceView();

    private PieceType pieceType = PieceType.NULL;
    private Color color= Color.UNDEFINED;

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
