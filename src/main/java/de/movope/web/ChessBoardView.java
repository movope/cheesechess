package de.movope.web;

import de.movope.domain.ChessBoard;
import de.movope.util.ChessGameUtils;

import java.util.Arrays;

public class ChessBoardView {

    private final PieceView[][] boardView;

    public ChessBoardView(ChessBoard board) {
        boardView = ChessGameUtils.getViewOfBoard(board);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessBoardView)) return false;

        return Arrays.deepEquals(boardView, ((ChessBoardView) o).boardView);
    }

    @Override
    public int hashCode() {
        return 55 + Arrays.hashCode(boardView);
    }
}
