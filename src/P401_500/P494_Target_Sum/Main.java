package P401_500.P494_Target_Sum;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println("Example 1 Output: " + solution.findTargetSumWays(nums1, target1)); // Output: 5

        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("Example 2 Output: " + solution.findTargetSumWays(nums2, target2)); // Output: 1
    }

    public int findTargetSumWays(int[] nums, int target) {
        return findWays(nums, 0, target, new HashMap<>());
    }

    private int findWays(int[] nums, int index, int target, Map<String, Integer> memo) {
        if (index == nums.length) {
            return target == 0 ? 1 : 0;
        }

        String key = index + "," + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Choose '+' or '-'
        int add = findWays(nums, index + 1, target - nums[index], memo);
        int subtract = findWays(nums, index + 1, target + nums[index], memo);

        memo.put(key, add + subtract);
        return memo.get(key);
    }

}

//Explanation:
//Base Case:
//-If the index is equal to the length of nums, check if the target is zero. If yes, return 1; otherwise, return 0.
//Memoization:
//-To optimize, use a Map<String, Integer> where the key is a combination of index and target. This avoids
// recomputing the same state multiple times.
//Recursive Cases:
//-For each number in nums, you can either add or subtract it from the target. Recursively calculate the
// result for both cases.
//Combine Results:
//-Sum the results of the add and subtract branches and store them in the memoization map.
//Complexity:
//Time Complexity:
//O(n⋅sum), where n is the length of nums and sum is the sum of all elements.
//Space Complexity:
//O(n⋅sum) due to the memoization map and recursion stack.


//You are given an integer array nums and an integer target.
//You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in
// nums and then concatenate all the integers.
//For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to
// build the expression "+2-1".
//Return the number of different expressions that you can build, which evaluates to target.
//
//Example 1:
//Input: nums = [1,1,1,1,1], target = 3
//Output: 5
//Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3

//Example 2:
//Input: nums = [1], target = 1
//Output: 1
//
//Constraints:
//1 <= nums.length <= 20
//0 <= nums[i] <= 1000
//0 <= sum(nums[i]) <= 1000
//-1000 <= target <= 1000
