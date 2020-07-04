package cycle;

import java.util.*;

public class DetectCycleInDirectedGraph {
    public static void main(String[] args) {
        DetectCycleInDirectedGraph obj = new DetectCycleInDirectedGraph();

        System.out.println(obj.hasCycle(3, new int[][]{{1}, {2}, {0}}));
        System.out.println(obj.hasCycle(3, new int[][]{{1,2}, {2}, {}}));
    }

    public boolean hasCycle(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = constructGraphFromEdges(n, edges);
        boolean[] visited = new boolean[n];
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (dfs(graph, i, visited, seen)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, int current, boolean[] visited, Set<Integer> seenEdges) {
//        if (seenEdges.contains(current)) return true;
        visited[current] = true;
//        seenEdges.add(current);

        for (int edge : graph.get(current)) {
            if (!visited[edge]) {
                dfs(graph, edge, visited, seenEdges);
            } else {
                return true;
            }
        }
        visited[current] = false;
//        seenEdges.remove(current);
        return false;
    }


    private Map<Integer, List<Integer>> constructGraphFromEdges(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.putIfAbsent(i, new ArrayList<>());
            for (int edge : edges[i])
                graph.get(i).add(edge);
        }

        return graph;
    }
}
