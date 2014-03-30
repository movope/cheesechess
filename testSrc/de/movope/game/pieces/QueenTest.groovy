package de.movope.game.pieces

import de.movope.game.ChessBoard
import de.movope.game.Square
import spock.lang.Specification

class QueenTest extends Specification {

    ChessBoard board;

    def setup() {
        board = new ChessBoard();
        board.initPieces();
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.getPieceAt("D1").moveTo(Square.create("C4"));
        board.getPieceAt("A2").moveTo(Square.create("A5"));
        board.getPieceAt("D2").moveTo(Square.create("D6"));
        board.getPieceAt("E2").moveTo(Square.create("E4"));
        board.getPieceAt("C7").moveTo(Square.create("B6"));
        board.print();
    }


    def "correct targets for queen"() {
        when:
        def evaluation = board.getPieceAt("C4").getPossibleMoves(board)

        then:
        evaluation.possibleTargets().contains(Square.create("B5"))
        evaluation.possibleTargets().contains(Square.create("C7"))
        evaluation.possibleTargets().contains(Square.create("A2"))
        evaluation.possibleTargets().contains(Square.create("E6"))
        evaluation.possibleTargets().contains(Square.create("E2"))
        evaluation.possibleTargets().size() == 15

        evaluation.possibleAttacks().contains(Square.create("F7"))
        evaluation.possibleAttacks().contains(Square.create("C8"))
        evaluation.possibleAttacks().size() == 2
    }
}
