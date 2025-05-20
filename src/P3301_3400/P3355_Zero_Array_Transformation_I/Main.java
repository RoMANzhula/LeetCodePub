package P3301_3400.P3355_Zero_Array_Transformation_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 0, 1};
        int[][] queries1 = {{0, 2}};
        System.out.println(solution.isZeroArray(nums1, queries1)); // true

        int[] nums2 = {4, 3, 2, 1};
        int[][] queries2 = {{1, 3}, {0, 2}};
        System.out.println(solution.isZeroArray(nums2, queries2)); // false
    }

    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] line = new int[n + 1]; // difference array

        // mark increments and decrements for each query
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            line[l] += 1;
            if (r + 1 < n) {
                line[r + 1] -= 1;
            }
        }

        // accumulate the number of available decrements and compare
        int decrement = 0;
        for (int i = 0; i < n; i++) {
            decrement += line[i];
            if (decrement < nums[i]) {
                return false; // not enough decrements available
            }
        }

        return true;
    }

}

//Complexity:
// time - O(n + q) q-gueries.length
// space - O(n)


//You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri].
//For each queries[i]:
//Select a subset of indices within the range [li, ri] in nums.
//Decrement the values at the selected indices by 1.
//A Zero Array is an array where all elements are equal to 0.
//Return true if it is possible to transform nums into a Zero Array after processing all the queries sequentially,
// otherwise return false.

//Example 1:
//Input: nums = [1,0,1], queries = [[0,2]]
//Output: true
//Explanation:
//For i = 0:
//Select the subset of indices as [0, 2] and decrement the values at these indices by 1.
//The array will become [0, 0, 0], which is a Zero Array.

//Example 2:
//Input: nums = [4,3,2,1], queries = [[1,3],[0,2]]
//Output: false
//Explanation:
//For i = 0:
//Select the subset of indices as [1, 2, 3] and decrement the values at these indices by 1.
//The array will become [4, 2, 1, 0].
//For i = 1:
//Select the subset of indices as [0, 1, 2] and decrement the values at these indices by 1.
//The array will become [3, 1, 0, 0], which is not a Zero Array.

//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 105
//1 <= queries.length <= 105
//queries[i].length == 2
//0 <= li <= ri < nums.length
