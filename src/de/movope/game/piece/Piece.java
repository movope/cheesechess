package de.movope.game.piece;

import de.movope.game.Color;

import java.awt.*;
import java.util.List;

public class Piece {

    Color color;
    String id;
    List<Point> directions;
    int maximumMoves;

    public static Piece NULL= new Piece();

    private Piece() {
    }

    public Piece(Color color) {
        this.color = color;
    }

    public List<Point> directions() {
        return directions;
    }

    public String printIdentifier() {
        return id;
    }

    public Color getColor() {
        return color;
    }


    public int getMaximumMoves() {
        return maximumMoves;
    }


}
