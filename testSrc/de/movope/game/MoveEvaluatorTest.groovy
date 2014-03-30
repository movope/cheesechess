package de.movope.game

import spock.lang.Specification

class MoveEvaluatorTest extends Specification {

    MoveEvaluator evaluator = new MoveEvaluator();
    ChessBoard board = new ChessBoard();

    def setup() {
        board.initPieces();

    }

    def "when entering a board and a position, you return a MoveEvaluationResult"() {
        when:
        def result = evaluator.analyse(board, "A2")

        then:
        result != null
    }

    def "when you enter a square outside the board, return null"() {
        when:
        def result = evaluator.analyse(board, "A9")

        then:
        result == null
    }

    def "when you enter a square without piece, return null"() {
        when:
        def result = evaluator.analyse(board, "A4")

        then:
        result == null
    }

    def "when you enter A2 (Pawn), the result contains one direction"() {
        when:
        MoveEvaluation result = evaluator.analyse(board, "A2")

        then:
        result.possibleTargets().size() == 2
        result.possibleAttacks().size() == 0

    }

    def "when you enter D1 (queen), the result contains no moves and attacks"() {
        when:
        def result = evaluator.analyse(board, "D1")

        then:
        result.possibleTargets().size() == 0
        result.possibleAttacks().size() == 0
    }


    def "correct targets for bishop with MoveEvaluator"() {
        given:
        setUpPeacesOnBoardForBishop()

        when:
        def result = evaluator.analyse(board, "D4");

        then:
        result.possibleTargets().size() == 6
    }

    def "correct targets for queen"() {
        given:
        setUpPeacesOnBoardForQueen()

        when:
        def result = evaluator.analyse(board,"C4")

        then:
        result.possibleTargets().size() == 15

    }

    def "correct targets for knight"() {
        given:
        setUpPeacesOnBoardForKnight();

        when:
        def result = evaluator.analyse(board,"C4")

        then:
        result.possibleTargets().size() == 5
    }

    def setUpPeacesOnBoardForBishop() {
        board.getPieceAt("C1").moveTo(Square.create("D4"));
        board.getPieceAt("E2").moveTo(Square.create("E4"));
        board.getPieceAt("F2").moveTo(Square.create("F3"));
        board.getPieceAt("B7").moveTo(Square.create("B6"));
        board.print();
    }

    def setUpPeacesOnBoardForQueen() {
        board.getPieceAt("D1").moveTo(Square.create("C4"));
        board.getPieceAt("A2").moveTo(Square.create("A5"));
        board.getPieceAt("D2").moveTo(Square.create("D6"));
        board.getPieceAt("E2").moveTo(Square.create("E4"));
        board.getPieceAt("C7").moveTo(Square.create("B6"));
        board.print();
    }

    def setUpPeacesOnBoardForKnight() {
        board.getPieceAt("B1").moveTo(Square.create("C4"));
        board.getPieceAt("A7").moveTo(Square.create("A5"));
        board.getPieceAt("D2").moveTo(Square.create("H3"));
        board.getPieceAt("D7").moveTo(Square.create("D6"));
        board.getPieceAt("E2").moveTo(Square.create("E4"));
        board.print();
    }
}
