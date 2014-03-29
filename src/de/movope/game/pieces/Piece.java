package de.movope.game.pieces;

import de.movope.game.Board;
import de.movope.game.Color;
import de.movope.game.MoveEvaluation;
import de.movope.game.Square;

import java.awt.*;
import java.util.Collection;
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
        Collection<Piece> myPieces = board.getPieces(color);
        Collection<Piece> enemyPieces = board.getPieces(color.invert());

        MoveEvaluation.Builder builder = new MoveEvaluation.Builder();
        for (Point dir : directions) {
            Square target = (Square) currentPosition.clone();
            int i = 1;
            while (i <= maximumMoves) {
                target = target.moveHorizontical(dir.x).moveVertical(dir.y);
                if (onBoard(target)) {
                    if (squareIsEmpty(myPieces, target) && squareIsEmpty(enemyPieces, target)) {
                        builder.addMove(target);
                        i++;
                    } else if (!squareIsEmpty(enemyPieces, target)) {
                        builder.addAttack(target);
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return builder.create();
    }

    public boolean squareIsEmpty(Collection<Piece> pieces, Square target) {
        for (Piece p : pieces) {
            if (p.getPosition().equals(target))
                return false;
        }
        return true;
    }


    public boolean onBoard(Square s) {
        if (s.getFile() < 0 || s.getFile() > 7 ||
                s.getRank() < 0 || s.getRank() > 7) {
            return false;
        }
        return true;
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
