package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day18 : Day(18, "Boiling Boulders") {
    private val cubes = inputList.map {
        val (x, y, z) = it.split(",").map(String::toInt)
        Cube(x, y, z)
    }

    override fun partOne() = cubes.flatMap(Cube::neighbors).count { it !in cubes }

    override fun partTwo(): Int {
        val start = Cube(cubes.minOf(Cube::x) - 1, cubes.minOf(Cube::y) - 1, cubes.minOf(Cube::z) - 1)
        val queue = ArrayDeque<Cube>().also { it.add(start) }
        val visited = mutableSetOf(start)

        while (queue.isNotEmpty()) {
            val cube = queue.removeFirst()

            visited += cube

            cube.neighbors().filterNot(cubes::contains).filterNot(visited::contains).forEach {
                if (it.x in start.x..cubes.maxOf(Cube::x) + 1
                    && it.y in start.y..cubes.maxOf(Cube::y) + 1
                    && it.z in start.z..cubes.maxOf(Cube::z) + 1
                ) {
                    queue.add(it)
                    visited += it
                }
            }
        }

        return cubes.sumOf { it.neighbors().count(visited::contains) }
    }

    private data class Cube(val x: Int, val y: Int, val z: Int) {
        fun neighbors() = buildSet {
            for (i in listOf(-1, 1)) {
                this.add(Cube(x + i, y, z))
                this.add(Cube(x, y + i, z))
                this.add(Cube(x, y, z + i))
            }
        }
    }
}
