package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day01 : Day(1, "Title") {
    override fun partOne(): Any {
        return inputList.first().toInt()
    }

    override fun partTwo(): Any {
        return inputList.last().toInt()
    }
}
