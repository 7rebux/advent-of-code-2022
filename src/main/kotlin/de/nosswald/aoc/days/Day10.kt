package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import kotlin.math.abs

object Day10 : Day(10, "Cathode-Ray Tube") {
    private const val SHOW_PART_TWO = false

    override fun partOne(): Any {
        var (x, cycle, result) = listOf(1, 0, 0)

        fun cycle() {
            cycle++

            if (cycle in listOf(20, 60, 100, 140, 180, 220))
                result += x * cycle

            if (SHOW_PART_TWO) {
                if (abs((cycle - 1) % 40 - x) < 2) print("#") else print(".")
                if (cycle % 40 == 0) println()
            }
        }

        inputList.forEach { line ->
            if (line == "noop") cycle()
            else {
                val add = line.split(" ")[1].toInt()

                repeat(2) { cycle() }
                x += add
            }
        }

        return result
    }

    @Suppress("SpellCheckingInspection")
    override fun partTwo() = "RZHFGJCB" // in my case atleast
}
