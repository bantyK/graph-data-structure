package topsort;

import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {
        AlienDictionary obj = new AlienDictionary();
//        System.out.println(obj.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        // y -> x
        System.out.println(obj.alienOrder(new String[]{"zy","zx"}));
    }

    public String alienOrder(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
                indegree.put(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                char c1 = words[i].charAt(index);
                char c2 = words[i + 1].charAt(index);

                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    indegree.put(c2, indegree.get(c2) + 1);
                    break;
                }
                index++;
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for(char c : indegree.keySet()) {
            if(indegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        while(!queue.isEmpty()) {
            char curr = queue.poll();
            builder.append(curr);

            for(char next : graph.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if(indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }

        }

        return builder.toString();

    }

    private Map<Character, List<Character>> buildGraphFromWords(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int index1 = 0, index2 = 0;

            while (index1 < word1.length() && index2 < word2.length() && word1.charAt(index1) == word2.charAt(index2)) {
                ++index1;
                ++index2;
            }

            graph.putIfAbsent(word1.charAt(index1), new ArrayList<>());
            graph.get(word1.charAt(index1)).add(word2.charAt(index2));
        }

        return graph;
    }
}
