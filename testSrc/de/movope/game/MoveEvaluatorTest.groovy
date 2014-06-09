package de.movope.game

import spock.lang.Specification

class MoveEvaluatorTest extends Specification {

    ChessBoard board = new ChessBoard();

    def setup() {
        board.initPieces();
    }

    def "when entering a board and a position, you return a MoveEvaluationResult"() {
        when:
        def result = MoveEvaluator.with(board).analyse("A2")

        then:
        result != null
    }

    def "when you enter a square outside the board, return null"() {
        when:
        def result = MoveEvaluator.with(board).analyse("A9")

        then:
        result == null
    }

    def "when you enter a square without piece, return null"() {
        when:
        def result = MoveEvaluator.with(board).analyse("A4")

        then:
        result == null
    }

    def "when you enter D1 (queen), the result contains no moves and attacks"() {
        when:
        def result = MoveEvaluator.with(board).analyse("D1")

        then:
        result.possibleTargets().size() == 0
        result.possibleAttacks().size() == 0
    }


    def "correct targets for bishop with MoveEvaluator"() {
        given:
        setUpPeacesOnBoardForBishop()

        when:
        def result = MoveEvaluator.with(board).analyse("D4")

        then:
        result.possibleTargets().size() == 6
    }

    def "correct targets for queen"() {
        given:
        setUpPeacesOnBoardForQueen()

        when:
        def result = MoveEvaluator.with(board).analyse("C4")

        then:
        result.possibleTargets().size() == 15

    }

    def "correct targets for knight"() {
        given:
        setUpPeacesOnBoardForKnight();

        when:
        def result = MoveEvaluator.with(board).analyse("C4")

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
