package P1001_1100.P1052_Grumpy_Bookstore_Owner;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int minutes = 3;
        System.out.println(solution.maxSatisfied(customers, grumpy, minutes)); // Output: 16

        int[] customers2 = {1};
        int[] grumpy2 = {0};
        int minutes2 = 1;
        System.out.println(solution.maxSatisfied(customers2, grumpy2, minutes2)); // Output: 1
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int baseSatisfied = 0;

        // Calculate the base satisfied customers (when the owner is not grumpy)
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                baseSatisfied += customers[i];
            }
        }

        // Use sliding window to find the maximum additional satisfied customers
        int maxAdditionalSatisfied = 0;
        int currentAdditionalSatisfied = 0;

        // Initialize the first window
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) {
                currentAdditionalSatisfied += customers[i];
            }
        }
        maxAdditionalSatisfied = currentAdditionalSatisfied;

        // Slide the window
        for (int i = minutes; i < n; i++) {
            if (grumpy[i] == 1) {
                currentAdditionalSatisfied += customers[i];
            }
            if (grumpy[i - minutes] == 1) {
                currentAdditionalSatisfied -= customers[i - minutes];
            }
            maxAdditionalSatisfied = Math.max(maxAdditionalSatisfied, currentAdditionalSatisfied);
        }

        return baseSatisfied + maxAdditionalSatisfied;
    }
}

//Explanation of the Code:
//Base Satisfied Customers Calculation: Loop through the customers and grumpy arrays to sum the customers
// when grumpy[i] == 0.
//Sliding Window for Additional Satisfied Customers: Initialize the sliding window for the first minutes elements
// and calculate the satisfied customers if the owner uses the secret technique during these minutes. Then slide
// the window one minute at a time, updating the count of additional satisfied customers and keeping track of
// the maximum value found.
//Return the Result: Sum the base satisfied customers and the maximum additional satisfied customers to
// get the final answer.


//There is a bookstore owner that has a store open for n minutes. Every minute, some number of customers enter
// the store. You are given an integer array customers of length n where customers[i] is the number of the
// customer that enters the store at the start of the ith minute and all those customers leave after
// the end of that minute.
//On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the
// bookstore owner is grumpy during the ith minute, and is 0 otherwise.
//When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.
//The bookstore owner knows a secret technique to keep themselves not grumpy for minutes consecutive minutes, but
// can only use it once.
//Return the maximum number of customers that can be satisfied throughout the day.
//
//Example 1:
//Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
//Output: 16
//Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
//The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

//Example 2:
//Input: customers = [1], grumpy = [0], minutes = 1
//Output: 1
//
//Constraints:
//n == customers.length == grumpy.length
//1 <= minutes <= n <= 2 * 104
//0 <= customers[i] <= 1000
//grumpy[i] is either 0 or 1.
