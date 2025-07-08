package P1701_1800.P1751_Maximum_Number_of_Events_That_Can_Be_Attended_II;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxValue(new int[][]{{1,2,4},{3,4,3},{2,3,1}}, 2)); // 7
        System.out.println(solution.maxValue(new int[][]{{1,2,4},{3,4,3},{2,3,10}}, 2)); // 10
        System.out.println(solution.maxValue(new int[][]{{1,1,1},{2,2,2},{3,3,3},{4,4,4}}, 3)); // 9
    }

    public int maxValue(int[][] events, int k) {
        // sort events by end time
        Arrays.sort(events, Comparator.comparingInt(e -> e[1]));

        int n = events.length;
        // dp[i][j] = max value using first i events with j picks
        int[][] dp = new int[n + 1][k + 1];

        // create an array of event end times for binary search
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            endTimes[i] = events[i][1];
        }

        for (int i = 1; i <= n; i++) {
            int[] currEvent = events[i - 1];
            int start = currEvent[0];
            int value = currEvent[2];

            // find the last event that ends before the current event starts
            int lastNonOverlap = binarySearch(endTimes, start - 1);

            for (int j = 1; j <= k; j++) {
                // option 1: don't take this event
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                // ooption 2: take this event and add value from the last non-overlapping
                dp[i][j] = Math.max(dp[i][j], dp[lastNonOverlap + 1][j - 1] + value);
            }
        }

        return dp[n][k];
    }

    // binary search to find the last index with endTime <= target
    private int binarySearch(int[] endTimes, int target) {
        int left = 0, right = endTimes.length - 1;
        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (endTimes[mid] <= target) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

}


//You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi
// and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an
// integer k which represents the maximum number of events you can attend.
//You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note
// that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends
// on the same day.
//Return the maximum sum of values that you can receive by attending events.

//Example 1:
//Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
//Output: 7
//Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.

//Example 2:
//Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
//Output: 10
//Explanation: Choose event 2 for a total value of 10.
//Notice that you cannot attend any other event as they overlap, and that you do not have to attend k events.

//Example 3:
//Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
//Output: 9
//Explanation: Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.

//Constraints:
//1 <= k <= events.length
//1 <= k * events.length <= 106
//1 <= startDayi <= endDayi <= 109
//1 <= valuei <= 106
