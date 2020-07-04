package bfs;

import java.util.*;
//994 https://leetcode.com/problems/rotting-oranges/
public class RottingOranges {
    public static void main(String[] args) {
        RottingOranges obj = new RottingOranges();
        System.out.println(obj.orangesRotting(new int[][]{{2, 1, 1}, {1, 2, 1}, {1, 1, 1}}));
        System.out.println(obj.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
    }

    public int orangesRotting(int[][] grid) {
        int time = -1;

        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;


        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 1) {
                    freshOranges++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        if(freshOranges == 0) return 0;

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();

            time += 1;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                for (int[] dir : directions) {
                    int newX = curr[0] + dir[0];
                    int newY = curr[1] + dir[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 1) {
                        queue.offer(new int[]{newX, newY});
                        grid[newX][newY] = 2;
                        freshOranges--;
                    }
                }

            }
        }

        return freshOranges == 0 ? time : -1;
    }
}
