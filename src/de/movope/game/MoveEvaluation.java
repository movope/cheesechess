package de.movope.game;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MoveEvaluation {

    private Set<Move> moves = new HashSet<>();
    private Set<Move> attacks = new HashSet<>();

    private MoveEvaluation() {
    }

    private MoveEvaluation(Set<Move> moves, Set<Move> attacks) {
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

    public MoveEvaluation join(MoveEvaluation evaluation) {
        MoveEvaluation result = MoveEvaluation.empty();
        result.attacks.addAll(attacks);
        result.attacks.addAll(evaluation.possibleAttacks());
        result.moves.addAll(moves);
        result.moves.addAll(evaluation.possibleTargets());
        return result;
    }

    public MoveEvaluation filterMovesBy(Predicate<Move> predicate) {
        moves = moves.stream()
                .filter(predicate)
                .collect(Collectors.toSet());

        attacks = attacks.stream()
                .filter(predicate)
                .collect(Collectors.toSet());

        return this;
    }

    public static class Builder {

        private Set<Move> moves = new HashSet<>();
        private Set<Move> attacks = new HashSet<>();

        public Builder() {
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
            return new MoveEvaluation(moves, attacks);
        }

        public void addMove(Move move) {
            this.moves.add(move);
        }

        public void addAttack(Move attack) {
            this.attacks.add(attack);
        }
    }
}
