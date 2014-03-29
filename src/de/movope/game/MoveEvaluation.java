package de.movope.game;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MoveEvaluation {

    private Set<Square> moves = Collections.emptySet();
    private Set<Square> attacks = Collections.emptySet();

    private MoveEvaluation() {
    }

    private MoveEvaluation(Set<Square> moves, Set<Square> attacks) {
        this.moves = moves;
        this.attacks = attacks;
    }

    public boolean isMovePossible() {
        return moves.size() > 0 || attacks.size() > 0;
    }

    public static MoveEvaluation empty() {
        return new MoveEvaluation();
    }

    public Collection<Square> possibleTargets() {
        return moves;
    }

    public Object possibleAttacks() {
        return attacks;
    }

    public static class Builder {

        private Set<Square> moves = new HashSet<>();
        private Set<Square> attacks = new HashSet<>();


        public void addAttack(Square attack) {
            attacks.add(attack);
        }

        public void addMove(Square move) {
            moves.add(move);
        }

        public MoveEvaluation create() {
            return new MoveEvaluation(moves, attacks);
        }
    }
}
