package de.movope.cheesechess.web.api;

import de.movope.cheesechess.domain.ChessBoard;
import de.movope.cheesechess.domain.Piece;
import de.movope.cheesechess.domain.Square;

import java.util.Map;

public class ChessBoardMapper {

    public static ChessBoardView getViewOfBoard(ChessBoard boardToPrint) {
        PieceView[][] board = createPeaceViews(boardToPrint);
        ChessBoardView view = new ChessBoardView();
        view.setBoardView(board);
        return view;
    }

    private static PieceView[][] createPeaceViews(ChessBoard boardToPrint) {
        PieceView[][] board = new PieceView[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = PieceView.NULL;
            }
        }

        for (Map.Entry<String, Piece> p : boardToPrint.getPieces().entrySet()) {
            Square position = Square.create(p.getKey());
            board[7 - position.getFile()][position.getRank()] = toPieceView(p.getValue());
        }
        return board;
    }

    private static PieceView toPieceView(Piece piece) {
        return new PieceView(piece.getPieceType(), piece.getColor());
    }


}
