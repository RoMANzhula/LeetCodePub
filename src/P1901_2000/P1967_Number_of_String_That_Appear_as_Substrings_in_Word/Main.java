package P1901_2000.P1967_Number_of_String_That_Appear_as_Substrings_in_Word;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] patterns1 = {"a", "abc", "bc", "d"};
        String word1 = "abc";
        System.out.println(solution.numOfStrings(patterns1, word1)); // 3

        String[] patterns2 = {"a", "b", "c"};
        String word2 = "aaaaabbbbb";
        System.out.println(solution.numOfStrings(patterns2, word2)); // 2

        String[] patterns3 = {"a", "a", "a"};
        String word3 = "ab";
        System.out.println(solution.numOfStrings(patterns3, word3)); // 3
    }

    public int numOfStrings(String[] patterns, String word) {
        int count = 0;

        for (String pattern : patterns) {
            if (word.contains(pattern)) count++;
        }

        return count;
    }

}

//Complexity:
// time - O(patterns.length * word.length * average length of a pattern)
// space - O(1)


//Given an array of strings patterns and a string word, return the number of strings in patterns that exist as a
// substring in word.
//A substring is a contiguous sequence of characters within a string.

//Example 1:
//Input: patterns = ["a","abc","bc","d"], word = "abc"
//Output: 3
//Explanation:
//- "a" appears as a substring in "abc".
//- "abc" appears as a substring in "abc".
//- "bc" appears as a substring in "abc".
//- "d" does not appear as a substring in "abc".
//3 of the strings in patterns appear as a substring in word.

//Example 2:
//Input: patterns = ["a","b","c"], word = "aaaaabbbbb"
//Output: 2
//Explanation:
//- "a" appears as a substring in "aaaaabbbbb".
//- "b" appears as a substring in "aaaaabbbbb".
//- "c" does not appear as a substring in "aaaaabbbbb".
//2 of the strings in patterns appear as a substring in word.

//Example 3:
//Input: patterns = ["a","a","a"], word = "ab"
//Output: 3
//Explanation: Each of the patterns appears as a substring in word "ab".

//Constraints:
//1 <= patterns.length <= 100
//1 <= patterns[i].length <= 100
//1 <= word.length <= 100
//patterns[i] and word consist of lowercase English letters.
