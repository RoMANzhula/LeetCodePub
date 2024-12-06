package P2501_2600.P2554_Maximum_Number_of_Integers_to_Choose_From_a_Range_I;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxCount(new int[]{1, 6, 5}, 5, 6)); // Output: 2
        System.out.println(solution.maxCount(new int[]{1, 2, 3, 4, 5, 6, 7}, 8, 1)); // Output: 0
        System.out.println(solution.maxCount(new int[]{11}, 7, 50)); // Output: 7
    }

    public int maxCount(int[] banned, int n, int maxSum) {
        // Step 1: Store banned numbers in a HashSet for quick lookup
        Set<Integer> bannedSet = new HashSet<>();
        for (int num : banned) {
            bannedSet.add(num);
        }

        // Step 2: Initialize variables for count and cumulative sum
        int count = 0;
        int currentSum = 0;

        // Step 3: Iterate through numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            // Check if the number is banned or adding it exceeds maxSum
            if (bannedSet.contains(i) || currentSum + i > maxSum) {
                continue;
            }

            // Add the number to the cumulative sum and increment the count
            currentSum += i;
            count++;
        }

        // Step 4: Return the count of chosen numbers
        return count;
    }
}

//Explanation
//Input Handling: Use a HashSet to quickly check if a number is banned.
//Iteration: Loop through integers from 1 to n. Skip banned numbers or numbers that would make the sum exceed maxSum.
//Validation: Increment the count and update the cumulative sum for valid numbers.
//Output: Return the count of chosen integers.
//Complexity
//Time Complexity:
//O(n+b), where b is the length of the banned array and n is the range of numbers.
//Space Complexity:
//O(b), for the HashSet to store banned numbers.


//You are given an integer array banned and two integers n and maxSum. You are choosing some number of
// integers following the below rules:
//The chosen integers have to be in the range [1, n].
//Each integer can be chosen at most once.
//The chosen integers should not be in the array banned.
//The sum of the chosen integers should not exceed maxSum.
//Return the maximum number of integers you can choose following the mentioned rules.
//
//Example 1:
//Input: banned = [1,6,5], n = 5, maxSum = 6
//Output: 2
//Explanation: You can choose the integers 2 and 4.
//2 and 4 are from the range [1, 5], both did not appear in banned, and their sum is 6, which did not exceed maxSum.

//Example 2:
//Input: banned = [1,2,3,4,5,6,7], n = 8, maxSum = 1
//Output: 0
//Explanation: You cannot choose any integer while following the mentioned conditions.

//Example 3:
//Input: banned = [11], n = 7, maxSum = 50
//Output: 7
//Explanation: You can choose the integers 1, 2, 3, 4, 5, 6, and 7.
//They are from the range [1, 7], all did not appear in banned, and their sum is 28, which did not exceed maxSum.
//
//Constraints:
//1 <= banned.length <= 104
//1 <= banned[i], n <= 104
//1 <= maxSum <= 109
