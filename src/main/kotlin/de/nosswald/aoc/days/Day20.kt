package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day20 : Day(20, "Grove Positioning System") {
    override fun partOne() = decrypt()

    override fun partTwo() = decrypt(key = 811589153, times = 10)

    private fun decrypt(key: Int = 1, times: Int = 1): Long {
        val original = inputList.mapIndexed { i, e -> i to e.toLong() * key }
        val decrypted = original.toMutableList()

        repeat(times) {
            original.forEach {
                val index = decrypted.indexOf(it)

                decrypted.removeAt(index)
                decrypted.add((index + it.second).mod(decrypted.size), it)
            }
        }

        return listOf(1000, 2000, 3000)
            .sumOf { decrypted[(it + decrypted.indexOfFirst { e -> e.second == 0L }) % decrypted.size].second }
    }
}
