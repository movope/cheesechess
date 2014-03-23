package de.movope.game

import spock.lang.Specification

class PieceTest extends Specification {

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


    def "correct targets for rook"() {
        expect:
        def evaluation = board.getPieceAt("A1").getPossibleMoves(board)
        evaluation.possibleTargets().size() == 3
    }

    def "correct targets for bishop"() {
        expect:
        def evaluation = board.getPieceAt("C1").getPossibleMoves(board)
        evaluation.possibleTargets().size() == 5
    }

    def "correct targets for queen"() {
        expect:
        def evaluation = board.getPieceAt("D1").getPossibleMoves(board)
        evaluation.possibleTargets().contains(Square.create("D2"))
        evaluation.possibleTargets().contains(Square.create("D4"))
        evaluation.possibleTargets().contains(Square.create("H5"))
        evaluation.possibleTargets().size() == 8
    }

    def "correct targets for knight"() {
        expect:
        def evaluation = board.getPieceAt("B1").getPossibleMoves(board)
        evaluation.possibleTargets().contains(Square.create("D2"))
        evaluation.possibleTargets().contains(Square.create("C3"))
        evaluation.possibleTargets().contains(Square.create("A3"))
        evaluation.possibleTargets().size() == 3
    }

    def "correct targets for king"() {
        expect:
        def evaluation = board.getPieceAt("E1").getPossibleMoves(board)
        evaluation.possibleTargets().contains(Square.create("D2"))
        evaluation.possibleTargets().contains(Square.create("E2"))
        evaluation.possibleTargets().size() == 2
    }

    def "correct targets for pawn"() {

        expect:
        def evaluation1 = board.getPieceAt("D6").getPossibleMoves(board)
        evaluation1.possibleTargets().size() == 0

        def evaluation2 = board.getPieceAt("B2").getPossibleMoves(board)
        evaluation2.possibleTargets().size() == 2

        def evaluation3 = board.getPieceAt("E4").getPossibleMoves(board)
        evaluation3.possibleTargets().size() == 1
    }

}
