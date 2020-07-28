package bfs

import java.util.*

//https://www.hackerearth.com/practice/algorithms/graphs/breadth-first-search/practice-problems/algorithm/monk-and-the-islands/description/
fun main() {

    var t = readLine()!!.toInt()

    while (t-- > 0) {
        val line1 = readLine()!!.split(" ")
        val n = line1[0].toInt()
        val m = line1[1].toInt()

        val graph = mutableMapOf<Int, MutableList<Int>>()

        for (i in 0 until m) {
            val line2 = readLine()!!.split(" ")
            val src = line2[0].toInt()
            val dest = line2[1].toInt()

            graph.putIfAbsent(src, mutableListOf())
            graph[src]!!.add(dest)
            graph.putIfAbsent(dest, mutableListOf())
            graph[dest]!!.add(src)
        }

        println(bfs(graph, n))
    }
}

fun bfs(graph: Map<Int, List<Int>>, destination: Int): Int {
    val queue = LinkedList<Int>()
    val visited = BooleanArray(destination + 1)
    queue.offer(1)
    var steps = 0
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val current = queue.poll()
            visited[current] = true
            if (current == destination) {
                return steps
            }
            if (graph.containsKey(current)) {
                for (neighbor in graph[current] ?: emptyList()) {
                    if (!visited[neighbor])
                        queue.offer(neighbor)
                }
            }
        }
        steps++
    }

    return -1
}

