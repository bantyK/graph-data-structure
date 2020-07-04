package topsort;
// Leetcode#441
public class SortedAlienDictionary {
    public static void main(String[] args) {
        SortedAlienDictionary obj = new SortedAlienDictionary();
    }

    public boolean isAlienSorted(String[] words, String order) {
        int index1 = 0, index2 = 0;

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            index1 = 0;
            index2 = 0;

            while (index1 < word1.length() && index2 < word2.length() && word1.charAt(index1) == word2.charAt(index2)) {
                index1++;
                index2++;
            }

            if (index1 == word1.length()) {
                // word1 is shorter, hence a valid sequence. Move to next pair
                i++;
            } else if (index2 == word2.length()) {
                // word2 is shorter, hence an invalid sequence. Return false no need to check ahead
                return false;
            } else {
                int positionOfChar1InOrder = order.indexOf(word1.charAt(index1));
                int positionOfChar2InOrder = order.indexOf(word2.charAt(index2));

                if (positionOfChar1InOrder > positionOfChar2InOrder) {
                    // char1 appears later than char2, hence this is not a valid sequence
                    return false;
                } else {
                    i++;
                }
            }
        }

        return true;
    }
}
