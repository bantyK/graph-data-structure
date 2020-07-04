package bfs;

import java.util.*;

public class BinaryMatrixShortestPath {
    public static void main(String[] args) {
        BinaryMatrixShortestPath obj = new BinaryMatrixShortestPath();
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1) return -1;
        int distance = 0;

        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        boolean[][] visited = new boolean[rows][cols];

        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int sz = queue.size();

            for (int i = 0; i < sz; i++) {
                int[] curr = queue.poll();

                if(curr[0] == rows - 1 && curr[1] == cols - 1) return distance;

                for (int[] dir : directions) {
                    int newX = curr[0] + dir[0];
                    int newY = curr[1] + dir[1];

                    if (newX < 0 || newX >= rows || newY < 0 || newY >= cols || grid[newX][newY] == 1 || visited[newX][newY]) {
                        continue;
                    }
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
            distance++;
        }

        return -1;
    }
}
