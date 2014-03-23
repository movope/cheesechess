package de.movope.game.pieces

import de.movope.game.Board
import de.movope.game.Square
import spock.lang.Specification

class PawnTest extends Specification {

    Board board;

    def setup() {
        board = new Board();
        board.initPieces();
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.getPieceAt("A2").moveTo(Square.create("A5"));
        board.getPieceAt("D2").moveTo(Square.create("D6"));
        board.getPieceAt("E2").moveTo(Square.create("E4"));
        board.print();
    }

    def "correct targets for pawn"() {

        expect:
        def evaluation1 = board.getPieceAt("D6").getPossibleMoves(board)
        evaluation1.possibleTargets().size() == 0

        def evaluation2 = board.getPieceAt("B2").getPossibleMoves(board)
        evaluation2.possibleTargets().size() == 2

        def evaluation3 = board.getPieceAt("E4").getPossibleMoves(board)
        evaluation3.possibleTargets().size() == 1

        //TODO: Attacks for Pawns ....
    }
}
