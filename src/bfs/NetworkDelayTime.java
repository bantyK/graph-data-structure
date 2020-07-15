package bfs;

import java.util.*;

// 743 https://leetcode.com/problems/network-delay-time/
public class NetworkDelayTime {
    public static void main(String[] args) {
        NetworkDelayTime obj = new NetworkDelayTime();
        System.out.println(obj.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(obj.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 4}}, 3, 1));
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int time = edge[2];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new Node(to, time));
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] time = new int[N + 1];
        Arrays.fill(time, -1);
        queue.offer(K);
        time[K] = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (graph.containsKey(curr)) {
                for (Node n : graph.get(curr)) {
                    int to = n.to;
                    int newTime = time[curr] + n.time;
                    if (time[to] == -1 || time[to] > newTime) {
                        // if this node is never visited or if we are reaching this node with a better time
                        // in both cases, update the time.
                        time[to] = newTime;
                        queue.offer(to);
                    }
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            if (time[i] == -1) {
                return -1;
            }
            maxTime = Math.max(maxTime, time[i]);
        }

        return maxTime;
    }

    static class Node {
        int to;
        int time;

        public Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }
}
