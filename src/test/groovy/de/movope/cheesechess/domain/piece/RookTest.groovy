package de.movope.cheesechess.domain.piece

import de.movope.cheesechess.domain.ChessBoard
import de.movope.ChessGamePrinter
import de.movope.cheesechess.domain.Move
import de.movope.cheesechess.evaluation.MoveEvaluator
import de.movope.cheesechess.domain.Square
import spock.lang.Specification

class RookTest extends Specification {

    ChessBoard board = ChessBoard.createNew()

    def setup() {
        setUpPeacesOnBoard()
        ChessGamePrinter.print(board);
    }

    def setUpPeacesOnBoard() {
        board.movePiece("A1", "C5")
        board.movePiece("E2", "E4")
        board.movePiece("C2", "D4")
        board.movePiece("G7", "G5")
    }


    def "correct targets for rook"() {
        when:
        def evaluation = MoveEvaluator.on(board).analyse(Square.create("C5"))

        then:
        evaluation.possibleTargets().size() == 9
        evaluation.possibleAttacks().size() == 2
        evaluation.possibleAttacks().contains(Move.create("C5", "C7"))
        evaluation.possibleAttacks().contains(Move.create("C5", "G5"))
    }
}
