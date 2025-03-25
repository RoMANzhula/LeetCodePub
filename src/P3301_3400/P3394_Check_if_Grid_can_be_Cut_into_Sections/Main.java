package P3301_3400.P3394_Check_if_Grid_can_be_Cut_into_Sections;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] rects1 = {{1, 0, 5, 2}, {0, 2, 2, 4}, {3, 2, 5, 3}, {0, 4, 4, 5}};
        System.out.println(solution.checkValidCuts(5, rects1)); // true

        int[][] rects2 = {{0, 0, 1, 1}, {2, 0, 3, 4}, {0, 2, 2, 3}, {3, 0, 4, 3}};
        System.out.println(solution.checkValidCuts(4, rects2)); // true

        int[][] rects3 = {{0, 2, 2, 4}, {1, 0, 3, 2}, {2, 2, 3, 4}, {3, 0, 4, 2}, {3, 2, 4, 4}};
        System.out.println(solution.checkValidCuts(4, rects3)); // false
    }

    public boolean checkValidCuts(int n, int[][] rectangles) {
        List<int[]> xs = new ArrayList<>();
        List<int[]> ys = new ArrayList<>();

        for (int[] rect : rectangles) {
            int startX = rect[0];
            int startY = rect[1];
            int endX = rect[2];
            int endY = rect[3];
            xs.add(new int[]{startX, endX});
            ys.add(new int[]{startY, endY});
        }

        return Math.max(countMerged(xs), countMerged(ys)) >= 3;
    }

    private int countMerged(List<int[]> intervals) {
        int count = 0;
        int prevEnd = 0;

        intervals.sort(Comparator.comparingInt(a -> a[0]));

        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            if (start < prevEnd) {
                prevEnd = Math.max(prevEnd, end);
            } else {
                prevEnd = end;
                count++;
            }
        }

        return count;
    }

}

//Explanation:
//Extracting Intervals:
//-Store the x-intervals (startX, endX) and y-intervals (startY, endY) from each rectangle.
//Sorting and Merging Intervals:
//-Sort the intervals by their start value.
//-Merge overlapping intervals to count the number of disjoint sections.
//Checking Valid Cuts:
//-If either x-intervals or y-intervals form at least 3 disjoint sections, return true; otherwise, return false.
//Time & Space Complexity:
//Sorting: O(m log m), where m is the number of rectangles.
//Merging: O(m).
//Total Complexity: O(m log m).
//Space Complexity: O(m) (for storing intervals).


//You are given an integer n representing the dimensions of an n x n grid, with the origin at the bottom-left
// corner of the grid. You are also given a 2D array of coordinates rectangles, where rectangles[i] is in
// the form [startx, starty, endx, endy], representing a rectangle on the grid. Each rectangle is defined as follows:
//(startx, starty): The bottom-left corner of the rectangle.
//(endx, endy): The top-right corner of the rectangle.
//Note that the rectangles do not overlap. Your task is to determine if it is possible to make either two
// horizontal or two vertical cuts on the grid such that:
//Each of the three resulting sections formed by the cuts contains at least one rectangle.
//Every rectangle belongs to exactly one section.
//Return true if such cuts can be made; otherwise, return false.

//Example 1:
//Input: n = 5, rectangles = [[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]]
//Output: true
//Explanation:
//The grid is shown in the diagram. We can make horizontal cuts at y = 2 and y = 4. Hence, output is true.

//Example 2:
//Input: n = 4, rectangles = [[0,0,1,1],[2,0,3,4],[0,2,2,3],[3,0,4,3]]
//Output: true
//Explanation:
//We can make vertical cuts at x = 2 and x = 3. Hence, output is true.

//Example 3:
//Input: n = 4, rectangles = [[0,2,2,4],[1,0,3,2],[2,2,3,4],[3,0,4,2],[3,2,4,4]]
//Output: false
//Explanation:
//We cannot make two horizontal or two vertical cuts that satisfy the conditions. Hence, output is false.

//Constraints:
//3 <= n <= 109
//3 <= rectangles.length <= 105
//0 <= rectangles[i][0] < rectangles[i][2] <= n
//0 <= rectangles[i][1] < rectangles[i][3] <= n
//No two rectangles overlap.
