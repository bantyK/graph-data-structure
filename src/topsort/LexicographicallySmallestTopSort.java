package topsort;

import java.util.*;

public class LexicographicallySmallestTopSort {
    public static void main(String[] args) {
        LexicographicallySmallestTopSort obj = new LexicographicallySmallestTopSort();
        System.out.println(
                obj.smallestTopSort(6, new int[][]{{5,0},{4,0},{4,1},{5,2},{2,3},{3,1}})
        );
    }

    public List<Integer> smallestTopSort(int verticesCount, int[][] edges) {
        int[] indegrees = new int[verticesCount];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < verticesCount; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            indegrees[to]++;
        }
        LinkedList<Integer> topSortOrder = new LinkedList<>();

        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                topSortOrder.addLast(curr);

                for(int neigh : graph.get(curr)) {
                    if(--indegrees[neigh] == 0) {
                        queue.offer(neigh);
                    }
                }
            }
        }

        return topSortOrder;
    }
}
