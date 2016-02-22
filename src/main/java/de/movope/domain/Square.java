package de.movope.domain;

import java.util.Arrays;

public class Square {

    private final String[] ranks = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private int file;
    private int rank;

    private Square(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square create(String square) {
        return new Square(Integer.valueOf(square.substring(1, 2)) - 1,
                getRankAsNumber(square.substring(0, 1)));
    }

    public static Square copy(Square square) {
        return new Square(square.file, square.rank);
    }

    private static int getRankAsNumber(String rank) {
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
    public String toString() {
        return ranks[rank] + (file + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;

        Square square = (Square) o;

        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        int result = ranks != null ? Arrays.hashCode(ranks) : 0;
        result = 31 * result + file;
        result = 31 * result + rank;
        return result;
    }

    public boolean onBoard() {
        return !(getFile() < 0 || getFile() > 7 ||
                getRank() < 0 || getRank() > 7);
    }
}
