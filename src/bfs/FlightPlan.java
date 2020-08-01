package bfs;

import java.util.*;

//https://www.hackerearth.com/practice/algorithms/graphs/breadth-first-search/practice-problems/algorithm/traffic-light-2-ee27ba45/discussion/description/
public class FlightPlan {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] line = s.nextLine().split(" ");
        int numVertices = Integer.parseInt(line[0]);
        int numEdges = Integer.parseInt(line[1]);
        int[][] edges = new int[numEdges][2];
        for (int i = 0; i < numEdges; i++) {
            line = s.nextLine().split(" ");
            edges[i][0] = Integer.parseInt(line[0]);
            edges[i][1] = Integer.parseInt(line[1]);
        }
        line = s.nextLine().split(" ");
        int src = Integer.parseInt(line[0]);
        int dest = Integer.parseInt(line[1]);
        flightPlan(numVertices, edges, src, dest);
    }

    public static void flightPlan(int n, int[][] edges, int src, int dest) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());

            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for(int i : graph.keySet()) {
            Collections.sort(graph.get(i));
        }

        int[] parents = new int[n + 1];
        parents[src] = 0;

        boolean[] visited = new boolean[n + 1];

        Queue<Integer> pq = new ArrayDeque<>();

        pq.offer(src);
        visited[src] = true;
        boolean done = false;

        while (!pq.isEmpty()) {
            int size = pq.size();

            if (done) {
                break;
            }

            for (int i = 0; i < size; i++) {
                int current = pq.poll();
                if (current == dest) {
                    done = true;
                    break;
                }

                for (int neigh : graph.getOrDefault(current, new ArrayList<>())) {
                    if (!visited[neigh]) {
                        visited[neigh] = true;
                        parents[neigh] = current;
                        pq.offer(neigh);
                    }
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        while (parents[dest] != 0) {
            stack.push(dest);
            dest = parents[dest];
        }
        stack.push(src);

        System.out.println(stack.size());
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
