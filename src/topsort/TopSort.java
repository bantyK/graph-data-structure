package topsort;

//https://www.lintcode.com/problem/topological-sorting
import java.util.*;

public class TopSort {
    public static void main(String[] args) {
        TopSort obj = new TopSort();
    }

    public ArrayList<DirectedGraphNode> topSortDFS(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Boolean> visited = new HashMap<>();
        ArrayList<DirectedGraphNode> topOrder = new ArrayList<>();
        for (DirectedGraphNode node : graph) {
            visited.put(node, false);
        }
        for (DirectedGraphNode startNode : graph) {
            dfs(startNode, visited, topOrder);
        }
        return topOrder;
    }

    private void dfs(DirectedGraphNode current, Map<DirectedGraphNode, Boolean> visited, ArrayList<DirectedGraphNode> topOrder) {
        if (visited.get(current)) {
            return;
        }

        visited.put(current, true);
        for (DirectedGraphNode node : current.neighbors) {
            dfs(node, visited, topOrder);
        }
        topOrder.add(0, current);
    }

    /**
     * Topological sort using BFS. Kahn's algorithm
     *
     * @param graph
     * @return
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        Map<DirectedGraphNode, Integer> indegrees = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            indegrees.putIfAbsent(node, 0);
            for (DirectedGraphNode neigh : node.neighbors) {
                indegrees.put(neigh, indegrees.getOrDefault(neigh, 0) + 1);
            }
        }

        for (DirectedGraphNode node : indegrees.keySet()) {
            if (indegrees.get(node) == 0) {
                queue.offer(node);
            }
        }

        ArrayList<DirectedGraphNode> topSort = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                DirectedGraphNode curr = queue.poll();
                topSort.add(curr);

                for (DirectedGraphNode node : curr.neighbors) {
                    indegrees.put(node, indegrees.get(node) - 1);
                    if (indegrees.get(node) == 0) {
                        queue.offer(node);
                    }
                }
            }
        }
        return topSort;
    }

    static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
}
