package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import de.nosswald.aoc.utils.Point

object Day14 : Day(14, "Regolith Reservoir") {
    private val data = inputList.map { line -> line
        .split(" -> ")
        .map { it.split(",").map(String::toInt) }
        .map { (x, y) -> Point(x, y) }
        .zipWithNext()
    }

    private fun findObstructedPoints() = buildSet {
        data.forEach { pairs ->
            pairs.forEach { (a, b) ->
                val (x1, x2) = listOf(a.x, b.x).sorted()
                val (y1, y2) = listOf(a.y, b.y).sorted()

                (y1..y2).forEach { y ->
                    (x1..x2).forEach { x ->
                        this.add(Point(x, y))
                    }
                }
            }
        }
    }

    override fun partOne(): Int {
        val obstructed = findObstructedPoints().toMutableSet()
        val abyss = obstructed.maxOf { it.y }
        var units = 0

        outer@ while (true) {
            var sand = Point(500, 0)

            while (true) {
                if (sand.y == abyss)
                    break@outer

                sand = when {
                    sand.down() !in obstructed -> sand.down()
                    sand.down().left() !in obstructed -> sand.down().left()
                    sand.down().right() !in obstructed -> sand.down().right()
                    else -> {
                        obstructed.add(sand)
                        break
                    }
                }
            }

            units++
        }

        return units
    }

    override fun partTwo(): Int {
        val obstructed = findObstructedPoints().toMutableSet()
        val floor = obstructed.maxOf { it.y } + 2
        var units = 0

        while (true) {
            var sand = Point(500, 0)

            units++

            while (true) {
                sand = when {
                    sand.down().y == floor -> {
                        obstructed.add(sand)
                        break
                    }
                    sand.down() !in obstructed -> sand.down()
                    sand.down().left() !in obstructed -> sand.down().left()
                    sand.down().right() !in obstructed -> sand.down().right()
                    else -> {
                        if (sand.y == 0)
                            return units

                        obstructed.add(sand)
                        break
                    }
                }
            }
        }
    }
}
