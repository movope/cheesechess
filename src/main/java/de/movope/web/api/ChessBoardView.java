package de.movope.web.api;

import java.util.Arrays;

public class ChessBoardView {

    private PieceView[][] boardView;

    public ChessBoardView() {
    }

    public PieceView[][] getBoardView() {
        return boardView;
    }

    public void setBoardView(PieceView[][] boardView) {
        this.boardView = boardView;
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
