package dfs;

import java.util.*;

// 417 https://leetcode.com/problems/pacific-atlantic-water-flow/
public class PacificAtlanticWaterFlowDFS {
    public static void main(String[] args) {
        PacificAtlanticWaterFlowDFS obj = new PacificAtlanticWaterFlowDFS();
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


        for (int r = 0; r < rows; r++) {
            if (!pacificWaterReach[r][0]) {
                markWaterReachRegions(matrix, pacificWaterReach, r, 0, -1);
            }

            if (!atlanticWaterReach[r][cols - 1]) {
                markWaterReachRegions(matrix, atlanticWaterReach, r, cols - 1, -1);
            }
        }

        for (int c = 0; c < cols; c++) {
            if (pacificWaterReach[0][c]) {
                markWaterReachRegions(matrix, pacificWaterReach, 0, c, -1);
            }
            if (!atlanticWaterReach[rows - 1][c]) {
                markWaterReachRegions(matrix, atlanticWaterReach, rows - 1, c, -1);
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if(atlanticWaterReach[r][c] && pacificWaterReach[r][c]) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }

        return res;
    }

    private void markWaterReachRegions(int[][] matrix, boolean[][] waterReachMatrix, int r, int c, int prevHeight) {
        if (r < 0 || r >= matrix.length) return;
        if (c < 0 || c >= matrix[r].length) return;
        if (waterReachMatrix[r][c]) return;
        if (matrix[r][c] < prevHeight) return;

        waterReachMatrix[r][c] = true;

        markWaterReachRegions(matrix, waterReachMatrix, r + 1, c, matrix[r][c]);
        markWaterReachRegions(matrix, waterReachMatrix, r - 1, c, matrix[r][c]);
        markWaterReachRegions(matrix, waterReachMatrix, r, c + 1, matrix[r][c]);
        markWaterReachRegions(matrix, waterReachMatrix, r, c - 1, matrix[r][c]);
    }
}
