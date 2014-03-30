package de.movope.game.pieces

import de.movope.game.ChessBoard
import de.movope.game.MoveEvaluator
import de.movope.game.Square
import spock.lang.Specification

class KnightTest extends Specification {

    ChessBoard board;

    def setup() {
        board = new ChessBoard();
        board.initPieces();
        setUpPeacesOnBoard()
    }

    def setUpPeacesOnBoard() {
        board.move("B1", "C4");
        board.move("A7", "A5");
        board.move("D2", "H3");
        board.move("D7", "D6");
        board.move("E2", "E4");
        board.print();
    }

    def "correct targets for knight"() {
        when:
        MoveEvaluator evaluator = new MoveEvaluator();
        def evaluation = evaluator.analyse(board, "C4")

        then:
        evaluation.possibleTargets().contains(Square.create("B6"))
        evaluation.possibleTargets().contains(Square.create("E5"))
        evaluation.possibleTargets().contains(Square.create("D2"))
        evaluation.possibleTargets().contains(Square.create("E3"))
        evaluation.possibleTargets().contains(Square.create("A3"))
        evaluation.possibleTargets().size() == 5

        evaluation.possibleAttacks().size() == 2
        evaluation.possibleAttacks().contains(Square.create("A5"))
        evaluation.possibleAttacks().contains(Square.create("D6"))
    }
}
