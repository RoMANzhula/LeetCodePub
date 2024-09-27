package P701_800.P731_My_Calendar_II;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.book(10, 20)); // true
        System.out.println(solution.book(50, 60)); // true
        System.out.println(solution.book(10, 40)); // true
        System.out.println(solution.book(5, 15));  // false (would cause a triple booking)
        System.out.println(solution.book(5, 10));  // true
        System.out.println(solution.book(25, 55)); // true
    }

//    private TreeMap<Integer, Integer> calendar;
//
//    public Main() {
//        calendar = new TreeMap<>();
//    }
//
//    public boolean book(int start, int end) {
//        // Increment the start time, decrement the end time
//        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
//        calendar.put(end, calendar.getOrDefault(end, 0) - 1);
//
//        int activeBookings = 0;
//        // Sweep through the timeline
//        for (Map.Entry<Integer, Integer> entry : calendar.entrySet()) {
//            activeBookings += entry.getValue();  // Update the number of active bookings
//            if (activeBookings >= 3) {  // Triple booking detected
//                // Revert changes since we can't allow this booking
//                calendar.put(start, calendar.get(start) - 1);
//                if (calendar.get(start) == 0) {
//                    calendar.remove(start);
//                }
//                calendar.put(end, calendar.get(end) + 1);
//                if (calendar.get(end) == 0) {
//                    calendar.remove(end);
//                }
//                return false;
//            }
//        }
//        return true;
//    }

    //faster solution

    private List<int[]> singleBookings;
    private List<int[]> doubleBookings;

    public Main() {
        singleBookings = new ArrayList<>();
        doubleBookings = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        // Check for triple booking: see if this overlaps with any double booking
        for (int[] booking : doubleBookings) {
            // Check if the new event overlaps with any double-booked time
            if (Math.max(start, booking[0]) < Math.min(end, booking[1])) {
                return false;  // This would result in a triple booking
            }
        }

        // Update double bookings: check where this overlaps with single bookings
        for (int[] booking : singleBookings) {
            int overlapStart = Math.max(start, booking[0]);
            int overlapEnd = Math.min(end, booking[1]);
            if (overlapStart < overlapEnd) {  // There is an overlap
                doubleBookings.add(new int[]{overlapStart, overlapEnd});  // Add this overlap to double bookings
            }
        }

        // No triple booking, so add this event to single bookings
        singleBookings.add(new int[]{start, end});
        return true;
    }
}

//Explanation for faster solution with List(ArrayList):
//singleBookings stores intervals that represent single bookings.
//doubleBookings stores intervals where two bookings overlap, i.e., double bookings.
//The book method checks if the new event overlaps with any double booking (which would result in a triple booking). If
// it does, we return false and do not book the event.
//If it doesn't cause a triple booking, the overlap with existing single bookings is added to the double bookings,
// and finally, the event itself is added to the single bookings.
//Time Complexity:
//The worst-case time complexity is
//O(n 2), where n is the number of events. This is because for every new event, we check the overlap with all previous
// events. Given the constraint of up to 1000 bookings, this complexity is acceptable.


//Explanation:
//TreeMap Structure:
//We use a TreeMap<Integer, Integer> calendar where the key is the time (either a start or an end of an event) and
// the value is an integer indicating the change in the number of active events at that time:
//At a start time, we increment the value (meaning an event is starting).
//At an end time, we decrement the value (meaning an event is ending).
//Booking Process:
//When trying to book a new event [start, end), we first add the event to the map.
//Then, we sweep through the TreeMap to check the number of active bookings at each time point. If at any point we
// have 3 or more overlapping events, that means a triple booking would occur, and we need to undo the
// changes and return false.
//Reverting Changes:
//If a triple booking is detected, we revert the changes by decreasing the start time's count and increasing the
// end time's count back to their original state.
//Time Complexity:
//The time complexity for inserting and checking each booking is
//O(log n) for insertion and removal operations in the TreeMap, and O(n) for sweeping through the entries. This
// results in an overall time complexity of O(n) for each booking, where n is the number of events.


//You are implementing a program to use as your calendar. We can add a new event if adding the event will
// not cause a triple booking.
//A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to
// all the three events.).
//The event can be represented as a pair of integers start and end that represents a booking on the half-open
// interval [start, end), the range of real numbers x such that start <= x < end.
//Implement the MyCalendarTwo class:
//MyCalendarTwo() Initializes the calendar object.
//boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without
// causing a triple booking. Otherwise, return false and do not add the event to the calendar.
//
//Example 1:
//Input
//["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
//[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
//Output
//[null, true, true, true, false, true, true]
//
//Explanation
//MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
//myCalendarTwo.book(10, 20); // return True, The event can be booked.
//myCalendarTwo.book(50, 60); // return True, The event can be booked.
//myCalendarTwo.book(10, 40); // return True, The event can be double booked.
//myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
//myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is
// already double booked.
//myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be
// double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be
// double booked with the second event.
//
//Constraints:
//0 <= start < end <= 109
//At most 1000 calls will be made to book.