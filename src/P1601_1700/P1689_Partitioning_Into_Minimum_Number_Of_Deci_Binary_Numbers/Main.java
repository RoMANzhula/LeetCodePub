package P1601_1700.P1689_Partitioning_Into_Minimum_Number_Of_Deci_Binary_Numbers;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minPartitions("32")); // 3
        System.out.println(solution.minPartitions("82734")); // 8
        System.out.println(solution.minPartitions("27346209830709182346")); // 9
    }

    public int minPartitions(String n) {
        int maxDigit = 0;

        for (int i = 0; i < n.length(); i++) {
            int digit = n.charAt(i) - '0';
            maxDigit = Math.max(maxDigit, digit);

            // if we find 9 - stop early
            if (maxDigit == 9) {
                return 9;
            }
        }

        return maxDigit;
    }

}

//Complexity:
// time - o(n)
// space - O(1)


//A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros. For
// example, 101 and 1100 are deci-binary, while 112 and 3001 are not.
//Given a string n that represents a positive decimal integer, return the minimum number of positive deci-binary
// numbers needed so that they sum up to n.

//Example 1:
//Input: n = "32"
//Output: 3
//Explanation: 10 + 11 + 11 = 32

//Example 2:
//Input: n = "82734"
//Output: 8

//Example 3:
//Input: n = "27346209830709182346"
//Output: 9

//Constraints:
//1 <= n.length <= 105
//n consists of only digits.
//n does not contain any leading zeros and represents a positive integer.
