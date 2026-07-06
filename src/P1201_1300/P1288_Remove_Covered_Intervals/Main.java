package P1201_1300.P1288_Remove_Covered_Intervals;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] intervals1 = {
                {1, 4},
                {3, 6},
                {2, 8}
        };

        System.out.println(solution.removeCoveredIntervals(intervals1)); // 2

        int[][] intervals2 = {
                {1, 4},
                {2, 3}
        };

        System.out.println(solution.removeCoveredIntervals(intervals2)); // 1

        int[][] intervals3 = {
                {1, 5},
                {1, 3},
                {2, 4}
        };

        System.out.println(solution.removeCoveredIntervals(intervals3)); // 1
    }

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];   // descending end
            }

            return a[0] - b[0];       // ascending start
        });

        int remaining = 0;
        int maxRight = -1;

        for (int[] interval : intervals) {
            if (interval[1] > maxRight) {
                remaining++;
                maxRight = interval[1];
            }
        }

        return remaining;
    }

}

//Complexity:
// time - O(n log n)
// space - O(1)


//Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that
// are covered by another interval in the list.
//The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
//Return the number of remaining intervals.

//Example 1:
//Input: intervals = [[1,4],[3,6],[2,8]]
//Output: 2
//Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.

//Example 2:
//Input: intervals = [[1,4],[2,3]]
//Output: 1

//Constraints:
//1 <= intervals.length <= 1000
//intervals[i].length == 2
//0 <= li < ri <= 105
//All the given intervals are unique.
