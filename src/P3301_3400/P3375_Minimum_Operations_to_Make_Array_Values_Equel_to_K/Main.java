package P3301_3400.P3375_Minimum_Operations_to_Make_Array_Values_Equel_to_K;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minOperations(new int[]{5, 2, 5, 4, 5}, 2)); // Output: 2
        System.out.println(solution.minOperations(new int[]{2, 1, 2}, 2));       // Output: -1
        System.out.println(solution.minOperations(new int[]{9, 7, 5, 3}, 1));     // Output: 4
    }

//    public int minOperations(int[] nums, int k) {
//        for (int num : nums) {
//            if (num < k) return -1; // can't increase numbers, only decrease
//        }
//
//        Set<Integer> seen = new HashSet<>();
//        int operations = 0;
//        int currentMax = getMax(nums);
//
//        while (currentMax > k) {
//            // find next lower value to reduce to
//            int next = getNextLower(nums, currentMax, k);
//
//            //check if all elements greater than next are equal
//            int finalCurrentMax = currentMax;
//            boolean valid = Arrays.stream(nums)
//                    .filter(x -> x > next)
//                    .allMatch(x -> x == finalCurrentMax);
//
//            if (!valid) return -1;
//
//            // perform the operation
//            for (int i = 0; i < nums.length; i++) {
//                if (nums[i] > next) {
//                    nums[i] = next;
//                }
//            }
//
//            operations++;
//            currentMax = getMax(nums);
//        }
//
//        return operations;
//    }
//
//    private int getMax(int[] nums) {
//        int max = nums[0];
//        for (int num : nums) {
//            max = Math.max(max, num);
//        }
//        return max;
//    }
//
//    private int getNextLower(int[] nums, int current, int k) {
//        int next = k;
//        for (int num : nums) {
//            if (num < current && num > next) {
//                next = num;
//            }
//        }
//        return next;
//    }

    // faster solution

    public int minOperations(int[] nums, int k) {
        Set<Integer> numsSet = new HashSet<>();
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            numsSet.add(num);
            if (num < min) min = num;
        }

        if (min < k) return -1;
        return (min > k) ? numsSet.size() : numsSet.size() - 1;
    }

}

//Complexity:
//time - O(n)
//space - O(n)


//You are given an integer array nums and an integer k.
//An integer h is called valid if all values in the array that are strictly greater than h are identical.
//For example, if nums = [10, 8, 10, 8], a valid integer is h = 9 because all nums[i] > 9 are equal to 10, but 5 is
// not a valid integer.
//You are allowed to perform the following operation on nums:
//Select an integer h that is valid for the current values in nums.
//For each index i where nums[i] > h, set nums[i] to h.
//Return the minimum number of operations required to make every element in nums equal to k. If it is
// impossible to make all elements equal to k, return -1.

//Example 1:
//Input: nums = [5,2,5,4,5], k = 2
//Output: 2
//Explanation:
//The operations can be performed in order using valid integers 4 and then 2.

//Example 2:
//Input: nums = [2,1,2], k = 2
//Output: -1
//Explanation:
//It is impossible to make all the values equal to 2.

//Example 3:
//Input: nums = [9,7,5,3], k = 1
//Output: 4
//Explanation:
//The operations can be performed using valid integers in the order 7, 5, 3, and 1.

//Constraints:
//1 <= nums.length <= 100
//1 <= nums[i] <= 100
//1 <= k <= 100
