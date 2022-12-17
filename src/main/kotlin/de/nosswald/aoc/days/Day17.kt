package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import de.nosswald.aoc.utils.Point

private typealias Rock = Set<Point>

object Day17 : Day(17, "Pyroclastic Flow") {
    private const val TOWER_WIDTH = 7
    private val ROCKS = setOf(
        setOf(Point(2, 0), Point(3, 0), Point(4, 0), Point(5, 0)),
        setOf(Point(3, 0), Point(2, 1), Point(3, 1), Point(4, 1), Point(3, 2)),
        setOf(Point(4, 0), Point(4, 1), Point(2, 0), Point(3, 0), Point(4, 2)),
        setOf(Point(2, 0), Point(2, 1), Point(2, 2), Point(2, 3)),
        setOf(Point(2, 0), Point(3, 0), Point(2, 1), Point(3, 1))
    )

    override fun partOne() = simulate(until = 2022)

    override fun partTwo() = TODO()

    private fun simulate(until: Int): Int {
        val tower = (0..6).map { Point(it, 0) }.toMutableSet()
        var i = 0
        var count = 0
        var top = 0

        while (true) {
            var rock = ROCKS.elementAt(count % 5).map { it.copy(y = it.y + top + 4) }.toSet()

            while (true) {
                if (inputString[i] == '<') {
                    rock = rock.left()

                    if (rock.any(tower::contains))
                        rock = rock.right()
                } else {
                    rock = rock.right()

                    if (rock.any(tower::contains))
                        rock = rock.left()
                }

                rock = rock.up()
                i = (i + 1) % inputString.length

                if (rock.any(tower::contains)) {
                    rock = rock.down()
                    tower.addAll(rock)

                    top = tower.maxOf(Point::y)

                    break
                }
            }

            count++

            if (count == until)
                return top
        }
    }

    private fun Rock.left() = if (this.any { it.x == 0 }) this else this.map(Point::left).toSet()

    private fun Rock.right() = if (this.any { it.x == TOWER_WIDTH - 1 }) this else this.map(Point::right).toSet()

    private fun Rock.down() = this.map(Point::down).toSet()

    private fun Rock.up() = this.map(Point::up).toSet()
}
