package bfs;

import java.util.*;

public class CheapestFlightsWithKStops {
    public static void main(String[] args) {
        CheapestFlightsWithKStops obj = new CheapestFlightsWithKStops();

        System.out.println(obj.findCheapestPrice(3, new int[][]{{0, 1, 1}, {0, 2, 5}, {1, 2, 1}}, 0, 2, 0));
        int[][] edges = new int[][]{{3,4,4},{2,5,6},{4,7,10},{9,6,5},{7,4,4},{6,2,10},{6,8,6},{7,9,4},{1,5,4},{1,0,4},{9,7,3},{7,0,5},{6,5,8},{1,7,6},{4,0,9},{5,9,1},{8,7,3},{1,2,6},{4,1,5},{5,2,4},{1,9,1},{7,8,10},{0,4,2},{7,2,8}};
        System.out.println(obj.findCheapestPrice(10, edges, 6, 0, 7));

    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        int cheapestPrice = Integer.MAX_VALUE;
        for (int[] edge : flights) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new Edge(to, cost));
        }
        int step = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(src, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (curr.node == dst) {
                    cheapestPrice = Math.min(cheapestPrice, curr.weight);
                }

                if (graph.containsKey(curr.node)) {
                    for (Edge edge : graph.get(curr.node)) {
                        if(curr.weight + edge.cost < cheapestPrice) {
                            queue.offer(new Node(edge.to, curr.weight + edge.cost));
                        }
                    }
                }
            }
            if(step++ > K) break;
        }

        return cheapestPrice == Integer.MAX_VALUE ? -1 : cheapestPrice;
    }

    private static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    private static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}