package P2601_2700.P2616_Minimize_the_Maximum_Difference_of_Pairs;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {10, 1, 2, 7, 1, 3};
        System.out.println(solution.minimizeMax(nums1, 2)); // Output: 1

        int[] nums2 = {4, 2, 1, 2};
        System.out.println(solution.minimizeMax(nums2, 1)); // Output: 0
    }

    public int minimizeMax(int[] nums, int p) {
        if (p == 0) return 0;
        Arrays.sort(nums);

        int left = 0, right = nums[nums.length - 1] - nums[0];

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFormPairs(nums, p, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canFormPairs(int[] nums, int p, int maxDiff) {
        int count = 0;
        for (int i = 1; i < nums.length && count < p; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                count++;
                i++; //skip the next index to avoid reusing elements
            }
        }
        return count >= p;
    }

}

//Explanation:
//Sort nums to ensure that close values are adjacent.
//Binary search for the smallest possible maximum difference.
//-left = 0 (smallest possible difference).
//-right = max difference in sorted nums``.
//Greedy check (canFormPairs):
//-Iterate through nums and greedily form pairs with a difference ≤ mid.
//-If we can form at least p pairs, the difference might be minimized further.
//-Otherwise, increase mid.
//Complexity:
//Sorting: O(nlogn)
//Binary Search: O(log M), where M is the range of numbers.
//Pairing (Greedy Check): O(n)
//Total: O(n log n)


//You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the
// maximum difference amongst all the pairs is minimized. Also, ensure no index appears more than once
// amongst the p pairs.
//Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where
// |x| represents the absolute value of x.
//Return the minimum maximum difference among all p pairs. We define the maximum of an empty set to be zero.

//Example 1:
//Input: nums = [10,1,2,7,1,3], p = 2
//Output: 1
//Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the
// indices 2 and 5.
//The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.

//Example 2:
//Input: nums = [4,2,1,2], p = 1
//Output: 0
//Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the
// minimum we can attain.
//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//0 <= p <= (nums.length)/2
