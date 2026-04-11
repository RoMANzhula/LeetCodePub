package P3701_3800.P3741_Minimum_Distance_Between_Three_Equal_Elements_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 1, 1, 3};
        System.out.println(solution.minimumDistance(nums1)); // 6

        int[] nums2 = {1, 1, 2, 3, 2, 1, 2};
        System.out.println(solution.minimumDistance(nums2)); // 8

        int[] nums3 = {1};
        System.out.println(solution.minimumDistance(nums3)); // -1
    }

    public int minimumDistance(int[] nums) {
        int n = nums.length;

        // group indices by value
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int minDistance = Integer.MAX_VALUE;

        //process each group
        for (List<Integer> indices : map.values()) {
            int size = indices.size();
            if (size < 3) continue;

            // sliding window of size 3
            for (int i = 0; i + 2 < size; i++) {
                int left = indices.get(i);
                int right = indices.get(i + 2);

                int distance = 2 * (right - left);
                minDistance = Math.min(minDistance, distance);
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

}

//Complexity:
// time and space - O(n)


//You are given an integer array nums.
//A tuple (i, j, k) of 3 distinct indices is good if nums[i] == nums[j] == nums[k].
//The distance of a good tuple is abs(i - j) + abs(j - k) + abs(k - i), where abs(x) denotes the absolute value of x.
//Return an integer denoting the minimum possible distance of a good tuple. If no good tuples exist, return -1.

//Example 1:
//Input: nums = [1,2,1,1,3]
//Output: 6
//Explanation:
//The minimum distance is achieved by the good tuple (0, 2, 3).
//(0, 2, 3) is a good tuple because nums[0] == nums[2] == nums[3] == 1. Its distance is
// abs(0 - 2) + abs(2 - 3) + abs(3 - 0) = 2 + 1 + 3 = 6.

//Example 2:
//Input: nums = [1,1,2,3,2,1,2]
//Output: 8
//Explanation:
//The minimum distance is achieved by the good tuple (2, 4, 6).
//(2, 4, 6) is a good tuple because nums[2] == nums[4] == nums[6] == 2. Its distance is
// abs(2 - 4) + abs(4 - 6) + abs(6 - 2) = 2 + 2 + 4 = 8.

//Example 3:
//Input: nums = [1]
//Output: -1
//Explanation:
//There are no good tuples. Therefore, the answer is -1.

//Constraints:
//1 <= n == nums.length <= 105
//1 <= nums[i] <= n
