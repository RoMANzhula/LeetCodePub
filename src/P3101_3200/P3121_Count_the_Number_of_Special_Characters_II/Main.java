package P3101_3200.P3121_Count_the_Number_of_Special_Characters_II;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numberOfSpecialChars("aaAbcBC")); // 3
        System.out.println(solution.numberOfSpecialChars("abc"));     // 0
        System.out.println(solution.numberOfSpecialChars("AbBCab"));  // 0
    }

    public int numberOfSpecialChars(String word) {
        int len = word.length();
        int result = 0;
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];

        Arrays.fill(lastLower, -1);
        Arrays.fill(firstUpper, -1);

        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);

            if (Character.isLowerCase(ch)) {
                lastLower[ch - 'a'] = i;
            } else {
                int idx = ch - 'A';

                // store only first uppercase occurrence
                if (firstUpper[idx] == -1) firstUpper[idx] = i;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (lastLower[i] != -1 && firstUpper[i] != -1 && lastLower[i] < firstUpper[i]) {
                result++;
            }
        }

        return result;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and
// every lowercase occurrence of c appears before the first uppercase occurrence of c.
//Return the number of special letters in word.

//Example 1:
//Input: word = "aaAbcBC"
//Output: 3
//Explanation:
//The special characters are 'a', 'b', and 'c'.

//Example 2:
//Input: word = "abc"
//Output: 0
//Explanation:
//There are no special characters in word.

//Example 3:
//Input: word = "AbBCab"
//Output: 0
//Explanation:
//There are no special characters in word.

//Constraints:
//1 <= word.length <= 2 * 105
//word consists of only lowercase and uppercase English letters.
