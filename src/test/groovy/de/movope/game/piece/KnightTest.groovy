package de.movope.game.piece

import de.movope.game.ChessBoard
import de.movope.game.ChessGameUtils
import de.movope.game.Move
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
        board.movePiece("B1", "C4");
        board.movePiece("A7", "A5");
        board.movePiece("D2", "H3");
        board.movePiece("D7", "D6");
        board.movePiece("E2", "E4");
        ChessGameUtils.print(board);
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
