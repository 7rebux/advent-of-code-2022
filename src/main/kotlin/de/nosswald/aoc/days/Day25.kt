package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day25 : Day(25, "Full of Hot Air") {
    override fun partOne() = inputList
        .sumOf { it.decode() }
        .encode()

    override fun partTwo() = -1

    private fun String.decode(): Long {
        var res = 0L

        for (c in this) {
            res *= 5
            res += when (c) {
                '0', '1', '2' -> c.digitToInt()
                '-' -> -1
                '=' -> -2
                else -> error("Invalid input format")
            }
        }

        return res
    }

    private fun Long.encode(): String {
        var temp = this
        var res = ""

        while (temp > 0) {
            res = "012=-"[(temp % 5).toInt()] + res
            temp -= (temp + 2) % 5 - 2
            temp /= 5
        }

        return res
    }
}
