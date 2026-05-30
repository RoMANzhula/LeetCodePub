package P3101_3200.P3161_Block_Placement_Queries;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] queries1 = {
                {1, 2},
                {2, 3, 3},
                {2, 3, 1},
                {2, 2, 2}
        };

        System.out.println(solution.getResults(queries1)); // [false, true, true]

        int[][] queries2 = {
                {1, 7},
                {2, 7, 6},
                {1, 2},
                {2, 7, 5},
                {2, 7, 6}
        };

        System.out.println(solution.getResults(queries2));// [true, true, false]
    }

    static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }

            int mid = (l + r) >>> 1;

            if (idx <= mid) {
                update(node * 2, l, mid, idx, val);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, val);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int node, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) {
                return 0;
            }

            if (ql <= l && r <= qr) {
                return tree[node];
            }

            int mid = (l + r) >>> 1;

            return Math.max(
                    query(node * 2, l, mid, ql, qr),
                    query(node * 2 + 1, mid + 1, r, ql, qr)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int MAX = 50001;

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(MAX);

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        SegmentTree seg = new SegmentTree(MAX + 1);

        Integer prev = null;

        for (int pos : obstacles) {
            if (prev != null) {
                seg.update(1, 0, MAX, pos, pos - prev);
            }
            prev = pos;
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {

            int[] q = queries[i];

            if (q[0] == 2) {

                int x = q[1];
                int sz = q[2];

                Integer pre = obstacles.floor(x);

                int bestGap = Math.max(
                        seg.query(1, 0, MAX, 0, pre),
                        x - pre
                );

                ans.add(bestGap >= sz);

            } else {

                int pos = q[1];

                Integer left = obstacles.lower(pos);
                Integer right = obstacles.higher(pos);

                seg.update(1, 0, MAX, right, right - left);

                obstacles.remove(pos);
            }
        }

        Collections.reverse(ans);
        return ans;
    }

}

//Complexity:
// time - O(n log 50_000)
// space - O(50_000)


//There exists an infinite number line, with its origin at 0 and extending towards the positive x-axis.
//You are given a 2D array queries, which contains two types of queries:
//For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x from the origin. It is guaranteed that
// there is no obstacle at distance x when the query is asked.
//For a query of type 2, queries[i] = [2, x, sz]. Check if it is possible to place a block of size sz anywhere in the
// range [0, x] on the line, such that the block entirely lies in the range [0, x]. A block cannot be placed if it
// intersects with any obstacle, but it may touch it. Note that you do not actually place the block. Queries
// are separate.
//Return a boolean array results, where results[i] is true if you can place the block specified in the ith query of
// type 2, and false otherwise.

//Example 1:
//Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]
//Output: [false,true,true]
//Explanation:
//For query 0, place an obstacle at x = 2. A block of size at most 2 can be placed before x = 3.

//Example 2:
//Input: queries = [[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]
//Output: [true,true,false]
//Explanation:
//Place an obstacle at x = 7 for query 0. A block of size at most 7 can be placed before x = 7.
//Place an obstacle at x = 2 for query 2. Now, a block of size at most 5 can be placed before x = 7, and a block of
// size at most 2 before x = 2.

//Constraints:
//1 <= queries.length <= 15 * 104
//2 <= queries[i].length <= 3
//1 <= queries[i][0] <= 2
//1 <= x, sz <= min(5 * 104, 3 * queries.length)
//The input is generated such that for queries of type 1, no obstacle exists at distance x when the query is asked.
//The input is generated such that there is at least one query of type 2.
