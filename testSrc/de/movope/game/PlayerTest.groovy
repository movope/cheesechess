package de.movope.game

import spock.lang.Specification

class PlayerTest extends Specification {

    def board = new ChessBoard()
    def white = new Player(board, Color.WHITE)
    def black = new Player(board, Color.BLACK)

    def setup() {
        board.initPieces();
    }

    def "checkmate is detected and player reacts correctly"() {
        when:
        board.move("F1", "H5")
        board.move("F7", "F5")
        board.move("D7", "D6")
        ChessGameUtils.print(board)

        then:
        white.checkmateOf(Color.BLACK)

        when:
        black.makeRandomMove()
        ChessGameUtils.print(board)

        then:
        board.getPieceAt("E8").getPieceType() == PieceType.NULL
        board.getPieceAt("D7").getPieceType() == PieceType.KING
    }
}
