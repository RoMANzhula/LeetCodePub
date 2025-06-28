package P2001_2100.P2099_Find_Subsicuence_of_Length_K_With_the_Largest_Sum;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(Arrays.toString(solution.maxSubsequence(new int[]{2, 1, 3, 3}, 2))); // [3, 3]
        System.out.println(Arrays.toString(solution.maxSubsequence(new int[]{-1, -2, 3, 4}, 3))); // [-1, 3, 4]
        System.out.println(Arrays.toString(solution.maxSubsequence(new int[]{3, 4, 3, 3}, 2))); // [4, 3] or [3, 4]
    }

    public int[] maxSubsequence(int[] nums, int k) {
        // pair each number with its original index
        int[][] indexedNums = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            indexedNums[i][0] = nums[i];
            indexedNums[i][1] = i;
        }

        // sort by value descending
        Arrays.sort(indexedNums, (a, b) -> Integer.compare(b[0], a[0]));

        // take top k elements
        int[][] topK = Arrays.copyOfRange(indexedNums, 0, k);

        // sort top k by original index to maintain order
        Arrays.sort(topK, Comparator.comparingInt(a -> a[1]));

        // extract values
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = topK[i][0];
        }

        return result;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that
// has the largest sum.
//Return any such subsequence as an integer array of length k.
//A subsequence is an array that can be derived from another array by deleting some or no elements without
// changing the order of the remaining elements.

//Example 1:
//Input: nums = [2,1,3,3], k = 2
//Output: [3,3]
//Explanation:
//The subsequence has the largest sum of 3 + 3 = 6.

//Example 2:
//Input: nums = [-1,-2,3,4], k = 3
//Output: [-1,3,4]
//Explanation:
//The subsequence has the largest sum of -1 + 3 + 4 = 6.

//Example 3:
//Input: nums = [3,4,3,3], k = 2
//Output: [3,4]
//Explanation:
//The subsequence has the largest sum of 3 + 4 = 7.
//Another possible subsequence is [4, 3].

//Constraints:
//1 <= nums.length <= 1000
//-105 <= nums[i] <= 105
//1 <= k <= nums.length
