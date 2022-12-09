@file:Suppress("spellCheckingInspection")

package de.nosswald.aoc

import de.nosswald.aoc.days.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DaysTest {
    private data class Answer(
        val day: Day,
        val partOne: Any,
        val partTwo: Any
    )

    @TestFactory
    fun answers() = listOf(
        Answer(Day01, 71502, 208191),
        Answer(Day02, 14531, 11258),
        Answer(Day03, 7889, 2825),
        Answer(Day04, 556, 876),
        Answer(Day05, "VJSFHWGFT", "LCTQFBVZV"),
        Answer(Day06, 1965, 2773),
        Answer(Day07, 1428881, 10475598),
        Answer(Day08, 1794, 199272),
    ).map {
        DynamicTest.dynamicTest("Day ${it.day.number} - ${it.day.title}") {
            print("Testing Part 1 - Expecting ${it.partOne}..")
            Assertions.assertEquals(it.partOne, it.day.partOne())
            print(" SUCCESS\n")

            print("Testing Part 2 - Expecting ${it.partTwo}..")
            Assertions.assertEquals(it.partTwo, it.day.partTwo())
            print(" SUCCESS\n")
        }
    }
}
