package P3201_3300.P3228_Maximum_Number_of_Operations_to_Move_Ones_to_the_End;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxOperations("1001101")); // Output: 4
        System.out.println(solution.maxOperations("00111"));   // Output: 0
        System.out.println(solution.maxOperations("010010"));  // Output: 3
    }

    public int maxOperations(String s) {
        int ans = 0;
        int cnt = 0;               // number of '1's seen so far
        int n = s.length();

        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);

            if (c == '1') {
                ++cnt;
            } else if (i > 0 && s.charAt(i - 1) == '1') {
                ans += cnt;
            }
        }

        return ans;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given a binary string s.
//You can perform the following operation on the string any number of times:
//Choose any index i from the string where i + 1 < s.length such that s[i] == '1' and s[i + 1] == '0'.
//Move the character s[i] to the right until it reaches the end of the string or another '1'. For example, for
// s = "010010", if we choose i = 1, the resulting string will be s = "000110".
//Return the maximum number of operations that you can perform.

//Example 1:
//Input: s = "1001101"
//Output: 4
//Explanation:
//We can perform the following operations:
//Choose index i = 0. The resulting string is s = "0011101".
//Choose index i = 4. The resulting string is s = "0011011".
//Choose index i = 3. The resulting string is s = "0010111".
//Choose index i = 2. The resulting string is s = "0001111".

//Example 2:
//Input: s = "00111"
//Output: 0

//Constraints:
//1 <= s.length <= 105
//s[i] is either '0' or '1'.
