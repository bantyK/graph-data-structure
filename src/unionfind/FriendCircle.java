package unionfind;

import java.util.*;

public class FriendCircle {

    public static void main(String[] args) {
        FriendCircle obj = new FriendCircle();

        int[][] matrix = new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };

        System.out.println(obj.findCircleNum(matrix));
    }


    // DFS
    public int findCircleNumDFS(int[][] mat) {
        int rows = mat.length;
        boolean[] visited = new boolean[rows];
        int group = 0;
        for(int i = 0; i < rows; ++i) {
            if(!visited[i]) {
                dfs(mat, i, visited);
                group++;
            }
        }

        return group;
    }

    private void dfs(int[][] mat, int row, boolean[] visited) {
        for(int col = 0; col < mat[row].length; ++col) {
            if(mat[row][col] == 1 && !visited[col]) {
                visited[col] = true;
                dfs(mat, col, visited);
            }
        }
    }

    // Union Find
    public int findCircleNum(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        UnionFind uf = new UnionFind(rows);

        int[] parents = new int[rows];
        int[] ranks = new int[rows];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    uf.union(parents, ranks, i, j);
                }
            }
        }

        return uf.sets;
    }

    class UnionFind {
        int sets;

        public UnionFind(int sets) {
            this.sets = sets;
        }

        private int find(int[] parents, int i) {
            if (parents[i] == i) {
                return i;
            } else {
                parents[i] = find(parents, parents[i]);
                return parents[i];
            }
        }

        private void union(int[] parents, int[] ranks, int i, int j) {
            int parentI = find(parents, i);
            int parentJ = find(parents, j);

            if (parentI != parentJ) {
                int rankI = ranks[parentI];
                int rankJ = ranks[parentJ];

                if (rankI == rankJ) {
                    parents[parentJ] = parents[parentI];
                    ranks[parentI] += 1;
                } else if (rankI > rankJ) {
                    parents[parentJ] = parents[parentI];
                } else {
                    parents[parentI] = parents[parentJ];
                }
                sets--;
            }
        }
    }
}
