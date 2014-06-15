package de.movope.game

import spock.lang.Specification

class MoveEvaluatorTest extends Specification {

    ChessBoard board = new ChessBoard();

    def setup() {
        board.initPieces();
    }

    def "when entering a board and a position, you return a empty MoveEvaluationResult"() {
        when:
        def result = MoveEvaluator.on(board).analyse(Square.create("A2"))

        then:
        result != null
    }

    def "when you enter a square outside the board, return empty MoveEvaluation"() {
        when:
        def result = MoveEvaluator.on(board).analyse(Square.create("A9"))

        then:
        result.isMovePossible() == false
    }

    def "when you enter a square without piece, return null"() {
        when:
        def result = MoveEvaluator.on(board).analyse(Square.create("A4"))

        then:
        result.isMovePossible() == false
    }

    def "when you enter D1 (queen), the result contains no moves and attacks"() {
        when:
        def result = MoveEvaluator.on(board).analyse(Square.create("D1"))

        then:
        result.isMovePossible() == false
    }


    def "correct targets for bishop with MoveEvaluator"() {
        given:
        setUpPeacesOnBoardForBishop()

        when:
        def result = MoveEvaluator.on(board).analyse(Square.create("D4"))

        then:
        result.possibleTargets().size() == 6
    }

    def "correct targets for queen"() {
        given:
        setUpPeacesOnBoardForQueen()

        when:
        def result = MoveEvaluator.on(board).analyse(Square.create("C4"))

        then:
        result.possibleTargets().size() == 15

    }

    def "correct targets for knight"() {
        given:
        setUpPeacesOnBoardForKnight();

        when:
        def result = MoveEvaluator.on(board).analyse(Square.create("C4"))

        then:
        result.possibleTargets().size() == 5
    }

    def "correct result of MoveEvaluation of all pieces at start"() {
            when:
            ChessGameUtils.print(board)

            then:
            def evaluation = MoveEvaluator.on(board).analyse(Color.BLACK);
            evaluation.possibleTargets().size() == 20
    }

    def "correct result of MoveEvaluation - setup 1"() {
        when:
        ChessGameUtils.print(board)
        board.move("B7", "B6")
        ChessGameUtils.print(board)

        then:
        def evaluation = MoveEvaluator.on(board).analyse(Color.BLACK);
        evaluation.possibleTargets().size() == 21

        when:
        board.move("E7", "E6")
        ChessGameUtils.print(board)

        then:
        def evaluation2 = MoveEvaluator.on(board).analyse(Color.BLACK);
        evaluation2.possibleTargets().size() == 31
    }


    def "correct result of MoveEvaluation of all pieces for checkmate"() {
        when:
        board.move("F1", "H5")
        board.move("F7", "F5")
        board.move("D7", "D6")
        ChessGameUtils.print(board)

        then:
        def evaluation = MoveEvaluator.on(board).analyse(Color.BLACK);
        evaluation.possibleTargets().size() == 2
    }

    def setUpPeacesOnBoardForBishop() {
        board.move("C1", "D4");
        board.move("E2", "E4");
        board.move("F2", "F3");
        board.move("B7", "B6");
        ChessGameUtils.print(board);
    }

    def setUpPeacesOnBoardForQueen() {
        board.move("D1", "C4");
        board.move("A2", "A5");
        board.move("D2", "D6");
        board.move("E2", "E4");
        board.move("C7", "B6");
        ChessGameUtils.print(board);
    }

    def setUpPeacesOnBoardForKnight() {
        board.move("B1", "C4");
        board.move("A7", "A5");
        board.move("D2", "H3");
        board.move("D7", "D6");
        board.move("E2", "E4");
        ChessGameUtils.print(board);
    }
}
