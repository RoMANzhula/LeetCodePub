package P1801_1900.P1845_Seat_Reservation_Manager;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        SeatManager seatManager = new SeatManager(5);
        System.out.println(seatManager.reserve());    // 1
        System.out.println(seatManager.reserve());    // 2
        seatManager.unreserve(2);
        System.out.println(seatManager.reserve());    // 2
        System.out.println(seatManager.reserve());    // 3
        System.out.println(seatManager.reserve());    // 4
        System.out.println(seatManager.reserve());    // 5
        seatManager.unreserve(5);
    }

}

class SeatManager {
    private boolean[] seats;
    private int n;
    private int nextAvailable;

    public SeatManager(int n) {
        this.n = n;
        seats = new boolean[n];
        nextAvailable = 1;
    }

    public int reserve() {
        while (nextAvailable <= n && seats[nextAvailable - 1]) {
            nextAvailable++;
        }
        if (nextAvailable <= n) {
            seats[nextAvailable - 1] = true;
            return nextAvailable++;
        }
        return -1; //no available seats
    }

    public void unreserve(int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= n) {
            seats[seatNumber - 1] = false;
            if (seatNumber < nextAvailable) {
                nextAvailable = seatNumber;
            }
        }
    }

}


//class SeatManager {
//    private PriorityQueue<Integer> availableSeats;
//
//    public SeatManager(int n) {
//        availableSeats = new PriorityQueue<>();
//        for (int i = 1; i <= n; i++) {
//            availableSeats.offer(i);
//        }
//    }
//
//    public int reserve() {
//        return availableSeats.poll();
//    }
//
//    public void unreserve(int seatNumber) {
//        availableSeats.offer(seatNumber);
//    }
//}



//Design a system that manages the reservation state of n seats that are numbered from 1 to n.
//Implement the SeatManager class:
//SeatManager(int n) Initializes a SeatManager object that will manage n seats numbered from 1 to n. All seats are
// initially available.
//int reserve() Fetches the smallest-numbered unreserved seat, reserves it, and returns its number.
//void unreserve(int seatNumber) Unreserves the seat with the given seatNumber.

//Example 1:
//Input
//["SeatManager", "reserve", "reserve", "unreserve", "reserve", "reserve", "reserve", "reserve", "unreserve"]
//[[5], [], [], [2], [], [], [], [], [5]]
//Output
//[null, 1, 2, null, 2, 3, 4, 5, null]
//Explanation
//SeatManager seatManager = new SeatManager(5); // Initializes a SeatManager with 5 seats.
//seatManager.reserve();    // All seats are available, so return the lowest numbered seat, which is 1.
//seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
//seatManager.unreserve(2); // Unreserve seat 2, so now the available seats are [2,3,4,5].
//seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
//seatManager.reserve();    // The available seats are [3,4,5], so return the lowest of them, which is 3.
//seatManager.reserve();    // The available seats are [4,5], so return the lowest of them, which is 4.
//seatManager.reserve();    // The only available seat is seat 5, so return 5.
//seatManager.unreserve(5); // Unreserve seat 5, so now the available seats are [5].

//Constraints:
//1 <= n <= 105
//1 <= seatNumber <= n
//For each call to reserve, it is guaranteed that there will be at least one unreserved seat.
//For each call to unreserve, it is guaranteed that seatNumber will be reserved.
//At most 105 calls in total will be made to reserve and unreserve.
