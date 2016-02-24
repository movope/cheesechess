package de.movope.domain.piece

import de.movope.domain.ChessBoard
import de.movope.ChessGamePrinter
import de.movope.evaluation.MoveEvaluator
import de.movope.domain.Square
import spock.lang.Specification

class PawnTest extends Specification {

    ChessBoard board = ChessBoard.createNew();

    def setup() {
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.movePiece("A2", "A5")
        board.movePiece("D2", "D6")
        board.movePiece("E2", "E4")
        ChessGamePrinter.print(board);
    }

    def "correct targets for pawn"() {

        expect:
        def evaluation1 = MoveEvaluator.on(board).analyse(Square.create("D6"))
        evaluation1.possibleTargets().size() == 0
        evaluation1.possibleAttacks().size() == 2

        def evaluation2 = MoveEvaluator.on(board).analyse(Square.create("B2"))
        evaluation2.possibleTargets().size() == 2
        evaluation2.possibleAttacks().size() == 0

        def evaluation4 = MoveEvaluator.on(board).analyse(Square.create("E7"))
        evaluation4.possibleTargets().size() == 2
        evaluation4.possibleAttacks().size() == 1

        def evaluation3 = MoveEvaluator.on(board).analyse(Square.create("E4"))
        evaluation3.possibleTargets().size() == 1
        evaluation3.possibleAttacks().size() == 0
    }
}
