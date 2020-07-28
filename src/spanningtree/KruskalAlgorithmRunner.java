package spanningtree;

import java.util.*;

public class KruskalAlgorithmRunner {
    public static void main(String[] args) {
        KruskalAlgorithmRunner obj = new KruskalAlgorithmRunner();
    }

    public void minimumSpanningTree(int n, int[][] edges) {
        int[] parents = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        // min heap to store the edges, returns the mininum weight edge
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge[2]));
        Collections.addAll(minHeap, edges);

        int i = 0;
        List<int[]> minimumSpanningTreeEdges = new ArrayList<>();
        while (i < n - 1) {
            int[] edge = minHeap.poll();
            if (union(parents, edge[0], edge[1])) {
                minimumSpanningTreeEdges.add(edge);
                i++;
            }
        }
    }

    public boolean union(int[] parents, int i, int j) {
        int parentI = find(parents, i);
        int parentJ = find(parents, j);
        if (parentI == parentJ) return false;
        parents[parentJ] = parents[parentI];
        return true;
    }

    public int find(int[] parents, int i) {
        if (parents[i] != i) {
            parents[i] = find(parents, parents[i]);
        }
        return parents[i];
    }

}
