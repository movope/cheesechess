package de.movope.domain.piece

import de.movope.domain.ChessBoard
import de.movope.util.ChessGameUtils
import de.movope.domain.Move
import de.movope.evaluation.MoveEvaluator
import de.movope.domain.Square
import spock.lang.Specification

class KingTest extends Specification {

    ChessBoard board = ChessBoard.createNew();

    def setup() {
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.movePiece("E1", "C4");
        board.movePiece("A8", "B5");
        board.movePiece("E2", "E4");
        ChessGameUtils.print(board);
    }

    def "correct targets for king"() {
        when:
        def evaluation = MoveEvaluator.on(board).analyse(Square.create("C4"))

        then:
        evaluation.possibleTargets().size() == 7
        evaluation.possibleTargets().contains(Move.create("C4", "B3"))
        evaluation.possibleTargets().contains(Move.create("C4", "D4"))

        evaluation.possibleAttacks().size() == 1
        evaluation.possibleAttacks().contains(Move.create("C4", "B5"))
    }
}
