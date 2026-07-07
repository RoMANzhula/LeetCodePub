package P3701_3800.P3754_Concatenate_Non_Zero_Digits_and_Multiply_by_Sum_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.sunAndMultiply(10203004)); // 12340
        System.out.println(solution.sunAndMultiply(1000)); // 1
    }

    public long sunAndMultiply(int n) {
        String toStr = String.valueOf(n);

        long result = 0;
        int sum = 0;

        for (char c : toStr.toCharArray()) {
            int digit = c - '0';

            if (digit != 0) {
                result = result * 10 + digit;
                sum += digit;
            }
        }

        return (result * sum);
    }

}

// Complexity:
// time and space - O(<= 10)


//You are given an integer n.
//Form a new integer x by concatenating all the non-zero digits of n in their original order. If there are no
// non-zero digits, x = 0.
//Let sum be the sum of digits in x.
//Return an integer representing the value of x * sum.

//Example 1:
//Input: n = 10203004
//Output: 12340
//Explanation:
//The non-zero digits are 1, 2, 3, and 4. Thus, x = 1234.
//The sum of digits is sum = 1 + 2 + 3 + 4 = 10.
//Therefore, the answer is x * sum = 1234 * 10 = 12340.

//Example 2:
//Input: n = 1000
//Output: 1
//Explanation:
//The non-zero digit is 1, so x = 1 and sum = 1.
//Therefore, the answer is x * sum = 1 * 1 = 1.

//Constraints:
//0 <= n <= 109
