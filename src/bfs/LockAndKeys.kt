package bfs

//https://www.hackerearth.com/practice/algorithms/graphs/breadth-first-search/practice-problems/algorithm/dhoom-4
import java.util.*

fun main() {
    val line = readLine()!!.split(" ")
    val initialValue = line[0].toLong()
    val finalValue = line[1].toLong()

    val n = readLine()!!.toInt()

    val inp = readLine()!!.split(" ")
    val keys: List<Int> = inp.map { c -> c.toInt() }

    println(bfs(keys, initialValue, finalValue))
}

internal fun bfs(keys: List<Int>, initialVal: Long, finalVal: Long): Int {
    val queue = LinkedList<Long>()
    queue.add(initialVal)
    var steps = 0
    val seen = HashSet<Long>()
    seen.add(initialVal);
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val current = queue.poll()

            for (key in keys) {
                val next = (key * current) % 100000

                if (next == finalVal) {
                    return steps + 1
                }

                if (!seen.contains(next)) {
                    queue.offer(next)
                    seen.add(next)
                }
            }
        }
        steps++
    }
    return -1
}