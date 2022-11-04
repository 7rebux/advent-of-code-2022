package de.nosswald.aoc

abstract class Day(val number: Int, val title: String) {
    protected val inputString by lazy { InputReader.readAsString(number) }
    protected val inputList by lazy { InputReader.readAsList(number) }

    abstract fun partOne(): Any
    abstract fun partTwo(): Any
}
