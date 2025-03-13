package P3301_3400.P3356_Zero_Array_Transformation_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 0, 2};
        int[][] queries1 = {{0, 2, 1}, {0, 2, 1}, {1, 1, 3}};
        System.out.println(solution.minZeroArray(nums1, queries1)); // Output: 2

        int[] nums2 = {4, 3, 2, 1};
        int[][] queries2 = {{1, 3, 2}, {0, 2, 1}};
        System.out.println(solution.minZeroArray(nums2, queries2)); // Output: -1
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int left = 0, right = queries.length, answer = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 2);
            if (canZeroArray(nums.clone(), queries, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private static boolean canZeroArray(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        int[] diff = new int[n + 1]; // difference array to optimize range updates

        for (int i = 0; i < k; i++) {
            int l = queries[i][0], r = queries[i][1], val = queries[i][2];
            diff[l] -= val;
            diff[r + 1] += val;
        }

        int current = 0;
        for (int i = 0; i < n; i++) {
            current += diff[i];
            if (nums[i] + current > 0) {
                return false;
            }
        }

        return true;
    }

}

//Explanation:
//Binary Search on k:
//We perform a binary search on the number of queries to determine the minimum k that turns nums into a zero array.
//Difference Array for Efficient Range Updates:
//Instead of modifying nums directly for each query (which would be too slow), we use a difference array to apply
// range updates in O(1) time.
//Validation Function (canZeroArray):
//Applies the first k queries using the difference array.
//Checks if nums becomes a zero array after applying the decrements.
//Complexity:
//Binary Search: O(log M), where M = queries.length
//Applying Queries: O(N + K), where N = nums.length, K = queries processed
//Total Complexity: O(N log M), which is efficient for large inputs.


//ou are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].
//Each queries[i] represents the following action on nums:
//Decrement the value at each index in the range [li, ri] in nums by at most vali.
//The amount by which each value is decremented can be chosen independently for each index.
//A Zero Array is an array with all its elements equal to 0.
//Return the minimum possible non-negative value of k, such that after processing the first k queries in sequence,
// nums becomes a Zero Array. If no such k exists, return -1.
//Example 1:
//Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]
//Output: 2
//Explanation:
//For i = 0 (l = 0, r = 2, val = 1):
//Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
//The array will become [1, 0, 1].
//For i = 1 (l = 0, r = 2, val = 1):
//Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
//The array will become [0, 0, 0], which is a Zero Array. Therefore, the minimum value of k is 2.

//Example 2:
//Input: nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]
//Output: -1
//Explanation:
//For i = 0 (l = 1, r = 3, val = 2):
//Decrement values at indices [1, 2, 3] by [2, 2, 1] respectively.
//The array will become [4, 1, 0, 0].
//For i = 1 (l = 0, r = 2, val = 1):
//Decrement values at indices [0, 1, 2] by [1, 1, 0] respectively.
//The array will become [3, 0, 0, 0], which is not a Zero Array.

//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 5 * 105
//1 <= queries.length <= 105
//queries[i].length == 3
//0 <= li <= ri < nums.length
//1 <= vali <= 5