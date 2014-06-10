package de.movope.game;

import java.util.*;

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

        private Builder(Square start) {
            this.start = start;
        }

        public static Builder startAt(Square start) {
            return new Builder(start);
        }

        public Builder addMoves(List<Square> moves) {
            this.moves.addAll(moves);
            return this;
        }

        public Builder addAttacks(List<Square> attacks) {
            this.attacks.addAll(attacks);
            return this;
        }

        public MoveEvaluation create() {
            if (start == null ) {
                throw new IllegalArgumentException("Start-Square must be defined.");
            }
            return new MoveEvaluation(start, moves, attacks);
        }
    }
}
