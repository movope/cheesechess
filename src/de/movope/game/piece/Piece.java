package de.movope.game.piece;

import de.movope.game.Color;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public interface Piece {

    public java.util.List<Point> directions();
    public String printIdentifier();
    public Color getColor();
    public int getMaximumMoves();

    public static Piece NULL = new Piece() {
        @Override
        public List<Point> directions() {
            return Collections.emptyList();
        }

        @Override
        public String printIdentifier() {
            return "-";
        }

        @Override
        public Color getColor() {
            return null;
        }

        @Override
        public int getMaximumMoves() {
            return 0;
        }
    };
}
