package P1501_1600.P1524_Number_of_Sub_arrays_With_Odd_Sum;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {1, 3, 5};
        System.out.println(solution.numOfSubarrays(arr1)); // Output: 4

        int[] arr2 = {2, 4, 6};
        System.out.println(solution.numOfSubarrays(arr2)); // Output: 0

        int[] arr3 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(solution.numOfSubarrays(arr3)); // Output: 16
    }

    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int odd_count = 0, even_count = 1;
        int sum = 0, result = 0;

        for (int num : arr) {
            sum += num;

            if (sum % 2 == 0) {
                result = (result + odd_count) % MOD;
                even_count++;
            } else {
                result = (result + even_count) % MOD;
                odd_count++;
            }
        }

        return result;
    }

}

//Complexity:
//Time Complexity: O(N), since we traverse the array once.
//Space Complexity: O(1), as we use only a few integer variables.


//Given an array of integers arr, return the number of subarrays with an odd sum.
//Since the answer can be very large, return it modulo 109 + 7.

//Example 1:
//Input: arr = [1,3,5]
//Output: 4
//Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
//All sub-arrays sum are [1,4,9,3,8,5].
//Odd sums are [1,9,3,5] so the answer is 4.

//Example 2:
//Input: arr = [2,4,6]
//Output: 0
//Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
//All sub-arrays sum are [2,6,12,4,10,6].
//All sub-arrays have even sum and the answer is 0.

//Example 3:
//Input: arr = [1,2,3,4,5,6,7]
//Output: 16
//
//Constraints:
//1 <= arr.length <= 105
//1 <= arr[i] <= 100
