package de.movope.game

import spock.lang.Specification

class ChessGameTest extends Specification {

    def "white player has to make first move"() {
        when:
        ChessGame game = ChessGame.createNew("ben")

        then:
        game.nextPlayerToMove() == Color.WHITE
    }

    def "the color if the winning player is undefined in a new game "() {
        when:
        ChessGame game = ChessGame.createNew("ben")

        then:
        game.colorOfWinningPlayer() == Color.UNDEFINED
    }


    def "after move of white player, black player has to execute next move"() {
        given:
        ChessGame game = ChessGame.createNew("ben")

        when:
        game.execute(Move.create("A2", "A4"))

        then:
        game.nextPlayerToMove() == Color.BLACK
    }

    def "you can activate the computer for a color and execute moves"() {
        given:
        ChessGame game = ChessGame.createNew("ben")
        def move = Move.create("A2", "A4")

        when:
        game.activateComputeForColor(Color.BLACK)
        game.execute(move)
        game.executeNextMoveForComputer();

        then:
        game.nextPlayerToMove() == Color.WHITE
        def boardWithMove = ChessBoard.createNew("ben")
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
        ChessGameUtils.print(game.board)

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
