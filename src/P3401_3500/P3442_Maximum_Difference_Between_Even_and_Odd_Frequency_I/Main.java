package P3401_3500.P3442_Maximum_Difference_Between_Even_and_Odd_Frequency_I;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "aaaaabbc";
        String s2 = "abcabcab";

        System.out.println("Output: " + solution.maxDifference(s1)); // Expected: 3
        System.out.println("Output: " + solution.maxDifference(s2)); // Expected: 1
    }

    public int maxDifference(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();

        // count frequencies of each character
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        int maxOdd = Integer.MIN_VALUE;
        int minEven = Integer.MAX_VALUE;

        // find max frequency with odd count and min frequency with even count
        for (int freq : freqMap.values()) {
            if (freq % 2 == 1) { // odd
                maxOdd = Math.max(maxOdd, freq);
            } else { // even
                minEven = Math.min(minEven, freq);
            }
        }

        // return the required maximum difference
        return maxOdd - minEven;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given a string s consisting of lowercase English letters.
//Your task is to find the maximum difference diff = freq(a1) - freq(a2) between the frequency of characters a1 and
// a2 in the string such that:
//a1 has an odd frequency in the string.
//a2 has an even frequency in the string.
//Return this maximum difference.

//Example 1:
//Input: s = "aaaaabbc"
//Output: 3
//Explanation:
//The character 'a' has an odd frequency of 5, and 'b' has an even frequency of 2.
//The maximum difference is 5 - 2 = 3.

//Example 2:
//Input: s = "abcabcab"
//Output: 1
//Explanation:
//The character 'a' has an odd frequency of 3, and 'c' has an even frequency of 2.
//The maximum difference is 3 - 2 = 1.

//Constraints:
//3 <= s.length <= 100
//s consists only of lowercase English letters.
//s contains at least one character with an odd frequency and one with an even frequency.
