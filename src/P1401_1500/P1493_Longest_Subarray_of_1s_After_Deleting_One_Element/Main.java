package P1401_1500.P1493_Longest_Subarray_of_1s_After_Deleting_One_Element;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 1, 0, 1};
        int[] nums2 = {0, 1, 1, 1, 0, 1, 1, 0, 1};
        int[] nums3 = {1, 1, 1};
        int[] nums4 = {0, 0, 0};  // edge case

        System.out.println(solution.longestSubarray(nums1)); // Output: 3
        System.out.println(solution.longestSubarray(nums2)); // Output: 5
        System.out.println(solution.longestSubarray(nums3)); // Output: 2
        System.out.println(solution.longestSubarray(nums4)); // Output: 0
    }

    public int longestSubarray(int[] nums) {
        int left = 0;   // left pointer of sliding window
        int zeroCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            // if more than 1 zero, shrink window
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // window length minus one element (the deleted one)
            maxLen = Math.max(maxLen, right - left);
        }

        return maxLen;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a binary array nums, you should delete one element from it.
//Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there
// is no such subarray.

//Example 1:
//Input: nums = [1,1,0,1]
//Output: 3
//Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.

//Example 2:
//Input: nums = [0,1,1,1,0,1,1,0,1]
//Output: 5
//Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with
// value of 1's is [1,1,1,1,1].

//Example 3:
//Input: nums = [1,1,1]
//Output: 2
//Explanation: You must delete one element.

//Constraints:
//1 <= nums.length <= 105
//nums[i] is either 0 or 1.
