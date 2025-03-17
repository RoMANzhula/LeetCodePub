package P2201_2300.P2206_Divide_Array_Into_Equal_Pairs;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 2, 3, 2, 2, 2};
        int[] nums2 = {1, 2, 3, 4};

        System.out.println(solution.divideArray(nums1)); // Output: true
        System.out.println(solution.divideArray(nums2)); // Output: false
    }

    public boolean divideArray(int[] nums) {
        HashMap<Integer, Integer> frequency = new HashMap<>();

        // count occurrences of each number
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        // check if all numbers appear an even number of times
        for (int count : frequency.values()) {
            if (count % 2 != 0) {
                return false;
            }
        }

        return true;
    }

}

//Complexity Analysis:
//Time Complexity: O(n), since we iterate through nums twice (once for counting and once for checking).
//Space Complexity: O(n), since we store counts in a HashMap.


//You are given an integer array nums consisting of 2 * n integers.
//You need to divide nums into n pairs such that:
//Each element belongs to exactly one pair.
//The elements present in a pair are equal.
//Return true if nums can be divided into n pairs, otherwise return false.

//Example 1:
//Input: nums = [3,2,3,2,2,2]
//Output: true
//Explanation:
//There are 6 elements in nums, so they should be divided into 6 / 2 = 3 pairs.
//If nums is divided into the pairs (2, 2), (3, 3), and (2, 2), it will satisfy all the conditions.

//Example 2:
//Input: nums = [1,2,3,4]
//Output: false
//Explanation:
//There is no way to divide nums into 4 / 2 = 2 pairs such that the pairs satisfy every condition.

//Constraints:
//nums.length == 2 * n
//1 <= n <= 500
//1 <= nums[i] <= 500
