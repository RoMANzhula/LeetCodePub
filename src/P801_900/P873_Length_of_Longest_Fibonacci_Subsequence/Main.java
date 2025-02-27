package P801_900.P873_Length_of_Longest_Fibonacci_Subsequence;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(solution.lenLongestFibSubseq(arr1)); // Output: 5

        int[] arr2 = {1, 3, 7, 11, 12, 14, 18};
        System.out.println(solution.lenLongestFibSubseq(arr2)); // Output: 3
    }

    public int lenLongestFibSubseq(int[] arr) {
        int len = arr.length;
        Map<Integer, Integer> indexMap = new HashMap<>();
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;

        //store indices of elements in arr for quick lookup
        for (int i = 0; i < len; i++) {
            indexMap.put(arr[i], i);
        }

        //iterate over pairs (i, j) and try to extend a Fibonacci-like sequence
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int a = arr[j], b = arr[i];
                int prevIndex = indexMap.getOrDefault(a + b, -1);

                if (prevIndex != -1) {
                    int key = j * len + i;
                    int prevKey = i * len + prevIndex;
                    dp.put(prevKey, dp.getOrDefault(key, 2) + 1);
                    maxLen = Math.max(maxLen, dp.get(prevKey));
                }
            }
        }

        return maxLen >= 3 ? maxLen : 0; // if no valid subsequence found, return 0
    }

}

//Explanation of the Code:
//Build indexMap → A HashMap stores indices of elements for quick lookups.
//Iterate through pairs (i, j) → Check if arr[i] + arr[j] exists in arr.
//Use DP table → Store the length of valid Fibonacci-like subsequences.
//Return the maximum length found in the DP table.
//Time Complexity:
//O(n²) due to the double loop to check pairs.
//O(1) average lookup in HashMap.
//Space Complexity:
//O(n²) for the DP table.


//A sequence x1, x2, ..., xn is Fibonacci-like if:
//n >= 3
//xi + xi+1 == xi+2 for all i + 2 <= n
//Given a strictly increasing array arr of positive integers forming a sequence, return the length of the longest
// Fibonacci-like subsequence of arr. If one does not exist, return 0.
//A subsequence is derived from another sequence arr by deleting any number of elements (including none) from arr,
// without changing the order of the remaining elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].
//
//Example 1:
//Input: arr = [1,2,3,4,5,6,7,8]
//Output: 5
//Explanation: The longest subsequence that is fibonacci-like: [1,2,3,5,8].

//Example 2:
//Input: arr = [1,3,7,11,12,14,18]
//Output: 3
//Explanation: The longest subsequence that is fibonacci-like: [1,11,12], [3,11,14] or [7,11,18].
//
//Constraints:
//3 <= arr.length <= 1000
//1 <= arr[i] < arr[i + 1] <= 109
