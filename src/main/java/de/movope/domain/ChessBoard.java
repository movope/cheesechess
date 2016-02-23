package de.movope.domain;


import com.google.common.base.Objects;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<String, Piece> pieces = new HashMap<>();

    private ChessBoard() {
    }

    public static ChessBoard copy(ChessBoard board) {
        ChessBoard copy = new ChessBoard();
        for (Map.Entry<String, Piece> entry: board.pieces.entrySet()) {
            copy.pieces.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    public static ChessBoard createNew() {
        ChessBoard board = new ChessBoard();
        board.initPieces();
        return board;
    }

    public Map<String, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public List<String> getSquaresWithPiece(Color color) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getColor() == color)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Piece getPieceAt(Square square) {
        Piece result = pieces.get(square.toString());
        return result != null ? result : Piece.NULL;
    }

    public Piece getPieceAt(String square) {
        return getPieceAt(Square.create(square));
    }

    public void execute(Move move) {
        movePiece(move.from().toString(), move.to().toString());
    }

    public void movePiece(String from, String to) {
        if (pieces.get(to) != null) {
            System.out.println("One piece removed: " + pieces.get(to));
        }
        Piece transfer = getPieceAt(from);
        pieces.remove(from);
        pieces.put(to, transfer);
    }

    public boolean isSquareOccupiedByPiece(Square square, Color color) {
        return getPieceAt(square).getColor() == color;
    }

    public boolean canPieceBeMovedTo(Square target) {
        return target.onBoard() && getPieceAt(target) == Piece.NULL;
    }

    private void initPieces() {
        initPieces(Color.WHITE);
        initPieces(Color.BLACK);
    }

    private void initPieces(Color color) {
        int row = (color == Color.WHITE) ? 1 : 8;
        pieces.put("A" + row, new Piece(PieceType.ROOK, color));
        pieces.put("H" + row, new Piece(PieceType.ROOK, color));
        pieces.put("B" + row, new Piece(PieceType.KNIGHT, color));
        pieces.put("G" + row, new Piece(PieceType.KNIGHT, color));
        pieces.put("C" + row, new Piece(PieceType.BISHOP, color));
        pieces.put("F" + row, new Piece(PieceType.BISHOP, color));
        pieces.put("D" + row, new Piece(PieceType.QUEEN, color));
        pieces.put("E" + row, new Piece(PieceType.KING, color));

        if (color == Color.WHITE) {
            row++;
        } else {
            row--;
        }

        String[] files = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < 8; i++) {
            pieces.put(files[i] + row, new Piece(PieceType.PAWN, color));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Objects.equal(pieces, that.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pieces);
    }
}
