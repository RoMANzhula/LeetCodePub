package P2001_2100.P2054_Two_Best_Non_Overlapping_Events;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] events1 = {{1, 3, 2}, {4, 5, 2}, {2, 4, 3}};
        System.out.println(solution.maxTwoEvents(events1)); // Output: 4

        int[][] events2 = {{1, 3, 2}, {4, 5, 2}, {1, 5, 5}};
        System.out.println(solution.maxTwoEvents(events2)); // Output: 5

        int[][] events3 = {{1, 5, 3}, {1, 5, 1}, {6, 6, 5}};
        System.out.println(solution.maxTwoEvents(events3)); // Output: 8
    }

    public int maxTwoEvents(int[][] events) {
        // Sort events by endTime
        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));

        // Store maximum value up to each point
        int[] maxValueUpTo = new int[events.length];
        maxValueUpTo[0] = events[0][2];

        for (int i = 1; i < events.length; i++) {
            maxValueUpTo[i] = Math.max(maxValueUpTo[i - 1], events[i][2]);
        }

        int maxSum = 0;

        for (int i = 0; i < events.length; i++) {
            // Take the value of the current event
            int currValue = events[i][2];

            // Find the latest compatible event using binary search
            int left = 0, right = i - 1, compatibleIdx = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (events[mid][1] < events[i][0]) {
                    compatibleIdx = mid; // This event is compatible
                    left = mid + 1; // Move to the right half
                } else {
                    right = mid - 1; // Move to the left half
                }
            }

            // If a compatible event exists, add its value
            if (compatibleIdx != -1) {
                currValue += maxValueUpTo[compatibleIdx];
            }

            // Update the maximum sum
            maxSum = Math.max(maxSum, currValue);
        }

        return maxSum;
    }
}


//You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith
// event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of
// valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.

//Return this maximum sum.
//Note that the start time and end time is inclusive: that is, you cannot attend two events where one of
// them starts and the other ends at the same time. More specifically, if you attend an event with end time t,
// the next event must start at or after t + 1.
//
//Example 1:
//Input: events = [[1,3,2],[4,5,2],[2,4,3]]
//Output: 4
//Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.

//Example 2:
//Example 1 Diagram
//Input: events = [[1,3,2],[4,5,2],[1,5,5]]
//Output: 5
//Explanation: Choose event 2 for a sum of 5.

//Example 3:
//Input: events = [[1,5,3],[1,5,1],[6,6,5]]
//Output: 8
//Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
//
//Constraints:
//2 <= events.length <= 105
//events[i].length == 3
//1 <= startTimei <= endTimei <= 109
//1 <= valuei <= 106