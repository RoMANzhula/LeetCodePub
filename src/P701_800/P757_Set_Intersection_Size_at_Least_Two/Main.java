package P701_800.P757_Set_Intersection_Size_at_Least_Two;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] intervals1 = {{1,3}, {3,7}, {8,9}};
        System.out.println(solution.intersectionSizeTwo(intervals1)); // Output: 5

        int[][] intervals2 = {{1,3}, {1,4}, {2,5}, {3,5}};
        System.out.println(solution.intersectionSizeTwo(intervals2)); // Output: 3

        int[][] intervals3 = {{1,2}, {2,3}, {2,4}, {4,5}};
        System.out.println(solution.intersectionSizeTwo(intervals3)); // Output: 5
    }

    public int intersectionSizeTwo(int[][] intervals) {
        // sort by end asc; if end equal, sort start desc
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);

            return Integer.compare(b[0], a[0]); // start descending when ends equal
        });

        int count = 0;
        int p1 = -1; // smaller selected point
        int p2 = -1; // larger selected point

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            boolean inP1 = (p1 >= start && p1 <= end);
            boolean inP2 = (p2 >= start && p2 <= end);

            if (inP1 && inP2) {
                // already >= 2 points in this interval
                continue;
            } else if (inP2) {
                // exactly one point (p2) is in interval -> add one new point (end)
                // shift p1 to previous p2, and set p2 = end
                p1 = p2;
                p2 = end;
                count += 1;
            } else {
                // zero points in interval -> add two points: end-1 and end
                p1 = end - 1;
                p2 = end;
                count += 2;
            }
        }

        return count;
    }

}

//Complexity:
// time - O(n log n)
// space - O(1)


//You are given a 2D integer array intervals where intervals[i] = [starti, endi] represents all the integers from
// starti to endi inclusively.
//A containing set is an array nums where each interval from intervals has at least two integers in nums.
//For example, if intervals = [[1,3], [3,7], [8,9]], then [1,2,4,7,8,9] and [2,3,4,8,9] are containing sets.
//Return the minimum possible size of a containing set.

//Example 1:
//Input: intervals = [[1,3],[3,7],[8,9]]
//Output: 5
//Explanation: let nums = [2, 3, 4, 8, 9].
//It can be shown that there cannot be any containing array of size 4.

//Example 2:
//Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
//Output: 3
//Explanation: let nums = [2, 3, 4].
//It can be shown that there cannot be any containing array of size 2.

//Example 3:
//Input: intervals = [[1,2],[2,3],[2,4],[4,5]]
//Output: 5
//Explanation: let nums = [1, 2, 3, 4, 5].
//It can be shown that there cannot be any containing array of size 4.

//Constraints:
//1 <= intervals.length <= 3000
//intervals[i].length == 2
//0 <= starti < endi <= 108
