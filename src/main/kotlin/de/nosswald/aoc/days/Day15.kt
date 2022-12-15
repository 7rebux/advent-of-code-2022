package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import de.nosswald.aoc.utils.Point
import kotlin.math.abs

object Day15 : Day(15, "Beacon Exclusion Zone") {
    private const val ROW = 2_000_000
    private val closestBeaconToSensor = inputList.map { line ->
        val (sX, sY, bX, bY) = Regex("-?\\d+")
            .findAll(line)
            .map { it.value.toInt() }
            .toList()

        Point(sX, sY) to Point(bX, bY)
    }

    override fun partOne(): Int {
        val invalid = mutableSetOf<Int>()
        val existing = mutableSetOf<Int>()

        closestBeaconToSensor.forEach { (sensor, beacon) ->
            val distance = sensor.distanceTo(beacon)
            val offset = distance - abs(sensor.y - ROW)
            val left = sensor.x - offset
            val right = sensor.x + offset

            for (x in left..right)
                invalid.add(x)

            if (beacon.y == ROW)
                existing.add(beacon.x)
        }

        return invalid.size - existing.size
    }

    override fun partTwo() = TODO()

    private fun Point.distanceTo(other: Point): Int {
        return abs(this.x - other.x) + abs(this.y - other.y)
    }
}
