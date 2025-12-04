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
    val grid : MutableList<String> = readInput(filePath)
    val result = mutableListOf<SimplePoint>()

    endless@ while(true) {
        val points = mutableListOf<SimplePoint>()
        for ((y, r) in grid.withIndex()) {
            for ((x, c) in grid[y].withIndex()) {
                if (c == '.') continue

                points.addAll(markRollNeighbors(y, x, grid))
            }
        }

        if (points.isEmpty()) break@endless
        result.addAll(points)
        for (point in points) {
            grid[point.y] = grid[point.y].replaceRange(point.x, point.x+1, ".")
        }
    }

    return result.size
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

fun markRollNeighbors(y : Int, x : Int, grid : List<String>) : List<SimplePoint> {
    var adj = 0
    val maxAdj = 4

    val maxY = grid.size
    val maxX = grid[0].length

    val marks = mutableListOf<SimplePoint>()

    for (yc in -1..1){
        for (xc in -1..1) {
            if (yc == 0 && xc == 0) continue

            if (isInsideGrid(y+yc, maxY) && isInsideGrid(x+xc, maxX)
                && grid[y+yc][x+xc] == '@') {
                adj++
            }
        }
    }

    if (adj < maxAdj) {
        marks.add(SimplePoint(y, x))
    }

    return marks
}

fun isInsideGrid(c : Int, m : Int) =
    c in 0..<m

fun readInput(filePath : String) : MutableList<String> =
    BufferedReader(FileReader(filePath)).readLines().toMutableList()

fun printGrid(grid : List<String>) =
    grid.forEach { it -> println(it) }