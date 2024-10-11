package P1901_2000.P1942_The_Number_of_the_Smallesr_Unoccupied_Chair;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] times1 = {{1, 4}, {2, 3}, {4, 6}};
        int targetFriend1 = 1;
        System.out.println("Example 1 Output: " + solution.smallestChair(times1, targetFriend1));  // Expected: 1

        int[][] times2 = {{3, 10}, {1, 5}, {2, 6}};
        int targetFriend2 = 0;
        System.out.println("Example 2 Output: " + solution.smallestChair(times2, targetFriend2));  // Expected: 2
    }

//    public int smallestChair(int[][] times, int targetFriend) {
//        // Create an array of events (arrival or departure)
//        List<int[]> events = new ArrayList<>();
//
//        // Fill the events list with arrival and departure times
//        for (int i = 0; i < times.length; i++) {
//            int arrive = times[i][0];
//            int leave = times[i][1];
//            events.add(new int[]{arrive, i, 1});  // Arrival event: [time, friendIndex, 1 (for arrival)]
//            events.add(new int[]{leave, i, 0});   // Departure event: [time, friendIndex, 0 (for departure)]
//        }
//
//        // Sort events first by time, and then prioritize departures if times are the same
//        Collections.sort(events, (a, b) -> a[0] == b[0] ? a[2] - b[2] : a[0] - b[0]);
//
//        // Min-heap to track the available (free) chairs
//        PriorityQueue<Integer> freeChairs = new PriorityQueue<>();
//        for (int i = 0; i < times.length; i++) {
//            freeChairs.add(i);  // Initially all chairs are free
//        }
//
//        // Array to store which chair each friend is sitting on
//        int[] chairAssignments = new int[times.length];
//
//        // Iterate through events
//        for (int[] event : events) {
//            int time = event[0];
//            int friendIndex = event[1];
//            int eventType = event[2];  // 1 for arrival, 0 for departure
//
//            if (eventType == 1) {  // Arrival
//                // Assign the smallest available chair
//                int assignedChair = freeChairs.poll();
//                chairAssignments[friendIndex] = assignedChair;
//
//                // If this is the target friend, return the assigned chair
//                if (friendIndex == targetFriend) {
//                    return assignedChair;
//                }
//            } else {  // Departure
//                // Free the chair
//                freeChairs.add(chairAssignments[friendIndex]);
//            }
//        }
//
//        return -1;  // This should never be reached
//    }

    //faster solution

    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;

        // Pair each friend's times with their index
        int[][] arrivalTimes = new int[n][3];  // [arrivalTime, leavingTime, friendIndex]
        for (int i = 0; i < n; i++) {
            arrivalTimes[i][0] = times[i][0];
            arrivalTimes[i][1] = times[i][1];
            arrivalTimes[i][2] = i;
        }

        // Sort friends by arrival time
        Arrays.sort(arrivalTimes, Comparator.comparingInt(a -> a[0]));

        // Min-heap for occupied chairs: [leavingTime, chairAssigned]
        PriorityQueue<int[]> occupiedChairs = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Min-heap for available chairs
        PriorityQueue<Integer> availableChairs = new PriorityQueue<>();

        // Next available chair number
        int nextAvailableChair = 0;

        for (int[] friend : arrivalTimes) {
            int arrivalTime = friend[0];
            int leavingTime = friend[1];
            int friendIndex = friend[2];

            // Free up chairs for friends who have left
            while (!occupiedChairs.isEmpty() && occupiedChairs.peek()[0] <= arrivalTime) {
                availableChairs.offer(occupiedChairs.poll()[1]);
            }

            // Assign chair
            int assignedChair;
            if (!availableChairs.isEmpty()) {
                assignedChair = availableChairs.poll();
            } else {
                assignedChair = nextAvailableChair++;
            }

            // If this is the target friend, return the assigned chair
            if (friendIndex == targetFriend) {
                return assignedChair;
            }

            // Add this friend's chair to the occupiedChairs heap
            occupiedChairs.offer(new int[]{leavingTime, assignedChair});
        }

        return -1;  // This should never be reached
    }

}

//Explanation:
//Arrival Times Array: We create an array arrivalTimes that contains each friend's arrival time, leaving
// time, and their index.
//Sorting: We sort the arrivalTimes array based on the arrival times.
//Occupied Chairs Heap (occupiedChairs): This min-heap keeps track of chairs that will become available,
// ordered by the leaving time.
//Available Chairs Heap (availableChairs): This min-heap contains the chairs that are currently free and
// can be assigned to arriving friends.
//Assigning Chairs:
//Before assigning a chair to the current friend, we free up chairs from friends who have left.
//If there are chairs in availableChairs, we assign the smallest one.
//If not, we assign a new chair using nextAvailableChair.
//We then add the friend's leaving time and assigned chair to the occupiedChairs heap.
//Target Friend Check: If the current friend is the targetFriend, we return the chair assigned to them immediately,
// without processing further friends.
//Advantages of This Approach:
//Reduced Number of Events: We only process each friend once, rather than creating separate arrival and
// departure events for each friend.
//Efficient Chair Management: By using two heaps, we efficiently manage both occupied and available chairs
// without unnecessary overhead.
//Avoid Sorting All Events: We avoid the extra overhead of sorting all arrival and departure events together,
// which can be significant when n is large.
//Time Complexity:
//Sorting Arrival Times:
//O(nlogn), where n is the number of friends.
//Heap Operations: Each friend causes at most two heap operations (one for freeing chairs and one for
// assigning a chair), each of which is O(log n). Since there are n friends, this is O(n logn).
//Overall: The total time complexity remains O(n log n), but with reduced constants and overhead, making
// it faster in practice.

//There is a party where n friends numbered from 0 to n - 1 are attending. There is an infinite number of
// chairs in this party that are numbered from 0 to infinity. When a friend arrives at the party, they sit on
// the unoccupied chair with the smallest number.
//For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
//When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives
// at that same moment, they can sit in that chair.
//You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi], indicating the arrival and
// leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.
//Return the chair number that the friend numbered targetFriend will sit on.
//
//Example 1:
//Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
//Output: 1
//Explanation:
//- Friend 0 arrives at time 1 and sits on chair 0.
//- Friend 1 arrives at time 2 and sits on chair 1.
//- Friend 1 leaves at time 3 and chair 1 becomes empty.
//- Friend 0 leaves at time 4 and chair 0 becomes empty.
//- Friend 2 arrives at time 4 and sits on chair 0.
//Since friend 1 sat on chair 1, we return 1.

//Example 2:
//Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
//Output: 2
//Explanation:
//- Friend 1 arrives at time 1 and sits on chair 0.
//- Friend 2 arrives at time 2 and sits on chair 1.
//- Friend 0 arrives at time 3 and sits on chair 2.
//- Friend 1 leaves at time 5 and chair 0 becomes empty.
//- Friend 2 leaves at time 6 and chair 1 becomes empty.
//- Friend 0 leaves at time 10 and chair 2 becomes empty.
//Since friend 0 sat on chair 2, we return 2.
//
//Constraints:
//n == times.length
//2 <= n <= 104
//times[i].length == 2
//1 <= arrivali < leavingi <= 105
//0 <= targetFriend <= n - 1
//Each arrivali time is distinct.
