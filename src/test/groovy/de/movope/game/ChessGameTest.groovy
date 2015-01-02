package de.movope.game

import spock.lang.Specification

class ChessGameTest extends Specification {

    def "white player has to make first move"() {
        when:
        ChessGame game = ChessGame.createNew("ben")

        then:
        game.nextPlayerToMove() == Color.WHITE
    }

}
