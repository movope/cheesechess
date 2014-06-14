package de.movope.game.piece;

import de.movope.game.Color;

import java.awt.*;
import java.util.List;

public interface Piece {

    public String printIdentifier();
    public Color getColor();
    public List<Point> getDirections();
    int getMaximumMoves();

    public static Piece NULL = new Piece() {
        @Override
        public String printIdentifier() {
            return "-";
        }

        @Override
        public Color getColor() {
            return null;
        }

        @Override
        public List<Point> getDirections() {
            return null;
        }

        @Override
        public int getMaximumMoves() {
            return 0;
        }
    };
}
