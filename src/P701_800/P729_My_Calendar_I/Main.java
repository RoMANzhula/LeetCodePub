package P701_800.P729_My_Calendar_I;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.book(10, 20)); // true
        System.out.println(solution.book(15, 25)); // false
        System.out.println(solution.book(20, 30)); // true
    }

//    private List<int[]> bookings; // List to store all bookings as intervals
//
//    public Main() {
//        bookings = new ArrayList<>(); // Initialization
//    }
//
//    public boolean book(int start, int end) {
//        // Check for any overlap with the existing bookings
//        for (int[] booking : bookings) {
//            int bookedStart = booking[0];
//            int bookedEnd = booking[1];
//            // Check if there is an overlap
//            if (start < bookedEnd && end > bookedStart) {
//                return false; // Overlap found, cannot book
//            }
//        }
//        // No overlap found, add the event to the list
//        bookings.add(new int[]{start, end});
//        return true; // Booking successful
//    }


    // faster solution
    private TreeMap<Integer, Integer> calendar;

    public Main() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        // Get the previous event (closest to the left)
        Integer prev = calendar.floorKey(start);
        // Get the next event (closest to the right)
        Integer next = calendar.ceilingKey(start);

        // Check if there is an overlap with the previous event
        if (prev != null && calendar.get(prev) > start) {
            return false; // Overlap with the previous event
        }

        // Check if there is an overlap with the next event
        if (next != null && next < end) {
            return false; // Overlap with the next event
        }

        // No overlap, add the event to the calendar
        calendar.put(start, end);
        return true;
    }
}

//Explanation:
//TreeMap:
//The TreeMap stores events with the start time as the key and the end time as the value. It is a balanced
// binary search tree that provides logarithmic time complexity for searching, inserting, and removing entries.
//Finding Neighbor Events:
//calendar.floorKey(start) finds the largest key less than or equal to start (i.e., the closest event
// before the current start time).
//calendar.ceilingKey(start) finds the smallest key greater than or equal to start (i.e., the closest
// event after the current start time).
//Overlap Checks:
//For the previous event (prev), if the end time of the previous event is greater than start, there is an overlap.
//For the next event (next), if the start time of the next event is less than end, there is an overlap.
//Time Complexity:
//The operations for finding the neighbors and inserting into the TreeMap take O(log n) time, making each book
// call much faster compared to the previous O(n) solution.


//You are implementing a program to use as your calendar. We can add a new event if adding the event will
// not cause a double booking.
//A double booking happens when two events have some non-empty
// intersection (i.e., some moment is common to both events.).
//The event can be represented as a pair of integers start and end that represents a booking on the
// half-open interval [start, end), the range of real numbers x such that start <= x < end.
//Implement the MyCalendar class:
//MyCalendar() Initializes the calendar object.
//boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without
// causing a double booking. Otherwise, return false and do not add the event to the calendar.

//Example 1:
//Input
//["MyCalendar", "book", "book", "book"]
//[[], [10, 20], [15, 25], [20, 30]]
//Output
//[null, true, false, true]
//Explanation
//MyCalendar myCalendar = new MyCalendar();
//myCalendar.book(10, 20); // return True
//myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
//myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less
// than 20, but not including 20.
//
//Constraints:
//0 <= start < end <= 109
//At most 1000 calls will be made to book.