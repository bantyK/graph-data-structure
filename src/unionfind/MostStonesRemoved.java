package unionfind;

import java.util.*;

//#947 https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
public class MostStonesRemoved {
    public static void main(String[] args) {
        MostStonesRemoved obj = new MostStonesRemoved();
        System.out.println(obj.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));
    }

    // Using DFS
    public int removeStonesDFS(int[][] stones) {
        int numIslands = 0;
        Set<String> visited = new HashSet<>();

        for(int[] stone: stones) {
            if(!visited.contains(stone[0] + " " + stone[1])) {
                dfs(stones, stone, visited);
                numIslands++;
            }
        }

        return stones.length - numIslands;
    }

    private void dfs(int[][] stones, int[] current, Set<String> visited) {
        visited.add(current[0] + " " + current[1]);
        for(int[] stone: stones) {
            if(!visited.contains(stone[0] + " " + stone[1]) && (current[0] == stone[0] || current[1] == stone[1])) {
                dfs(stones, stone, visited);
            }
        }
    }

    private String getKey(int[] stone) {
        return stone[0] + " " + stone[1];
    }


    // Using union-find
    public int removeStones(int[][] stones) {
        Map<String, String> parents = new HashMap<>();

        for (int i = 0; i < stones.length; i++) {
            final String s = getKey(stones[i]);
            parents.put(s, s);
        }

        int unions = 0;

        for (int i = 0; i < stones.length; i++) {
            String s1 = getKey(stones[i]);
            for (int j = 0; j < stones.length; j++) {
                String s2 = getKey(stones[j]);
                if (union(parents, s1, s2)) {
                    ++unions;
                }
            }
        }
        return unions;
    }

    private String find(Map<String, String> parents, String s) {
        if (parents.get(s).equals(s)) return s;
        parents.put(s, find(parents, parents.get(s)));
        return parents.get(s);
    }

    private boolean union(Map<String, String> parents, String s1, String s2) {
        String p1 = find(parents, s1);
        String p2 = find(parents, s2);

        if (p1.equals(p2)) return false;
        parents.put(p2, p1);
        return true;
    }
}
