package P3701_3800.P3783_Mirror_Distance_of_an_Integer;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.mirrorDistance(25)); // 27
        System.out.println(solution.mirrorDistance(10)); // 9
        System.out.println(solution.mirrorDistance(7));  // 0
    }

    public int mirrorDistance(int n) {
        int reversed = reverseNumber(n);
        return Math.abs(n - reversed);
    }

    private static int reverseNumber(int n) {
        int reversed = 0;

        while (n > 0) {
            int digit = n % 10;
            reversed = reversed * 10 + digit;
            n /= 10;
        }

        return reversed;
    }

}

//Complexity:
// time - O(num of digits)
// space - O(1)


//You are given an integer n.
//Define its mirror distance as: abs(n - reverse(n)) where reverse(n) is the integer formed by reversing the
// digits of n.
//Return an integer denoting the mirror distance of n.
//abs(x) denotes the absolute value of x.

//Example 1:
//Input: n = 25
//Output: 27
//Explanation:
//reverse(25) = 52.
//Thus, the answer is abs(25 - 52) = 27.

//Example 2:
//Input: n = 10
//Output: 9
//Explanation:
//reverse(10) = 01 which is 1.
//Thus, the answer is abs(10 - 1) = 9.

//Example 3:
//Input: n = 7
//Output: 0
//Explanation:
//reverse(7) = 7.
//Thus, the answer is abs(7 - 7) = 0.

//Constraints:
//1 <= n <= 109
