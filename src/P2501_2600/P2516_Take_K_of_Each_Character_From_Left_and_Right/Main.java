package P2501_2600.P2516_Take_K_of_Each_Character_From_Left_and_Right;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.takeCharacters("aabaaaacaabc", 2)); // Output: 8
        System.out.println(solution.takeCharacters("a", 1)); // Output: -1
    }

    public int takeCharacters(String s, int k) {
        if (k == 0) return 0; // If k is 0, no characters are needed.

        int n = s.length();
        int[] count = new int[3]; // Counts for 'a', 'b', and 'c'

        // Count all occurrences of each character in the string
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        // Check if it's possible to take k of each character
        for (int c : count) {
            if (c < k) return -1; // Not enough characters
        }

        // Sliding window to find the maximum size of a substring that leaves at least k of each character outside
        int left = 0, maxValidLength = 0;
        int[] window = new int[3]; // Sliding window counts

        for (int right = 0; right < n; right++) {
            window[s.charAt(right) - 'a']++;

            // Ensure the remaining outside the window satisfies the condition
            while ((count[0] - window[0] < k) ||
                    (count[1] - window[1] < k) ||
                    (count[2] - window[2] < k)) {
                window[s.charAt(left) - 'a']--;
                left++;
            }

            // Update the maximum valid substring length
            maxValidLength = Math.max(maxValidLength, right - left + 1);
        }

        // Minimum minutes needed is total length minus the maximum valid substring length
        return n - maxValidLength;
    }
}

//Explanation:
//Counting Characters:
//Count the occurrences of 'a', 'b', and 'c' in the string.
//If any character count is less than k, return -1 because it's impossible to satisfy the condition.
//Sliding Window:
//Use a sliding window to maximize the size of a contiguous substring where at least k characters of
// each type remain outside the window.
//Expand the window by including the rightmost character and shrink it by moving the left pointer if
// the outside counts violate the condition.
//Result Calculation:
//The result is n - maxValidLength, where maxValidLength is the size of the maximum valid substring.
//Complexity:
//Time Complexity:
//O(n), where n is the length of the string. Each character is processed at most twice (once when
// expanding the window and once when shrinking it).
//Space Complexity:
//O(1), as we only use a fixed array of size 3 for counting.


//You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each
// minute, you may take either the leftmost character of s, or the rightmost character of s.
//Return the minimum number of minutes needed for you to take at least k of each character, or
// return -1 if it is not possible to take k of each character.
//
//Example 1:
//Input: s = "aabaaaacaabc", k = 2
//Output: 8
//Explanation:
//Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
//Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
//A total of 3 + 5 = 8 minutes is needed.
//It can be proven that 8 is the minimum number of minutes needed.

//Example 2:
//Input: s = "a", k = 1
//Output: -1
//Explanation: It is not possible to take one 'b' or 'c' so return -1.
//
//Constraints:
//1 <= s.length <= 105
//s consists of only the letters 'a', 'b', and 'c'.
//0 <= k <= s.length