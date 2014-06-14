package de.movope.game

import spock.lang.Specification

import java.awt.*

class DirectionTest extends Specification {

    def "builder with no flag creates empty list"() {
        when:
        def directions = Direction.Builder.create().directions()

        then:
        directions.size() == 0
    }

    def "builder with parallel flag creates correct list"() {
        when:
        def directions = Direction.Builder.create().parallel().directions()

        then:
        directions.size() == 4
        directions.containsAll(Arrays.asList(new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)));
    }

    def "builder with diagonal flag creates correct list"() {
        when:
        def directions = Direction.Builder.create().diagonal().directions()

        then:
        directions.size() == 4
        directions.containsAll(Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1)))
    }

    def "builder with parallel and diagonal flag creates correct list"() {
        when:
        def directions = Direction.Builder.create().parallel().diagonal().directions()

        then:
        directions.size() == 8
        directions.containsAll(Arrays.asList(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1),
                new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)))
    }

    def "builder with knight flag creates correct list"() {
        when:
        def directions = Direction.Builder.create().knight().directions()

        then:
        directions.size() == 8
        directions.containsAll(Arrays.asList(new Point(2, 1), new Point(2, -1), new Point(-2, 1), new Point(-2, -1),
                new Point(1, 2), new Point(1, -2), new Point(-1, 2), new Point(-1, -2)))
    }
}
