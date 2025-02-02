package P1701_1800.P1752_Check_if_Array_Is_Sorted_and_Rotated;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println(solution.check(nums1));

        int[] nums2 = {2, 1, 3, 4};
        System.out.println(solution.check(nums2));

        int[] nums3 = {1, 2, 3};
        System.out.println(solution.check(nums3));
    }

    public boolean check(int[] nums) {
        int len = nums.length;
        int count = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] > nums[(i + 1) % len]) count++;

            if (count > 1) return false;
        }

        return true;
    }
}


//Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some
// number of positions (including zero). Otherwise, return false.
//There may be duplicates in the original array.
//Note: An array A rotated by x positions results in an array B of the same length such
// that A[i] == B[(i+x) % A.length], where % is the modulo operation.
//
//Example 1:
//Input: nums = [3,4,5,1,2]
//Output: true
//Explanation: [1,2,3,4,5] is the original sorted array.
//You can rotate the array by x = 3 positions to begin on the the element of value 3: [3,4,5,1,2].

//Example 2:
//Input: nums = [2,1,3,4]
//Output: false
//Explanation: There is no sorted array once rotated that can make nums.

//Example 3:
//Input: nums = [1,2,3]
//Output: true
//Explanation: [1,2,3] is the original sorted array.
//You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.
//
//Constraints:
//1 <= nums.length <= 100
//1 <= nums[i] <= 100