package P1701_1800.P1758_Minimum_Changes_To_Make_Alternating_Binary_String;

public class Main {

    public static void main(String[] args) {
        System.out.println(minOperations("0100")); // Output 1
        System.out.println(minOperations("10")); // Output 0
        System.out.println(minOperations("1111")); // Output 2
    }

    public static int minOperations(String s) {
        char[] chars = s.toCharArray();
        int count1 = 0; // кількість операцій для того, щоб рядок починався з '1'
        int count2 = 0; // кількість операцій для того, щоб рядок починався з '0'

        for (int i = 0; i < chars.length; i++) {
            //чи символ на парному індексі відрізняється від '1'
            count1 += (i % 2) ^ (chars[i] - '0');

            // чи символ на парному індексі відрізняється від '0'
            count2 += ((i + 1) % 2) ^ (chars[i] - '0');
        }

        // pозрахунок мінімальної кількості операцій
        return Math.min(count1, count2);
    }

}

//You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0'
// to '1' or vice versa.
//The string is called alternating if no two adjacent characters are equal. For example, the string "010" is
// alternating, while the string "0100" is not.
//Return the minimum number of operations needed to make s alternating.

//Example 1:
//Input: s = "0100"
//Output: 1
//Explanation: If you change the last character to '1', s will be "0101", which is alternating.

//Example 2:
//Input: s = "10"
//Output: 0
//Explanation: s is already alternating.

//Example 3:
//Input: s = "1111"
//Output: 2
//Explanation: You need two operations to reach "0101" or "1010".

//Constraints:
//1 <= s.length <= 104
//s[i] is either '0' or '1'.
