package P201_300.P212_Word_Search_II;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        char[][] board1 = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        String[] words1 = {"oath", "pea", "eat", "rain"};

        System.out.println(solution.findWords(board1, words1)); // [oath, eat]


        Main solution2 = new Main();

        char[][] board2 = {
                {'a', 'b'},
                {'c', 'd'}
        };

        String[] words2 = {"abcb"};

        System.out.println(solution2.findWords(board2, words2)); // []
    }

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    private final int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    private final List<String> result = new ArrayList<>();
    private char[][] board;
    private int m;
    private int n;

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;

        TrieNode root = new TrieNode();

        for (String word : words) {
            insert(root, word);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, root);
            }
        }

        return result;
    }

    private void insert(TrieNode root, String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.word = word;
    }

    private void dfs(int row, int col, TrieNode node) {
        char c = board[row][col];

        if (c == '#') {
            return;
        }

        int index = c - 'a';
        TrieNode next = node.children[index];

        if (next == null) {
            return;
        }

        if (next.word != null) {
            result.add(next.word);
            next.word = null;
        }

        board[row][col] = '#';

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n) {

                dfs(newRow, newCol, next);
            }
        }

        board[row][col] = c;

        // Remove exhausted Trie branches.
        boolean hasChildren = false;

        for (TrieNode child : next.children) {
            if (child != null) {
                hasChildren = true;
                break;
            }
        }

        if (!hasChildren && next.word == null) {
            node.children[index] = null;
        }
    }

}


//Given an m x n board of characters and a list of strings words, return all words on the board.
//Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
// vertically neighboring. The same letter cell may not be used more than once in a word.

//Example 1:
//Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
// words = ["oath","pea","eat","rain"]
//Output: ["eat","oath"]

//Example 2:
//Input: board = [["a","b"],["c","d"]], words = ["abcb"]
//Output: []

//Constraints:
//m == board.length
//n == board[i].length
//1 <= m, n <= 12
//board[i][j] is a lowercase English letter.
//1 <= words.length <= 3 * 104
//1 <= words[i].length <= 10
//words[i] consists of lowercase English letters.
//All the strings of words are unique.
