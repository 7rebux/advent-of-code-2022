package de.nosswald.aoc.utils

fun <T> List<T>.split(predicate: (T) -> Boolean): List<List<T>> {
    val index = this.indexOfFirst(predicate)

    return if (index == -1)
        listOf(this)
    else
        return listOf(this.take(index)) + this.drop(index + 1).split(predicate)
}
