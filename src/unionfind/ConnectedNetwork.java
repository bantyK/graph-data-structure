package unionfind;

import java.util.*;

// https://leetcode.com/problems/number-of-operations-to-make-network-connected/
public class ConnectedNetwork {
    public static void main(String[] args) {
        ConnectedNetwork obj = new ConnectedNetwork();

        System.out.println(obj.makeConnected2(4, new int[][]{{0, 1}, {0, 2}, {1, 2}})); //1
        System.out.println(obj.makeConnected2(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}}));//2
        System.out.println(obj.makeConnected2(5, new int[][]{{0, 1}, {0, 2}, {3, 4}, {2, 3}}));//0
        System.out.println(obj.makeConnected2(12, new int[][]{{1, 5}, {1, 7}, {1, 2}, {1, 4}, {3, 7}, {4, 7}, {3, 5}, {0, 6}, {0, 1},
                {0, 4}, {2, 6}, {0, 3}, {0, 2}}));//4

        System.out.println(obj.makeConnected2(11, new int[][]{{1, 4}, {0, 3}, {1, 3}, {3, 7}, {2, 7}, {0, 1}, {2, 4}, {3, 6}, {5, 6}, {6, 7}, {4, 7}, {0, 7}, {5, 7}}));//3
    }

    public int makeConnected2(int n, int[][] connections) {
        if (n - 1 > connections.length) return -1; // not enough connections available
        boolean[] visited = new boolean[n];

        Map<Integer, List<Integer>> graph = createGraph(n, connections);
        int validConnections = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                validConnections += dfs(graph, visited, i, 0);
            }
        }

        return n - validConnections - 1;
    }

    private int dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int current, int connections) {
        visited[current] = true;
        if (graph.containsKey(current)) {
            for (int vertex : graph.get(current)) {
                if (!visited[vertex]) {
                    connections = dfs(graph, visited, vertex, connections + 1);
                }
            }
        }
        return connections;
    }

    // returns an undirected graph
    private Map<Integer, List<Integer>> createGraph(int n, int[][] connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] connection : connections) {
            graph.putIfAbsent(connection[0], new ArrayList<>());
            graph.putIfAbsent(connection[1], new ArrayList<>());

            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);
        }
        return graph;
    }


    /////////////////////////////// Union Find ///////////////////////////////

    public int makeConnected(int n, int[][] connections) {
        int countConnections = connections.length;
        if (countConnections < n - 1) return -1;
        DisjointSet set = new DisjointSet(n);
        int required = 0;

        for (int[] connection : connections) {
            int x = connection[0];
            int y = connection[1];

            if (set.union(x, y)) {
                required++;
            }
        }
        System.out.println("Required : " + required);

        return n - required - 1;
    }

    static class DisjointSet {
        int[] parents;
        int[] rank;
        int n;

        public DisjointSet(int n) {
            this.n = n;
            parents = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        private int find(int i) {
            if (parents[i] == i) {
                return i;
            } else {
                parents[i] = find(parents[i]);
                return parents[i];
            }
        }

        public boolean union(int i, int j) {
            int rootX = find(i);
            int rootY = find(j);

            if (rootX == rootY) return false;

            int rankX = rank[rootX];
            int rankY = rank[rootY];

            if (rankX > rankY) {
                parents[rootY] = rootX;
            } else if (rankY > rankX) {
                parents[rootX] = rootY;
            } else {
                parents[rootY] = parents[rootX];
                rank[rootX] += 1;
            }
            return true;
        }
    }
}
