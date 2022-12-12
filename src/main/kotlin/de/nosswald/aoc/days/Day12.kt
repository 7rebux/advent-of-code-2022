package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import de.nosswald.aoc.utils.Point
import kotlin.collections.ArrayDeque

object Day12 : Day(12, "Hill Climbing Algorithm") {
    private val startPos = inputList
        .mapIndexed { y, line -> Point(line.indexOf('S'), y) }
        .first { it.x != -1 }
    private val endPos = inputList
        .mapIndexed { y, line -> Point(line.indexOf('E'), y) }
        .first { it.x != -1 }
    private val grid = inputList
        .map { line ->
            line.toCharArray().map {
                when (it) {
                    'S' -> 1
                    'E' -> 26
                    else -> it - 'a' + 1
                }
            }
        }
    private val lowestPoints = grid
        .mapIndexed { y, heights ->
            buildSet {
                heights.forEachIndexed { x, it ->
                    if (it == 1) this.add(Point(x, y))
                }
            }
        }
        .flatten()

    override fun partOne() = breadthFirstSearch(startPos)

    override fun partTwo() = lowestPoints.minOf(::breadthFirstSearch)

    private fun breadthFirstSearch(start: Point): Int {
        val queue = ArrayDeque<Point>()
        val visited = mutableSetOf<Point>()
        val prev = mutableMapOf<Point, Point>()

        queue.add(start)

        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()

            if (pos == endPos)
                break

            pos.neighbors()
                .filter { (x, y) -> y in grid.indices && x in grid[y].indices && grid[y][x] <= grid[pos.y][pos.x] + 1 }
                .filterNot(visited::contains)
                .forEach {
                    visited.add(it)
                    prev[it] = pos
                    queue.add(it)
                }
        }

        // no path found
        if (endPos !in prev)
            return Int.MAX_VALUE

        val path = mutableSetOf(endPos)

        while (!path.contains(start)) {
            val next = prev[path.last()]!!

            if (next == start)
                break

            path.add(next)
        }

        return path.size
    }
}
