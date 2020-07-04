package unionfind;

import java.util.*;

public class RedundantConnections {
    public static void main(String[] args) {
        RedundantConnections obj = new RedundantConnections();

        System.out.println(Arrays.toString(obj.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {1, 4}, {3, 4}, {1, 5}})));
        System.out.println(Arrays.toString(obj.findRedundantConnection(new int[][]{{3, 4}, {1, 2}, {2, 4}, {3, 5}, {2, 5}})));
    }

    public int[] findRedundantConnection(int[][] edges) {
        final int n = edges.length;
        DisjointSet disjointSet = new DisjointSet(n);

        for (int[] edge : edges) {
            int src = edge[0] - 1;
            int dest = edge[1] - 1;
            boolean union = disjointSet.union(src, dest);
            if (!union)
                return new int[]{src + 1, dest + 1};
        }
        return new int[]{};
    }

    static class DisjointSet {
        int n;
        int[] parents;
        int[] rank;

        public DisjointSet(int n) {
            this.n = n;
            parents = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int i) {
            if (parents[i] == i) {
                return i;
            } else {
                parents[i] = find(parents[i]);
                return parents[i];
            }
        }

        public boolean union(int i, int j) {
            int p1 = find(i);
            int p2 = find(j);

            if (p1 == p2) return false;
            if (rank[p1] < rank[p2]) {
                parents[p1] = p2;
            } else if (rank[p1] > rank[p2]) {
                parents[p2] = p1;
            } else {
                parents[p1] = p2;
                rank[p2]++;
            }

            return true;
        }
    }
}
