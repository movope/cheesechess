package de.movope.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MoveEvaluationResult {

    private List<MoveVector> moveVectors = new ArrayList<>();

    private MoveVector currentVector = new MoveVector();

    public List<MoveVector> getMoveVectors() {
        return moveVectors;
    }

    public void add(Square target) {
        currentVector.add(target);
    }

    public void nextDirection() {
        if (currentVector.size() > 0) {
            moveVectors.add(currentVector);
            currentVector = new MoveVector();
        }
    }

    public Set<Square> possibleTargets() {
        Set<Square> result = new HashSet<>();
        for (MoveVector vector : moveVectors) {
            result.addAll(vector.getMoves());
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("MoveEvaluationResult: {");
        for (MoveVector vector : moveVectors) {
            buffer.append(vector);
            buffer.append(" ,");
        }
        buffer.append("}");
        return buffer.toString();
    }

    public void addPossibleAttack(Square target) {
        currentVector.addAttack(target);
    }
}
