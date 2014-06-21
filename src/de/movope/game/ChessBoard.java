package de.movope.game;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    Map<String, Piece> pieces = new HashMap<>();

    public ChessBoard() {}

    public ChessBoard(ChessBoard board) {
        for (Map.Entry<String, Piece> entry: board.pieces.entrySet()) {
            pieces.put(entry.getKey(), entry.getValue());
        }
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

    public void movePiece(Move move) {
        movePiece(move.from().toString(), move.to().toString());
    }

    public void movePiece(String from, String to) {

        System.out.println("Move: " + from + " -> " +to);
        if (pieces.get(to) != null) {
            System.out.println("One piece removed: " + pieces.get(to));
        }
        Piece transfer = getPieceAt(from);
        pieces.remove(from);
        pieces.put(to, transfer);
    }

    public boolean occupiedFromEnemy(Square target, Color enemyColor) {
        if ((target == null) || !target.onBoard()) {
            return false;
        }
        return getPieceAt(target).getColor() == enemyColor;
    }

    public boolean canPieceMoveTo(Square target) {
        return target.onBoard() && getPieceAt(target) == Piece.NULL;
    }

    public void initPieces() {
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

}
