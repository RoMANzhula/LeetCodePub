package P301_400.P326_Power_of_Three;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.isPowerOfThree(27)); // true
        System.out.println(solution.isPowerOfThree(0));  // false
        System.out.println(solution.isPowerOfThree(-1)); // false
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

}

//Complexity:
// time O(log3 n)
// space O(1)


//Given an integer n, return true if it is a power of three. Otherwise, return false.
//An integer n is a power of three, if there exists an integer x such that n == 3x.

//Example 1:
//Input: n = 27
//Output: true
//Explanation: 27 = 33

//Example 2:
//Input: n = 0
//Output: false
//Explanation: There is no x where 3x = 0.

//Example 3:
//Input: n = -1
//Output: false
//Explanation: There is no x where 3x = (-1).

//Constraints:
//-231 <= n <= 231 - 1
