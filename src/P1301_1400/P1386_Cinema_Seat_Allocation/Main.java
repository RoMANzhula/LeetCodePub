package P1301_1400.P1386_Cinema_Seat_Allocation;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n = 3;
        int[][] reservedSeats = {
                {1, 2},
                {1, 3},
                {1, 8},
                {2, 6},
                {3, 1},
                {3, 10}
        };

        System.out.println(solution.maxNumberOfFamilies(n, reservedSeats)); // 4
    }

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // row -> bitmask of reserved seats
        Map<Integer, Integer> reserved = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // bit (col - 1) represents seat col
            reserved.put(row, reserved.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        // seats 2,3,4,5
        int left = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4);

        // seats 4,5,6,7
        int middle = (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6);

        // seats 6,7,8,9
        int right = (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8);

        int result = (n - reserved.size()) * 2;

        for (int mask : reserved.values()) {
            boolean canUseLeft = (mask & left) == 0;
            boolean canUseMiddle = (mask & middle) == 0;
            boolean canUseRight = (mask & right) == 0;

            if (canUseLeft && canUseRight) {
                // two non-overlapping groups: 2-5 and 6-9
                result += 2;
            } else if (canUseLeft || canUseMiddle || canUseRight) {
                // at least one group can be placed
                result += 1;
            }
        }

        return result;
    }

}

//Complexity:
// time and space - O(reservedSeats.length)


//A cinema has n rows of seats, numbered from 1 to n. Each row has 10 seats, numbered from 1 to 10.
//You are given a 2D integer array reservedSeats, where reservedSeats[i] = [rowi, seati] means that seat seati in
// row rowi is already reserved.
//A four-person group must be assigned to four seats in the same row. The group can be seated in one of the
// following seat blocks:
//seats 2, 3, 4, 5
//seats 4, 5, 6, 7
//seats 6, 7, 8, 9
//A block can be used only if none of its seats are reserved. Each seat can be assigned to at most one group.
//Return an integer denoting the maximum number of four-person groups that can be assigned.

//Example 1:
//Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
//Output: 4
//Explanation: The figure above shows an optimal allocation of four groups. Seats marked in blue are already
// reserved, and each set of four contiguous seats marked in orange is assigned to one group.

//Example 2:
//Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
//Output: 2

//Example 3:
//Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
//Output: 4

//Constraints:
//1 <= n <= 109
//1 <= reservedSeats.length <= min(10 * n, 104)
//reservedSeats[i] == [rowi, seati]
//1 <= rowi <= n
//1 <= seati <= 10
//All reservedSeats[i] are distinct.
