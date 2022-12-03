package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day03 : Day(3, "Rucksack Reorganization") {
    override fun partOne() = inputList
        .map { Pair(it.substring(0, it.length / 2), it.substring(it.length / 2)) }
        .sumOf { compartments ->
            compartments.first.toCharArray()
                .find { compartments.second.contains(it) }!!
                .getPriority()
        }

    override fun partTwo() = inputList
        .chunked(3)
        .sumOf { compartments ->
            compartments[0].toCharArray()
                .find { compartments[1].contains(it) && compartments[2].contains(it) }!!
                .getPriority()
        }

    @Suppress("spellCheckingInspection")
    private fun Char.getPriority() = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(this) + 1
}
