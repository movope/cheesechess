package de.movope.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Direction {

    private int x;
    private int y;

    public int y() {
        return y;
    }

    public int x() {
        return x;
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

        public List<Point> directions() {
            List<Point> result = new ArrayList<>();
            if (parallel) {
                result.addAll(Arrays.asList(new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)));
            }
            if(diagonal) {
                result.addAll(Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1)));
            }
            if (knight) {
                result.addAll(Arrays.asList(new Point(2, 1), new Point(2, -1), new Point(-2, 1), new Point(-2, -1),
                        new Point(1, 2), new Point(1, -2), new Point(-1, 2), new Point(-1, -2)));
            }
            return result;
        }
    }
}
