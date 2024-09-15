package P1301_1400.P1371_Find_the_Longest_Substring_Containing_Vowels_in_Even_Counts;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.findTheLongestSubstring("eleetminicoworoep")); // Output: 13
        System.out.println(solution.findTheLongestSubstring("leetcodeisgreat"));   // Output: 5
        System.out.println(solution.findTheLongestSubstring("bcbcbc"));            // Output: 6
    }

    public int findTheLongestSubstring(String s) {
        // Map to store the first occurrence of each bitmask
        HashMap<Integer, Integer> firstOccurrence = new HashMap<>();
        // Initial state: no vowels, mask = 0, occurs at index -1
        firstOccurrence.put(0, -1);

        // Current bitmask
        int mask = 0;
        int maxLength = 0;

        // Iterate over the string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Update the bitmask based on the vowel
            if (ch == 'a') {
                mask ^= (1 << 0); // Toggle bit for 'a'
            } else if (ch == 'e') {
                mask ^= (1 << 1); // Toggle bit for 'e'
            } else if (ch == 'i') {
                mask ^= (1 << 2); // Toggle bit for 'i'
            } else if (ch == 'o') {
                mask ^= (1 << 3); // Toggle bit for 'o'
            } else if (ch == 'u') {
                mask ^= (1 << 4); // Toggle bit for 'u'
            }

            // If this mask has been seen before, update the max length
            if (firstOccurrence.containsKey(mask)) {
                int prevIndex = firstOccurrence.get(mask);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                // Otherwise, store the first occurrence of this mask
                firstOccurrence.put(mask, i);
            }
        }

        return maxLength;
    }
}

//Explanation:
//Bitmasking: The state of vowels is represented using a bitmask where each bit corresponds to the even/odd
// occurrence of a vowel.
//HashMap: We store the first occurrence of each bitmask and use it to calculate the length of the longest
// substring with an even count of vowels.
//Efficiency: The algorithm runs in linear time O(n) as we only pass through the string once and use a
// constant amount of space for the bitmask and the hashmap.
//Time Complexity: O(n) where n is the length of the string.
//Space Complexity: O(1) for the bitmask (constant space) and O(n) for the hashmap storing first occurrences.


//Given the string s, return the size of the longest substring containing each vowel an even number of
// times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
//
//Example 1:
//Input: s = "eleetminicoworoep"
//Output: 13
//Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and
// zero of the vowels: a and u.

//Example 2:
//Input: s = "leetcodeisgreat"
//Output: 5
//Explanation: The longest substring is "leetc" which contains two e's.

//Example 3:
//Input: s = "bcbcbc"
//Output: 6
//Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and
// u appear zero times.
//
//Constraints:
//1 <= s.length <= 5 x 10^5
//s contains only lowercase English letters.