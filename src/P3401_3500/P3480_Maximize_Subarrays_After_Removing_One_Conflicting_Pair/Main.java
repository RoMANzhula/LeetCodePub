package P3401_3500.P3480_Maximize_Subarrays_After_Removing_One_Conflicting_Pair;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] pairs1 = {{2, 3}, {1, 4}};
        System.out.println(solution.maxSubarrays(4, pairs1)); // Output: 9

        int[][] pairs2 = {{1, 2}, {2, 5}, {3, 5}};
        System.out.println(solution.maxSubarrays(5, pairs2)); // Output: 12
    }

    public long maxSubarrays(int n, int[][] conflictingPairs) {
        long validSubarrays = 0;
        int maxLeft = 0;
        int secondMaxLeft = 0;

        long[] gains = new long[n + 1]; // gains for removing conflict at position i
        List<List<Integer>> conflicts = new ArrayList<>();

        // initialize the list for each index
        for (int i = 0; i <= n; i++) {
            conflicts.add(new ArrayList<>());
        }

        // build the conflict list
        for (int[] pair : conflictingPairs) {
            int a = pair[0];
            int b = pair[1];
            conflicts.get(Math.max(a, b)).add(Math.min(a, b));
        }

        // process each index from 1 to n (like right pointer)
        for (int right = 1; right <= n; right++) {
            for (int left : conflicts.get(right)) {
                if (left > maxLeft) {
                    secondMaxLeft = maxLeft;
                    maxLeft = left;
                } else if (left > secondMaxLeft) {
                    secondMaxLeft = left;
                }
            }

            // add subarrays ending at `right` that start after `maxLeft`
            validSubarrays += right - maxLeft;

            // potential gain if we removed the most restrictive left
            gains[maxLeft] += maxLeft - secondMaxLeft;
        }

        // find max gain
        long maxGain = 0;

        for (long g : gains) {
            if (g > maxGain) maxGain = g;
        }

        return validSubarrays + maxGain;
    }

}


//You are given an integer n which represents an array nums containing the numbers from 1 to n in order. Additionally,
// you are given a 2D array conflictingPairs, where conflictingPairs[i] = [a, b] indicates that a and b form a
// conflicting pair.
//Remove exactly one element from conflictingPairs. Afterward, count the number of non-empty subarrays of nums which
// do not contain both a and b for any remaining conflicting pair [a, b].
//Return the maximum number of subarrays possible after removing exactly one conflicting pair.

//Example 1:
//Input: n = 4, conflictingPairs = [[2,3],[1,4]]
//Output: 9
//Explanation:
//Remove [2, 3] from conflictingPairs. Now, conflictingPairs = [[1, 4]].
//There are 9 subarrays in nums where [1, 4] do not appear together. They
// are [1], [2], [3], [4], [1, 2], [2, 3], [3, 4], [1, 2, 3] and [2, 3, 4].
//The maximum number of subarrays we can achieve after removing one element from conflictingPairs is 9.

//Example 2:
//Input: n = 5, conflictingPairs = [[1,2],[2,5],[3,5]]
//Output: 12
//Explanation:
//Remove [1, 2] from conflictingPairs. Now, conflictingPairs = [[2, 5], [3, 5]].
//There are 12 subarrays in nums where [2, 5] and [3, 5] do not appear together.
//The maximum number of subarrays we can achieve after removing one element from conflictingPairs is 12.

//Constraints:
//2 <= n <= 105
//1 <= conflictingPairs.length <= 2 * n
//conflictingPairs[i].length == 2
//1 <= conflictingPairs[i][j] <= n
//conflictingPairs[i][0] != conflictingPairs[i][1]
