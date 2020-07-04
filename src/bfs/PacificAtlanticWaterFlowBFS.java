package bfs;

import java.util.*;

public class PacificAtlanticWaterFlowBFS {
    public static void main(String[] args) {
        PacificAtlanticWaterFlowBFS obj = new PacificAtlanticWaterFlowBFS();

        final List<List<Integer>> lists = obj.pacificAtlantic(new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        });

        lists.forEach(System.out::println);
    }

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        int rows = matrix.length;
        if (rows == 0) return res;
        int cols = matrix[0].length;

        boolean[][] pacificWaterReach = new boolean[rows][cols];
        boolean[][] atlanticWaterReach = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < rows; r++) {
            pacificWaterReach[r][0] = true;
            queue.offer(new int[]{r, 0});
        }
        for(int c = 0; c < cols; c++) {
            pacificWaterReach[0][c] = true;
            queue.offer(new int[]{0, c});
        }

        bfs(matrix, pacificWaterReach, queue);

        queue.clear();

        for (int r = 0; r < rows; r++) {
            atlanticWaterReach[r][cols - 1] = true;
            queue.offer(new int[]{r, cols - 1});
        }
        for(int c = 0; c < cols; c++) {
            atlanticWaterReach[rows - 1][c] = true;
            queue.offer(new int[]{rows - 1, c});
        }
        bfs(matrix, atlanticWaterReach, queue);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if(atlanticWaterReach[r][c] && pacificWaterReach[r][c]) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }

        return res;
    }

    private void bfs(int[][] matrix, boolean[][] waterReachMatrix, Queue<int[]> queue) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int rows = matrix.length;
        int cols = matrix[0].length;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                final int x = curr[0];
                final int y = curr[1];
                waterReachMatrix[x][y] = true;

                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    if (newX < 0 || newX >= rows || newY < 0 || newY >= cols || waterReachMatrix[newX][newY] ||matrix[newX][newY] < matrix[x][y]) {
                        continue;
                    }
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
    }
}
