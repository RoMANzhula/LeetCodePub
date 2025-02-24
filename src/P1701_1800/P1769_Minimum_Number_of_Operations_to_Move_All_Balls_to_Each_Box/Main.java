package P1701_1800.P1769_Minimum_Number_of_Operations_to_Move_All_Balls_to_Each_Box;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String boxes1 = "110";
        System.out.println(Arrays.toString(solution.minOperations(boxes1))); // Output: [1, 1, 3]

        String boxes2 = "001011";
        System.out.println(Arrays.toString(solution.minOperations(boxes2))); // Output: [11, 8, 5, 4, 3, 4]
    }

    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] answer = new int[n];

        // Calculate from left to right
        int count = 0; // Number of balls encountered so far
        int operations = 0; // Total operations for the current pass
        for (int i = 0; i < n; i++) {
            answer[i] += operations;
            count += boxes.charAt(i) == '1' ? 1 : 0;
            operations += count;
        }

        // Calculate from right to left
        count = 0; // Reset ball counter
        operations = 0; // Reset operation counter
        for (int i = n - 1; i >= 0; i--) {
            answer[i] += operations;
            count += boxes.charAt(i) == '1' ? 1 : 0;
            operations += count;
        }

        return answer;
    }
}


//You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box
// is empty, and '1' if it contains one ball.
//In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box
// j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.
//Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all
// the balls to the ith box.
//Each answer[i] is calculated considering the initial state of the boxes.
//
//Example 1:
//Input: boxes = "110"
//Output: [1,1,3]
//Explanation: The answer for each box is as follows:
//1) First box: you will have to move one ball from the second box to the first box in one operation.
//2) Second box: you will have to move one ball from the first box to the second box in one operation.
//3) Third box: you will have to move one ball from the first box to the third box in two operations, and
// move one ball from the second box to the third box in one operation.

//Example 2:
//Input: boxes = "001011"
//Output: [11,8,5,4,3,4]
//
//Constraints:
//n == boxes.length
//1 <= n <= 2000
//boxes[i] is either '0' or '1'.
