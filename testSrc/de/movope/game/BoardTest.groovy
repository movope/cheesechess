package de.movope.game

import de.movope.game.pieces.Knight
import de.movope.game.pieces.Pawn
import de.movope.game.pieces.Rook
import spock.lang.Specification

class BoardTest extends Specification {

    Board board = new Board();

    public setup() {
        board.initPieces();
    }

    def "you get the risht piece for a specific square"() {
        expect:
        board.getPieceAt("A1") instanceof Rook
        board.getPieceAt("A2") instanceof Pawn
        board.getPieceAt("B1") instanceof Knight
    }

    def "correct number of peaces is created"() {
        expect:
        board.getPieces(Color.WHITE).size() == 16
        board.getPieces(Color.BLACK).size() == 16

    }
}
