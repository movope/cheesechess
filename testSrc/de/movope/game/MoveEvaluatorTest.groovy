package de.movope.game

import spock.lang.Specification

class MoveEvaluatorTest extends Specification {

    ChessBoard board = new ChessBoard();

    def setup() {
        board.initPieces();
    }

    def "when entering a board and a position, you return a MoveEvaluationResult"() {
        when:
        def piece = board.getPieceAt("A2")
        def result = piece.getMoveEvaluationFor(board, Square.create("A2"))

        then:
        result != null
    }

    def "when you enter a square outside the board, return null"() {
        when:
        def piece = board.getPieceAt("A9")
        def result = piece.getMoveEvaluationFor(board, Square.create("A9"))

        then:
        result == null
    }

    def "when you enter a square without piece, return null"() {
        when:
        def piece = board.getPieceAt("A4")
        def result = piece.getMoveEvaluationFor(board, Square.create("A4"))

        then:
        result == null
    }

    def "when you enter D1 (queen), the result contains no moves and attacks"() {
        when:
        def piece = board.getPieceAt("D1")
        def result = piece.getMoveEvaluationFor(board, Square.create("D1"))

        then:
        result.possibleTargets().size() == 0
        result.possibleAttacks().size() == 0
    }


    def "correct targets for bishop with MoveEvaluator"() {
        given:
        setUpPeacesOnBoardForBishop()

        when:
        def piece = board.getPieceAt("D4")
        def result = piece.getMoveEvaluationFor(board, Square.create("D4"))

        then:
        result.possibleTargets().size() == 6
    }

    def "correct targets for queen"() {
        given:
        setUpPeacesOnBoardForQueen()

        when:
        def piece = board.getPieceAt("C4")
        def result = piece.getMoveEvaluationFor(board, Square.create("C4"))

        then:
        result.possibleTargets().size() == 15

    }

    def "correct targets for knight"() {
        given:
        setUpPeacesOnBoardForKnight();

        when:
        def piece = board.getPieceAt("C4")
        def result = piece.getMoveEvaluationFor(board, Square.create("C4"))

        then:
        result.possibleTargets().size() == 5
    }

    def setUpPeacesOnBoardForBishop() {
        board.move("C1", "D4");
        board.move("E2", "E4");
        board.move("F2", "F3");
        board.move("B7", "B6");
        board.print();
    }

    def setUpPeacesOnBoardForQueen() {
        board.move("D1", "C4");
        board.move("A2", "A5");
        board.move("D2", "D6");
        board.move("E2", "E4");
        board.move("C7", "B6");
        board.print();
    }

    def setUpPeacesOnBoardForKnight() {
        board.move("B1", "C4");
        board.move("A7", "A5");
        board.move("D2", "H3");
        board.move("D7", "D6");
        board.move("E2", "E4");
        board.print();
    }
}
