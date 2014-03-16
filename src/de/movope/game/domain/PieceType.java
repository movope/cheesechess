package de.movope.game.domain;


import de.movope.game.MoveRule;

import java.awt.*;

public enum PieceType {
    NULL("+", MoveRule.NULL),
    PAWN("B", MoveRule.PAWN),
    ROOK("T", MoveRule.ROOK) ,
    BISHOP("L", MoveRule.BISHOP),
    QUEEN("Q", MoveRule.QUEEN),
    KNIGHT("P", MoveRule.KNIGHT),
    KING("K", MoveRule.KING);

    final private String identifier;
    final private MoveRule moveRule;

    private PieceType(String id, MoveRule rule) {
        identifier = id;
        moveRule = rule;
    }

    public String print(){
        return identifier;
    }

    public Point[] getDirections() {
        return moveRule.getDirections();
    }

    public int getMaximumMoves() {
        return  moveRule.getMaximumMoves();
    }


}
