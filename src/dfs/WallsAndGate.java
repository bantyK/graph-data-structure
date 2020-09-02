package dfs;

import java.util.*;

//286 https://leetcode.com/problems/walls-and-gates/
public class WallsAndGate {
    public static void main(String[] args) {
        WallsAndGate obj = new WallsAndGate();
        int INF = Integer.MAX_VALUE;

        int[][] rooms = new int[][]{
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}};

        obj.wallsAndGates(rooms);

    }

    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        if (rows == 0) return;
        int cols = rooms[0].length;
        if (cols == 0) return;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (rooms[row][col] == 0) {
                    dfs(rooms, row, col, 0);
                }
            }
        }

        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }
    }

    private void dfs(int[][] rooms, int r, int c, int distance) {
        if (r < 0 || r >= rooms.length) return;
        if (c < 0 || c >= rooms[r].length) return;
        if (rooms[r][c] == -1) return;
        if (rooms[r][c] < distance) return;

        rooms[r][c] = distance;

        dfs(rooms, r + 1, c, distance + 1);
        dfs(rooms, r - 1, c, distance + 1);
        dfs(rooms, r, c + 1, distance + 1);
        dfs(rooms, r, c - 1, distance + 1);
    }
}
