package P2101_2200.P2179_Count_Good_Triplets_in_an_Array;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 0, 1, 3};
        int[] nums2 = {0, 1, 2, 3};
        System.out.println(solution.goodTriplets(nums1, nums2)); // Output: 1

        int[] nums1_2 = {4, 0, 1, 3, 2};
        int[] nums2_2 = {4, 1, 0, 2, 3};
        System.out.println(solution.goodTriplets(nums1_2, nums2_2)); // Output: 4
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums2[i]] = i;
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = pos[nums1[i]];
        }

        FenwickTree bit1 = new FenwickTree(n);
        FenwickTree bit2 = new FenwickTree(n);
        long[] left = new long[n];
        long[] right = new long[n];

        for (int i = 0; i < n; i++) {
            left[i] = bit1.query(arr[i] - 1);
            bit1.update(arr[i], 1);
        }

        for (int i = n - 1; i >= 0; i--) {
            right[i] = bit2.query(n - 1) - bit2.query(arr[i]);
            bit2.update(arr[i], 1);
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += left[i] * right[i];
        }

        return result;
    }

    static class FenwickTree {
        int[] bit;
        int n;

        FenwickTree(int size) {
            n = size;
            bit = new int[n + 1];
        }

        void update(int index, int value) {
            index++;
            while (index <= n) {
                bit[index] += value;
                index += index & -index;
            }
        }

        int query(int index) {
            index++;
            int sum = 0;
            while (index > 0) {
                sum += bit[index];
                index -= index & -index;
            }
            return sum;
        }

        int queryRange(int left, int right) {
            return query(right) - query(left - 1);
        }
    }

}

//We want to count good triplets (x, y, z) such that:
//pos1[x] < pos1[y] < pos1[z] (in nums1)
//pos2[x] < pos2[y] < pos2[z] (in nums2)
//Solve:
//Map positions of elements in nums2 to get pos2[value].
//Transform nums1 into a new array arr where arr[i] = pos2[nums1[i]].
//  Now the problem becomes finding increasing triplets in arr.
//Use a Fenwick Tree (BIT) to:
//  Count elements before i that are smaller than arr[i].
//  Count elements after i that are greater than arr[i].
//For each i, compute:
//  leftCount[i]: number of values less than arr[i] to the left.
//  rightCount[i]: number of values greater than arr[i] to the right.
//  total triplets: leftCount[i] * rightCount[i].
//Complexity:
// time - O(n log n)
// space - O(n)


//You are given two 0-indexed arrays nums1 and nums2 of length n, both of which are permutations of [0, 1, ..., n - 1].
//A good triplet is a set of 3 distinct values which are present in increasing order by position both in nums1 and
// nums2. In other words, if we consider pos1v as the index of the value v in nums1 and pos2v as the index of
// the value v in nums2, then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1, such that
// pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.
//Return the total number of good triplets.

//Example 1:
//Input: nums1 = [2,0,1,3], nums2 = [0,1,2,3]
//Output: 1
//Explanation:
//There are 4 triplets (x,y,z) such that pos1x < pos1y < pos1z. They are (2,0,1), (2,0,3), (2,1,3), and (0,1,3).
//Out of those triplets, only the triplet (0,1,3) satisfies pos2x < pos2y < pos2z. Hence, there is only 1 good triplet.

//Example 2:
//Input: nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
//Output: 4
//Explanation: The 4 good triplets are (4,0,3), (4,0,2), (4,1,3), and (4,1,2).

//Constraints:
//n == nums1.length == nums2.length
//3 <= n <= 105
//0 <= nums1[i], nums2[i] <= n - 1
//nums1 and nums2 are permutations of [0, 1, ..., n - 1].
