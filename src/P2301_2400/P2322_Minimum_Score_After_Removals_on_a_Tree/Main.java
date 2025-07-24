package P2301_2400.P2322_Minimum_Score_After_Removals_on_a_Tree;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 5, 5, 4, 11};
        int[][] edges1 = {{0, 1}, {1, 2}, {1, 3}, {3, 4}};
        System.out.println("Output: " + solution.minimumScore(nums1, edges1));  // Output: 9

        int[] nums2 = {5, 5, 2, 4, 4, 2};
        int[][] edges2 = {{0, 1}, {1, 2}, {5, 2}, {4, 3}, {1, 3}};
        System.out.println("Output: " + solution.minimumScore(nums2, edges2));  // Output: 0
    }

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        int totalXor = 0;
        for (int num : nums) totalXor ^= num;

        int[] subXor = Arrays.copyOf(nums, n);
        List<List<Integer>> tree = new ArrayList<>();
        List<Set<Integer>> children = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
            Set<Integer> set = new HashSet<>();
            set.add(i);
            children.add(set);
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(0, -1, tree, subXor, children);

        int minScore = Integer.MAX_VALUE;
        int m = edges.length;

        for (int i = 0; i < m; ++i) {
            int a = edges[i][0], b = edges[i][1];

            if (children.get(a).contains(b)) {
                int tmp = a; a = b; b = tmp;
            }

            for (int j = 0; j < i; ++j) {
                int c = edges[j][0], d = edges[j][1];

                if (children.get(c).contains(d)) {
                    int tmp = c; c = d; d = tmp;
                }

                List<Integer> xors;

                if (a != c && children.get(a).contains(c)) {
                    int x = subXor[c];
                    int y = subXor[a] ^ subXor[c];
                    int z = totalXor ^ subXor[a];
                    xors = List.of(x, y, z);
                } else if (a != c && children.get(c).contains(a)) {
                    int x = subXor[a];
                    int y = subXor[c] ^ subXor[a];
                    int z = totalXor ^ subXor[c];
                    xors = List.of(x, y, z);
                } else {
                    int x = subXor[a];
                    int y = subXor[c];
                    int z = totalXor ^ subXor[a] ^ subXor[c];
                    xors = List.of(x, y, z);
                }

                int max = Collections.max(xors);
                int min = Collections.min(xors);
                minScore = Math.min(minScore, max - min);
            }
        }

        return minScore;
    }

    private Pair<Integer, Set<Integer>> dfs(
            int u,
            int parent,
            List<List<Integer>> tree,
            int[] subXor,
            List<Set<Integer>> children
    ) {
        for (int v : tree.get(u)) {
            if (v == parent) continue;

            Pair<Integer, Set<Integer>> res = dfs(v, u, tree, subXor, children);
            subXor[u] ^= res.getKey();
            children.get(u).addAll(res.getValue());
        }

        return new Pair<>(subXor[u], children.get(u));
    }

    // pair class (since Java doesn't have a native one in all versions)
    static class Pair<K, V> {
        private final K key;
        private final V value;
        public Pair(K key, V value) { this.key = key; this.value = value; }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }

}

//Complexity:
// time and space: O(n^2)


//There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
//You are given a 0-indexed integer array nums of length n where nums[i] represents the value of the ith node. You
// are also given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an
// edge between nodes ai and bi in the tree.
//Remove two distinct edges of the tree to form three connected components. For a pair of removed edges, the
// following steps are defined:
//Get the XOR of all the values of the nodes for each of the three components respectively.
//The difference between the largest XOR value and the smallest XOR value is the score of the pair.
//For example, say the three components have the node values: [4,5,7], [1,9], and [3,3,3]. The three XOR values
// are 4 ^ 5 ^ 7 = 6, 1 ^ 9 = 8, and 3 ^ 3 ^ 3 = 3. The largest XOR value is 8 and the smallest XOR value is 3. The
// score is then 8 - 3 = 5.
//Return the minimum score of any possible pair of edge removals on the given tree.

//Example 1:
//Input: nums = [1,5,5,4,11], edges = [[0,1],[1,2],[1,3],[3,4]]
//Output: 9
//Explanation: The diagram above shows a way to make a pair of removals.
//- The 1st component has nodes [1,3,4] with values [5,4,11]. Its XOR value is 5 ^ 4 ^ 11 = 10.
//- The 2nd component has node [0] with value [1]. Its XOR value is 1 = 1.
//- The 3rd component has node [2] with value [5]. Its XOR value is 5 = 5.
//The score is the difference between the largest and smallest XOR value which is 10 - 1 = 9.
//It can be shown that no other pair of removals will obtain a smaller score than 9.

//Example 2:
//Input: nums = [5,5,2,4,4,2], edges = [[0,1],[1,2],[5,2],[4,3],[1,3]]
//Output: 0
//Explanation: The diagram above shows a way to make a pair of removals.
//- The 1st component has nodes [3,4] with values [4,4]. Its XOR value is 4 ^ 4 = 0.
//- The 2nd component has nodes [1,0] with values [5,5]. Its XOR value is 5 ^ 5 = 0.
//- The 3rd component has nodes [2,5] with values [2,2]. Its XOR value is 2 ^ 2 = 0.
//The score is the difference between the largest and smallest XOR value which is 0 - 0 = 0.
//We cannot obtain a smaller score than 0.

//Constraints:
//n == nums.length
//3 <= n <= 1000
//1 <= nums[i] <= 108
//edges.length == n - 1
//edges[i].length == 2
//0 <= ai, bi < n
//ai != bi
//edges represents a valid tree.
