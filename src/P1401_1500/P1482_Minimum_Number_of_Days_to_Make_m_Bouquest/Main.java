package P1401_1500.P1482_Minimum_Number_of_Days_to_Make_m_Bouquest;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] bloomDay1 = {1, 10, 3, 10, 2};
        int m1 = 3, k1 = 1;
        System.out.println(solution.minDays(bloomDay1, m1, k1)); // Output: 3

        int[] bloomDay2 = {1, 10, 3, 10, 2};
        int m2 = 3, k2 = 2;
        System.out.println(solution.minDays(bloomDay2, m2, k2)); // Output: -1

        int[] bloomDay3 = {7, 7, 7, 7, 12, 7, 7};
        int m3 = 2, k3 = 3;
        System.out.println(solution.minDays(bloomDay3, m3, k3)); // Output: 12
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if ( (long) m * k > n) return -1; // Not enough flowers to make m bouquets

        int left = 1;
        int right = 0;
        for (int day : bloomDay) {
            right = Math.max(right, day);
        }

        while (left < right) {
            int mid = (left + right) / 2;
            if (canMakeBouquets(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canMakeBouquets(int[] bloomDay, int m, int k, int days) {
        int bouquets = 0;
        int flowers = 0;

        for (int bloom : bloomDay) {
            if (bloom <= days) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }

        return bouquets >= m;
    }
}

//Explanation:
//Binary Search Setup:
//Initialize left to the minimum possible day (1).
//Initialize right to the maximum day in the bloomDay array.
//Binary Search Process:
//Calculate mid as the average of left and right.
//Use a helper method canMakeBouquets to check if it is possible to make m bouquets in mid days.
//If it is possible, adjust the right boundary to mid.
//If it is not possible, adjust the left boundary to mid + 1.
//Helper Method (canMakeBouquets):
//Iterate through the bloomDay array.
//Count the number of consecutive flowers that have bloomed by days.
//If the count of consecutive bloomed flowers equals k, increment the bouquet count and reset the flower count.
//If the bouquet count reaches m, return true.
//If the iteration ends and the bouquet count is less than m, return false.
//This approach ensures we efficiently find the minimum number of days required using binary search and a
// greedy counting method, making the solution both optimal and easy to understand.


//You are given an integer array bloomDay, an integer m and an integer k.
//You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
//The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used
// in exactly one bouquet.
//Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If
// it is impossible to make m bouquets return -1.
//
//Example 1:
//Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
//Output: 3
//Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower
// did not bloom in the garden.
//We need 3 bouquets each should contain 1 flower.
//After day 1: [x, _, _, _, _]   // we can only make one bouquet.
//After day 2: [x, _, _, _, x]   // we can only make two bouquets.
//After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.

//Example 2:
//Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
//Output: -1
//Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers
// so it is impossible to get the needed bouquets and we return -1.

//Example 3:
//Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
//Output: 12
//Explanation: We need 2 bouquets each should have 3 flowers.
//Here is the garden after the 7 and 12 days:
//After day 7: [x, x, x, x, _, x, x]
//We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet
// from the last three flowers that bloomed because they are not adjacent.
//After day 12: [x, x, x, x, x, x, x]
//It is obvious that we can make two bouquets in different ways.
//
//Constraints:
//bloomDay.length == n
//1 <= n <= 105
//1 <= bloomDay[i] <= 109
//1 <= m <= 106
//1 <= k <= n
