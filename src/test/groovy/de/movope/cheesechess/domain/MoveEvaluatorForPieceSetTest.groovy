package de.movope.cheesechess.domain

import de.movope.cheesechess.evaluation.MoveEvaluator
import de.movope.ChessGamePrinter
import spock.lang.Specification

class MoveEvaluatorForPieceSetTest extends Specification {

    ChessBoard board = ChessBoard.createNew();

    def "correct result of MoveEvaluation of all pieces at start"() {
            when:
            ChessGamePrinter.print(board)

            then:
            def evaluation = MoveEvaluator.on(board).analyse(Color.BLACK);
            evaluation.possibleTargets().size() == 20
    }

    def "correct result of MoveEvaluation - setup 1"() {
        when:
        ChessGamePrinter.print(board)
        board.movePiece("B7", "B6")
        ChessGamePrinter.print(board)

        then:
        def evaluation = MoveEvaluator.on(board).analyse(Color.BLACK);
        evaluation.possibleTargets().size() == 21

        when:
        board.movePiece("E7", "E6")
        ChessGamePrinter.print(board)

        then:
        def evaluation2 = MoveEvaluator.on(board).analyse(Color.BLACK);
        evaluation2.possibleTargets().size() == 31
    }


    def "correct result of MoveEvaluation of all pieces for checkmate"() {
        when:
        board.movePiece("F1", "H5")
        board.movePiece("F7", "F5")
        board.movePiece("D7", "D6")
        ChessGamePrinter.print(board)

        then:
        def evaluation = MoveEvaluator.on(board).analyse(Color.BLACK);
        evaluation.possibleTargets().size() == 2
    }

    def "evaluation contains no move after which the king is in check"() {
        when:
        board.movePiece("C2", "C3")
        board.movePiece("F1", "C2")
        board.movePiece("F7", "F5")
        board.movePiece("D2", "D4")
        board.movePiece("D1", "D3")
        board.movePiece("F8", "E7")
        ChessGamePrinter.print(board)

        then:
        def evaluation1 = MoveEvaluator.on(board).analyse(Color.BLACK);
        evaluation1.possibleTargets().size() == 27

        when:
        board.movePiece("D3", "E3")
        board.movePiece("C2", "B3")
        ChessGamePrinter.print(board)

        then:
        def evaluation2 = MoveEvaluator.on(board).analyse(Color.BLACK);
        !evaluation2.possibleTargets().contains(Move.create("E8","F7"))
        !evaluation2.possibleTargets().contains(Move.create("E7","D6"))
        !evaluation2.possibleTargets().contains(Move.create("E7","C6"))
        evaluation2.possibleTargets().size() == 18
    }
}
