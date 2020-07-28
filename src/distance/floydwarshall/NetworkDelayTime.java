package distance.floydwarshall;

import java.util.*;

// 743 https://leetcode.com/problems/network-delay-time/
// Solved using Floyd Warshall Algorithm
public class NetworkDelayTime {
    public static void main(String[] args) {
        NetworkDelayTime obj = new NetworkDelayTime();
        System.out.println(obj.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(obj.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 4}}, 3, 1));
        System.out.println(obj.networkDelayTime(new int[][]{{1, 2, 1}}, 3, 1));

    }

    public int networkDelayTime(int[][] times, int N, int K) {
        final int MAX_VALUE = 100000;
        int[][] graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], MAX_VALUE);
            graph[i][i] = 0;
        }


        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }


        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

//        printArray(graph);

        int maxDistance = 0;
        int[] ints = graph[K];
        for (int i = 1; i < ints.length; i++) { ;
            maxDistance = Math.max(maxDistance, graph[K][i]);
        }

        return maxDistance == MAX_VALUE ? -1 : maxDistance;
    }


    private void printArray(int[][] dp) {
        int n = dp.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }

            System.out.println();
        }
        System.out.println();
    }
}
