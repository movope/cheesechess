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
        def targets = board.getPieceAt("A1").getPossibleTargets(board)
        targets.size() == 3
    }

    def "correct targets for bishop"() {
        expect:
        def targets = board.getPieceAt("C1").getPossibleTargets(board)
        targets.size() == 5
    }

    def "correct targets for queen"() {
        expect:
        def targets = board.getPieceAt("D1").getPossibleTargets(board)
        targets.contains(Square.create("D2"))
        targets.contains(Square.create("D4"))
        targets.contains(Square.create("H5"))
        targets.size() == 8
    }

    def "correct targets for knight"() {
        expect:
        def targets = board.getPieceAt("B1").getPossibleTargets(board)
        targets.contains(Square.create("D2"))
        targets.contains(Square.create("C3"))
        targets.contains(Square.create("A3"))
        targets.size() == 3
    }

    def "correct targets for king"() {
        expect:
        def targets = board.getPieceAt("E1").getPossibleTargets(board)
        targets.contains(Square.create("D2"))
        targets.contains(Square.create("E2"))
        targets.size() == 2
    }

    def "correct targets for pawn"() {

        expect:
        def targets1 = board.getPieceAt("D6").getPossibleTargets(board)
        targets1.size() == 0

        def targets2 = board.getPieceAt("B2").getPossibleTargets(board)
        targets2.size() == 2

        def targets3 = board.getPieceAt("E4").getPossibleTargets(board)
        targets3.size() == 1
    }

}
