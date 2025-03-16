package P2501_2600.P2594_Minimum_Time_to_Repair_Cars;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] ranks1 = {4, 2, 3, 1};
        int cars1 = 10;
        System.out.println(solution.repairCars(ranks1, cars1)); // Output: 16

        int[] ranks2 = {5, 1, 8};
        int cars2 = 6;
        System.out.println(solution.repairCars(ranks2, cars2)); // Output: 16
    }

    public long repairCars(int[] ranks, int cars) {
        Arrays.sort(ranks);
        long left = 1, right = (long) ranks[0] * cars * cars; // upper bound for time

        while (left < right) {
            long middle = left + ((right - left) >> 2);

            if (canRepairInTime(ranks, cars, middle)) right = middle; // try to minimize time
            else left = middle + 1;

        }

        return left;
    }

    private boolean canRepairInTime(int[] ranks, int cars, long time) {
        int count = 0;

        for (int rank : ranks) {
            long n = (long) Math.sqrt(time / rank);

            count += n;

            if (count >= cars) return true;
        }

        return false;
    }

}

//Explanation:
//We sort the ranks array to prioritize lower ranks.
//We use binary search to find the minimum feasible time.
//The canRepairInTime function calculates the total number of cars repaired within time.
//The time complexity is O(n log (cars² * minRank)), which is efficient given the constraints.
//Complexity Analysis:
//Sorting the array takes O(n log n).
//The binary search runs in O(log (cars² * minRank)).
//For each mid value, checking feasibility runs in O(n).
//Overall complexity is O(n log (cars² * minRank)), which is feasible for large inputs.


//You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith
// mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.
//You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.
//Return the minimum time taken to repair all the cars.
//Note: All the mechanics can repair the cars simultaneously.

//Example 1:
//Input: ranks = [4,2,3,1], cars = 10
//Output: 16
//Explanation:
//- The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
//- The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
//- The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
//- The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
//It can be proved that the cars cannot be repaired in less than 16 minutes.

//Example 2:
//Input: ranks = [5,1,8], cars = 6
//Output: 16
//Explanation:
//- The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
//- The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
//- The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
//It can be proved that the cars cannot be repaired in less than 16 minutes.

//Constraints:
//1 <= ranks.length <= 105
//1 <= ranks[i] <= 100
//1 <= cars <= 106
