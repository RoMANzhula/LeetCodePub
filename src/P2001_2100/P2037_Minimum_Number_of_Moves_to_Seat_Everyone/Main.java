package P2001_2100.P2037_Minimum_Number_of_Moves_to_Seat_Everyone;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] seats1 = {3, 1, 5};
        int[] students1 = {2, 7, 4};
        System.out.println(solution.minMovesToSeat(seats1, students1)); // Output: 4

        int[] seats2 = {4, 1, 5, 9};
        int[] students2 = {1, 3, 2, 6};
        System.out.println(solution.minMovesToSeat(seats2, students2)); // Output: 7

        int[] seats3 = {2, 2, 6, 6};
        int[] students3 = {1, 3, 2, 6};
        System.out.println(solution.minMovesToSeat(seats3, students3)); // Output: 4
    }

    public int minMovesToSeat(int[] seats, int[] students) {
        // Sort both arrays
        Arrays.sort(seats);
        Arrays.sort(students);

        int totalMoves = 0;

        // Sum up the absolute differences between the corresponding elements
        for (int i = 0; i < seats.length; i++) {
            totalMoves += Math.abs(seats[i] - students[i]);
        }

        return totalMoves; // bingo
    }
}

//Explanation:
//Sorting: By sorting both seats and students arrays, we can directly align students with the nearest possible seats,
// ensuring the minimal total movement.
//Calculating Moves: By iterating through both sorted arrays and calculating the absolute difference between
// corresponding elements, we get the minimum moves required for each student to reach a seat.
//The given implementation solves the problem efficiently with a time complexity ofO(n log n) due to the sorting
// step, followed by a linear pass through the arrays to calculate the total moves, which is
//O(n). Thus, the overall time complexity is O(n log n).

//There are n seats and n students in a room. You are given an array seats of length n, where seats[i] is the
// position of the ith seat. You are also given the array students of length n, where students[j] is
// the position of the jth student.
//You may perform the following move any number of times:
//Increase or decrease the position of the ith student by 1 (i.e., moving the ith student from
// position x to x + 1 or x - 1)
//Return the minimum number of moves required to move each student to a seat such that no two
// students are in the same seat.
//Note that there may be multiple seats or students in the same position at the beginning.
//
//Example 1:
//Input: seats = [3,1,5], students = [2,7,4]
//Output: 4
//Explanation: The students are moved as follows:
//- The first student is moved from from position 2 to position 1 using 1 move.
//- The second student is moved from from position 7 to position 5 using 2 moves.
//- The third student is moved from from position 4 to position 3 using 1 move.
//In total, 1 + 2 + 1 = 4 moves were used.

//Example 2:
//Input: seats = [4,1,5,9], students = [1,3,2,6]
//Output: 7
//Explanation: The students are moved as follows:
//- The first student is not moved.
//- The second student is moved from from position 3 to position 4 using 1 move.
//- The third student is moved from from position 2 to position 5 using 3 moves.
//- The fourth student is moved from from position 6 to position 9 using 3 moves.
//In total, 0 + 1 + 3 + 3 = 7 moves were used.

//Example 3:
//Input: seats = [2,2,6,6], students = [1,3,2,6]
//Output: 4
//Explanation: Note that there are two seats at position 2 and two seats at position 6.
//The students are moved as follows:
//- The first student is moved from from position 1 to position 2 using 1 move.
//- The second student is moved from from position 3 to position 6 using 3 moves.
//- The third student is not moved.
//- The fourth student is not moved.
//In total, 1 + 3 + 0 + 0 = 4 moves were used.
//
//Constraints:
//n == seats.length == students.length
//1 <= n <= 100
//1 <= seats[i], students[j] <= 100
