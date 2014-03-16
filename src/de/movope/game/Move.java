package de.movope.game;


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

    public Square getFrom() {
        return from;
    }

    public Square getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
