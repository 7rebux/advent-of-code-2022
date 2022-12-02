package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day02 : Day(2, "Rock Paper Scissors") {
    override fun partOne() = inputList
        .map { Pair("ABC".indexOf(it[0]), "XYZ".indexOf(it[2])) }
        .sumOf { it.second + 1 + arrayOf(3, 6, 0)[(it.second - it.first + 3) % 3] }

    override fun partTwo() = inputList
        .map { Pair("ABC".indexOf(it[0]), "XYZ".indexOf(it[2])) }
        .sumOf { it.second * 3 + (it.first + it.second + 2) % 3 + 1 }
}
