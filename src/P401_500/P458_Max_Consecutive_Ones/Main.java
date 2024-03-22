package P401_500.P458_Max_Consecutive_Ones;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 1, 0, 1, 1, 1};
        System.out.println("Example 1 Output: " + solution.findMaxConsecutiveOnes(nums1)); // Output: 3

        int[] nums2 = {1, 0, 1, 1, 0, 1};
        System.out.println("Example 2 Output: " + solution.findMaxConsecutiveOnes(nums2)); // Output: 2
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                left = right + 1;
            } else {
                maxCount = Math.max(maxCount, right - left + 1);
            }
        }

        return maxCount;
    }

        // and else one work solve to this problem
    //public int findMaxConsecutiveOnes(int[] nums) {
    //        int maxCount = 0;
    //        int count = 0;
    //
    //        for (int num : nums) {
    //            if (num == 1) {
    //                count++;
    //                maxCount = Math.max(maxCount, count);
    //            } else {
    //                count = 0;
    //            }
    //        }
    //
    //        return maxCount;
    //    }
}

//Given a binary array nums, return the maximum number of consecutive 1's in the array.
//Example 1:
//Input: nums = [1,1,0,1,1,1]
//Output: 3
//Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

//Example 2:
//Input: nums = [1,0,1,1,0,1]
//Output: 2
//
//Constraints:
//1 <= nums.length <= 105
//nums[i] is either 0 or 1.
