package distance.floydwarshall;

import java.util.*;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

public class FloydWarshall {

    private static final int REACHES_NEGATIVE_CYCLE = -1;
    private int n;
    private boolean solved;
    private double[][] dp;
    private Integer[][] next;

    public FloydWarshall(double[][] mat) {
        n = mat.length;
        dp = new double[n][n];
        next = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != POSITIVE_INFINITY) {
                    next[i][j] = j;
                }
                dp[i][j] = mat[i][j];
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        double[][] graph = createGraph(n);

        graph[1][0] = 7;
        graph[1][3] = 1;
        graph[3][4] = 3;
        graph[4][2] = 1;
        graph[0][2] = 8;

        FloydWarshall obj = new FloydWarshall(graph);
        double[][] distances = obj.getAllPairShortestPathMatrix();

//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                System.out.printf("This shortest path from node %d to node %d is %.3f\n", i, j, distances[i][j]);
    }

    public static double[][] createGraph(int n) {
        double[][] graph = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], POSITIVE_INFINITY);
            graph[i][i] = 0;
        }
        return graph;
    }

    public double[][] getAllPairShortestPathMatrix() {
        solve();
        return dp;
    }

    public void solve() {
        if (solved) return;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }

            System.out.println("k = " + k);
            printArray(dp);
        }

        // negative cycle
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = NEGATIVE_INFINITY;
                        next[i][j] = REACHES_NEGATIVE_CYCLE;
                    }
                }
            }
        }

        solved = true;
    }

    private void printArray(double[][] dp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }

            System.out.println();
        }
        System.out.println();
    }

    public List<Integer> reconstructPath(int start, int end) {
        solve();
        List<Integer> path = new LinkedList<>();

        if (dp[start][end] == POSITIVE_INFINITY) return path;
        int at = start;
        for (; at != end; at = next[at][end]) {
            if (at == REACHES_NEGATIVE_CYCLE) return null;
            path.add(at);
        }

        if (next[at][end] == REACHES_NEGATIVE_CYCLE) return null;
        path.add(at);
        return path;
    }

}
