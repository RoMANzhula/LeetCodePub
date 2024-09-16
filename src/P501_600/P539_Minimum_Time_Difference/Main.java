package P501_600.P539_Minimum_Time_Difference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        List<String> timePoints1 = Arrays.asList("23:59", "00:00");
        System.out.println(solution.findMinDifference(timePoints1));  // Output: 1

        List<String> timePoints2 = Arrays.asList("00:00", "23:59", "00:00");
        System.out.println(solution.findMinDifference(timePoints2));  // Output: 0
    }


//    public int findMinDifference(List<String> timePoints) {
//        // List to store the time points in minutes
//        List<Integer> minutesList = new ArrayList<>();
//
//        // Convert each time point to minutes and add to the list
//        for (String time : timePoints) {
//            String[] parts = time.split(":");
//            int hours = Integer.parseInt(parts[0]);
//            int minutes = Integer.parseInt(parts[1]);
//            minutesList.add(hours * 60 + minutes);
//        }
//
//        // Sort the list of time points
//        Collections.sort(minutesList);
//
//        // Initialize the minimum difference with a large value
//        int minDiff = Integer.MAX_VALUE;
//
//        // Compare consecutive time points to find the minimum difference
//        for (int i = 1; i < minutesList.size(); i++) {
//            int diff = minutesList.get(i) - minutesList.get(i - 1);
//            minDiff = Math.min(minDiff, diff);
//        }
//
//        // Also consider the difference between the last and first time point,
//        // accounting for the circular nature of the clock (24 hours -> 1440 minutes).
//        int circularDiff = 1440 - minutesList.get(minutesList.size() - 1) + minutesList.get(0);
//        minDiff = Math.min(minDiff, circularDiff);
//
//        return minDiff;
//    }

    //faster solution
    public int findMinDifference(List<String> timePoints) {
        // If the number of time points exceeds 1440, return 0 immediately
        // because there must be a duplicate (pigeonhole principle)
        if (timePoints.size() > 1440) {
            return 0;
        }

        // Boolean array to mark each minute of the day
        boolean[] timeExists = new boolean[1440];

        // Convert each time point to minutes and mark in the boolean array
        for (String time : timePoints) {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int totalMinutes = hours * 60 + minutes;

            // If we encounter a duplicate time, return 0 immediately
            if (timeExists[totalMinutes]) {
                return 0;
            }
            timeExists[totalMinutes] = true;
        }

        // Variables to track the minimum difference
        int minDiff = Integer.MAX_VALUE;
        int firstTime = -1, prevTime = -1;
        int lastTime = -1;

        // Traverse through the boolean array to calculate the minimum difference
        for (int i = 0; i < 1440; i++) {
            if (timeExists[i]) {
                if (firstTime == -1) {
                    firstTime = i; // Store the first time point
                } else {
                    minDiff = Math.min(minDiff, i - prevTime); // Compare with the previous time
                }
                prevTime = i; // Update the previous time
                lastTime = i; // Track the last time
            }
        }

        // Finally, compare the circular difference (between the last and first time points)
        minDiff = Math.min(minDiff, (1440 - lastTime + firstTime));

        return minDiff;
    }
}

//Explanation:
//Boolean Array: We create a boolean[] array of size 1440 to mark which minutes are present in the input. This
// avoids the need to sort the list.
//Time Point Marking: As we convert each time point to minutes, we check for duplicates. If a duplicate is
// found, we return 0 immediately, since the minimum difference will be 0.
//Traverse to Find Minimum Difference: After marking all the time points, we traverse the boolean array to
// compute the minimum difference between consecutive time points.
//Circular Difference: After the main traversal, we also compute the circular difference between the last
// and the first time points.
//Optimized Time Complexity:
//Time Complexity: This approach has a time complexity of O(n), where n is the number of time points. Each time
// point is processed in constant time (since we only convert it to minutes and mark the boolean array). We
// also traverse the boolean array once (1440 steps), which is independent of the input size.
//Space Complexity: The space complexity is O(1) with respect to the input size, as we always use a
// fixed-size boolean array of 1440 elements.



//Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference
// between any two time-points in the list.
//
//Example 1:
//Input: timePoints = ["23:59","00:00"]
//Output: 1

//Example 2:
//Input: timePoints = ["00:00","23:59","00:00"]
//Output: 0
//
//Constraints:
//2 <= timePoints.length <= 2 * 104
//timePoints[i] is in the format "HH:MM".