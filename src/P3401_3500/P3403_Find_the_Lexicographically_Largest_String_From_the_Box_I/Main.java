package P3401_3500.P3403_Find_the_Lexicographically_Largest_String_From_the_Box_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.answerString("dbca", 2)); // Output: "dbc"
        System.out.println(solution.answerString("gggg", 4)); // Output: "g"
        System.out.println(solution.answerString("gh", 1));   // Output: "gh"
    }


    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }

        String s = lastSubstring(word);
        int maxLen = word.length() - numFriends + 1;

        return s.substring(0, Math.min(s.length(), maxLen));
    }

    // equivalent to Leetcode 1163: Last Substring in Lexicographical Order
    private String lastSubstring(String s) {
        int i = 0, j = 1, k = 0;
        int n = s.length();

        while (j + k < n) {
            if (s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            } else if (s.charAt(i + k) > s.charAt(j + k)) {
                j = j + k + 1;
                k = 0;
            } else {
                i = Math.max(i + k + 1, j);
                j = i + 1;
                k = 0;
            }
        }

        return s.substring(i);
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given a string word, and an integer numFriends.
//Alice is organizing a game for her numFriends friends. There are multiple rounds in the game, where in each round:
//word is split into numFriends non-empty strings, such that no previous round has had the exact same split.
//All the split words are put into a box.
//Find the lexicographically largest string from the box after all the rounds are finished.

//Example 1:
//Input: word = "dbca", numFriends = 2
//Output: "dbc"
//Explanation:
//All possible splits are:
//"d" and "bca".
//"db" and "ca".
//"dbc" and "a".

//Example 2:
//Input: word = "gggg", numFriends = 4
//Output: "g"
//Explanation:
//The only possible split is: "g", "g", "g", and "g".

//Constraints:
//1 <= word.length <= 5 * 103
//word consists only of lowercase English letters.
//1 <= numFriends <= word.length
