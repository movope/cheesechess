package main.java.de.movope.game;


public class Move {

    Square from;
    Square to;

    private Move(Square start, Square to) {
        this.from = start;
        this.to = to;
    }

    static public Move create(String from, String to) {
        return new Move(Square.create(from), Square.create(to));
    }

    static public Move create(Square from, Square to) {
        return new Move(from, to);
    }

    public Square from() {
        return from;
    }

    public Square to() {
        return to;
    }

    @Override
    public String toString() {
        return "{" + from + "->" + to + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;

        Move move = (Move) o;

        if (!from.equals(move.from)) return false;
        if (!to.equals(move.to)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }
}
