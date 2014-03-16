package de.movope.game;

import java.awt.*;


public class MoveRule {

    private Point[] directions;
    private int maximumMoves;

    public MoveRule(Point[] directions, int maximumMoves) {
        this.directions = directions;
        this.maximumMoves = maximumMoves;
    }

    public Point[] getDirections() {
        return directions;
    }

    public int getMaximumMoves() {
        return maximumMoves;
    }

    static public MoveRule NULL = new MoveRule(new Point[]{new Point(0,0)}, 0);
    static public MoveRule PAWN = new MoveRule(new Point[]{new Point(0,1)}, 2);
    static public MoveRule ROOK = new MoveRule(new Point[]{new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)}, 7);
    static public MoveRule BISHOP = new MoveRule(new Point[]{new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1)}, 7);
    static public MoveRule KNIGHT = new MoveRule(new Point[]{new Point(2, 1), new Point(2, -1), new Point(-2, 1), new Point(-2, -1),
            new Point(1, 2), new Point(1, -2), new Point(-1, 2), new Point(-1, -2)}, 1);
    static public MoveRule QUEEN = new MoveRule(new Point[]{new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
            new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)}, 7);

    static public MoveRule KING = new MoveRule(new Point[]{new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
            new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)}, 1);


}
