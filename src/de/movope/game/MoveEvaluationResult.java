package de.movope.game;

import java.util.*;

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

    public Iterator<MoveVector> getVectorIterator() {
        return moveVectors.iterator();
    }

    public void deleteEmptyVectors() {
        Iterator<MoveVector> it = moveVectors.iterator();
        while (it.hasNext()) {
            MoveVector vector = it.next();
            if (vector.size() == 0) {
                it.remove();
            }
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
}
