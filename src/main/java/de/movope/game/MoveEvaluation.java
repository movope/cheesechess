package de.movope.game;

import java.util.Collection;
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

    public void addAll(MoveEvaluation evaluation) {
        attacks.addAll(evaluation.possibleAttacks());
        moves.addAll(evaluation.possibleTargets());
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

    public Move selectMove() {
        if (possibleAttacks().size() > 0) {
            return chooseOneOf(possibleAttacks());
        } else {
            return chooseOneOf(possibleTargets());
        }
    }

    private Move chooseOneOf(Collection<Move> moves) {
        Move[] move = moves.toArray(new Move[moves.size()]);
        int rand = (int) (moves.size() * Math.random());
        return move[rand];
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
