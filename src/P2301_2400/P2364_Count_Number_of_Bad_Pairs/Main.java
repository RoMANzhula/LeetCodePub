package P2301_2400.P2364_Count_Number_of_Bad_Pairs;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {4, 1, 3, 3};
        System.out.println(solution.countBadPairs(nums1)); // Output 5

        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println(solution.countBadPairs(nums2)); // Output 0
    }

    public long countBadPairs(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();

        int len = nums.length;
        long goodPairs = 0;
        long totalPairs = (long) len * (len - 1) / 2; // total number of pairs

        for (int i = 0; i < len; i++) {
            int key = i - nums[i]; // use formula: j - i == nums[j] - nums[i]
            goodPairs += countMap.getOrDefault(key, 0); // count good pairs
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }

        return totalPairs - goodPairs; //bingo
    }

}

//Complexity: O(n)


//You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad
// pair if i < j and j - i != nums[j] - nums[i].
//Return the total number of bad pairs in nums.
//
//Example 1:
//Input: nums = [4,1,3,3]
//Output: 5
//Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
//The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
//The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
//The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
//The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
//There are a total of 5 bad pairs, so we return 5.

//Example 2:
//Input: nums = [1,2,3,4,5]
//Output: 0
//Explanation: There are no bad pairs.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
