package dfs;

import java.util.*;

public class WordSearch {
    public static void main(String[] args) {
        WordSearch obj = new WordSearch();
        char[][] board = new char[][]{{'a'}};
        System.out.println(obj.exist(board, "a"));
    }

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        if (rows == 0) return false;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (otherCharsExist(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean otherCharsExist(char[][] board, String word, int r, int c, int index) {
        if(index >= word.length()) return true;
        if (r < 0 || r >= board.length) return false;
        if (c < 0 || c >= board[r].length) return false;
        if (board[r][c] != word.charAt(index)) return false;

        char currentChar = word.charAt(index);
        board[r][c] = ' ';

        boolean found = otherCharsExist(board, word, r + 1, c, index + 1)
                        || otherCharsExist(board, word, r - 1, c, index + 1)
                        || otherCharsExist(board, word, r, c + 1, index + 1)
                        || otherCharsExist(board, word, r, c - 1, index + 1);

        board[r][c] = currentChar;
        return found;
    }
}
