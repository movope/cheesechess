package de.movope.game.piece

import de.movope.game.ChessBoard
import de.movope.game.ChessGameUtils
import de.movope.game.Square
import spock.lang.Specification

class RookTest extends Specification {

    ChessBoard board;

    def setup() {
        board = new ChessBoard();
        board.initPieces();
        setUpPeacesOnBoard()
        ChessGameUtils.print(board);
    }

    def setUpPeacesOnBoard() {
        board.move("A1", "C5")
        board.move("E2", "E4")
        board.move("C2", "D4")
        board.move("G7", "G5")
    }


    def "correct targets for rook"() {
        when:
        def evaluation = board.getMoveEvaluationFor(Square.create("C5"))

        then:
        evaluation.possibleTargets().size() == 9
        evaluation.possibleAttacks().size() == 2
        evaluation.possibleAttacks().contains(Square.create("C7"))
        evaluation.possibleAttacks().contains(Square.create("G5"))
    }
}
