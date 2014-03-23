package de.movope.game.pieces

import de.movope.game.Board
import de.movope.game.Color
import de.movope.game.Square
import spock.lang.Specification

import static org.mockito.Mockito.*

class BishopTest extends Specification {

    Board board;

    def setup() {
        board = new Board()
        board.initPieces()
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.getPieceAt("C1").moveTo(Square.create("D4"));
        board.getPieceAt("E2").moveTo(Square.create("E4"));
        board.getPieceAt("F2").moveTo(Square.create("F3"));
        board.getPieceAt("B7").moveTo(Square.create("B6"));
        board.print();
    }


    def "correct targets for bishop"() {
        expect:
        def evaluation = board.getPieceAt("D4").getPossibleMoves(board)
        evaluation.possibleTargets().size() == 6
    }
}
