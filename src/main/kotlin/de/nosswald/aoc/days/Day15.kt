package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import de.nosswald.aoc.utils.Point
import kotlin.math.abs

object Day15 : Day(15, "Beacon Exclusion Zone") {
    private const val ROW = 2_000_000
    private const val MAX = 4_000_000
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

            if (beacon.y == ROW) existing.add(beacon.x)
            (sensor.x - offset..sensor.x + offset).forEach(invalid::add)
        }

        return invalid.size - existing.size
    }

    override fun partTwo(): Long {
        (0..MAX).forEach { y ->
            var x = 0

            while (x <= MAX) {
                val point = Point(x, y)
                var flag = true

                for ((sensor, beacon) in closestBeaconToSensor) {
                    if (sensor.distanceTo(beacon) >= sensor.distanceTo(point)) {
                        flag = false
                        x = sensor.x + sensor.distanceTo(beacon) - abs(sensor.y - y)
                        break
                    }
                }

                if (flag)
                    return 1L * point.x * MAX + point.y

                x++
            }
        }
        return -1L
    }

    private fun Point.distanceTo(other: Point): Int {
        return abs(this.x - other.x) + abs(this.y - other.y)
    }
}
