package P3801_3900.P3841_Palindromic_Path_Queries_in_a_Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}};
        String s1 = "aac";
        String[] queries1 = {
                "query 0 2",
                "update 1 b",
                "query 0 2"
        };

        System.out.println(solution.palindromePath(n1, edges1, s1, queries1));

        int n2 = 4;
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}};
        String s2 = "abca";
        String[] queries2 = {
                "query 1 2",
                "update 0 b",
                "query 2 3",
                "update 3 a",
                "query 1 3"
        };

        System.out.println(solution.palindromePath(n2, edges2, s2, queries2));
    }

    private List<Integer>[] adj;
    private int[] parent, depth, size, heavy, head, pos;
    private int timer;
    private int[] segmentTree;
    private int n;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        Arrays.fill(heavy, -1);

        dfs(0, -1);
        decompose(0, 0);

        segmentTree = new int[4 * n];

        for (int i = 0; i < n; i++) {
            updateTree(1, 0, n - 1, pos[i],
                    1 << (s.charAt(i) - 'a'));
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                char c = parts[2].charAt(0);

                updateTree(1, 0, n - 1, pos[u],
                        1 << (c - 'a'));
            } else {
                int v = Integer.parseInt(parts[2]);
                int mask = queryPath(u, v);

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private int dfs(int u, int p) {
        parent[u] = p;
        size[u] = 1;
        int maxSize = 0;

        for (int v : adj[u]) {
            if (v == p) {
                continue;
            }

            depth[v] = depth[u] + 1;
            int childSize = dfs(v, u);
            size[u] += childSize;

            if (childSize > maxSize) {
                maxSize = childSize;
                heavy[u] = v;
            }
        }

        return size[u];
    }

    private void decompose(int u, int h) {
        head[u] = h;
        pos[u] = timer++;

        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }

        for (int v : adj[u]) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    private void updateTree(int node, int left, int right,
                            int index, int value) {
        if (left == right) {
            segmentTree[node] = value;
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            updateTree(node * 2, left, mid, index, value);
        } else {
            updateTree(node * 2 + 1, mid + 1, right, index, value);
        }

        segmentTree[node] =
                segmentTree[node * 2] ^ segmentTree[node * 2 + 1];
    }

    private int queryTree(int node, int left, int right,
                          int ql, int qr) {
        if (ql > right || qr < left) {
            return 0;
        }

        if (ql <= left && right <= qr) {
            return segmentTree[node];
        }

        int mid = left + (right - left) / 2;

        return queryTree(node * 2, left, mid, ql, qr)
                ^ queryTree(node * 2 + 1, mid + 1, right, ql, qr);
    }

    private int queryPath(int u, int v) {
        int mask = 0;

        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            mask ^= queryTree(1, 0, n - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }

        int left = Math.min(pos[u], pos[v]);
        int right = Math.max(pos[u], pos[v]);

        mask ^= queryTree(1, 0, n - 1, left, right);

        return mask;
    }

}


//You are given an undirected tree with n nodes labeled 0 to n - 1. This is represented by a 2D array edges of
// length n - 1, where edges[i] = [ui, vi] indicates an undirected edge between nodes ui and vi.
//You are also given a string s of length n consisting of lowercase English letters, where s[i] represents the
// character assigned to node i.
//You are also given a string array queries, where each queries[i] is either:
//"update ui c": Change the character at node ui to c. Formally, update s[ui] = c.
//"query ui vi": Determine whether the string formed by the characters on the unique path from ui to vi (inclusive)
// can be rearranged into a palindrome.
//Return a boolean array answer, where answer[j] is true if the jth query of type "query ui vi"
//  can be rearranged into a palindrome, and false otherwise.

//Example 1:
//Input: n = 3, edges = [[0,1],[1,2]], s = "aac", queries = ["query 0 2","update 1 b","query 0 2"]
//Output: [true,false]
//Explanation:
//"query 0 2": Path 0 → 1 → 2 gives "aac", which can be rearranged to form "aca", a palindrome. Thus, answer[0] = true.
//"update 1 b": Update node 1 to 'b', now s = "abc".
//"query 0 2": Path characters are "abc", which cannot be rearranged to form a palindrome. Thus, answer[1] = false.
//Thus, answer = [true, false].

//Example 2:
//Input: n = 4, edges = [[0,1],[0,2],[0,3]], s = "abca", queries = ["query 1 2","update 0 b","query 2 3","update 3 a","query 1 3"]
//Output: [false,false,true]
//Explanation:
//"query 1 2": Path 1 → 0 → 2 gives "bac", which cannot be rearranged to form a palindrome. Thus, answer[0] = false.
//"update 0 b": Update node 0 to 'b', now s = "bbca".
//"query 2 3": Path 2 → 0 → 3 gives "cba", which cannot be rearranged to form a palindrome. Thus, answer[1] = false.
//"update 3 a": Update node 3 to 'a', s = "bbca".
//"query 1 3": Path 1 → 0 → 3 gives "bba", which can be rearranged to form "bab", a palindrome. Thus, answer[2] = true.
//Thus, answer = [false, false, true].

//Constraints:
//1 <= n == s.length <= 5 * 104
//edges.length == n - 1
//edges[i] = [ui, vi]
//0 <= ui, vi <= n - 1
//s consists of lowercase English letters.
//The input is generated such that edges represents a valid tree.
//1 <= queries.length <= 5 * 104​​​​​​​
//queries[i] = "update ui c" or
//queries[i] = "query ui vi"
//0 <= ui, vi <= n - 1
//c is a lowercase English letter.
