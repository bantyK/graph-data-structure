package dfs;

import java.util.*;

public class ReconstructItinerary {
    public static void main(String[] args) {
        ReconstructItinerary obj = new ReconstructItinerary();
        final List<List<String>> lists = Arrays.asList(Arrays.asList("JFK", "KUL"), Arrays.asList("JFK", "NRT"), Arrays.asList("NRT", "JFK"));
        System.out.println(obj.findItinerary(lists));
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        String from, to;
        for (List<String> ticket : tickets) {
            from = ticket.get(0);
            to = ticket.get(1);
            graph.putIfAbsent(from, new PriorityQueue<>(String::compareTo));
            graph.get(from).add(to);
        }
        LinkedList<String> res = new LinkedList<>();

        dfs(graph, "JFK", res);

        return res;
    }

    private void dfs(Map<String, PriorityQueue<String>> graph, String current, LinkedList<String> res) {
        final PriorityQueue<String> pq = graph.get(current);
        while(pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(graph, next, res);
        }

        // if the node has no edges, that node should be added in the result list at the last position.
        // All the other nodes will be added before it(addFirst)
        res.addFirst(current);
    }


}
