package P2001_2100.P2028_Find_Missing_Observations;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] rolls1 = {3, 2, 4, 3};
        int mean1 = 4;
        int n1 = 2;
        int[] result1 = solution.missingRolls(rolls1, mean1, n1);
        System.out.println(java.util.Arrays.toString(result1)); // Output: [6, 6]

        int[] rolls2 = {1, 5, 6};
        int mean2 = 3;
        int n2 = 4;
        int[] result2 = solution.missingRolls(rolls2, mean2, n2);
        System.out.println(java.util.Arrays.toString(result2)); // Output: [2, 3, 2, 2]

        int[] rolls3 = {1, 2, 3, 4};
        int mean3 = 6;
        int n3 = 4;
        int[] result3 = solution.missingRolls(rolls3, mean3, n3);
        System.out.println(java.util.Arrays.toString(result3)); // Output: []
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;

        // Calculate the total sum we need for all rolls
        int totalSum = mean * (n + m);

        // Calculate the sum of known rolls
        int currentSum = 0;
        for (int roll : rolls) {
            currentSum += roll;
        }

        // Calculate the sum we need for the missing rolls
        int missingSum = totalSum - currentSum;

        // Check if it's possible to have such missing rolls
        if (missingSum < n || missingSum > 6 * n) {
            return new int[0]; // Not possible to achieve the sum
        }

        // Distribute the missingSum across the n missing rolls
        int[] result = new int[n];
        int baseValue = missingSum / n;
        int remainder = missingSum % n;

        // Fill the result array
        for (int i = 0; i < n; i++) {
            result[i] = baseValue;
        }

        // Distribute the remainder to make the sum exact
        for (int i = 0; i < remainder; i++) {
            result[i]++;
        }

        return result;
    }

}

//Explanation:
//totalSum: The total sum needed for all n + m rolls is calculated as mean * (n + m).
//currentSum: This is the sum of the observed rolls in the rolls array.
//missingSum: The sum of the missing n rolls is computed as totalSum - currentSum.
//Feasibility check: We check if the missing sum can be achieved with the n missing rolls, ensuring it
// lies between n and n * 6.
//Base distribution: The missing sum is divided equally among the rolls. Any remainder is distributed by
// incrementing some of the rolls.
//Complexity:
//Time complexity: O(m + n), where m is the length of the rolls array, and n is the number of missing rolls.
//Space complexity: O(n) for storing the result.


//You have observations of n + m 6-sided dice rolls with each face numbered from 1 to 6. n of the observations went
// missing, and you only have the observations of m rolls. Fortunately, you have also calculated the average value of
// the n + m rolls.
//You are given an integer array rolls of length m where rolls[i] is the value of the ith observation. You are also
// given the two integers mean and n.
//Return an array of length n containing the missing observations such that the average value of the n + m rolls is
// exactly mean. If there are multiple valid answers, return any of them. If no such array exists,
// return an empty array.
//The average value of a set of k numbers is the sum of the numbers divided by k.
//Note that mean is an integer, so the sum of the n + m rolls should be divisible by n + m.
//
//Example 1:
//Input: rolls = [3,2,4,3], mean = 4, n = 2
//Output: [6,6]
//Explanation: The mean of all n + m rolls is (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4.

//Example 2:
//Input: rolls = [1,5,6], mean = 3, n = 4
//Output: [2,3,2,2]
//Explanation: The mean of all n + m rolls is (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3.

//Example 3:
//Input: rolls = [1,2,3,4], mean = 6, n = 4
//Output: []
//Explanation: It is impossible for the mean to be 6 no matter what the 4 missing rolls are.
//
//Constraints:
//m == rolls.length
//1 <= n, m <= 105
//1 <= rolls[i], mean <= 6
