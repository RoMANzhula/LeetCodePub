package P1801_1900.P1888_Minimum_Numbeof_Flips_to_Make_the_Binary_String_Alternating;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minFlips("111000")); // 2
        System.out.println(solution.minFlips("010"));    // 0
        System.out.println(solution.minFlips("1110"));   // 1
    }

    public int minFlips(String s) {
        int n = s.length();
        String s2 = s + s;

        int diff1 = 0; // mismatches with pattern 010101....
        int diff2 = 0; // mismatches with pattern 101010...

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < s2.length(); i++) {
            char expected1 = (i % 2 == 0) ? '0' : '1';
            char expected2 = (i % 2 == 0) ? '1' : '0';

            if (s2.charAt(i) != expected1) diff1++;
            if (s2.charAt(i) != expected2) diff2++;

            // shrink window if larger than n
            if (i >= n) {
                char prev = s2.charAt(i - n);

                char prevExpected1 = ((i - n) % 2 == 0) ? '0' : '1';
                char prevExpected2 = ((i - n) % 2 == 0) ? '1' : '0';

                if (prev != prevExpected1) diff1--;
                if (prev != prevExpected2) diff2--;
            }

            // valid window
            if (i >= n - 1) {
                result = Math.min(result, Math.min(diff1, diff2));
            }
        }

        return result;
    }

}

//Complexity:
// time and space - O(n)


//You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
//Type-1: Remove the character at the start of the string s and append it to the end of the string.
//Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
//Return the minimum number of type-2 operations you need to perform such that s becomes alternating.
//The string is called alternating if no two adjacent characters are equal.
//For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

//Example 1:
//Input: s = "111000"
//Output: 2
//Explanation: Use the first operation two times to make s = "100011".
//Then, use the second operation on the third and sixth elements to make s = "101010".

//Example 2:
//Input: s = "010"
//Output: 0
//Explanation: The string is already alternating.

//Example 3:
//Input: s = "1110"
//Output: 1
//Explanation: Use the second operation on the second element to make s = "1010".

//Constraints:
//1 <= s.length <= 105
//s[i] is either '0' or '1'.
