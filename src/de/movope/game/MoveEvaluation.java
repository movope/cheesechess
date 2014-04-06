package de.movope.game;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MoveEvaluation {

    private Set<Square> moves = Collections.emptySet();
    private Set<Square> attacks = Collections.emptySet();
    private Square start;

    private MoveEvaluation() {
    }

    private MoveEvaluation(Square start, Set<Square> moves, Set<Square> attacks) {
        this.start = start;
        this.moves = moves;
        this.attacks = attacks;
    }

    public boolean isMovePossible() {
        return attacks.size() + moves.size() > 0;
    }

    public static MoveEvaluation empty() {
        return new MoveEvaluation();
    }

    public Collection<Square> possibleTargets() {
        return moves;
    }

    public Collection<Square> possibleAttacks() {
        return attacks;
    }

    public Square getStart() {
        return start;
    }

    public static class Builder {

        private Set<Square> moves = new HashSet<>();
        private Set<Square> attacks = new HashSet<>();
        private Square start;

        public void startAt(Square start) {
            this.start = start;
        }

        public void addAttack(Square attack) {
            attacks.add(attack);
        }

        public void addMove(Square move) {
            moves.add(move);
        }

        public MoveEvaluation create() {
            if (start == null ) {
                throw new IllegalArgumentException("Start-Square must be defined.");
            }
            return new MoveEvaluation(start, moves, attacks);
        }
    }
}
