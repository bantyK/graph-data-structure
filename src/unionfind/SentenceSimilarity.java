package unionfind;

import java.util.*;

//737 https://leetcode.com/problems/sentence-similarity-ii/
public class SentenceSimilarity {
    public static void main(String[] args) {
        SentenceSimilarity obj = new SentenceSimilarity();
        String[] words1 = new String[]{"a", "b", "c"};
        String[] words2 = new String[]{"d", "e", "f"};

        List<List<String>> pairs = Arrays.asList(
                Arrays.asList("a", "z"),
                Arrays.asList("z", "a"),
                Arrays.asList("b", "x"),
                Arrays.asList("x", "e"),
                Arrays.asList("c", "t"),
                Arrays.asList("f", "t"));

        System.out.println(obj.areSentencesSimilarTwo(words1, words2, pairs));
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;

        if (pairs.size() == 0) {
            for (int i = 0; i < words1.length; i++) {
                if (!words1[i].equals(words2[i])) return false;
            }
            return true;
        }

        DisjointSet ds = new DisjointSet(pairs);

        for (List<String> pair : pairs) {
            String a = pair.get(0);
            String b = pair.get(1);
            ds.union(a, b);
        }

        for (int i = 0; i < words1.length; i++) {
            String word1 = words1[i];
            String word2 = words2[i];

            if (!ds.find(word1).equals(ds.find(word2))) {
                return false;
            }
        }

        return true;

    }

    private static class DisjointSet {
        Map<String, String> parents;
        Map<String, Integer> rank;

        public DisjointSet(List<List<String>> pairs) {
            parents = new HashMap<>();
            rank = new HashMap<>();

            for (List<String> pair : pairs) {
                String a = pair.get(0);
                String b = pair.get(1);
                parents.put(a, a);
                parents.put(b, b);

                rank.put(a, 1);
                rank.put(b, 1);
            }
        }

        public String find(String s) {
            if (!parents.containsKey(s)) return s;
            if (parents.get(s).equals(s)) {
                return s;
            }
            parents.put(s, find(parents.get(s)));
            return parents.get(s);
        }

        public void union(String a, String b) {
            String parentA = find(a);
            String parentB = find(b);

            if (parentA.equals(parentB)) return;

            if (rank.get(parentA) >= rank.get(parentB)) {
                // parentA will be parent of parentB
                parents.put(parentB, parentA);
                rank.put(parentA, rank.get(parentA) + 1);
            } else {
                parents.put(parentA, parentB);
                rank.put(parentB, rank.get(parentB) + 1);
            }
        }
    }
}