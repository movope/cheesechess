package de.movope.game.pieces

import de.movope.game.ChessBoard
import de.movope.game.MoveEvaluator
import spock.lang.Specification

class PawnTest extends Specification {

    ChessBoard board;

    def setup() {
        board = new ChessBoard();
        board.initPieces();
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.move("A2", "A5")
        board.move("D2", "D6")
        board.move("E2", "E4")
        board.print();
    }

    def "correct targets for pawn"() {

        expect:
        MoveEvaluator evaluator = new MoveEvaluator();
        def evaluation1 = evaluator.analyse(board, "D6")
        evaluation1.possibleTargets().size() == 0

        def evaluation2 = evaluator.analyse(board, "B2")
        evaluation2.possibleTargets().size() == 2

        def evaluation3 = evaluator.analyse(board, "E4")
        evaluation3.possibleTargets().size() == 1

        //TODO: Attacks for Pawns ....
    }
}
