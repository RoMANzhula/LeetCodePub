package P3101_3200.P3169_Count_Days_Without_Meetings;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countDays(10, new int[][]{{5, 7}, {1, 3}, {9, 10}})); // Output: 2
        System.out.println(solution.countDays(5, new int[][]{{2, 4}, {1, 3}})); // Output: 1
        System.out.println(solution.countDays(6, new int[][]{{1, 6}})); // Output: 0
    }

    public int countDays(int days, int[][] meetings) {
        if (meetings.length == 0) return days; // no meetings, all days are available

        // sort meetings by start day
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        int busyDays = 0;
        int prevStart = meetings[0][0], prevEnd = meetings[0][1];

        for (int i = 1; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            if (start > prevEnd) {
                //count the merged interval
                busyDays += (prevEnd - prevStart + 1);
                prevStart = start;
                prevEnd = end;
            } else {
                // merge overlapping intervals
                prevEnd = Math.max(prevEnd, end);
            }
        }

        //count the last merged interval
        busyDays += (prevEnd - prevStart + 1);

        // total available days
        return days - busyDays;
    }

}

//Explanation:
//Sort the meetings by their start time.
//Merge overlapping intervals to find the exact busy days.
//Subtract busy days from total days to get available workdays.
//Time Complexity:
//O(n log n) for sorting.
//O(n) for merging intervals.
//Total: O(n log n).


//You are given a positive integer days representing the total number of days an employee is available for work
// (starting from day 1). You are also given a 2D array meetings of size n where, meetings[i] = [start_i, end_i]
// represents the starting and ending days of meeting i (inclusive).
//Return the count of days when the employee is available for work but no meetings are scheduled.
//Note: The meetings may overlap.

//Example 1:
//Input: days = 10, meetings = [[5,7],[1,3],[9,10]]
//Output: 2
//Explanation:
//There is no meeting scheduled on the 4th and 8th days.

//Example 2:
//Input: days = 5, meetings = [[2,4],[1,3]]
//Output: 1
//Explanation:
//There is no meeting scheduled on the 5th day.

//Example 3:
//Input: days = 6, meetings = [[1,6]]
//Output: 0
//Explanation:
//Meetings are scheduled for all working days.

//Constraints:
//1 <= days <= 109
//1 <= meetings.length <= 105
//meetings[i].length == 2
//1 <= meetings[i][0] <= meetings[i][1] <= days
