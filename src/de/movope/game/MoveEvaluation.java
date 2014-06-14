package de.movope.game;

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

    public Set<Square> possibleTargets() {
        return moves;
    }

    public Set<Square> possibleAttacks() {
        return attacks;
    }

    public Square getStart() {
        return start;
    }

    public MoveEvaluation join(MoveEvaluation evaluation) {
        MoveEvaluation result = MoveEvaluation.empty();
        result.attacks = new HashSet<>();
        result.moves = new HashSet<>();
        result.start = start;
        result.attacks.addAll(attacks);
        result.attacks.addAll(evaluation.possibleAttacks());
        result.moves.addAll(moves);
        result.moves.addAll(evaluation.possibleTargets());
        return result;
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

        public Builder addMoves(Set<Square> moves) {
            this.moves.addAll(moves);
            return this;
        }

        public Builder addAttacks(Set<Square> attacks) {
            this.attacks.addAll(attacks);
            return this;
        }

        public MoveEvaluation create() {
            if (start == null ) {
                throw new IllegalArgumentException("Start-Square must be defined.");
            }
            return new MoveEvaluation(start, moves, attacks);
        }

        public void addMove(Square move) {
            this.moves.add(move);
        }

        public void addAttack(Square attack) {
            this.attacks.add(attack);
        }
    }
}
