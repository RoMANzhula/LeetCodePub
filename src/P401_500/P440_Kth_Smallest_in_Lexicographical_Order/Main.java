package P401_500.P440_Kth_Smallest_in_Lexicographical_Order;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 13;
        int k1 = 2;
        int result1 = solution.findKthNumber(n1, k1);
        System.out.println("Example 1: n = " + n1 + ", k = " + k1 + " -> " + result1);  // Output: 10

        int n2 = 1;
        int k2 = 1;
        int result2 = solution.findKthNumber(n2, k2);
        System.out.println("Example 2: n = " + n2 + ", k = " + k2 + " -> " + result2);  // Output: 1
    }

    public int findKthNumber(int n, int k) {
        int current = 1;
        k--;  // because we start with the first number, decrement k to make it zero-indexed

        while (k > 0) {
            long step = calculateSteps(n, current, current + 1);
            if (step <= k) {
                // Move to the next sibling
                current++;
                k -= step;
            } else {
                // Go deeper into the prefix tree
                current *= 10;
                k--;
            }
        }

        return current;
    }

    private long calculateSteps(int n, long first, long next) {
        long steps = 0;
        while (first <= n) {
            steps += Math.min(n + 1, next) - first;
            first *= 10;
            next *= 10;
        }
        return steps;
    }
}


//Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
//
//Example 1:
//Input: n = 13, k = 2
//Output: 10
//Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the
// second smallest number is 10.

//Example 2:
//Input: n = 1, k = 1
//Output: 1
//
//Constraints:
//1 <= k <= n <= 109