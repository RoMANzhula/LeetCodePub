package P3701_3800.P3702_Longest_Subsequence_With_None_Zero_Bitwise_XOR;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3};
        System.out.println(solution.longestSubsequence(nums1)); // 2

        int[] nums2 = {2, 3, 4};
        System.out.println(solution.longestSubsequence(nums2)); // 3

        int[] nums3 = {0, 0, 0};
        System.out.println(solution.longestSubsequence(nums3)); // 0

        int[] nums4 = {1, 1};
        System.out.println(solution.longestSubsequence(nums4)); // 1
    }

    public int longestSubsequence(int[] nums) {
        int xor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            xor ^= num;

            if (num != 0) {
                hasNonZero = true;
            }
        }

        if (xor != 0) {
            return nums.length;
        }

        return hasNonZero ? nums.length - 1 : 0;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given an integer array nums.
//Return the length of the longest subsequence in nums whose bitwise XOR is non-zero. If no such subsequence
// exists, return 0.

//Example 1:
//Input: nums = [1,2,3]
//Output: 2
//Explanation:
//One longest subsequence is [2, 3]. The bitwise XOR is computed as 2 XOR 3 = 1, which is non-zero.

//Example 2:
//Input: nums = [2,3,4]
//Output: 3
//Explanation:
//The longest subsequence is [2, 3, 4]. The bitwise XOR is computed as 2 XOR 3 XOR 4 = 5, which is non-zero.

//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
