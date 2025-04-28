package P2301_2400.P2302_Count_Subarrays_With_Score_Less_Than_K;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 1, 4, 3, 5};
        long k1 = 10;
        System.out.println(solution.countSubarrays(nums1, k1)); // Output: 6

        int[] nums2 = {1, 1, 1};
        long k2 = 5;
        System.out.println(solution.countSubarrays(nums2, k2)); // Output: 5
    }

    public long countSubarrays(int[] nums, long k) {
        long result = 0;
        long sum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            //shrink the window if the score is too large
            while (sum * (right - left + 1) >= k) {
                sum -= nums[left];
                left++;
            }

            //number of valid subarrays ending at 'right'
            result += (right - left + 1);
        }

        return result;
    }

}

//Explanation:
//sum keeps the sum of the current window [left, right].
//If sum * length is too big, we move left++.
//Every time we have a valid window, we add (right - left + 1) to the result, because all subarrays ending at
// right and starting from left to right are valid.
//Complexity:
// time - O(n)
// space - O(1)


//The score of an array is defined as the product of its sum and its length.
//For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
//Given a positive integer array nums and an integer k, return the number of non-empty subarrays of nums whose
// score is strictly less than k.
//A subarray is a contiguous sequence of elements within an array.

//Example 1:
//Input: nums = [2,1,4,3,5], k = 10
//Output: 6
//Explanation:
//The 6 subarrays having scores less than 10 are:
//- [2] with score 2 * 1 = 2.
//- [1] with score 1 * 1 = 1.
//- [4] with score 4 * 1 = 4.
//- [3] with score 3 * 1 = 3.
//- [5] with score 5 * 1 = 5.
//- [2,1] with score (2 + 1) * 2 = 6.
//Note that subarrays such as [1,4] and [4,3,5] are not considered because their scores are 10 and 36 respectively,
// while we need scores strictly less than 10.

//Example 2:
//Input: nums = [1,1,1], k = 5
//Output: 5
//Explanation:
//Every subarray except [1,1,1] has a score less than 5.
//[1,1,1] has a score (1 + 1 + 1) * 3 = 9, which is greater than 5.
//Thus, there are 5 subarrays having scores less than 5.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
//1 <= k <= 1015
