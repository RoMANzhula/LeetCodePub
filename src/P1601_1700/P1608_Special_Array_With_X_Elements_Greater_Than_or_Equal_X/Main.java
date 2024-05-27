package P1601_1700.P1608_Special_Array_With_X_Elements_Greater_Than_or_Equal_X;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 5};
        System.out.println(solution.specialArray(nums1));  // Output: 2

        int[] nums2 = {0, 0};
        System.out.println(solution.specialArray(nums2));  // Output: -1

        int[] nums3 = {0, 4, 3, 0, 4};
        System.out.println(solution.specialArray(nums3));  // Output: 3
    }

//    public int specialArray(int[] nums) {
//        Arrays.sort(nums);
//        int n = nums.length;
//
//        // Check for each possible value of x
//        for (int x = 0; x <= n; x++) {
//            int count = 0;
//            for (int num : nums) {
//                if (num >= x) {
//                    count++;
//                }
//            }
//            if (count == x) {
//                return x;
//            }
//        }
//
//        return -1;
//    }

    //some faster solution
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int x = 1; x <= n; x++) {
            if (nums[n - x] >= x && (n - x == 0 || nums[n - x - 1] < x)) {
                return x;
            }
        }

        return -1;
    }
}


//You are given an array nums of non-negative integers. nums is considered special if there exists a number x such
// that there are exactly x numbers in nums that are greater than or equal to x.
//Notice that x does not have to be an element in nums.
//Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the
// value for x is unique.
//
//Example 1:
//Input: nums = [3,5]
//Output: 2
//Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.

//Example 2:
//Input: nums = [0,0]
//Output: -1
//Explanation: No numbers fit the criteria for x.
//If x = 0, there should be 0 numbers >= x, but there are 2.
//If x = 1, there should be 1 number >= x, but there are 0.
//If x = 2, there should be 2 numbers >= x, but there are 0.
//x cannot be greater since there are only 2 numbers in nums.

//Example 3:
//Input: nums = [0,4,3,0,4]
//Output: 3
//Explanation: There are 3 values that are greater than or equal to 3.
//
//Constraints:
//1 <= nums.length <= 100
//0 <= nums[i] <= 1000