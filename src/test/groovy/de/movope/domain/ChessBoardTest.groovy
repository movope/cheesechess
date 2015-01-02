package de.movope.domain

import spock.lang.Specification

class ChessBoardTest extends Specification {

    ChessBoard board = new ChessBoard();

    public setup() {
        board.initPieces();
    }

    def "you get the right piece for a specific square"() {
        expect:
        board.getPieceAt("A1").getPieceType() == PieceType.ROOK
        board.getPieceAt("A2").getPieceType() == PieceType.PAWN
        board.getPieceAt("B1").getPieceType() == PieceType.KNIGHT
    }


    def "correct number of peaces is created"() {
        expect:
        board.getSquaresWithPiece(Color.WHITE).size() == 16
        board.getSquaresWithPiece(Color.BLACK).size() == 16
    }

    def "copy constructor works"() {
        given:
        ChessBoard copy = new ChessBoard(board)

        when:
        copy.movePiece("A2", "A4");

        then:
        copy.getPieceAt("A2").getPieceType() == PieceType.NULL
        board.getPieceAt("A2").getPieceType() == PieceType.PAWN
    }
}
