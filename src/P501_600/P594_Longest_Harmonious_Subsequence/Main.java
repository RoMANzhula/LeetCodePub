package P501_600.P594_Longest_Harmonious_Subsequence;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.findLHS(new int[]{1,3,2,2,5,2,3,7})); // 5
        System.out.println(solution.findLHS(new int[]{1,2,3,4}));         // 2
        System.out.println(solution.findLHS(new int[]{1,1,1,1}));         // 0
    }

    public int findLHS(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        // count frequencies
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxLen = 0;

        // check each num if num + 1 exists
        for (int key : freqMap.keySet()) {
            if (freqMap.containsKey(key + 1)) {
                int currLen = freqMap.get(key) + freqMap.get(key + 1);
                maxLen = Math.max(maxLen, currLen);
            }
        }

        return maxLen;
    }

}

//Complexity:
// time and space: O(n)


//We define a harmonious array as an array where the difference between its maximum value and its
// minimum value is exactly 1.
//Given an integer array nums, return the length of its longest harmonious subsequence among all its
// possible subsequences.

//Example 1:
//Input: nums = [1,3,2,2,5,2,3,7]
//Output: 5
//Explanation:
//The longest harmonious subsequence is [3,2,2,2,3].

//Example 2:
//Input: nums = [1,2,3,4]
//Output: 2
//Explanation:
//The longest harmonious subsequences are [1,2], [2,3], and [3,4], all of which have a length of 2.

//Example 3:
//Input: nums = [1,1,1,1]
//Output: 0
//Explanation:
//No harmonic subsequence exists.

//Constraints:
//1 <= nums.length <= 2 * 104
//-109 <= nums[i] <= 109
