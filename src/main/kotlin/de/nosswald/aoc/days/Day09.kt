package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import kotlin.math.abs
import kotlin.math.sign

private typealias Position = Pair<Int, Int>

object Day09 : Day(9, "Rope Bridge") {
    override fun partOne() = simulateRope(2)

    override fun partTwo() = simulateRope(10)

    private fun simulateRope(length: Int): Int {
        val rope = Array(length) { Position(0, 0) }
        val tailPath = mutableSetOf(rope.last())

        inputList
            .map { line -> line.split(" ").let { it[0] to it[1].toInt() } }
            .forEach { (dir, times) ->
                repeat(times) {
                    when (dir) {
                        "R" -> rope[0] = rope[0].copy(first = rope[0].first + 1)
                        "L" -> rope[0] = rope[0].copy(first = rope[0].first - 1)
                        "D" -> rope[0] = rope[0].copy(second = rope[0].second + 1)
                        "U" -> rope[0] = rope[0].copy(second = rope[0].second - 1)
                    }

                    rope.indices.drop(1).forEach {
                        val prev = rope[it - 1]

                        if (!(rope[it] touches prev)) {
                            rope[it] = rope[it].follow(prev)
                            tailPath.add(rope.last())
                        }
                    }
                }
            }

        return tailPath.size
    }

    private fun Position.follow(to: Position): Position {
        return if (this.first == to.first)
            this.copy(second = this.second - (this.second - to.second).sign)
        else if (this.second == to.second)
            this.copy(first = this.first - (this.first - to.first).sign)
        else when ((to.first > this.first) to (to.second > this.second)) {
            (true to true) -> Position(this.first + 1, this.second + 1)
            (true to false) -> Position(this.first + 1, this.second - 1)
            (false to true) -> Position(this.first - 1, this.second + 1)
            else -> Position(this.first - 1, this.second - 1)
        }
    }

    private infix fun Position.touches(other: Position) =
        abs(this.first - other.first) <= 1 && abs(this.second - other.second) <= 1
}
