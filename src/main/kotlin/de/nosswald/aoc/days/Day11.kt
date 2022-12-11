package de.nosswald.aoc.days

import de.nosswald.aoc.Day
import de.nosswald.aoc.utils.split

object Day11 : Day(11, "Monkey in the Middle") {
    private val monkeys = inputList
        .split(String::isEmpty)
        .map {
            val id = it[0].elementAt(7).digitToInt()
            val items = it[1]
                .substringAfter(": ")
                .split(", ")
                .map(String::toLong)
                .toMutableList()
            val arithmeticOperator = it[2].split("old ")[1].first().toOperator()
            val numberOrNull = it[2].split(" ").last().toLongOrNull()
            val divisor = it[3].split(" ").last().toLong()
            val idIfTrue = it[4].split(" ").last().toInt()
            val idIfFalse = it[5].split(" ").last().toInt()

            Monkey(id, items,
                operate = { x -> arithmeticOperator(x, numberOrNull ?: x) },
                test = { x -> if (x % divisor == 0L) idIfTrue else idIfFalse }
            )
        }

    private val magicNumber = inputList
        .filter { it.contains("divisible by") }
        .map { it.split(" ").last().toInt() }
        .reduce(Int::times)

    override fun partOne(): Long = with(monkeys.clone()) {
        repeat(20) { simulateRound(this) { it / 3 } }
        this.calcMonkeyBusiness()
    }

    override fun partTwo(): Long = with(monkeys.clone()) {
        repeat(10000) { simulateRound(this) { it % magicNumber } }
        this.calcMonkeyBusiness()
    }

    private fun simulateRound(monkeys: List<Monkey>, manage: (Long) -> Long) {
        monkeys.forEach { monkey ->
            while (monkey.items.isNotEmpty()) {
                val item = monkey.items.removeFirst()
                val worryLevel = manage(monkey.operate(item))
                val receiver = monkey.test(worryLevel)

                monkey.opCount++

                monkeys.find { it.id == receiver }!!.items.add(worryLevel)
            }
        }
    }

    private fun List<Monkey>.calcMonkeyBusiness() = this
        .map(Monkey::opCount)
        .sortedDescending()
        .take(2)
        .reduce(Long::times)

    private fun Char.toOperator(): (Long, Long) -> Long {
        when (this) {
            '+' -> return { a, b -> a + b }
            '*' -> return { a, b -> a * b }
            else -> error("Invalid operator: $this")
        }
    }

    private data class Monkey(
        val id: Int,
        val items: MutableList<Long>,
        val operate: (Long) -> Long,
        val test: (Long) -> Int,
        var opCount: Long = 0
    )

    private fun List<Monkey>.clone() =
        this.map { Monkey(it.id, it.items.toMutableList(), it.operate, it.test) }
}
