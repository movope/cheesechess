package de.movope.cheesechess.domain

import spock.lang.Specification

class PlayerTest extends Specification {

    def "a player can be created"() {
        given:
        def player = Player.newPlayer()

        expect:
        !player.controlledByComputer
        !player.gameOver
    }

    def "a player controlled by the computer can be created"() {
        given:
        def player = Player.computer()

        expect:
        player.isControlledByComputer()
        !player.gameOver
    }

    def "a player can be set to gameover"() {
        given:
        def player = Player.newPlayer()

        when:
        player.gameOver()

        then:
        player.gameOver

    }
}
