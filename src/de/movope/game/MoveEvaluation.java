package de.movope.game;

import java.util.HashSet;
import java.util.Set;

public class MoveEvaluation {

    private Set<Move> moves = new HashSet<>();
    private Set<Move> attacks = new HashSet<>();
    private Square start;

    private MoveEvaluation() {
    }

    private MoveEvaluation(Square start, Set<Move> moves, Set<Move> attacks) {
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

    public Set<Move> possibleTargets() {
        return moves;
    }

    public Set<Move> possibleAttacks() {
        return attacks;
    }

    public Square getStart() {
        return start;
    }

    public MoveEvaluation join(MoveEvaluation evaluation) {
        MoveEvaluation result = MoveEvaluation.empty();
        result.attacks.addAll(attacks);
        result.attacks.addAll(evaluation.possibleAttacks());
        result.moves.addAll(moves);
        result.moves.addAll(evaluation.possibleTargets());
        return result;
    }

    public static class Builder {

        private Set<Move> moves = new HashSet<>();
        private Set<Move> attacks = new HashSet<>();
        private Square start;

        private Builder(Square start) {
            this.start = start;
        }

        public static Builder startAt(Square start) {
            return new Builder(start);
        }

        public Builder addMoves(Set<Move> moves) {
            this.moves.addAll(moves);
            return this;
        }

        public Builder addAttacks(Set<Move> attacks) {
            this.attacks.addAll(attacks);
            return this;
        }

        public MoveEvaluation create() {
            if (start == null ) {
                throw new IllegalArgumentException("Start-Square must be defined.");
            }
            return new MoveEvaluation(start, moves, attacks);
        }

        public void addMove(Move move) {
            this.moves.add(move);
        }

        public void addAttack(Move attack) {
            this.attacks.add(attack);
        }
    }
}
