package P3301_3400.P3302_Find_the_Lexicographically_Smalles_Valid_Sequence;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String word1 = "vbcca"; String word2 = "abc";
        System.out.println(Arrays.toString(solution.validSequence(word1, word2)) ); // [0, 1, 2]

        word1 = "bacdc"; word2 = "abc";
        System.out.println(Arrays.toString(solution.validSequence(word1, word2)) ); // [1, 2, 4]

        word1 = "aaaaaa"; word2 = "aaabc";
        System.out.println(Arrays.toString(solution.validSequence(word1, word2)) ); // []
    }

    public int[] validSequence(String word1, String word2) {
        int[] ans = new int[word2.length()];
        int[] last = new int[word2.length()];

        Arrays.fill(last, -1);
        int i = word1.length() - 1;
        int j = word2.length() - 1;
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j--] = i;
            }
            i--;
        }

        boolean canSkip = true;
        j = 0;

        for (i = 0; i < word1.length(); i++) {
            if (j == word2.length()) {
                break;
            }

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j++] = i;
            } else if (canSkip && (j == word2.length() - 1 || i < last[j + 1])) {
                canSkip = false;
                ans[j++] = i;
            }
        }

        return j == word2.length() ? ans : new int[0];
    }

}

//Complexity:
// time - O(len1 + len2)  1 - word1.length, 2 - word2.length
// space - O(len2)


//You are given two strings word1 and word2.
//A string x is called almost equal to y if you can change at most one character in x to make it identical to y.
//A sequence of indices seq is called valid if:
//The indices are sorted in ascending order.
//Concatenating the characters at these indices in word1 in the same order results in a string that is almost equal
// to word2.
//Return an array of size word2.length representing the lexicographically smallest valid sequence of indices. If no
// such sequence of indices exists, return an empty array.
//Note that the answer must represent the lexicographically smallest array, not the corresponding string formed by
// those indices.

//Example 1:
//Input: word1 = "vbcca", word2 = "abc"
//Output: [0,1,2]
//Explanation:
//The lexicographically smallest valid sequence of indices is [0, 1, 2]:
//Change word1[0] to 'a'.
//word1[1] is already 'b'.
//word1[2] is already 'c'.

//Example 2:
//Input: word1 = "bacdc", word2 = "abc"
//Output: [1,2,4]
//Explanation:
//The lexicographically smallest valid sequence of indices is [1, 2, 4]:
//word1[1] is already 'a'.
//Change word1[2] to 'b'.
//word1[4] is already 'c'.

//Example 3:
//Input: word1 = "aaaaaa", word2 = "aaabc"
//Output: []
//Explanation:
//There is no valid sequence of indices.

//Example 4:
//Input: word1 = "abc", word2 = "ab"
//Output: [0,1]

//Constraints:
//1 <= word2.length < word1.length <= 3 * 105
//word1 and word2 consist only of lowercase English letters.
