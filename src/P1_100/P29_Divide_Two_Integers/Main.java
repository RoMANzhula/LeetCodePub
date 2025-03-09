package P1_100.P29_Divide_Two_Integers;

public class Main {
    public static void main(String[] args) {
        int dividend = 2147483647;
        int divisor = -1;
        System.out.println(divide(dividend, divisor));
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) { // to avoid integer overflow when dividing the minimum
            // possible Integer value (-2^31) by -1
            return Integer.MAX_VALUE; // maximum possible Integer value
        }

        if (divisor == 1) {
            return dividend;
        }

        if (divisor == -1) {
            return -dividend; // return the dividend with a "-" sign
        }

        if (divisor == dividend) {
            return 1;
        }

        long result = 0;

        long mathAbs = Math.abs((long) divisor); // to store the absolute (positive)
        long mathAbs2 = Math.abs((long) dividend); //to store the absolute (positive)

        for (long i = mathAbs2; i > 0; i = i - mathAbs) {
            result++;
        }
        if (mathAbs2 % mathAbs != 0) { // to prevent an extra unit in the result when an integer division
            // does not yield a whole number, we subtract one from the result counter
            result = result - 1; // if there is a remainder in division, subtract one iteration from the result counter
        }

        String str = String.valueOf(divisor); // convert the divisor to a string
        char[] ch = str.toCharArray(); // convert the string to a character array
        String str2 = String.valueOf(dividend); // convert the dividend to a string
        char[] ch2 = str2.toCharArray(); // convert the string to a character array

        if (ch[0] == '-' && ch2[0] == '-') { // if both the divisor and dividend have a minus sign
            return (int) result;
        } else if (ch[0] == '-' || ch2[0] == '-') { // otherwise, if either the divisor or dividend has a minus sign
            return -((int) result); // the result with a minus sign
        } else { // In other cases
            return (int) result; // the result counter cast to int
        }
    }
}



//Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
//The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would
// be truncated to 8, and -2.7335 would be truncated to -2.
//Return the quotient after dividing dividend by divisor.
//Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer
// range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1,
// and if the quotient is strictly less than -231, then return -231.

//Example 1:
//Input: dividend = 10, divisor = 3
//Output: 3
//Explanation: 10/3 = 3.33333.. which is truncated to 3.

//Example 2:
//Input: dividend = 7, divisor = -3
//Output: -2
//Explanation: 7/-3 = -2.33333.. which is truncated to -2.

//Constraints:
//-231 <= dividend, divisor <= 231 - 1
//divisor != 0