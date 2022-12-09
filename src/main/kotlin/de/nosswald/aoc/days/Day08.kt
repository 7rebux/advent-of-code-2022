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
            *this.indices.map { i -> Pair(this.asReversed().subList(i, this.size), this.lastIndex - i) }.toTypedArray(),
            *this.indices.map { i -> Pair(this.subList(i, this.size), i) }.toTypedArray()
        )
            .map { (list, i) -> Pair(list.isEmpty() || list.drop(1).all { it < list[0] }, i) }
            .filter { it.first }
            .map { if (transposed) Pair(index, it.second) else Pair(it.second, index) }
            .toSet()
    }

    private fun scenicScore(x: Int, y: Int): Int = TODO()

    private fun List<List<Int>>.transpose(): List<List<Int>> = List(this.first().size) { i -> this.map { it[i] }.toList() }
}
