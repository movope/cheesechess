package de.movope.game;

import java.util.Arrays;

public class Square implements Cloneable {

    final private String[] ranks = {"A", "B", "C", "D", "E", "F", "G", "H"};
    int file;
    int rank;

    private Square(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    static public Square create(String square) {
        return new Square(Integer.valueOf(square.substring(1, 2)) - 1,
                getRankAsNumber(square.substring(0, 1)));
    }

    static private int getRankAsNumber(String rank) {
        switch (rank) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            default:
                throw new RuntimeException("Could not resolve square [" + rank + "].");

        }
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public Square move(int x, int y) {
        return new Square(file + y, rank + x);
    }

    @Override
    public Object clone() {
        Square clone = null;
        try {
            clone = (Square) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        clone.file = file;
        clone.rank = rank;
        return clone;

    }

    @Override
    public String toString() {
        return ranks[rank] + (file + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;

        Square square = (Square) o;

        if (file != square.file) return false;
        if (rank != square.rank) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = ranks != null ? Arrays.hashCode(ranks) : 0;
        result = 31 * result + file;
        result = 31 * result + rank;
        return result;
    }

    public boolean onBoard() {
        if (getFile() < 0 || getFile() > 7 ||
                getRank() < 0 || getRank() > 7) {
            return false;
        }
        return true;
    }
}
