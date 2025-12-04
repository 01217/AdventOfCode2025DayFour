import java.io.BufferedReader
import java.io.FileReader

fun main() {
    val resultTaskOne = taskOne("inp/input.in")
    val resultTaskTwo = taskTwo("inp/input.in")

    println("Task1: $resultTaskOne")
    println("Task2: $resultTaskTwo")
}

fun taskOne(filePath : String) : Int{
    val grid = readInput(filePath)
    var result = 0

    for ((y, r) in grid.withIndex()) {
        for ((x, c) in grid[y].withIndex()) {
            if (c == '.') continue

            if (checkRollNeighbors(y, x, grid)) {
                result++
            }
        }
    }

    return result
}

fun taskTwo(filePath : String) : Int {
    return 0
}

fun checkRollNeighbors(y : Int, x : Int, grid : List<String>) : Boolean {
    var adj = 0
    val maxAdj = 4
    val maxY = grid.size
    val maxX = grid[0].length

    for(yc in -1 .. 1) {
        for (xc in -1 .. 1) {
            if (yc == 0 && xc == 0) continue

            if (isInsideGrid(y+yc, maxY) && isInsideGrid(x+xc, maxX)
                && grid[y+yc][x+xc] == '@') {
                adj++
            }
        }
    }

    return adj < maxAdj
}

fun isInsideGrid(c : Int, m : Int) =
    c in 0..<m

fun readInput(filePath : String) : List<String> =
    BufferedReader(FileReader(filePath)).readLines()

fun printGrid(grid : List<String>) =
    grid.forEach { it -> println(it) }