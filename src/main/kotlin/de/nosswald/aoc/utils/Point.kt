package de.nosswald.aoc.utils

data class Point(val x: Int, val y: Int) {
    private fun right() = Point(x + 1, y)
    private fun left() = Point(x - 1, y)
    private fun down() = Point(x, y + 1)
    private fun up() = Point(x, y - 1)

    fun neighbors(): Set<Point> = setOf(right(), left(), down(), up())
}
