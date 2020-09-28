package dfs;

import java.util.*;

public class EvaluateDivision {
    public static void main(String[] args) {
        EvaluateDivision obj = new EvaluateDivision();
        System.out.println(Arrays.toString(
                obj.calcEquation(
                        Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "d"), Arrays.asList("a", "c"), Arrays.asList("c", "e")),
                        new double[]{2, 1, 3, 4},
                        Arrays.asList(Arrays.asList("x", "x"), Arrays.asList("a", "d"), Arrays.asList("a", "e"), Arrays.asList("b", "a")))
        ));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] results = new double[queries.size()];
        Map<String, List<Node>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String from = equation.get(0);
            String to = equation.get(1);
            double value = values[i];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new Node(to, value));

            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(to).add(new Node(from, 1 / value)); // assuming valid inputs and no divide by zero exception
        }

        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            results[i] = calculateValue(graph, query);
        }

        return results;
    }

    private double calculateValue(Map<String, List<Node>> graph, List<String> query) {
        String src = query.get(0);
        String dest = query.get(1);
        if (!graph.containsKey(src) || !graph.containsKey(dest)) return -1;
        Set<String> visited = new HashSet<>();
        return dfs(graph, src, dest, visited, 1);
    }

    private double dfs(Map<String, List<Node>> graph, String src, String dest, Set<String> visited, double valueSoFar) {
        if (src.equals(dest)) return valueSoFar;
        visited.add(src);
        for (Node neigh : graph.getOrDefault(src, Collections.emptyList())) {
            if (!visited.contains(neigh.to)) {
                double res = dfs(graph, neigh.to, dest, visited, valueSoFar * neigh.value);
                if (res != -1) return res;

            }
        }

        return -1.0;
    }

    private static class Node {
        String to;
        double value;

        public Node(String to, double value) {
            this.to = to;
            this.value = value;
        }
    }
}
