package dfs;

import java.util.*;

public class KeysAndRooms {
    public static void main(String[] args) {
        KeysAndRooms obj = new KeysAndRooms();
        System.out.println(obj.canVisitAllRooms(Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(0))));
        System.out.println(obj.canVisitAllRooms(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 0, 1), Arrays.asList(2), Arrays.asList(0))));
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        final int size = rooms.size();
        boolean[] canEnterRoom = new boolean[size];

        dfs(rooms, 0, canEnterRoom);

        System.out.println(Arrays.toString(canEnterRoom));
        for (int i = 0; i < size; i++) {
            if (!canEnterRoom[i]) return false;
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int current, boolean[] visited) {
        visited[current] = true;
        for (int room : rooms.get(current)) {
            if (!visited[room]) {
                dfs(rooms, room, visited);
            }
        }
    }
}
