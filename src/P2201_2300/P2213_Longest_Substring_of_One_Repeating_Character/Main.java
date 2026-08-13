package P2201_2300.P2213_Longest_Substring_of_One_Repeating_Character;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s = "babacc";
        String queryCharacters = "bcb";
        int[] queryIndices = {1, 3, 3};

        int[] result = solution.longestRepeating(s, queryCharacters, queryIndices);

        System.out.println(Arrays.toString(result));

        s = "abyzz";
        queryCharacters = "aa";
        queryIndices = new int[]{2, 1};

        result = solution.longestRepeating(s, queryCharacters, queryIndices);

        System.out.println(Arrays.toString(result));
    }


    class Node {
        int length;
        int prefix;
        int suffix;
        int best;
        char leftChar;
        char rightChar;

        Node() {
        }

        Node(char c) {
            length = 1;
            prefix = 1;
            suffix = 1;
            best = 1;
            leftChar = c;
            rightChar = c;
        }
    }

    class SegmentTree {
        private final Node[] tree;
        private final int n;

        SegmentTree(String s) {
            n = s.length();
            tree = new Node[4 * n];
            build(s, 1, 0, n - 1);
        }

        private void build(String s, int node, int left, int right) {
            if (left == right) {
                tree[node] = new Node(s.charAt(left));
                return;
            }

            int mid = left + (right - left) / 2;

            build(s, node * 2, left, mid);
            build(s, node * 2 + 1, mid + 1, right);

            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private Node merge(Node a, Node b) {
            if (a == null) return b;
            if (b == null) return a;

            Node result = new Node();

            result.length = a.length + b.length;
            result.leftChar = a.leftChar;
            result.rightChar = b.rightChar;

            // prefix
            result.prefix = a.prefix;

            if (a.prefix == a.length && a.rightChar == b.leftChar) {
                result.prefix = a.length + b.prefix;
            }

            // suffix
            result.suffix = b.suffix;

            if (b.suffix == b.length && a.rightChar == b.leftChar) {
                result.suffix = b.length + a.suffix;
            }

            // best inside either segment
            result.best = Math.max(a.best, b.best);

            // substring crossing the boundary
            if (a.rightChar == b.leftChar) {
                result.best = Math.max(
                        result.best,
                        a.suffix + b.prefix
                );
            }

            return result;
        }

        public void update(int index, char c) {
            update(1, 0, n - 1, index, c);
        }

        private void update(
                int node,
                int left,
                int right,
                int index,
                char c
        ) {
            if (left == right) {
                tree[node] = new Node(c);
                return;
            }

            int mid = left + (right - left) / 2;

            if (index <= mid) {
                update(node * 2, left, mid, index, c);
            } else {
                update(node * 2 + 1, mid + 1, right, index, c);
            }

            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        public int getBest() {
            return tree[1].best;
        }
    }

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices
    ) {
        int k = queryIndices.length;
        int[] result = new int[k];

        SegmentTree segmentTree = new SegmentTree(s);

        for (int i = 0; i < k; i++) {
            int index = queryIndices[i];
            char character = queryCharacters.charAt(i);

            segmentTree.update(index, character);

            result[i] = segmentTree.getBest();
        }

        return result;
    }

}


//You are given a 0-indexed string s. You are also given a 0-indexed string queryCharacters of length k and a
// 0-indexed array of integer indices queryIndices of length k, both of which are used to describe k queries.
//The ith query updates the character in s at index queryIndices[i] to the character queryCharacters[i].
//Return an array lengths of length k where lengths[i] is the length of the longest substring of s consisting of
// only one repeating character after the ith query is performed.

//Example 1:
//Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
//Output: [3,3,4]
//Explanation:
//- 1st query updates s = "bbbacc". The longest substring consisting of one repeating character is "bbb" with length 3.
//- 2nd query updates s = "bbbccc".
//  The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
//- 3rd query updates s = "bbbbcc". The longest substring consisting of one repeating character is "bbbb" with length 4.
//Thus, we return [3,3,4].

//Example 2:
//Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
//Output: [2,3]
//Explanation:
//- 1st query updates s = "abazz". The longest substring consisting of one repeating character is "zz" with length 2.
//- 2nd query updates s = "aaazz". The longest substring consisting of one repeating character is "aaa" with length 3.
//Thus, we return [2,3].

//Constraints:
//1 <= s.length <= 105
//s consists of lowercase English letters.
//k == queryCharacters.length == queryIndices.length
//1 <= k <= 105
//queryCharacters consists of lowercase English lett
