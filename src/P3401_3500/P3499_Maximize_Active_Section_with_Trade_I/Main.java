package P3401_3500.P3499_Maximize_Active_Section_with_Trade_I;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxActiveSectionsAfterTrade("01"));       // 1
        System.out.println(solution.maxActiveSectionsAfterTrade("0100"));     // 4
        System.out.println(solution.maxActiveSectionsAfterTrade("1000100"));  // 7
        System.out.println(solution.maxActiveSectionsAfterTrade("01010"));    // 4
    }

    public int maxActiveSectionsAfterTrade(String s) {
        int originalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                originalOnes++;
            }
        }

        String t = "1" + s + "1";

        List<Character> chars = new ArrayList<>();
        List<Integer> lens = new ArrayList<>();

        int i = 0;
        while (i < t.length()) {
            char ch = t.charAt(i);
            int j = i;

            while (j < t.length() && t.charAt(j) == ch) {
                j++;
            }

            chars.add(ch);
            lens.add(j - i);

            i = j;
        }

        int answer = originalOnes;

        for (int k = 1; k + 1 < chars.size(); k++) {
            if (chars.get(k) == '1'
                    && chars.get(k - 1) == '0'
                    && chars.get(k + 1) == '0') {

                int gain = lens.get(k - 1) + lens.get(k + 1);
                answer = Math.max(answer, originalOnes + gain);
            }
        }

        return answer;
    }

}

//Complexity:
// time and space - O(n)


//You are given a binary string s of length n, where:
//'1' represents an active section.
//'0' represents an inactive section.
//You can perform at most one trade to maximize the number of active sections in s. In a trade, you:
//Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
//Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
//Return the maximum number of active sections in s after making the optimal trade.
//Note: Treat s as if it is augmented with a '1' at both ends, forming t = '1' + s + '1'. The augmented '1's do not
// contribute to the final count.

//Example 1:
//Input: s = "01"
//Output: 1
//Explanation:
//Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active
// sections is 1.

//Example 2:
//Input: s = "0100"
//Output: 4
//Explanation:
//String "0100" → Augmented to "101001".
//Choose "0100", convert "101001" → "100001" → "111111".
//The final string without augmentation is "1111". The maximum number of active sections is 4.

//Example 3:
//Input: s = "1000100"
//Output: 7
//Explanation:
//String "1000100" → Augmented to "110001001".
//Choose "000100", convert "110001001" → "110000001" → "111111111".
//The final string without augmentation is "1111111". The maximum number of active sections is 7.

//Example 4:
//Input: s = "01010"
//Output: 4
//Explanation:
//String "01010" → Augmented to "1010101".
//Choose "010", convert "1010101" → "1000101" → "1111101".
//The final string without augmentation is "11110". The maximum number of active sections is 4.
//Constraints:
//1 <= n == s.length <= 105
//s[i] is either '0' or '1'
