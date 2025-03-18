package P1501_1600.P1512_Number_of_Good_Pairs;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        int[] nums1 = {1, 2, 3, 1, 1, 3};
        int[] nums2 = {1, 1, 1, 1};
        int[] nums3 = {1, 2, 3};

        System.out.println(solution.numIdenticalPairs(nums1)); //4
        System.out.println(solution.numIdenticalPairs(nums2)); //6
        System.out.println(solution.numIdenticalPairs(nums3)); //0
    }

    public int numIdenticalPairs(int[] nums) {
        int result = 0;
        Map<Integer, Integer> numCount = new HashMap<>();

        for (int num : nums) {
            if (numCount.containsKey(num)) {
                result += numCount.get(num);
                numCount.put(num, numCount.get(num) + 1);
            } else {
                numCount.put(num, 1);
            }
        }

        return result;
    }

}

//Given an array of integers nums, return the number of good pairs.
//A pair (i, j) is called good if nums[i] == nums[j] and i < j.

//Example 1:
//Input: nums = [1,2,3,1,1,3]
//Output: 4
//Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.

//Example 2:
//Input: nums = [1,1,1,1]
//Output: 6
//Explanation: Each pair in the array are good.

//Example 3:
//Input: nums = [1,2,3]
//Output: 0

//Constraints:
//1 <= nums.length <= 100
//1 <= nums[i] <= 100
