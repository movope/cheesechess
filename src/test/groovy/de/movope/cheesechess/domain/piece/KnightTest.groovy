package de.movope.cheesechess.domain.piece

import de.movope.cheesechess.domain.ChessBoard
import de.movope.ChessGamePrinter
import de.movope.cheesechess.domain.Move
import de.movope.cheesechess.evaluation.MoveEvaluator
import de.movope.cheesechess.domain.Square
import spock.lang.Specification

class KnightTest extends Specification {

    ChessBoard board = ChessBoard.createNew()

    def setup() {
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.movePiece("B1", "C4");
        board.movePiece("A7", "A5");
        board.movePiece("D2", "H3");
        board.movePiece("D7", "D6");
        board.movePiece("E2", "E4");
        ChessGamePrinter.print(board);
    }

    def "correct targets for knight"() {
        when:
        def evaluation = MoveEvaluator.on(board).analyse(Square.create("C4"))

        then:
        evaluation.possibleTargets().contains(Move.create("C4", "B6"))
        evaluation.possibleTargets().contains(Move.create("C4", "E5"))
        evaluation.possibleTargets().contains(Move.create("C4", "D2"))
        evaluation.possibleTargets().contains(Move.create("C4", "E3"))
        evaluation.possibleTargets().contains(Move.create("C4", "A3"))
        evaluation.possibleTargets().size() == 5

        evaluation.possibleAttacks().size() == 2
        evaluation.possibleAttacks().contains(Move.create("C4", "A5"))
        evaluation.possibleAttacks().contains(Move.create("C4", "D6"))
    }
}
