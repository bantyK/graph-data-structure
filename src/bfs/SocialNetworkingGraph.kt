package bfs

import java.util.*
// https://www.hackerearth.com/practice/algorithms/graphs/breadth-first-search/practice-problems/algorithm/social-networking-graph/description/
fun bfs(graph: Map<Int, List<Int>>, numVertices: Int, src: Int, level: Int): Int {
    val queue = LinkedList<Int>()
    val visited = BooleanArray(numVertices + 1)

    val nodes = mutableListOf<Int>()
    var currentLevel = 0

    queue.add(src)
    visited[src] = true

    while (queue.isNotEmpty() && currentLevel < level) {
        val size = queue.size
        nodes.clear()
        if(currentLevel > level) return 0
        currentLevel++
        for(i in 0 until size) {
            val current = queue.poll()
            for(neighbor in graph.getOrDefault(current, emptyList())) {
                if(!visited[neighbor]) {
                    queue.offer(neighbor)
                    visited[neighbor] = true
                }
            }
        }
        nodes.addAll(queue)
    }
    return nodes.size
}

fun main() {
    val line1 = readLine()!!.split(" ")
    val n = line1[0].toInt()
    val e = line1[1].toInt()

    val graph = mutableMapOf<Int, MutableList<Int>>()

    for (i in 0 until e) {
        val line = readLine()!!.split(" ")
        val src = line[0].toInt()
        val dest = line[1].toInt()

        graph.putIfAbsent(src, mutableListOf())
        graph[src]!!.add(dest)

        graph.putIfAbsent(dest, mutableListOf())
        graph[dest]!!.add(src)
    }

    var numQueries = readLine()!!.toInt()

    while (numQueries-- > 0) {
        val query = readLine()!!.split(" ")
        println(bfs(graph, n, query[0].toInt(), query[1].toInt()))
    }
}
