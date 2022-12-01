package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day01 : Day(1, "Calorie Counting") {
    private const val SEPARATOR = ""
    private val calories = Array(inputList.count { it == SEPARATOR } + 1) { 0 }

    init {
        var i = 0

        for (line in inputList)
            if (line == SEPARATOR) i++ else calories[i] += line.toInt()
    }

    override fun partOne() = calories.max()

    override fun partTwo() = calories.sorted().takeLast(3).sum()
}
