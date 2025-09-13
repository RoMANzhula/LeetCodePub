package P3501_3600.P3541_Find_Most_Frequent_Vowel_and_Consonant;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxFreqSum("successes")); // 6
        System.out.println(solution.maxFreqSum("aeiaeia"));   // 3
        System.out.println(solution.maxFreqSum("zzzz"));      // 4 (no vowels, so 0 + 4)
        System.out.println(solution.maxFreqSum("a"));         // 1 (only vowel, so 1 + 0)
    }

    public int maxFreqSum(String s) {
        // define vowels set for quick lookup
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        // frequency map for all characters
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;

        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            char ch = entry.getKey();
            int count = entry.getValue();

            if (vowels.contains(ch)) {
                maxVowelFreq = Math.max(maxVowelFreq, count);
            } else {
                maxConsonantFreq = Math.max(maxConsonantFreq, count);
            }
        }

        return maxVowelFreq + maxConsonantFreq;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given a string s consisting of lowercase English letters ('a' to 'z').
//Your task is to:
//Find the vowel (one of 'a', 'e', 'i', 'o', or 'u') with the maximum frequency.
//Find the consonant (all other letters excluding vowels) with the maximum frequency.
//Return the sum of the two frequencies.
//Note: If multiple vowels or consonants have the same maximum frequency, you may choose any one of them. If
// there are no vowels or no consonants in the string, consider their frequency as 0.
//The frequency of a letter x is the number of times it occurs in the string.

//Example 1:
//Input: s = "successes"
//Output: 6
//Explanation:
//The vowels are: 'u' (frequency 1), 'e' (frequency 2). The maximum frequency is 2.
//The consonants are: 's' (frequency 4), 'c' (frequency 2). The maximum frequency is 4.
//The output is 2 + 4 = 6.

//Example 2:
//Input: s = "aeiaeia"
//Output: 3
//Explanation:
//The vowels are: 'a' (frequency 3), 'e' ( frequency 2), 'i' (frequency 2). The maximum frequency is 3.
//There are no consonants in s. Hence, maximum consonant frequency = 0.
//The output is 3 + 0 = 3.

//Constraints:
//1 <= s.length <= 100
//s consists of lowercase English letters only.
