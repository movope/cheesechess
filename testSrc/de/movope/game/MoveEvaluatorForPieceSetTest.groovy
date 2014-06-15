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
            def evaluation = MoveEvaluatorForPieceSet.on(board).analyse(Color.BLACK);
            evaluation.possibleTargets().size() == 20
    }

    def "correct result of MoveEvaluation - setup 1"() {
        when:
        ChessGameUtils.print(board)
        board.move("B7", "B6")
        ChessGameUtils.print(board)

        then:
        def evaluation = MoveEvaluatorForPieceSet.on(board).analyse(Color.BLACK);
        evaluation.possibleTargets().size() == 21

        when:
        board.move("E7", "E6")
        ChessGameUtils.print(board)

        then:
        def evaluation2 = MoveEvaluatorForPieceSet.on(board).analyse(Color.BLACK);
        evaluation2.possibleTargets().size() == 31
    }


    def "correct result of MoveEvaluation of all pieces for checkmate"() {
        when:
        board.move("F1", "H5")
        board.move("F7", "F5")
        board.move("D7", "D6")
        ChessGameUtils.print(board)

        then:
        def evaluation = MoveEvaluatorForPieceSet.on(board).analyse(Color.BLACK);
        evaluation.possibleTargets().size() == 2
    }

    def "evaluation contains no move after which the king is in check"() {
        when:
        board.move("C2", "C3")
        board.move("F1", "C2")
        board.move("F7", "F5")
        board.move("D2", "D4")
        board.move("D1", "D3")
        board.move("F8", "E7")
        ChessGameUtils.print(board)

        then:
        def evaluation1 = MoveEvaluatorForPieceSet.on(board).analyse(Color.BLACK);
        evaluation1.possibleTargets().size() == 27

        when:
        board.move("D3", "E3")
        board.move("C2", "B3")
        ChessGameUtils.print(board)

        then:
        def evaluation2 = MoveEvaluatorForPieceSet.on(board).analyse(Color.BLACK);
        !evaluation2.possibleTargets().contains(Move.create("E8","F7"))
        !evaluation2.possibleTargets().contains(Move.create("E7","D6"))
        !evaluation2.possibleTargets().contains(Move.create("E7","C6"))
        evaluation2.possibleTargets().size() == 18
    }
}
