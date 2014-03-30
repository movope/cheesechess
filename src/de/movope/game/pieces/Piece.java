package de.movope.game.pieces;

import de.movope.game.*;
import de.movope.game.Color;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class Piece {

    Color color;
    Square currentPosition;
    String id;
    List<Point> directions;
    int maximumMoves;

    public static Piece NULL= new Piece();

    private Piece() {
    }

    public Piece(Color color, String position) {
        this.color = color;
        currentPosition = Square.create(position);
    }

    public List<Point> getAttackDirections() {
        return directions;
    }


    public Square getPosition() {
        return currentPosition;
    }

    public String printIdentifier() {
        return id;
    }

    public void moveTo(Square square) {
        System.out.println("Player:" + color + " Move: " + id + " " + currentPosition + " -> " + square);
        currentPosition = square;
    }


    public MoveEvaluation getPossibleMoves(Board board) {
        MoveEvaluator evaluator = new MoveEvaluator();
        return evaluator.analyse(board, currentPosition.toString());
    }


    public Color getColor() {
        return color;
    }

    public Iterator<Point> directions() {
        return directions.iterator();
    }

    public int getMaximumMoves() {
        return maximumMoves;
    }


}
