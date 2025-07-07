package P1301_1400.P1353_Maximum_Number_of_Events_That_Can_Be_Attended;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] events1 = {{1,2},{2,3},{3,4}};
        int[][] events2 = {{1,2},{2,3},{3,4},{1,2}};

        System.out.println(solution.maxEvents(events1)); // Output: 3
        System.out.println(solution.maxEvents(events2)); // Output: 4
    }

    public int maxEvents(int[][] events) {
        // sort events by start day
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        // min-heap to keep track of event end days
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int i = 0, day = 1, res = 0;
        int n = events.length;

        // find the last possible day we need to consider
        int maxDay = 0;
        for (int[] event : events) {
            maxDay = Math.max(maxDay, event[1]);
        }

        // go through each day
        for (day = 1; day <= maxDay; day++) {
            // add all events that start today
            while (i < n && events[i][0] == day) {
                minHeap.offer(events[i][1]);
                i++;
            }

            //remove all events that already expired
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            //attend the event that ends the earliest
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                res++;
            }
        }

        return res;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and
// ends at endDayi.
//You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event
// at any time d.
//Return the maximum number of events you can attend.

//Example 1:
//Input: events = [[1,2],[2,3],[3,4]]
//Output: 3
//Explanation: You can attend all the three events.
//One way to attend them all is as shown.
//Attend the first event on day 1.
//Attend the second event on day 2.
//Attend the third event on day 3.

//Example 2:
//Input: events= [[1,2],[2,3],[3,4],[1,2]]
//Output: 4

//Constraints:
//1 <= events.length <= 105
//events[i].length == 2
//1 <= startDayi <= endDayi <= 105
