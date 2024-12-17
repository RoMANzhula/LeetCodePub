package P2101_2200.P2182_Construct_String_With_Repeat_Limit;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.repeatLimitedString("cczazcc", 3)); // Output: "zzcccac"
        System.out.println(solution.repeatLimitedString("aababab", 2)); // Output: "bbabaa"
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        // Frequency map to count occurrences of characters
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Max heap to prioritize larger characters first
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                maxHeap.add(new int[]{i, freq[i]}); // {character_index, frequency}
            }
        }

        StringBuilder result = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            int[] current = maxHeap.poll(); // Get the lexicographically largest character
            int charIndex = current[0];
            int charFreq = current[1];

            // Append the current character up to the repeatLimit
            int useCount = Math.min(repeatLimit, charFreq);
            for (int i = 0; i < useCount; i++) {
                result.append((char) (charIndex + 'a'));
            }

            // Update the remaining frequency for the current character
            charFreq -= useCount;

            // If the current character still has frequency left, try to "break" the repetition
            if (charFreq > 0) {
                if (maxHeap.isEmpty()) break; // No other character to use, stop

                // Use the next largest character to break the repetition
                int[] next = maxHeap.poll();
                int nextCharIndex = next[0];
                int nextCharFreq = next[1];

                // Append one instance of the next character
                result.append((char) (nextCharIndex + 'a'));
                nextCharFreq--;

                // Add back both characters to the heap if they still have remaining frequency
                if (nextCharFreq > 0) {
                    maxHeap.add(new int[]{nextCharIndex, nextCharFreq});
                }
                maxHeap.add(new int[]{charIndex, charFreq});
            }
        }

        return result.toString();
    }

}

//Explanation:
//Character Frequency: The freq array counts the occurrences of each character in the input string.
//Max Heap: A priority queue stores characters in descending order (based on their ASCII value). This allows us
// to always pick the largest possible character.
//Repeat Limit Logic:
//-Add as many characters as allowed (repeatLimit).
//-If the limit is hit and characters remain, use the next largest character to break the repetition.
//Remaining Frequency: Characters with remaining frequencies are re-added to the heap for further processing.
//Complexity Analysis:
//Time Complexity:
// O(n log 26), where n is the length of the string. The heap operations are efficient, and there are at
// most 26 characters.
//Space Complexity:
// O(26) for the frequency array and heap.


//You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the
// characters of s such that no letter appears more than repeatLimit times in a row. You do not have to
// use all characters from s.
//Return the lexicographically largest repeatLimitedString possible.
//A string a is lexicographically larger than a string b if in the first position where a and b differ, string a
// has a letter that appears later in the alphabet than the corresponding letter in b. If the first
// min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.
//
//Example 1:
//Input: s = "cczazcc", repeatLimit = 3
//Output: "zzcccac"
//Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
//The letter 'a' appears at most 1 time in a row.
//The letter 'c' appears at most 3 times in a row.
//The letter 'z' appears at most 2 times in a row.
//Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
//The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
//Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a
// row, so it is not a valid repeatLimitedString.

//Example 2:
//Input: s = "aababab", repeatLimit = 2
//Output: "bbabaa"
//Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa".
//The letter 'a' appears at most 2 times in a row.
//The letter 'b' appears at most 2 times in a row.
//Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
//The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
//Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in
// a row, so it is not a valid repeatLimitedString.
//
//Constraints:
//1 <= repeatLimit <= s.length <= 105
//s consists of lowercase English letters.
