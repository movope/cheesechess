package de.movope.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Direction {

    private final int x;
    private final int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction create(int x, int y) {
        return new Direction(x, y);
    }

    public int y() {
        return y;
    }

    public int x() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Direction)) return false;

        Direction direction = (Direction) o;

        if (x != direction.x) return false;
        if (y != direction.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public static class Builder {

        private boolean parallel = false;
        private boolean diagonal = false;
        private boolean knight = false;

        public static Builder create() {
            return new Builder();
        }

        public Builder parallel() {
            this.parallel = true;
            return this;
        }

        public Builder diagonal() {
            this.diagonal = true;
            return this;
        }

        public Builder knight() {
            this.knight = true;
            return this;
        }

        public List<Direction> directions() {
            List<Direction> result = new ArrayList<>();
            if (parallel) {
                result.addAll(Arrays.asList(new Direction(0, 1), new Direction(1, 0), new Direction(-1, 0), new Direction(0, -1)));
            }
            if(diagonal) {
                result.addAll(Arrays.asList(new Direction(1, 1), new Direction(-1, -1), new Direction(1, -1), new Direction(-1, 1)));
            }
            if (knight) {
                result.addAll(Arrays.asList(new Direction(2, 1), new Direction(2, -1), new Direction(-2, 1), new Direction(-2, -1),
                        new Direction(1, 2), new Direction(1, -2), new Direction(-1, 2), new Direction(-1, -2)));
            }
            return result;
        }
    }
}
