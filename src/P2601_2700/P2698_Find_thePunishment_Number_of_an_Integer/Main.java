package P2601_2700.P2698_Find_thePunishment_Number_of_an_Integer;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.punishmentNumber(10)); // Output: 182
        System.out.println(solution.punishmentNumber(37)); // Output: 1478
    }

    public int punishmentNumber(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int square = i * i;

            if (canPartition(String.valueOf(square), i, 0, 0)) {
                sum += square;
            }
        }

        return sum;
    }

    private boolean canPartition(String s, int target, int index, int currentSum) {
        int len = s.length();

        if (index == len) return currentSum == target;

        int num = 0;

        for (int i = index; i < len; i++) {
            num = num * 10 + (s.charAt(i) - '0');

            if (canPartition(s, target, i + 1, currentSum + num)) {
                return true;
            }
        }

        return false;
    }

}

//The goal is to find numbers whose squares (i * i) can be split into parts that add up to i.
//Steps in the solution:
//Loop through all numbers from 1 to n.
//Calculate the square of each number (square = i * i).
//Check if the square can be split into parts that sum up to i.
//If the condition is met, add square to the total sum.
//Return the total sum at the end.
//Complexity: O(n^3)


//Given a positive integer n, return the punishment number of n.
//The punishment number of n is defined as the sum of the squares of all integers i such that:
//1 <= i <= n
//The decimal representation of i * i can be partitioned into contiguous substrings such that the sum of the
// integer values of these substrings equals i.
//
//Example 1:
//Input: n = 10
//Output: 182
//Explanation: There are exactly 3 integers i in the range [1, 10] that satisfy the conditions in the statement:
//- 1 since 1 * 1 = 1
//- 9 since 9 * 9 = 81 and 81 can be partitioned into 8 and 1 with a sum equal to 8 + 1 == 9.
//- 10 since 10 * 10 = 100 and 100 can be partitioned into 10 and 0 with a sum equal to 10 + 0 == 10.
//Hence, the punishment number of 10 is 1 + 81 + 100 = 182

//Example 2:
//Input: n = 37
//Output: 1478
//Explanation: There are exactly 4 integers i in the range [1, 37] that satisfy the conditions in the statement:
//- 1 since 1 * 1 = 1.
//- 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1.
//- 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0.
//- 36 since 36 * 36 = 1296 and 1296 can be partitioned into 1 + 29 + 6.
//Hence, the punishment number of 37 is 1 + 81 + 100 + 1296 = 1478
//
//Constraints:
//1 <= n <= 1000