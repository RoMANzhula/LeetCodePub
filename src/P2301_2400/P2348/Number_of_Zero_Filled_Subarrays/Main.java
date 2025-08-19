package P2301_2400.P2348.Number_of_Zero_Filled_Subarrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1,3,0,0,2,0,0,4};
        int[] nums2 = {0,0,0,2,0,0};
        int[] nums3 = {2,10,2019};

        System.out.println(solution.zeroFilledSubarray(nums1)); // 6
        System.out.println(solution.zeroFilledSubarray(nums2)); // 9
        System.out.println(solution.zeroFilledSubarray(nums3)); // 0
    }

    public long zeroFilledSubarray(int[] nums) {
        long result = 0;
        long count = 0; // counts consecutive zeros

        for (int num : nums) {
            if (num == 0) {
                count++;
                result += count; // add subarrays ending here
            } else {
                count = 0; // reset when non-zero
            }
        }

        return result;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given an integer array nums, return the number of subarrays filled with 0.
//A subarray is a contiguous non-empty sequence of elements within an array.

//Example 1:
//Input: nums = [1,3,0,0,2,0,0,4]
//Output: 6
//Explanation:
//There are 4 occurrences of [0] as a subarray.
//There are 2 occurrences of [0,0] as a subarray.
//There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.

//Example 2:
//Input: nums = [0,0,0,2,0,0]
//Output: 9
//Explanation:
//There are 5 occurrences of [0] as a subarray.
//There are 3 occurrences of [0,0] as a subarray.
//There is 1 occurrence of [0,0,0] as a subarray.
//There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.

//Example 3:
//Input: nums = [2,10,2019]
//Output: 0
//Explanation: There is no subarray filled with 0. Therefore, we return 0.

//Constraints:
//1 <= nums.length <= 105
//-109 <= nums[i] <= 109
