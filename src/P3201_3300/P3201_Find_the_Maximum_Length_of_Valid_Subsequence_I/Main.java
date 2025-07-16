package P3201_3300.P3201_Find_the_Maximum_Length_of_Valid_Subsequence_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = new int[]{1, 2, 3, 4};
        int[] nums2 = new int[]{1, 2, 1, 1, 2, 1, 2};
        int[] nums3 = new int[]{1, 3};

        System.out.println(solution.maximumLength(nums1));       // 4
        System.out.println(solution.maximumLength(nums2));       // 6
        System.out.println(solution.maximumLength(nums3));       // 2
    }

    public int maximumLength(int[] nums) {
        int[][] dp = new int[2][2]; // dp[i][j] = max length ending with i, expecting j

        for (int x : nums) {
            int xParity = x % 2;
            for (int y = 0; y < 2; y++) {
                dp[xParity][y] = dp[y][xParity] + 1;
            }
        }

        int max = 0;

        for (int[] row : dp) {
            for (int val : row) {
                max = Math.max(max, val);
            }
        }

        return max;
    }

}

//Complexity::
// time - O(n)
// space - O(1)



//You are given an integer array nums.
//A subsequence sub of nums with length x is called valid if it satisfies:
//(sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
//Return the length of the longest valid subsequence of nums.
//A subsequence is an array that can be derived from another array by deleting some or no elements without
// changing the order of the remaining elements.

//Example 1:
//Input: nums = [1,2,3,4]
//Output: 4
//Explanation:
//The longest valid subsequence is [1, 2, 3, 4].

//Example 2:
//Input: nums = [1,2,1,1,2,1,2]
//Output: 6
//Explanation:
//The longest valid subsequence is [1, 2, 1, 2, 1, 2].

//Example 3:
//Input: nums = [1,3]
//Output: 2
//Explanation:
//The longest valid subsequence is [1, 3].

//Constraints:
//2 <= nums.length <= 2 * 105
//1 <= nums[i] <= 107
