package de.nosswald.aoc.days

import de.nosswald.aoc.Day

object Day07 : Day(7, "No Space Left On Device") {
    private const val TOTAL_SPACE = 70000000
    private const val NEEDED_SPACE = 30000000

    private data class File(val name: String, val size: Int)

    private class Directory(val name: String, val contents: MutableSet<Any>) {
        fun totalSize(): Int = this.contents.sumOf {
                when (it) {
                    is File -> it.size
                    is Directory -> it.totalSize()
                    else -> 0
                }
            }

        fun dirs(): List<Directory> = this.contents
            .filterIsInstance<Directory>()
            .flatMap { it.dirs() }
            .plus(this)
    }

    private val root = Directory("/", mutableSetOf())

    init {
        var current = root

        inputList.forEach { line ->
            when {
                line.startsWith("$ ls") -> return@forEach
                line.startsWith("$ cd") -> {
                    current = when (val name = line.split("$ cd ")[1]) {
                        ".." -> root.dirs().first { it.contents.contains(current) }
                        "/" -> root
                        else -> current.contents
                            .filterIsInstance<Directory>()
                            .first { it.name == name }
                    }
                }
                else -> {
                    val (dirOrSize, name) = line.split(" ")

                    current.contents += if (dirOrSize == "dir")
                        Directory(name, mutableSetOf())
                    else
                        File(name, dirOrSize.toInt())
                }
            }
        }
    }

    override fun partOne() = root.dirs()
        .filter { it.totalSize() < 100000 }
        .sumOf(Directory::totalSize)

    override fun partTwo() = root.dirs()
        .filter { it.totalSize() >= NEEDED_SPACE - (TOTAL_SPACE - root.totalSize()) }
        .minOf(Directory::totalSize)
}
