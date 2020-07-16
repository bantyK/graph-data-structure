package distance.bellmanford;

import java.util.*;

//743 https://leetcode.com/problems/network-delay-time/
public class NetworkDelayTime {
    public static void main(String[] args) {
        NetworkDelayTime obj = new NetworkDelayTime();

        System.out.println(obj.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(obj.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 4}}, 3, 1));
        System.out.println(obj.networkDelayTime(new int[][]{{1, 2, 1}}, 3, 1));
        System.out.println(obj.networkDelayTime(new int[][]{{1, 2, 3},{2, 1, 3}}, 2, 2));
        System.out.println(obj.networkDelayTime(new int[][]{{1, 2, 1},{2, 1, 3}}, 2, 2));
        System.out.println(obj.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 7}, {1, 3, 4}, {2, 1, 2}}, 3, 2));

    }

    public int networkDelayTime(int[][] times, int N, int K) {
        List<Edge>[] graph = new List[N + 1];
        final int MAX_VALUE = 100000;

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];
            graph[from].add(new Edge(from, to, cost));
        }
        int[] distances = new int[N + 1];
        Arrays.fill(distances, MAX_VALUE);
        distances[K] = 0;
        for (int v = 1; v <= N; v++) {
            for (List<Edge> edges : graph) {
                for (Edge edge : edges) {
                    int from = edge.from;
                    int to = edge.to;
                    int cost = edge.cost;

                    distances[to] = Math.min(distances[to], distances[from] + cost);
                }
            }
        }

        int maxDistance = 0;
        System.out.println(Arrays.toString(distances));

        for (int i = 1; i < distances.length; i++) {
            maxDistance = Math.max(distances[i], maxDistance);
        }

        return maxDistance == MAX_VALUE ? -1 : maxDistance;
    }

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

}
