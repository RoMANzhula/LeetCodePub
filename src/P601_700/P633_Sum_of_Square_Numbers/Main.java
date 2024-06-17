package P601_700.P633_Sum_of_Square_Numbers;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int c1 = 5;
        System.out.println(solution.judgeSquareSum(c1)); // Output: true

        int c2 = 3;
        System.out.println(solution.judgeSquareSum(c2)); // Output: false
    }

    public boolean judgeSquareSum(int c) {
        long a = 0;
        long b = (long) Math.sqrt(c);

        while (a <= b) {
            long sum = a * a + b * b;
            if (sum == c) {
                return true;
            } else if (sum < c) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }
}

//Explanation:
//Initialization:
//a is initialized to 0.
//b is initialized to the integer part of the square root of c (since b2 cannot be greater than c).
//Loop condition:
//The loop runs while a is less than or equal to b.
//Sum calculation and checks:
//If sum is equal to c, return true (since a and b are the required integers).
//If sum is less than c, increment a to increase the sum.
//If sum is greater than c, decrement b to decrease the sum.
//Return false:
//If the loop completes without finding a and b such that a2+b2=c, return false.
//This solution ensures that all possible pairs (a, b) are checked efficiently without redundant calculations.


//Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
//
//Example 1:
//Input: c = 5
//Output: true
//Explanation: 1 * 1 + 2 * 2 = 5

//Example 2:
//Input: c = 3
//Output: false
//
//Constraints:
//0 <= c <= 231 - 1