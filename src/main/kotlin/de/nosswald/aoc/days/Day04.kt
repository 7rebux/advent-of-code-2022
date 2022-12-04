package de.nosswald.aoc.days

import de.nosswald.aoc.Day

private typealias Section = Pair<IntRange, IntRange>

object Day04 : Day(4, "Camp Cleanup") {
    private var sections: List<Section> = inputList
        .map { it.split(",") }
        .map { it.map { range -> range.split("-").map { num -> num.toInt() } } }
        .map { Section(IntRange(it[0][0], it[0][1]), IntRange(it[1][0], it[1][1])) }

    override fun partOne() = sections.count {
        it.first.subtract(it.second).isEmpty() || it.second.subtract(it.first).isEmpty()
    }

    override fun partTwo() = sections.count { section ->
        section.first.any { section.second.contains(it) } || section.second.any { section.first.contains(it) }
    }
}
