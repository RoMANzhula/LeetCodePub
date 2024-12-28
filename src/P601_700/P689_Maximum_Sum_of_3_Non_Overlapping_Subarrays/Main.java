package P601_700.P689_Maximum_Sum_of_3_Non_Overlapping_Subarrays;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 1, 2, 6, 7, 5, 1};
        int k1 = 2;
        System.out.println(Arrays.toString(solution.maxSumOfThreeSubarrays(nums1, k1))); // Output: [0, 3, 5]

        int[] nums2 = {1, 2, 1, 2, 1, 2, 1, 2, 1};
        int k2 = 2;
        System.out.println(Arrays.toString(solution.maxSumOfThreeSubarrays(nums2, k2))); // Output: [0, 2, 4]
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n - k + 1];

        // step 1: Calculate sums of all subarrays of length k
        int windowSum = 0;
        for (int i = 0; i < n; i++) {
            windowSum += nums[i];
            if (i >= k - 1) {
                sum[i - k + 1] = windowSum;
                windowSum -= nums[i - k + 1];
            }
        }

        // Step 2: Calculate left max indices
        int[] left = new int[sum.length];
        int maxIndex = 0;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] > sum[maxIndex]) {
                maxIndex = i;
            }
            left[i] = maxIndex;
        }

        // step 3: calculate right max indices
        int[] right = new int[sum.length];
        maxIndex = sum.length - 1;
        for (int i = sum.length - 1; i >= 0; i--) {
            if (sum[i] >= sum[maxIndex]) {
                maxIndex = i;
            }
            right[i] = maxIndex;
        }

        // step 4: find maximum sum by iterating middle subarray
        int[] result = new int[3];
        int maxSum = 0;
        for (int mid = k; mid < sum.length - k; mid++) {
            int leftIndex = left[mid - k];
            int rightIndex = right[mid + k];
            int totalSum = sum[leftIndex] + sum[mid] + sum[rightIndex];

            if (totalSum > maxSum) {
                maxSum = totalSum;
                result[0] = leftIndex;
                result[1] = mid;
                result[2] = rightIndex;
            }
        }

        return result;
    }
}

//Explanation:
//Sliding Window: Compute the sum of each subarray of size k and store it in the sum array.
//Left Array: For each position, track the index of the maximum subarray on the left.
//Right Array: Similarly, track the index of the maximum subarray on the right.
//Middle Subarray Iteration: For each valid middle subarray, compute the total sum of the three
// subarrays and update the result if a larger sum is found.
//This approach ensures O(n) time complexity for computing the result, making it efficient for large inputs.


//Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with
// maximum sum and return them.
//Return the result as a list of indices representing the starting position of each interval (0-indexed). If
// there are multiple answers, return the lexicographically smallest one.
//
//Example 1:
//Input: nums = [1,2,1,2,6,7,5,1], k = 2
//Output: [0,3,5]
//Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
//We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.

//Example 2:
//Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
//Output: [0,2,4]
//
//Constraints:
//1 <= nums.length <= 2 * 104
//1 <= nums[i] < 216
//1 <= k <= floor(nums.length / 3)
