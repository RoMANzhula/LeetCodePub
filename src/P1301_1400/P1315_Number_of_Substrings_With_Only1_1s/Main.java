package P1301_1400.P1315_Number_of_Substrings_With_Only1_1s;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numSub("0110111")); // 9
        System.out.println(solution.numSub("101"));     // 2
        System.out.println(solution.numSub("111111"));  // 21
    }


    static final long MOD = 1_000_000_007;

    public int numSub(String s) {
        long count = 0;       // current streak of 1's
        long result = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                count++;
            } else {
                // end of a streak
                result = (result + count * (count + 1) / 2) % MOD;
                count = 0;
            }
        }

        // add the last streak if it ends with '1'
        result = (result + count * (count + 1) / 2) % MOD;

        return (int) result;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too large,
// return it modulo 109 + 7.

//Example 1:
//Input: s = "0110111"
//Output: 9
//Explanation: There are 9 substring in total with only 1's characters.
//"1" -> 5 times.
//"11" -> 3 times.
//"111" -> 1 time.

//Example 2:
//Input: s = "101"
//Output: 2
//Explanation: Substring "1" is shown 2 times in s.

//Example 3:
//Input: s = "111111"
//Output: 21
//Explanation: Each substring contains only 1's characters.

//Constraints:
//1 <= s.length <= 105
//s[i] is either '0' or '1'.
