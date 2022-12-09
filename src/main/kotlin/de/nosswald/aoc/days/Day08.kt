package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day08 : Day(8, "Treetop Tree House") {
    private val grid = inputList.map { it.toList().map(Char::digitToInt) }

    override fun partOne() = setOf(
        *grid.flatMapIndexed { y, row -> row.visibleTreeIndices(false, y) }.toTypedArray(),
        *grid.transpose().flatMapIndexed { x, col -> col.visibleTreeIndices(true, x) }.toTypedArray()
    ).size

    override fun partTwo() = grid
        .flatMapIndexed { y, row -> row.indices.map { x -> scenicScore(x, y) } }
        .max()

    private fun List<Int>.visibleTreeIndices(transposed: Boolean, index: Int): Set<Pair<Int, Int>> {
        return sequenceOf(
            *this.indices.map { i -> this.asReversed().subList(i, this.size) to this.lastIndex - i }.toTypedArray(),
            *this.indices.map { i -> this.subList(i, this.size) to i }.toTypedArray()
        )
            .map { (list, i) -> (list.isEmpty() || list.drop(1).all { it < list[0] }) to i }
            .filter { it.first }
            .map { if (transposed) index to it.second else it.second to index }
            .toSet()
    }

    private fun scenicScore(x: Int, y: Int): Int {
        val right = (x + 1 until grid.size).map { it to y }
        val left = (x - 1 downTo 0).map { it to y }
        val down = (y + 1 until grid[y].size).map { x to it }
        val up = (y - 1 downTo 0).map { x to it }

        return listOf(right, left, down, up)
            .map { minOf(it.takeWhile { (a, b) -> grid[b][a] < grid[y][x] }.size + 1, it.size) }
            .reduce(Int::times)
    }

    private fun List<List<Int>>.transpose(): List<List<Int>> = List(this.first().size) { i -> this.map { it[i] }.toList() }
}
