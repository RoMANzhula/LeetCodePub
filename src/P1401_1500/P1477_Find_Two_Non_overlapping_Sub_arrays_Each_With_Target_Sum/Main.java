package P1401_1500.P1477_Find_Two_Non_overlapping_Sub_arrays_Each_With_Target_Sum;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {3, 2, 2, 4, 3};
        int target1 = 3;

        System.out.println(solution.minSumOfLengths(arr1, target1));

        int[] arr2 = {7, 3, 4, 7};
        int target2 = 7;

        System.out.println(solution.minSumOfLengths(arr2, target2));

        int[] arr3 = {4, 3, 2, 6, 2, 3, 4};
        int target3 = 6;

        System.out.println(solution.minSumOfLengths(arr3, target3));
    }

    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int answer = Integer.MAX_VALUE;

        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);

        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int length = right - left + 1;

                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    answer = Math.min(answer, length + best[left - 1]);
                }

                minLength = Math.min(minLength, length);
            }

            best[right] = minLength;
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

}

//Complexity:
// time and space - O(n)


//You are given an array of integers arr and an integer target.
//You have to find two non-overlapping sub-arrays of arr each with a sum equal target. There can be multiple
// answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
//Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two
// sub-arrays.

//Example 1:
//Input: arr = [3,2,2,4,3], target = 3
//Output: 2
//Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.

//Example 2:
//Input: arr = [7,3,4,7], target = 7
//Output: 2
//Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but we will choose
// the first and third sub-arrays as the sum of their lengths is 2.

//Example 3:
//Input: arr = [4,3,2,6,2,3,4], target = 6
//Output: -1
//Explanation: We have only one sub-array of sum = 6.

//Constraints:
//1 <= arr.length <= 105
//1 <= arr[i] <= 1000
//1 <= target <= 108
