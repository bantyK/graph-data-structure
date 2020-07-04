package bfs;

import java.util.*;

public class ZeroOneMatrix {
    public static void main(String[] args) {
        ZeroOneMatrix obj = new ZeroOneMatrix();
        int[][] matrix = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 0}};
        int[][] res = obj.updateMatrix(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }

    }

    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return new int[][]{};
        int cols = matrix[0].length;

        int[][] result = new int[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int[] dir : directions) {
                int newX = current[0] + dir[0];
                int newY = current[1] + dir[1];

                if(newX < 0 || newX >= rows || newY < 0 || newY >= cols || matrix[newX][newY] <= 1 + matrix[current[0]][current[1]]) {
                    continue;
                }

                queue.offer(new int[]{newX, newY});
                matrix[newX][newY] = 1 + matrix[current[0]][current[1]];
            }
        }

        return matrix;
    }

    private int bfs(int[][] matrix, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int distance = 0;
        while (queue.size() > 0) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] current = queue.poll();
                int r = current[0];
                int c = current[1];
                if (matrix[r][c] == 0) return distance;
                int newR, newC;
                for (int j = 0; j < 4; j++) {
                    newR = r + directions[j][0];
                    newC = c + directions[j][1];

                    if (newR >= 0 && newR < matrix.length && newC >= 0 && newC < matrix[0].length) {
                        queue.add(new int[]{newR, newC});
                    }
                }
            }

            distance++;
        }

        return distance;
    }
}
