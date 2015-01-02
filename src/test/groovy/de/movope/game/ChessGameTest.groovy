package de.movope.game

import spock.lang.Specification

class ChessGameTest extends Specification {

    def "white player has to make first move"() {
        when:
        ChessGame game = ChessGame.createNew("ben")

        then:
        game.nextPlayerToMove() == Color.WHITE
    }


    def "after move of white player, black player has to execute next move"() {
        given:
        ChessGame game = ChessGame.createNew("ben")

        when:
        game.execute(Move.create("A2", "A4"))

        then:
        game.nextPlayerToMove() == Color.BLACK
    }

}
