package P3501_3600.P3525_Find_X_Value_of_Array_II;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 4, 5};
        int k1 = 3;
        int[][] queries1 = {
                {2, 2, 0, 2},
                {3, 3, 3, 0},
                {0, 1, 0, 1}
        };

        System.out.println(Arrays.toString(solution.resultArray(nums1, k1, queries1))); // [2, 2, 2]


        int[] nums2 = {1, 2, 4, 8, 16, 32};
        int k2 = 4;
        int[][] queries2 = {
                {0, 2, 0, 2},
                {0, 2, 0, 1}
        };
        System.out.println(Arrays.toString(solution.resultArray(nums2, k2, queries2))); // [1, 0]


        int[] nums3 = {1, 1, 2, 1, 1};
        int k3 = 2;
        int[][] queries3 = {
                {2, 1, 0, 1}
        };
        System.out.println(Arrays.toString(solution.resultArray(nums3, k3, queries3))); // [5]
    }

    class Node {
        int[] prefixCount;
        int product;

        Node(int k) {
            prefixCount = new int[k];
        }
    }

    class SegmentTree {
        private final int n;
        private final int k;
        private final Node[] tree;

        SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];

            build(1, 0, n - 1, nums);
        }

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                tree[node] = new Node(k);

                int remainder = nums[left] % k;

                tree[node].product = remainder;
                tree[node].prefixCount[remainder] = 1;

                return;
            }

            int mid = left + (right - left) / 2;

            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);

            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        public void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        private void update(int node, int left, int right,
                            int index, int value) {

            if (left == right) {
                tree[node] = new Node(k);

                int remainder = value % k;

                tree[node].product = remainder;
                tree[node].prefixCount[remainder] = 1;

                return;
            }

            int mid = left + (right - left) / 2;

            if (index <= mid) {
                update(node * 2, left, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, right, index, value);
            }

            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        public Node query(int queryLeft, int queryRight) {
            return query(1, 0, n - 1, queryLeft, queryRight);
        }

        private Node query(int node, int left, int right,
                           int queryLeft, int queryRight) {

            if (queryLeft <= left && right <= queryRight) {
                return tree[node];
            }

            int mid = left + (right - left) / 2;

            if (queryRight <= mid) {
                return query(node * 2, left, mid,
                        queryLeft, queryRight);
            }

            if (queryLeft > mid) {
                return query(node * 2 + 1, mid + 1, right,
                        queryLeft, queryRight);
            }

            Node leftNode = query(
                    node * 2,
                    left,
                    mid,
                    queryLeft,
                    queryRight
            );

            Node rightNode = query(
                    node * 2 + 1,
                    mid + 1,
                    right,
                    queryLeft,
                    queryRight
            );

            return merge(leftNode, rightNode);
        }

        private Node merge(Node a, Node b) {
            Node result = new Node(k);

            // prefixes completely inside A
            for (int r = 0; r < k; r++) {
                result.prefixCount[r] += a.prefixCount[r];
            }

            // prefixes that contain all of A and then a prefix of B
            for (int r = 0; r < k; r++) {
                if (b.prefixCount[r] == 0) {
                    continue;
                }

                int newRemainder = (a.product * r) % k;

                result.prefixCount[newRemainder] +=
                        b.prefixCount[r];
            }

            result.product = (a.product * b.product) % k;

            return result;
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        SegmentTree segmentTree = new SegmentTree(nums, k);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // this update persists for all following queries
            segmentTree.update(index, value);

            // after removing nums[0 .. start-1] every possible remaining array is a non-empty prefix of nums[start .. n-1]
            Node node = segmentTree.query(start, nums.length - 1);

            result[i] = node.prefixCount[x];
        }

        return result;
    }

}


//You are given an array of positive integers nums and a positive integer k. You are also given a 2D array queries,
// where queries[i] = [indexi, valuei, starti, xi].
//You are allowed to perform an operation once on nums, where you can remove any suffix from nums such that nums
// remains non-empty.
//The x-value of nums for a given x is defined as the number of ways to perform this operation so that the product of
// the remaining elements leaves a remainder of x modulo k.
//For each query in queries you need to determine the x-value of nums for xi after performing the following actions:
//Update nums[indexi] to valuei. Only this step persists for the rest of the queries.
//Remove the prefix nums[0..(starti - 1)] (where nums[0..(-1)] will be used to represent the empty prefix).
//Return an array result of size queries.length where result[i] is the answer for the ith query.
//A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
//A suffix of an array is a subarray that starts at any point within the array and extends to the end of the array.
//Note that the prefix and suffix to be chosen for the operation can be empty.
//Note that x-value has a different definition in this version.

//Example 1:
//Input: nums = [1,2,3,4,5], k = 3, queries = [[2,2,0,2],[3,3,3,0],[0,1,0,1]]
//Output: [2,2,2]
//Explanation:
//For query 0, nums becomes [1, 2, 2, 4, 5], and the empty prefix must be removed. The possible operations are:
//Remove the suffix [2, 4, 5]. nums becomes [1, 2].
//Remove the empty suffix. nums becomes [1, 2, 2, 4, 5] with a product 80, which gives remainder 2 when divided by 3.
//For query 1, nums becomes [1, 2, 2, 3, 5], and the prefix [1, 2, 2] must be removed. The possible operations are:
//Remove the empty suffix. nums becomes [3, 5].
//Remove the suffix [5]. nums becomes [3].
//For query 2, nums becomes [1, 2, 2, 3, 5], and the empty prefix must be removed. The possible operations are:
//Remove the suffix [2, 2, 3, 5]. nums becomes [1].
//Remove the suffix [3, 5]. nums becomes [1, 2, 2].

//Example 2:
//Input: nums = [1,2,4,8,16,32], k = 4, queries = [[0,2,0,2],[0,2,0,1]]
//Output: [1,0]
//Explanation:
//For query 0, nums becomes [2, 2, 4, 8, 16, 32]. The only possible operation is:
//Remove the suffix [2, 4, 8, 16, 32].
//For query 1, nums becomes [2, 2, 4, 8, 16, 32]. There is no possible way to perform the operation.

//Example 3:
//Input: nums = [1,1,2,1,1], k = 2, queries = [[2,1,0,1]]
//Output: [5]

//Constraints:
//1 <= nums[i] <= 109
//1 <= nums.length <= 105
//1 <= k <= 5
//1 <= queries.length <= 2 * 104
//queries[i] == [indexi, valuei, starti, xi]
//0 <= indexi <= nums.length - 1
//1 <= valuei <= 109
//0 <= starti <= nums.length - 1
//0 <= xi <= k - 1
