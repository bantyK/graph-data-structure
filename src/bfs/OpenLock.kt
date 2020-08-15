package bfs

import java.util.*
import kotlin.collections.HashSet

//752 https://leetcode.com/problems/open-the-lock/submissions/
fun openLock(deadends: Array<String>, target: String): Int {
    if (deadends.contains("0000") || deadends.contains(target)) {
        return -1;
    }
    val seen = HashSet<String>()
    seen.addAll(deadends);
    val queue = LinkedList<String>()
    queue.offer("0000")
    seen.add("0000")
    var steps = 0
    while (queue.isNotEmpty()) {
        val size = queue.size

        for (i in 0 until size) {
            val current = queue.poll()
            if (current == target) return steps

            val next = getNextStates(current, seen)
            queue.addAll(next)
            seen.addAll(next)
        }
        steps++
    }

    return -1
}

private fun getNextStates(currentState: String, seen: Set<String>): List<String> {
    val next = mutableListOf<String>()

    for (i in 0..3) {
        val currentChar = currentState[i] - '0'
        val forward = currentState.substring(0, i) + (if (currentChar == 9) 0 else currentChar + 1) + currentState.substring(i + 1)
        val backward = currentState.substring(0, i) + (if (currentChar == 0) 9 else currentChar - 1) + currentState.substring(i + 1)

        if (!seen.contains(forward)) {
            next.add(forward)
        }

        if (!seen.contains(backward)) {
            next.add(backward)
        }
    }

    return next
}
