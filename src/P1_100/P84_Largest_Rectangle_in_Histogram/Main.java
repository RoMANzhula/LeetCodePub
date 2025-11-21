package P1_100.P84_Largest_Rectangle_in_Histogram;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] heights1 = {2, 1, 5, 6, 2, 3};
        System.out.println(solution.largestRectangleArea(heights1)); // Output: 10

        int[] heights2 = {2, 4};
        System.out.println(solution.largestRectangleArea(heights2)); // Output: 4
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            int h = (i == n ? 0 : heights[i]);
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

}

//Complexity:
// time and space - O(n)


//Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
// return the area of the largest rectangle in the histogram.

//Example 1:
//Input: heights = [2,1,5,6,2,3]
//Output: 10
//Explanation: The above is a histogram where width of each bar is 1.
//The largest rectangle is shown in the red area, which has an area = 10 units.

//Example 2:
//Input: heights = [2,4]
//Output: 4

//Constraints:
//1 <= heights.length <= 105
//0 <= heights[i] <= 104
