package P1701_1800.P1780_Check_if_Number_is_a_Sum_of_Powers_of_Three;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.checkPowersOfThree(12)); // true
        System.out.println(solution.checkPowersOfThree(91)); // true
        System.out.println(solution.checkPowersOfThree(21)); // false
    }

    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) return false;

            n /= 3; // reduce "n" by dividing by 3
        }

        return true;
    }

}

//Explanation:
//Convert n to base-3 using n % 3 (to extract digits) and n /= 3 (to shift).
//If any digit is 2, return false.
//Otherwise, return true.
//Time Complexity:
//O(log3 n) → Since we divide n by 3 in each step, the number of iterations is proportional to log3 n


//Given an integer n, return true if it is possible to represent n as the sum of distinct powers of
// three. Otherwise, return false.
//An integer y is a power of three if there exists an integer x such that y == 3x.

//Example 1:
//Input: n = 12
//Output: true
//Explanation: 12 = 31 + 32

//Example 2:
//Input: n = 91
//Output: true
//Explanation: 91 = 30 + 32 + 34

//Example 3:
//Input: n = 21
//Output: false

//Constraints:
//1 <= n <= 107
