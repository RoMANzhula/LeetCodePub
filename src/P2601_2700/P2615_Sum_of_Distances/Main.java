package P2601_2700.P2615_Sum_of_Distances;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3, 1, 1, 2};
        long[] res1 = solution.distance(nums1);
        System.out.println("Output 1: " + Arrays.toString(res1));

        int[] nums2 = {0, 5, 3};
        long[] res2 = solution.distance(nums2);
        System.out.println("Output 2: " + Arrays.toString(res2));
    }

    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] result = new long[n];

        // group indices by value
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // process each group
        for (List<Integer> indices : map.values()) {
            int size = indices.size();

            // prefix sum of indices
            long[] prefix = new long[size];
            prefix[0] = indices.get(0);
            for (int i = 1; i < size; i++) {
                prefix[i] = prefix[i - 1] + indices.get(i);
            }

            for (int i = 0; i < size; i++) {
                int index = indices.get(i);

                // left side
                long left = 0;
                if (i > 0) {
                    long sumLeft = prefix[i - 1];
                    left = (long) index * i - sumLeft;
                }

                // right side
                long right = 0;
                if (i < size - 1) {
                    long sumRight = prefix[size - 1] - prefix[i];
                    right = sumRight - (long) index * (size - i - 1);
                }

                result[index] = left + right;
            }
        }

        return result;
    }

}

//Complexity:
// time and space - O(n)


//You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where
// arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set
// arr[i] to be 0.
//Return the array arr.

//Example 1:
//Input: nums = [1,3,1,1,2]
//Output: [5,0,3,4,0]
//Explanation:
//When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
//When i = 1, arr[1] = 0 because there is no other index with value 3.
//When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
//When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
//When i = 4, arr[4] = 0 because there is no other index with value 2.

//Example 2:
//Input: nums = [0,5,3]
//Output: [0,0,0]
//Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.

//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
