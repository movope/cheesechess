package de.movope.game.pieces

import de.movope.game.Board
import de.movope.game.Square
import spock.lang.Specification

class KnightTest extends Specification {

    Board board;

    def setup() {
        board = new Board();
        board.initPieces();
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.getPieceAt("B1").moveTo(Square.create("C4"));
        board.getPieceAt("A7").moveTo(Square.create("A5"));
        board.getPieceAt("D2").moveTo(Square.create("H3"));
        board.getPieceAt("D7").moveTo(Square.create("D6"));
        board.getPieceAt("E2").moveTo(Square.create("E4"));
        board.print();
    }

    def "correct targets for knight"() {
        when:
        def evaluation = board.getPieceAt("C4").getPossibleMoves(board)

        then:
        evaluation.possibleTargets().contains(Square.create("B6"))
        evaluation.possibleTargets().contains(Square.create("E5"))
        evaluation.possibleTargets().contains(Square.create("D2"))
        evaluation.possibleTargets().contains(Square.create("E3"))
        evaluation.possibleTargets().contains(Square.create("A3"))
        evaluation.possibleTargets().size() == 5

        evaluation.possibleAttacks().size() == 2
        evaluation.possibleAttacks().contains(Square.create("A5"))
        evaluation.possibleAttacks().contains(Square.create("D6"))
    }
}
