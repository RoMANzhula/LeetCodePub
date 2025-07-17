package P3201_3300.P3202_Find_the_Maximum_Length_of_Valid_Subsequence_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 4, 5};
        int k1 = 2;
        System.out.println("Example 1 Output: " + solution.maximumLength(nums1, k1)); // 5

        int[] nums2 = {1, 4, 2, 3, 1, 4};
        int k2 = 3;
        System.out.println("Example 2 Output: " + solution.maximumLength(nums2, k2)); // 4
    }

    public int maximumLength(int[] nums, int k) {
        int[][] dp = new int[k][k];

        for (int x : nums) {
            int modX = x % k;
            int[] newDpCol = new int[k];

            for (int y = 0; y < k; y++) {
                newDpCol[y] = dp[y][modX] + 1;
            }

            for (int y = 0; y < k; y++) {
                dp[modX][y] = Math.max(dp[modX][y], newDpCol[y]);
            }
        }

        int maxLen = 0;

        for (int[] row : dp) {
            for (int val : row) {
                maxLen = Math.max(maxLen, val);
            }
        }

        return maxLen;
    }

}

//Complexity:
// time - O(n * k)
// space - O(k^2)


//You are given an integer array nums and a positive integer k.
//A subsequence sub of nums with length x is called valid if it satisfies:
//(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
//Return the length of the longest valid subsequence of nums.

//Example 1:
//Input: nums = [1,2,3,4,5], k = 2
//Output: 5
//Explanation:
//The longest valid subsequence is [1, 2, 3, 4, 5].

//Example 2:
//Input: nums = [1,4,2,3,1,4], k = 3
//Output: 4
//Explanation:
//The longest valid subsequence is [1, 4, 1, 4].

//Constraints:
//2 <= nums.length <= 103
//1 <= nums[i] <= 107
//1 <= k <= 103
