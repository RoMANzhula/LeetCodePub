package P1_100.P56_Merge_Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
        int[][] result1 = solution.merge(intervals1);
        System.out.println(Arrays.deepToString(result1)); // [[1, 6], [8, 10], [15, 18]]

        int[][] intervals2 = {{1,4},{4,5}};
        int[][] result2 = solution.merge(intervals2);
        System.out.println(Arrays.deepToString(result2)); // [[1, 5]]

    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        // sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        // iterate and merge
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if (nextStart <= currentEnd) { // overlap
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else { // no overlap
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an
// array of the non-overlapping intervals that cover all the intervals in the input.

//Example 1:
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

//Example 2:
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.

//Constraints:
//1 <= intervals.length <= 104
//intervals[i].length == 2
//0 <= starti <= endi <= 104
