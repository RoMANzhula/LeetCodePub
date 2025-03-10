package P3301_3400.P3306_Count_of_Substrings_Containing_Every_Vowel_and_K_Consonants_II;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countOfSubstrings("aeioqq", 1)); // Output: 0
        System.out.println(solution.countOfSubstrings("aeiou", 0)); // Output: 1
        System.out.println(solution.countOfSubstrings("ieaouqqieaouqq", 1)); // Output: 3
    }

    public long countOfSubstrings(String word, int k) {
        return substringsWithAtMost(word, k) - substringsWithAtMost(word, k - 1);
    }

    private long substringsWithAtMost(String word, int k) {
        if (k == -1) return 0;

        long count = 0;
        int vowelCount = 0, uniqueVowelCount = 0;
        int left = 0;
        Map<Character, Integer> vowelLastSeen = new HashMap<>();

        for (int right = 0; right < word.length(); right++) {
            char c = word.charAt(right);

            if (isVowel(c)) {
                vowelCount++;
                if (!vowelLastSeen.containsKey(c) || vowelLastSeen.get(c) < left) {
                    uniqueVowelCount++;
                }
                vowelLastSeen.put(c, right);
            }

            while (right - left + 1 - vowelCount > k) {
                char leftChar = word.charAt(left);
                if (isVowel(leftChar)) {
                    vowelCount--;
                    if (vowelLastSeen.get(leftChar) == left) {
                        uniqueVowelCount--;
                    }
                }
                left++;
            }

            if (uniqueVowelCount == 5) {
                count += Collections.min(Arrays.asList(
                        vowelLastSeen.getOrDefault('a', Integer.MAX_VALUE),
                        vowelLastSeen.getOrDefault('e', Integer.MAX_VALUE),
                        vowelLastSeen.getOrDefault('i', Integer.MAX_VALUE),
                        vowelLastSeen.getOrDefault('o', Integer.MAX_VALUE),
                        vowelLastSeen.getOrDefault('u', Integer.MAX_VALUE)
                )) - left + 1;
            }
        }

        return count;
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

}

//This code counts substrings in a string word that contain at most k non-vowel characters and all
// five vowels ('a', 'e', 'i', 'o', 'u').
//countOfSubstrings calls substringsWithAtMost twice and calculates the difference.
//substringsWithAtMost uses a sliding window with two pointers to track vowels and non-vowels, counting valid
// substrings when all five vowels appear.
//Complexity:
//Time Complexity: O(n)
//Space Complexity: O(1)


//You are given a string word and a non-negative integer k.
//Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once
// and exactly k consonants.

//Example 1:
//Input: word = "aeioqq", k = 1
//Output: 0
//Explanation:
//There is no substring with every vowel.
//
//Example 2:
//Input: word = "aeiou", k = 0
//Output: 1
//Explanation:
//The only substring with every vowel and zero consonants is word[0..4], which is "aeiou".
//
//Example 3:
//Input: word = "ieaouqqieaouqq", k = 1
//Output: 3
//Explanation:
//The substrings with every vowel and one consonant are:
//word[0..5], which is "ieaouq".
//word[6..11], which is "qieaou".
//word[7..12], which is "ieaouq".

//Constraints:
//5 <= word.length <= 2 * 105
//word consists only of lowercase English letters.
//0 <= k <= word.length - 5
