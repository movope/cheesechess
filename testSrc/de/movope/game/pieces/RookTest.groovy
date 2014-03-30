package de.movope.game.pieces

import de.movope.game.ChessBoard
import de.movope.game.Square
import spock.lang.Specification

class RookTest extends Specification {

    ChessBoard board;

    def setup() {
        board = new ChessBoard();
        board.initPieces();
        setUpPeacesOnBoard()
        board.print();
    }

    def setUpPeacesOnBoard() {
        board.getPieceAt("A1").moveTo(Square.create("C5"));
        board.getPieceAt("E2").moveTo(Square.create("E4"));
        board.getPieceAt("C2").moveTo(Square.create("D4"));
        board.getPieceAt("G7").moveTo(Square.create("G5"));
    }


    def "correct targets for rook"() {
        when:
        def evaluation = board.getPieceAt("C5").getPossibleMoves(board)

        then:
        evaluation.possibleTargets().size() == 9
        evaluation.possibleAttacks().size() == 2
        evaluation.possibleAttacks().contains(Square.create("C7"))
        evaluation.possibleAttacks().contains(Square.create("G5"))
    }
}
