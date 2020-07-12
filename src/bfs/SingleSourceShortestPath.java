package bfs;

import java.util.*;

// Returns the shortest path from some start node to all other nodes in the graph reachable from that start node
// Assume distance from one node to another as 1

public class SingleSourceShortestPath {

    private static final int EDGE_DISTANCE = 1;

    public static void main(String[] args) {
        SingleSourceShortestPath obj = new SingleSourceShortestPath();

        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}, {0, 2}};
        int[] distances = obj.shortestPath(4, edges, 0);
        System.out.println(Arrays.toString(distances));
    }

    public int[] shortestPath(int n, int[][] edges, int startNode) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int vertex1 = edge[0];
            int vertex2 = edge[1];

            graph.putIfAbsent(vertex1, new ArrayList<>());
            graph.putIfAbsent(vertex2, new ArrayList<>());

            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
        }

        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(startNode);

        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        distances[startNode] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph.get(current)) {
                if (distances[neighbor] == -1) {
                    distances[neighbor] = distances[current] + EDGE_DISTANCE;
                    queue.offer(neighbor);
                }
            }
        }

        return distances;
    }
}
