package de.movope.domain

import spock.lang.Specification

import static de.movope.domain.Direction.Builder.directions

class DirectionTest extends Specification {

    def "builder with no flag creates empty list"() {
        when:
        def directions = directions().create()

        then:
        directions.size() == 0
    }

    def "builder with parallel flag creates correct list"() {
        when:
        def directions = directions().parallel().create()

        then:
        directions.size() == 4
        directions.containsAll(Arrays.asList(Direction.create(0, 1), Direction.create(1, 0), Direction.create(-1, 0),
                                            Direction.create(0, -1)));
    }

    def "builder with diagonal flag creates correct list"() {
        when:
        def directions = directions().diagonal().create()

        then:
        directions.size() == 4
        directions.containsAll(Arrays.asList(Direction.create(1, 1), Direction.create(-1, -1), Direction.create(1, -1),
                                                Direction.create(-1, 1)))
    }

    def "builder with parallel and diagonal flag creates correct list"() {
        when:
        def directions = directions().parallel().diagonal().create()

        then:
        directions.size() == 8
        directions.containsAll(Arrays.asList(Direction.create(1, 1), Direction.create(-1, -1), Direction.create(1, -1), Direction.create(-1, 1),
                Direction.create(0, 1), Direction.create(1, 0), Direction.create(-1, 0), Direction.create(0, -1)))
    }

    def "builder with knight flag creates correct list"() {
        when:
        def directions = directions().knight().create()

        then:
        directions.size() == 8
        directions.containsAll(Arrays.asList(Direction.create(2, 1), Direction.create(2, -1), Direction.create(-2, 1), Direction.create(-2, -1),
                Direction.create(1, 2), Direction.create(1, -2), Direction.create(-1, 2), Direction.create(-1, -2)))
    }
}
