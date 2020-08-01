package bfs

import java.util.*

// https://www.hackerearth.com/practice/algorithms/graphs/breadth-first-search/practice-problems/algorithm/waves-b18625d7/

fun main() {
    val line = readLine()!!.split(" ")
    val rows = line[0].toInt()
    val cols = line[1].toInt()

    val startRow = line[2].toInt()
    val startCol = line[3].toInt()

    bfs(rows, cols, startRow, startCol)
}

private fun bfs(rows: Int, cols: Int, startRow: Int, startCol: Int) {
    val res = Array(rows) { IntArray(cols) }
    val visited = Array(rows) { BooleanArray(cols) }

    val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(1, 1), intArrayOf(1, -1), intArrayOf(-1, 1), intArrayOf(-1, -1))
    val queue = LinkedList<IntArray>()
    queue.offer(intArrayOf(startRow, startCol))
    visited[startRow][startCol] = true

    var currentValue = 0

    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val cell = queue.poll()

            val row = cell[0]
            val col = cell[1]

            res[row][col] = currentValue

            for (dir in dirs) {
                val newRow = row + dir[0]
                val newCol = col + dir[1]
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || visited[newRow][newCol]) continue
                queue.offer(intArrayOf(newRow, newCol))
                visited[newRow][newCol] = true
            }
        }
        ++currentValue
    }

    for (r in 0 until rows) {
        for (c in 0 until cols) {
            print("${res[r][c]} ")
        }
        println()
    }
}