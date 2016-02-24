package de.movope.domain

import de.movope.ChessGamePrinter
import spock.lang.Specification

class ChessGameTest extends Specification {

    def "the color if the winning player is undefined in a new game "() {
        when:
        ChessGame game = ChessGame.createNew("ben")

        then:
        game.colorOfWinningPlayer() == Color.UNDEFINED
    }

    def "you can activate the computer for a color and execute moves"() {
        given:
        ChessGame game = ChessGame.createNew("ben")
        def move = Move.create("A2", "A4")

        when:
        game.execute(move)
        game.executeNextMoveForComputer();

        then:
        def boardWithMove = ChessBoard.createNew()
        boardWithMove.execute(move)
        game.board.getSquaresWithPiece(Color.WHITE) == boardWithMove.getSquaresWithPiece(Color.WHITE)
        game.board.getSquaresWithPiece(Color.BLACK) != boardWithMove.getSquaresWithPiece(Color.BLACK)
    }

    def "a player can be game over"() {
        given:
        ChessGame game = ChessGame.createNew("ben")
        List<Move> movesToGameOver = createGameOverMoves()

        when:
        for (Move m : movesToGameOver) {
            game.execute(m)
        }
        ChessGamePrinter.print(game.board)

        then:
        game.colorOfWinningPlayer() == Color.WHITE
    }

    def createGameOverMoves() {
        List<Move> moves = new ArrayList<>();
        moves.add(Move.create("E2", "E4"))
        moves.add(Move.create("F7", "F6"))
        moves.add(Move.create("D2", "D3"))
        moves.add(Move.create("G7", "G5"))
        moves.add(Move.create("D1", "H5"))
        return moves
    }
}
