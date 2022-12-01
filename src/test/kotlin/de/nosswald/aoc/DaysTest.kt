package de.nosswald.aoc

import de.nosswald.aoc.days.Day01
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
        Answer(Day01, 71502, 208191)
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
