package topsort;

import java.util.*;

public class AllTopSort {
    public static void main(String[] args) {
        AllTopSort obj = new AllTopSort();

        List<List<Integer>> lists = obj.allTopSorts(4, new int[][]{{0, 1}, {0, 2},{1, 3},{2, 3}});
        lists.forEach(System.out::println);
    }

    public List<List<Integer>> allTopSorts(int numVertices, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegrees = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        List<List<Integer>> allTopSort = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        buildGraph(graph, edges, indegrees);

        allTopSortOrder(numVertices, graph, allTopSort, current, visited, indegrees);

        return allTopSort;

    }

    private void allTopSortOrder(int numVertices, Map<Integer, List<Integer>> graph, List<List<Integer>> allTopSort, List<Integer> current, boolean[] visited, int[] indegree) {
        for (int i = 0; i < numVertices; i++) {
            if (indegree[i] == 0 && !visited[i]) {
                current.add(i);
                visited[i] = true;

                for (int neigh : graph.get(i)) {
                    indegree[neigh]--;
                }

                allTopSortOrder(numVertices, graph, allTopSort, current, visited, indegree);

                for (int neigh : graph.get(i)) {
                    indegree[neigh]++;
                }

                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }

        if (current.size() == indegree.length) {
            allTopSort.add(new ArrayList<>(current));
        }
    }

    private void buildGraph(Map<Integer, List<Integer>> graph, int[][] edges, int[] indegrees) {
        for (int i = 0; i < indegrees.length; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            graph.get(from).add(to);
            indegrees[to]++;
        }
    }
}
