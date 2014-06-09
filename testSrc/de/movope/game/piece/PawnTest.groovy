package de.movope.game.piece

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
        def evaluation1 = MoveEvaluator.with(board).analyse("D6")
        evaluation1.possibleTargets().size() == 0
        evaluation1.possibleAttacks().size() == 2

        def evaluation2 = MoveEvaluator.with(board).analyse("B2")
        evaluation2.possibleTargets().size() == 2
        evaluation2.possibleAttacks().size() == 0

        def evaluation4 = MoveEvaluator.with(board).analyse("E7")
        evaluation4.possibleTargets().size() == 2
        evaluation4.possibleAttacks().size() == 1

        def evaluation3 = MoveEvaluator.with(board).analyse("E4")
        evaluation3.possibleTargets().size() == 1
        evaluation3.possibleAttacks().size() == 0
    }
}
