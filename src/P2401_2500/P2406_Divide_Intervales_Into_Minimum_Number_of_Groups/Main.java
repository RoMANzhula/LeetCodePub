package P2401_2500.P2406_Divide_Intervales_Into_Minimum_Number_of_Groups;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] intervals1 = {{5,10}, {6,8}, {1,5}, {2,3}, {1,10}};
        System.out.println(solution.minGroups(intervals1));  // Output: 3

        int[][] intervals2 = {{1,3}, {5,6}, {8,10}, {11,13}};
        System.out.println(solution.minGroups(intervals2));  // Output: 1
    }

//    public int minGroups(int[][] intervals) {
//        // Create a list of events (start and end times)
//        List<int[]> events = new ArrayList<>();
//
//        // Add start and end times for each interval
//        for (int[] interval : intervals) {
//            events.add(new int[]{interval[0], 1});  // Start of an interval
//            events.add(new int[]{interval[1] + 1, -1});  // End of an interval
//        }
//
//        // Sort events by time; in case of tie, process end (-1) before start (+1)
//        events.sort((a, b) -> {
//            if (a[0] == b[0]) {
//                return a[1] - b[1];
//            } else {
//                return a[0] - b[0];
//            }
//        });
//
//        int maxGroups = 0;
//        int currentGroups = 0;
//
//        // Process all events
//        for (int[] event : events) {
//            currentGroups += event[1];
//            maxGroups = Math.max(maxGroups, currentGroups);
//        }
//
//        return maxGroups;
//    }

    // faster solution

    public int minGroups(int[][] intervals) {
        // The range of interval boundaries is 1 to 10^6, so we need an array that size
        int MAX_TIME = 1_000_000;
        int[] timeline = new int[MAX_TIME + 2]; // +2 to handle intervals exactly at MAX_TIME

        // Mark the start and end points in the timeline
        for (int[] interval : intervals) {
            timeline[interval[0]]++;        // Increment at the start of the interval
            timeline[interval[1] + 1]--;    // Decrement after the end of the interval
        }

        int maxGroups = 0;
        int currentGroups = 0;

        // Sweep through the timeline to calculate the number of active intervals
        for (int i = 1; i <= MAX_TIME; i++) {
            currentGroups += timeline[i];   // Add the current event (start or end)
            maxGroups = Math.max(maxGroups, currentGroups);
        }

        return maxGroups;
    }

}

//Key Improvements:
//Avoid Sorting: Instead of sorting the start and end points of intervals, we use an array (timeline[]) to
// directly mark the beginning and end of intervals. This reduces sorting time complexity to
//O(1) for each interval, making this approach linear in terms of the number of intervals.
//
//Linear Time Complexity:
//Marking events: For each interval, we mark the start and end in constant time, which results in
//O(n) time complexity for processing intervals.
//Sweeping through the timeline: Once we have marked all start and end events in the timeline[], we only
// need one pass through the array to compute the maximum number of overlapping intervals. This also takes
//O(m), where m= 10 in the 6th degree is the maximum possible time point, which is manageable.
//Thus, the overall time complexity is
//O(n+m), where:
//n is the number of intervals (up to 10 in the 5th degree)
//m is the maximum time range (up to 10 in the 6th degree)


//You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the
// inclusive interval [lefti, righti].
//You have to divide the intervals into one or more groups such that each interval is in exactly one group, and
// no two intervals that are in the same group intersect each other.
//Return the minimum number of groups you need to make.
//Two intervals intersect if there is at least one common number between them. For example, the
// intervals [1, 5] and [5, 8] intersect.
//
//Example 1:
//Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
//Output: 3
//Explanation: We can divide the intervals into the following groups:
//- Group 1: [1, 5], [6, 8].
//- Group 2: [2, 3], [5, 10].
//- Group 3: [1, 10].
//It can be proven that it is not possible to divide the intervals into fewer than 3 groups.

//Example 2:
//Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
//Output: 1
//Explanation: None of the intervals overlap, so we can put all of them in one group.
//
//Constraints:
//1 <= intervals.length <= 105
//intervals[i].length == 2
//1 <= lefti <= righti <= 106
