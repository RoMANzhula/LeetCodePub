package P3401_3500.P3439_Reschedule_Meetings_for_Maximum_Free_Time_I;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxFreeTime(5, 1, new int[]{1, 3}, new int[]{2, 5})); // Output: 2
        System.out.println(solution.maxFreeTime(10, 1, new int[]{0, 2, 9}, new int[]{1, 4, 10})); // Output: 6
        System.out.println(solution.maxFreeTime(5, 2, new int[]{0, 1, 2, 3, 4}, new int[]{1, 2, 3, 4, 5})); // Output: 0
        System.out.println(solution.maxFreeTime(21, 1, new int[]{7,10,16}, new int[]{10,14,18})); // Expected: 7
    }

    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        List<Integer> gaps = getGaps(eventTime, startTime, endTime);

        // sliding window of size k + 1
        int windowSum = 0;

        for (int i = 0; i <= k && i < gaps.size(); ++i) {
            windowSum += gaps.get(i);
        }

        int ans = windowSum;

        for (int i = k + 1; i < gaps.size(); ++i) {
            windowSum += gaps.get(i) - gaps.get(i - k - 1);
            ans = Math.max(ans, windowSum);
        }

        return ans;
    }

    private List<Integer> getGaps(int eventTime, int[] startTime, int[] endTime) {
        List<Integer> gaps = new ArrayList<>();
        gaps.add(startTime[0]); // time before first meeting

        for (int i = 1; i < startTime.length; ++i) {
            gaps.add(startTime[i] - endTime[i - 1]); // between meetings
        }

        gaps.add(eventTime - endTime[endTime.length - 1]); // time after last meeting

        return gaps;
    }

}

//Complexity:
// time and space - O(n)


//You are given an integer eventTime denoting the duration of an event, where the event occurs from time t = 0 to
// time t = eventTime.
//You are also given two integer arrays startTime and endTime, each of length n. These represent the start and end
// time of n non-overlapping meetings, where the ith meeting occurs during the time [startTime[i], endTime[i]].
//You can reschedule at most k meetings by moving their start time while maintaining the same duration, to maximize
// the longest continuous period of free time during the event.
//The relative order of all the meetings should stay the same and they should remain non-overlapping.
//Return the maximum amount of free time possible after rearranging the meetings.
//Note that the meetings can not be rescheduled to a time outside the event.

//Example 1:
//Input: eventTime = 5, k = 1, startTime = [1,3], endTime = [2,5]
//Output: 2
//Explanation:
//Reschedule the meeting at [1, 2] to [2, 3], leaving no meetings during the time [0, 2].

//Example 2:
//Input: eventTime = 10, k = 1, startTime = [0,2,9], endTime = [1,4,10]
//Output: 6
//Explanation:
//Reschedule the meeting at [2, 4] to [1, 3], leaving no meetings during the time [3, 9].

//Example 3:
//Input: eventTime = 5, k = 2, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
//Output: 0
//Explanation:
//There is no time during the event not occupied by meetings.

//Constraints:
//1 <= eventTime <= 109
//n == startTime.length == endTime.length
//2 <= n <= 105
//1 <= k <= n
//0 <= startTime[i] < endTime[i] <= eventTime
//endTime[i] <= startTime[i + 1] where i lies in the range [0, n - 2].