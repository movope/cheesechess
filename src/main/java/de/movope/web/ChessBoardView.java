package de.movope.web;

import de.movope.game.ChessBoard;
import de.movope.game.ChessGameUtils;
import de.movope.game.Piece;

public class ChessBoardView {

    Piece[][] boardView = new Piece[8][8];

    public ChessBoardView(ChessBoard board) {
        boardView = ChessGameUtils.getSquaresOfBoard(board);
    }

    public Piece[][] getBoardView() {
        return boardView;
    }
}
