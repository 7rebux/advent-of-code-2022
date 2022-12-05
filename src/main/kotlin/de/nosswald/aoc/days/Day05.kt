package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day05 : Day(5, "Supply Stacks") {
    private val stacks = (0..8).map { i ->
        inputList
            .takeWhile { it.isNotBlank() }
            .dropLast(1)
            .reversed()
            .map { line ->
                val index = i * 4 + 1

                if (line.length >= index)
                    line[index] else ' '
            }
            .filter { it != ' ' }
            .toList()
    }.map { ArrayDeque(it) }

    private val instructions = inputList
        .takeLastWhile { it.isNotBlank() }
        .map { line ->
            val (amount, from, to) = line
                .split(" ")
                .filter { it.toIntOrNull() != null }
                .map { it.toInt() }

            Instruction(amount, from - 1, to - 1)
        }

    override fun partOne() = stacks.clone().also { stacks ->
        instructions.forEach {
            repeat(it.amount) { _ ->
                stacks[it.to].add(stacks[it.from].removeLast())
            }
        }
    }.map { it.last() }.joinToString("")

    override fun partTwo() = stacks.clone().also { stacks ->
        instructions.forEach {
            (0 until it.amount)
            .map { _ -> stacks[it.from].removeLast() }
            .reversed()
            .toList()
                .forEach { crate -> stacks[it.to].add(crate) }
        }
    }.map { it.last() }.joinToString("")

    private data class Instruction(
        val amount: Int,
        val from: Int,
        val to: Int
    )

    private fun List<ArrayDeque<Char>>.clone(): List<ArrayDeque<Char>> {
        return this.map { ArrayDeque(it.toList()) }
    }
}
