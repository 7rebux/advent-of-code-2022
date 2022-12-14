package de.nosswald.aoc.utils

data class Point(val x: Int, val y: Int) {
    fun right() = Point(x + 1, y)
    fun left() = Point(x - 1, y)
    fun down() = Point(x, y + 1)
    fun up() = Point(x, y - 1)

    fun neighbors(): Set<Point> = setOf(right(), left(), down(), up())
}
