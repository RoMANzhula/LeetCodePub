package P2501_2600.P2537_Count_the_Number_of_Good_Subarrays;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 1, 1, 1, 1};
        int k1 = 10;
        System.out.println("Output: " + solution.countGood(nums1, k1)); // Output: 1

        int[] nums2 = {3, 1, 4, 3, 2, 2, 4};
        int k2 = 2;
        System.out.println("Output: " + solution.countGood(nums2, k2)); // Output: 4
    }

    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        long result = 0, pairs = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            int count = freq.getOrDefault(nums[right], 0);

            pairs += count;
            freq.put(nums[right], count + 1);

            // shrink the window from the left while we have enough pairs
            while (pairs >= k) {
                // all subarrays starting from `left` to `right` are valid
                result += nums.length - right;

                int leftCount = freq.get(nums[left]);
                pairs -= (leftCount - 1); // removing nums[left] reduces pairs by (count - 1)
                freq.put(nums[left], leftCount - 1);

                left++;
            }
        }

        return result;
    }

}

//Complexity:
//time - O(n)
//space - O(n)


//Given an integer array nums and an integer k, return the number of good subarrays of nums.
//A subarray arr is good if there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].
//A subarray is a contiguous non-empty sequence of elements within an array.

//Example 1:
//Input: nums = [1,1,1,1,1], k = 10
//Output: 1
//Explanation: The only good subarray is the array nums itself.

//Example 2:
//Input: nums = [3,1,4,3,2,2,4], k = 2
//Output: 4
//Explanation: There are 4 different good subarrays:
//- [3,1,4,3,2,2] that has 2 pairs.
//- [3,1,4,3,2,2,4] that has 3 pairs.
//- [1,4,3,2,2,4] that has 2 pairs.
//- [4,3,2,2,4] that has 2 pairs.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i], k <= 109
