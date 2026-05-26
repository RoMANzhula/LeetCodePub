package P3101_3200.P3120_Count_the_Number_of_Special_Characters_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numberOfSpecialChars("aaAbcBC")); // 3
        System.out.println(solution.numberOfSpecialChars("abc"));     // 0
        System.out.println(solution.numberOfSpecialChars("abBCab"));  // 1
    }

    public int numberOfSpecialChars(String word) {
        boolean[] lower = new boolean[26];
        boolean[] upper = new boolean[26];

        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lower[c - 'a'] = true;
            } else {
                upper[c - 'A'] = true;
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {
            if (lower[i] && upper[i]) {
                count++;
            }
        }

        return count;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
//Return the number of special letters in word.

//Example 1:
//Input: word = "aaAbcBC"
//Output: 3
//Explanation:
//The special characters in word are 'a', 'b', and 'c'.

//Example 2:
//Input: word = "abc"
//Output: 0
//Explanation:
//No character in word appears in uppercase.

//Example 3:
//Input: word = "abBCab"
//Output: 1
//Explanation:
//The only special character in word is 'b'.

//Constraints:
//1 <= word.length <= 50
//word consists of only lowercase and uppercase English letters.
