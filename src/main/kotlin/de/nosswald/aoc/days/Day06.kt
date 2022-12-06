package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day06 : Day(6, "Tuning Trouble") {
    private fun findFirstMarker(packetSize: Int): Int = inputString
        .toCharArray()
        .indices
        .find { i ->
            if (i < packetSize) return@find false

            val packet = inputString.substring(i - packetSize, i)

            packet.toCharArray().groupBy { it }.none { it.value.size > 1 }
        } ?: -1

    override fun partOne() = findFirstMarker(4)

    override fun partTwo() = findFirstMarker(14)
}
