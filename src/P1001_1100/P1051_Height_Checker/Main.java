package P1001_1100.P1051_Height_Checker;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] heights1 = {1, 1, 4, 2, 1, 3};
        System.out.println(solution.heightChecker(heights1));  // Output: 3

        int[] heights2 = {5, 1, 2, 3, 4};
        System.out.println(solution.heightChecker(heights2));  // Output: 5

        int[] heights3 = {1, 2, 3, 4, 5};
        System.out.println(solution.heightChecker(heights3));  // Output: 0
    }

    public int heightChecker(int[] heights) {
        // Create a copy of the heights array and sort it to get the expected array
        int[] expected = heights.clone();
        Arrays.sort(expected);

        // Initialize a counter to count the number of indices where heights[i] != expected[i]
        int count = 0;

        // Compare each element in heights with the corresponding element in expected
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expected[i]) {
                count++;
            }
        }

        // Return the count of mismatched indices
        return count;
    }
}

//A school is trying to take an annual photo of all the students. The students are asked to stand in a single
// file line in non-decreasing order by height. Let this ordering be represented by the integer array expected
// where expected[i] is the expected height of the ith student in line.
//You are given an integer array heights representing the current order that the students are standing in. Each
// heights[i] is the height of the ith student in line (0-indexed).
//Return the number of indices where heights[i] != expected[i].
//
//Example 1:
//Input: heights = [1,1,4,2,1,3]
//Output: 3
//Explanation:
//heights:  [1,1,4,2,1,3]
//expected: [1,1,1,2,3,4]
//Indices 2, 4, and 5 do not match.

//Example 2:
//Input: heights = [5,1,2,3,4]
//Output: 5
//Explanation:
//heights:  [5,1,2,3,4]
//expected: [1,2,3,4,5]
//All indices do not match.

//Example 3:
//Input: heights = [1,2,3,4,5]
//Output: 0
//Explanation:
//heights:  [1,2,3,4,5]
//expected: [1,2,3,4,5]
//All indices match.
//
//Constraints:
//1 <= heights.length <= 100
//1 <= heights[i] <= 100
