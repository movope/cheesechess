package de.movope.domain;


public enum Color {
    WHITE("w"),
    BLACK("b"),
    UNDEFINED("-");

    Color(String id) {
        this.id = id;
    }

    public Color invert() {
        if (this == Color.WHITE)
            return Color.BLACK;
        if (this == Color.BLACK)
            return Color.WHITE;
        return Color.UNDEFINED;
    }

    public String print() {
        return id;
    }
    private String id;
}
