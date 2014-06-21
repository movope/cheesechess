package de.movope.game

import spock.lang.Specification

class MoveEvaluatorForPiecesTest extends Specification {

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

    def setUpPeacesOnBoardForBishop() {
        board.movePiece("C1", "D4");
        board.movePiece("E2", "E4");
        board.movePiece("F2", "F3");
        board.movePiece("B7", "B6");
        ChessGameUtils.print(board);
    }

    def setUpPeacesOnBoardForQueen() {
        board.movePiece("D1", "C4");
        board.movePiece("A2", "A5");
        board.movePiece("D2", "D6");
        board.movePiece("E2", "E4");
        board.movePiece("C7", "B6");
        ChessGameUtils.print(board);
    }

    def setUpPeacesOnBoardForKnight() {
        board.movePiece("B1", "C4");
        board.movePiece("A7", "A5");
        board.movePiece("D2", "H3");
        board.movePiece("D7", "D6");
        board.movePiece("E2", "E4");
        ChessGameUtils.print(board);
    }
}
