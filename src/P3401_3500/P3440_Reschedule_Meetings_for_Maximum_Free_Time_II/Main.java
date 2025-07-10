package P3401_3500.P3440_Reschedule_Meetings_for_Maximum_Free_Time_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxFreeTime(5, new int[]{1, 3}, new int[]{2, 5})); // 2
        System.out.println(solution.maxFreeTime(10, new int[]{0, 7, 9}, new int[]{1, 8, 10})); // 7
        System.out.println(solution.maxFreeTime(10, new int[]{0, 3, 7, 9}, new int[]{1, 4, 8, 10})); // 6
        System.out.println(solution.maxFreeTime(5, new int[]{0, 1, 2, 3, 4}, new int[]{1, 2, 3, 4, 5})); // 0

    }

    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] gaps = getGaps(eventTime, startTime, endTime);

        int[] maxLeft = new int[n + 1];   // maxLeft[i] = max(gaps[0..i])
        int[] maxRight = new int[n + 1];  // maxRight[i] = max(gaps[i..n])

        maxLeft[0] = gaps[0];

        for (int i = 1; i <= n; ++i) {
            maxLeft[i] = Math.max(gaps[i], maxLeft[i - 1]);
        }

        maxRight[n] = gaps[n];

        for (int i = n - 1; i >= 0; --i) {
            maxRight[i] = Math.max(gaps[i], maxRight[i + 1]);
        }

        int ans = 0;

        for (int i = 0; i < n; ++i) {
            int currMeetingTime = endTime[i] - startTime[i];
            int adjacentGapsSum = gaps[i] + gaps[i + 1];

            int maxOtherGap = Math.max(
                    i > 0 ? maxLeft[i - 1] : 0,
                    (i + 2 < n + 1) ? maxRight[i + 2] : 0
            );

            boolean canMoveMeeting = currMeetingTime <= maxOtherGap;
            int freeTime = adjacentGapsSum + (canMoveMeeting ? currMeetingTime : 0);
            ans = Math.max(ans, freeTime);
        }

        return ans;
    }

    private int[] getGaps(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] gaps = new int[n + 1];
        gaps[0] = startTime[0];  // before first meeting

        for (int i = 1; i < n; ++i) {
            gaps[i] = startTime[i] - endTime[i - 1];  // between meetings
        }

        gaps[n] = eventTime - endTime[n - 1];  // after last meeting

        return gaps;
    }

}

//Complexity:
// time and space: O(n)


//You are given an integer eventTime denoting the duration of an event. You are also given two integer arrays
// startTime and endTime, each of length n.
//These represent the start and end times of n non-overlapping meetings that occur during the event between
// time t = 0 and time t = eventTime, where the ith meeting occurs during the time [startTime[i], endTime[i]].
//You can reschedule at most one meeting by moving its start time while maintaining the same duration, such that
// the meetings remain non-overlapping, to maximize the longest continuous period of free time during the event.
//Return the maximum amount of free time possible after rearranging the meetings.
//Note that the meetings can not be rescheduled to a time outside the event and they should remain non-overlapping.
//Note: In this version, it is valid for the relative ordering of the meetings to change after rescheduling one meeting.

//Example 1:
//Input: eventTime = 5, startTime = [1,3], endTime = [2,5]
//Output: 2
//Explanation:
//Reschedule the meeting at [1, 2] to [2, 3], leaving no meetings during the time [0, 2].

//Example 2:
//Input: eventTime = 10, startTime = [0,7,9], endTime = [1,8,10]
//Output: 7
//Explanation:
//Reschedule the meeting at [0, 1] to [8, 9], leaving no meetings during the time [0, 7].

//Example 3:
//Input: eventTime = 10, startTime = [0,3,7,9], endTime = [1,4,8,10]
//Output: 6
//Explanation:
//Reschedule the meeting at [3, 4] to [8, 9], leaving no meetings during the time [1, 7].

//Example 4:
//Input: eventTime = 5, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
//Output: 0
//Explanation:
//There is no time during the event not occupied by meetings.

//Constraints:
//1 <= eventTime <= 109
//n == startTime.length == endTime.length
//2 <= n <= 105
//0 <= startTime[i] < endTime[i] <= eventTime
//endTime[i] <= startTime[i + 1] where i lies in the range [0, n - 2].
