package bfs;

import java.util.*;

//https://leetcode.com/problems/word-ladder/
public class WordLadder {
    public static void main(String[] args) {
        WordLadder obj = new WordLadder();
        System.out.println(obj.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "sog")));
        System.out.println(obj.ladderLength("abc", "def", Arrays.asList("def")));
        System.out.println(obj.ladderLength("hot", "dog", Arrays.asList("hot","dog")));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int transformations = 0;
        Set<String> dictionary = new HashSet<>(wordList);
        if(!dictionary.contains(endWord)) return 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            transformations++;

            for (int s = 0; s < size; s++) {
                String currentWord = queue.poll();
                if (currentWord.equals(endWord)) {
                    return transformations;
                }

                for (int i = 0; i < currentWord.length(); i++) {
                    char[] wordArr = currentWord.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        wordArr[i] = ch;
                        final String newWord = new String(wordArr);
                        if (dictionary.contains(newWord) && !newWord.equals(currentWord)) {
                            queue.offer(newWord);
                            dictionary.remove(newWord);
                        }
                    }
                }
            }
        }

        return 0;
    }
}
