package de.movope.game

import spock.lang.Specification

import java.awt.*

class SquareTest extends Specification {

    Square square;

    def setup() {
        square = Square.create("D2");
    }
    def "toString returns correct String"() {
        expect:
        square.toString() == "D2"
    }

    def "move works"() {
        when:
        square = square.move(new Point(1,0))

        then:
        square.toString() == "E2"
    }

    def "move works2"() {
        when:
        square = square.move(new Point(-1,0))

        then:
        square.toString() == "C2"
    }

    def "move works3"() {
        when:
        square = square.move(new Point(0,-1))

        then:
        square.toString() == "D1"
    }

    def "move works4"() {
        when:
        square = square.move(new Point(1,1))

        then:
        square.toString() == "E3"
    }

    def "move works5"() {
        when:
        square = square.move(new Point(1,0))

        then:
        square.toString() == "E2"
    }

    def "move works6"() {
        when:
        square = square.move(new Point(0,1))

        then:
        square.toString() == "D3"
    }



}
