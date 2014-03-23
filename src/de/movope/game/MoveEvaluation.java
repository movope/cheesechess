package de.movope.game;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class MoveEvaluation {

    private Set<Square> moves = Collections.emptySet();
    private Set<Square> attacks = Collections.emptySet();

    private MoveEvaluation() {
    }

    public MoveEvaluation(Set<Square> moves, Set<Square> attacks) {
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
}
