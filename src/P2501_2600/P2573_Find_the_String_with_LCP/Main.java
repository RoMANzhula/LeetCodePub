package P2501_2600.P2573_Find_the_String_with_LCP;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] lcp1 = {
                {4,0,2,0},
                {0,3,0,1},
                {2,0,2,0},
                {0,1,0,1}
        };

        int[][] lcp2 = {
                {4,3,2,1},
                {3,3,2,1},
                {2,2,2,1},
                {1,1,1,1}
        };

        int[][] lcp3 = {
                {4,3,2,1},
                {3,3,2,1},
                {2,2,2,1},
                {1,1,1,3}
        };

        System.out.println(solution.findTheString(lcp1)); // abab
        System.out.println(solution.findTheString(lcp2)); // aaaa
        System.out.println(solution.findTheString(lcp3)); // ""
    }

    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // check diagonal
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        // union positions
        DSU dsu = new DSU(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] > 0) {
                    dsu.union(i, j);
                }
            }
        }

        // assign characters
        char[] res = new char[n];
        Map<Integer, Character> map = new HashMap<>();
        char current = 'a';

        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            if (!map.containsKey(root)) {
                if (current > 'z') return ""; // too many groups
                map.put(root, current++);
            }
            res[i] = map.get(root);
        }

        String word = new String(res);

        // validate by recomputing LCP
        int[][] calc = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word.charAt(i) == word.charAt(j)) {
                    if (i == n - 1 || j == n - 1) {
                        calc[i][j] = 1;
                    } else {
                        calc[i][j] = 1 + calc[i + 1][j + 1];
                    }
                } else {
                    calc[i][j] = 0;
                }

                if (calc[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }

        return word;
    }

}

class DSU {
    int[] parent;

    DSU(int n) {
        parent = new int[n];

        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);

        return parent[x];
    }

    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) parent[pb] = pa;
    }

}

//Complexity:
// time and space - O(n^2)


//We define the lcp matrix of any 0-indexed string word of n lowercase English letters as an n x n grid such that:
//lcp[i][j] is equal to the length of the longest common prefix between the substrings word[i,n-1] and word[j,n-1].
//Given an n x n matrix lcp, return the alphabetically smallest string word that corresponds to lcp. If there is no
// such string, return an empty string.
//A string a is lexicographically smaller than a string b (of the same length) if in the first position where
// a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For
// example, "aabd" is lexicographically smaller than "aaca" because the first position they differ is at the third
// letter, and 'b' comes before 'c'.

//Example 1:
//Input: lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
//Output: "abab"
//Explanation: lcp corresponds to any 4 letter string with two alternating letters. The lexicographically smallest of
// them is "abab".

//Example 2:
//Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,1]]
//Output: "aaaa"
//Explanation: lcp corresponds to any 4 letter string with a single distinct letter. The lexicographically smallest of
// them is "aaaa".

//Example 3:
//Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]
//Output: ""
//Explanation: lcp[3][3] cannot be equal to 3 since word[3,...,3] consists of only a single letter; Thus, no
// answer exists.

//Constraints:
//1 <= n == lcp.length == lcp[i].length <= 1000
//0 <= lcp[i][j] <= n
