package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import de.nosswald.aoc.utils.split

object Day13 : Day(13, "Distress Signal") {
    private val data = inputList.map { it.replace("10", "A") }
    private val DIVIDERS = listOf("[[2]]", "[[6]]")

    override fun partOne() = data
        .split(String::isEmpty)
        .mapIndexed { index, (a, b) ->
            if (compare(a, b)) index + 1 else 0
        }
        .sum()

    override fun partTwo() = DIVIDERS
        .map { divider ->
            (data
                .filter(String::isNotEmpty)
                .plus(DIVIDERS)
                .sortedWith { a, b -> if (compare(a, b)) -1 else 1 }
            ).indexOf(divider) + 1
        }
        .reduce(Int::times)

    private fun compare(a: String, b: String): Boolean {
        return if (a.first() == b.first())
            compare(a.substring(1), b.substring(1))
        else if (a.first() == ']')
            true
        else if (b.first() == ']')
            false
        else if (a.first() == '[')
            compare(a.substring(1), "${b.first()}]${b.substring(1)}")
        else if (b.first() == '[')
            compare("${a.first()}]${a.substring(1)}", b.substring(1))
        else
            a.first() < b.first()
    }
}
