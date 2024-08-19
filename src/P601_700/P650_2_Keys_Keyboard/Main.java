package P601_700.P650_2_Keys_Keyboard;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minSteps(3)); // Output: 3
        System.out.println(solution.minSteps(1)); // Output: 0
        System.out.println(solution.minSteps(6)); // Output: 5
        System.out.println(solution.minSteps(9)); // Output: 6
    }

    public int minSteps(int n) {
        int result = 0;
        int factor = 2;

        while (n > 1) {
            while (n % factor == 0) {
                result += factor;
                n /= factor;
            }
            factor++;
        }

        return result;
    }
}

//Explanation:
//While Loop: The loop continues as long as n > 1.
//Inner While Loop: Divides n by the smallest factor (factor) and adds the factor to the result, effectively
// counting the number of operations needed.
//Increment Factor: Move to the next possible factor and repeat until n becomes 1.
//This approach ensures the solution is optimal, and the algorithm runs efficiently for the given constraints.


//There is only one character 'A' on the screen of a notepad. You can perform one of two operations on
// this notepad for each step:
//Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
//Paste: You can paste the characters which are copied last time.
//Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.
//
//Example 1:
//Input: n = 3
//Output: 3
//Explanation: Initially, we have one character 'A'.
//In step 1, we use Copy All operation.
//In step 2, we use Paste operation to get 'AA'.
//In step 3, we use Paste operation to get 'AAA'.

//Example 2:
//Input: n = 1
//Output: 0
//
//Constraints:
//1 <= n <= 1000