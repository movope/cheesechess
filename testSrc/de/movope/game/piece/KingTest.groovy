package de.movope.game.piece

import de.movope.game.*
import spock.lang.Specification

class KingTest extends Specification {

    ChessBoard board;

    def setup() {
        board = new ChessBoard();
        board.initPieces();
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.move("E1", "C4");
        board.move("A8", "B5");
        board.move("E2", "E4");
        ChessGameUtils.print(board);
    }

    def "correct targets for king"() {
        when:
        def evaluation = MoveEvaluatorForPieceSet.on(board).analyse(Square.create("C4"))

        then:
        evaluation.possibleTargets().size() == 7
        evaluation.possibleTargets().contains(Move.create("C4", "B3"))
        evaluation.possibleTargets().contains(Move.create("C4", "D4"))

        evaluation.possibleAttacks().size() == 1
        evaluation.possibleAttacks().contains(Move.create("C4", "B5"))
    }
}
