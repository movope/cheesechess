package de.movope.game.domain;

import de.movope.game.Board;
import de.movope.game.Color;
import de.movope.game.Square;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Piece {

    Color color;
    Square currentPosition;
    String id;
    Point[] directions;
    int maximumMoves;

    public static Piece NULL= new Piece();

    private Piece() {
    }

    public Piece(Color color, String position) {
        this.color = color;
        currentPosition = Square.create(position);
    }


    public Square getPosition() {
        return currentPosition;
    }

    public String printIdentifier() {
        return id;
    }

    public void moveTo(Square square) {
        System.out.println("Move: " + id + " " + currentPosition + " -> " + square);
        currentPosition = square;
    }


    public Collection<Square> getPossibleTargets(Board board) {
        Collection<Piece> myPieces = board.getPieces(color);
        Collection<Piece> enemyPieces = board.getPieces(color.invert());
        Set<Square> targets = new HashSet<>();
        for (Point dir : directions) {
            Square target = (Square) currentPosition.clone();
            int i = 1;
            while (i <= maximumMoves) {
                target = target.moveHorizontical(dir.x).moveVertical(dir.y);
                if (onBoard(target)) {
                    if (squareIsEmpty(myPieces, target) && squareIsEmpty(enemyPieces, target)) {
                        targets.add(target);
                        i++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return targets;
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

}
