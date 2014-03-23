package de.movope.game.pieces

import de.movope.game.Board
import de.movope.game.Square
import spock.lang.Specification

class KingTest extends Specification {

    Board board;

    def setup() {
        board = new Board();
        board.initPieces();
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.getPieceAt("E1").moveTo(Square.create("C4"));
        board.getPieceAt("A8").moveTo(Square.create("B5"));
        board.getPieceAt("E2").moveTo(Square.create("E4"));
        board.print();
    }

    def "correct targets for king"() {
        when:
        def evaluation = board.getPieceAt("C4").getPossibleMoves(board)

        then:
        evaluation.possibleTargets().size() == 7
        evaluation.possibleTargets().contains(Square.create("B3"))
        evaluation.possibleTargets().contains(Square.create("D4"))

        evaluation.possibleAttacks().size() == 1
        evaluation.possibleAttacks().contains(Square.create("B5"))
    }
}
