package P801_900.P823_Binary_Trees_With_Factors;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr1 = {2, 4};
        System.out.println(numFactoredBinaryTrees(arr1)); // Output: 3

        int[] arr2 = {2, 4, 5, 10};
        System.out.println(numFactoredBinaryTrees(arr2)); // Output: 7
    }

    public static int numFactoredBinaryTrees(int[] arr) {
        int MOD = 1000000007;
        int n = arr.length;

        //sort the array in ascending order.
        Arrays.sort(arr);

        //initialize a map to store the number of binary trees for each element.
        long[] dp = new long[n];
        Arrays.fill(dp, 1);

        //create a map to quickly look up the index of each element.
        long result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int right = arr[i] / arr[j];
                    int rightIndex = Arrays.binarySearch(arr, right);
                    if (rightIndex >= 0) {
                        dp[i] = (dp[i] + dp[j] * dp[rightIndex]) % MOD;
                    }
                }
            }
            result = (result + dp[i]) % MOD; //check big answer
        }

        return (int) result; //bingo
    }
}

//Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
//We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf
// node's value should be equal to the product of the values of its children.
//Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.

//Example 1:
//Input: arr = [2,4]
//Output: 3
//Explanation: We can make these trees: [2], [4], [4, 2, 2]

//Example 2:
//Input: arr = [2,4,5,10]
//Output: 7
//Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].

//Constraints:
//1 <= arr.length <= 1000
//2 <= arr[i] <= 109
//All the values of arr are unique.
