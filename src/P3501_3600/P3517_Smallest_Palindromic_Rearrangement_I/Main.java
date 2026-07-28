package P3501_3600.P3517_Smallest_Palindromic_Rearrangement_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "z";
        String s2 = "babab";
        String s3 = "daccad";

        System.out.println(solution.smallestPalindrome(s1));
        System.out.println(solution.smallestPalindrome(s2));
        System.out.println(solution.smallestPalindrome(s3));
    }

    public String smallestPalindrome(String s) {
        int[] frequency = new int[26];

        // count the frequency of every character
        for (char ch : s.toCharArray()) {
            frequency[ch - 'a']++;
        }

        StringBuilder leftHalf = new StringBuilder();
        char middleCharacter = 0;

        // build the lexicographically smallest left half
        for (int i = 0; i < 26; i++) {
            int halfCount = frequency[i] / 2;

            for (int j = 0; j < halfCount; j++) {
                leftHalf.append((char) ('a' + i));
            }

            // odd frequency means this character is in the middle
            if (frequency[i] % 2 == 1) {
                middleCharacter = (char) ('a' + i);
            }
        }

        StringBuilder result = new StringBuilder();

        result.append(leftHalf);

        if (middleCharacter != 0) {
            result.append(middleCharacter);
        }

        result.append(leftHalf.reverse());

        return result.toString();
    }

}

//Complexity:
// time and space - O(n)


//You are given a palindromic string s.
//Return the lexicographically smallest palindromic permutation of s.

//Example 1:
//Input: s = "z"
//Output: "z"
//Explanation:
//A string of only one character is already the lexicographically smallest palindrome.

//Example 2:
//Input: s = "babab"
//Output: "abbba"
//Explanation:
//Rearranging "babab" → "abbba" gives the smallest lexicographic palindrome.

//Example 3:
//Input: s = "daccad"
//Output: "acddca"
//Explanation:
//Rearranging "daccad" → "acddca" gives the smallest lexicographic palindrome.

//Constraints:
//1 <= s.length <= 105
//s consists of lowercase English letters.
//s is guaranteed to be palindromic.
