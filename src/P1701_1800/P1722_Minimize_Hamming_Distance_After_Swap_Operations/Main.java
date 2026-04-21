package P1701_1800.P1722_Minimize_Hamming_Distance_After_Swap_Operations;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] source = {1, 2, 3, 4};
        int[] target = {2, 1, 4, 5};
        int[][] allowedSwaps = {{0, 1}, {2, 3}};

        System.out.println(solution.minimumHammingDistance(source, target, allowedSwaps)); // output: 1
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        UnionFind uf = new UnionFind(n);

        // build components
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        // map root -> frequency map of source values
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            map.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> freq = map.get(root);
            freq.put(source[i], freq.getOrDefault(source[i], 0) + 1);
        }

        // compare with target
        int hammingDistance = 0;

        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            Map<Integer, Integer> freq = map.get(root);

            if (freq.getOrDefault(target[i], 0) > 0) {
                freq.put(target[i], freq.get(target[i]) - 1);
            } else {
                hammingDistance++;
            }
        }

        return hammingDistance;
    }


    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }

            return parent[x];
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) return;

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }
    }

}

//Complexity:
// time and space - O(n)


//You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps
// where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and
// index bi (0-indexed) of array source. Note that you can swap elements at a specific pair of indices multiple
// times and in any order.
//The Hamming distance of two arrays of the same length, source and target, is the number of positions where the
// elements are different. Formally, it is the number of indices i for 0 <= i <= n-1 where
// source[i] != target[i] (0-indexed).
//Return the minimum Hamming distance of source and target after performing any amount of swap operations on
// array source.

//Example 1:
//Input: source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
//Output: 1
//Explanation: source can be transformed the following way:
//- Swap indices 0 and 1: source = [2,1,3,4]
//- Swap indices 2 and 3: source = [2,1,4,3]
//The Hamming distance of source and target is 1 as they differ in 1 position: index 3.

//Example 2:
//Input: source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
//Output: 2
//Explanation: There are no allowed swaps.
//The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.

//Example 3:
//Input: source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
//Output: 0

//Constraints:
//n == source.length == target.length
//1 <= n <= 105
//1 <= source[i], target[i] <= 105
//0 <= allowedSwaps.length <= 105
//allowedSwaps[i].length == 2
//0 <= ai, bi <= n - 1
//ai != bi
