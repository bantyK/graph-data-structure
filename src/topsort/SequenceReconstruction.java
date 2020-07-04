package topsort;

import java.util.*;

//444 https://leetcode.com/problems/sequence-reconstruction/
public class SequenceReconstruction {
    public static void main(String[] args) {
        SequenceReconstruction obj = new SequenceReconstruction();
        int[] org = new int[]{1, 2, 3, 4};
        int[][] seqs = new int[][]{{1, 2}, {2, 3}, {2, 4}};

        org = new int[]{1};
        seqs = new int[][]{{1}};
        System.out.println(obj.sequenceReconstruction(org, seqs));
    }

    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if(seqs.length == 0) return org.length == 0;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegrees = new HashMap<>();

        buildGraph(seqs, graph, indegrees);
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for(int i : graph.keySet()) {
            if(indegrees.get(i) == 0) {
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            if(size > 1) return false;

            int curr = queue.poll();
            order.add(curr);

            for(int neigh : graph.get(curr)) {
                indegrees.put(neigh, indegrees.get(neigh) - 1);
                if(indegrees.get(neigh) == 0) {
                    queue.offer(neigh);
                }
            }
        }

        if(order.size() != org.length) return false;
        for(int i = 0; i < order.size(); i++) {
            if(order.get(i) != org[i]) return false;
        }

        return true;
    }

    private void buildGraph(int[][] seqs, Map<Integer, List<Integer>> graph, Map<Integer, Integer> indegrees) {
        for(int[] seq : seqs) {
            for(int i : seq) {
                graph.putIfAbsent(i, new ArrayList<>());
                indegrees.putIfAbsent(i, 0);
            }
        }

        for(int[] seq : seqs) {
            for(int i = 0; i < seq.length - 1; i++) {
                int from = seq[i];
                int to = seq[i + 1];

                graph.get(from).add(to);
                indegrees.put(to, indegrees.get(to) + 1);
            }
        }
    }

}
