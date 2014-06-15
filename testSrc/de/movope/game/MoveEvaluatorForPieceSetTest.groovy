package de.movope.game

import spock.lang.Specification

class MoveEvaluatorForPieceSetTest extends Specification {

    ChessBoard board = new ChessBoard();

    def setup() {
        board.initPieces();
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
}
