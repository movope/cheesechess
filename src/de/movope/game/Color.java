package de.movope.game;


public enum Color {
    WHITE, BLACK, UNDEFINED;

    public Color invert() {
        if (this == Color.WHITE)
            return Color.BLACK;
        if (this == Color.BLACK)
            return Color.WHITE;
        return Color.UNDEFINED;
    }
}
