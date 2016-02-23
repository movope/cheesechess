package de.movope.domain

import spock.lang.Specification

import static de.movope.domain.Color.BLACK
import static de.movope.domain.Color.WHITE

class ChessBoardTest extends Specification {

    ChessBoard board = ChessBoard.createNew()

    def "you get the right piece for a specific square"() {
        expect:
        board.getPieceAt("A1").getPieceType() == PieceType.ROOK
        board.getPieceAt("A2").getPieceType() == PieceType.PAWN
        board.getPieceAt("B1").getPieceType() == PieceType.KNIGHT
    }


    def "correct number of peaces is created"() {
        expect:
        board.getSquaresWithPiece(WHITE).size() == 16
        board.getSquaresWithPiece(BLACK).size() == 16
    }

    def "copy factory method works"() {
        given:
        ChessBoard copy = ChessBoard.copy(board)

        when:
        copy.movePiece("A2", "A4");

        then:
        copy.getPieceAt("A2").getPieceType() == PieceType.NULL
        board.getPieceAt("A2").getPieceType() == PieceType.PAWN
    }

    def "you can evaluate if square is occupied by piece"() {
        expect:
        board.isSquareOccupiedByPiece(Square.create("A2"), WHITE)
        !board.isSquareOccupiedByPiece(Square.create("A2"), BLACK)

        !board.isSquareOccupiedByPiece(Square.create("A4"), WHITE)
        !board.isSquareOccupiedByPiece(Square.create("A4"), BLACK)

        !board.isSquareOccupiedByPiece(Square.create("D8"), WHITE)
        board.isSquareOccupiedByPiece(Square.create("D8"), BLACK)
    }
}
