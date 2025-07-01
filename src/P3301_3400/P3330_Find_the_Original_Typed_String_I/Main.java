package P3301_3400.P3330_Find_the_Original_Typed_String_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.possibleStringCount("abbcccc")); // Output: 5
        System.out.println(solution.possibleStringCount("abcd"));     // Output: 1
        System.out.println(solution.possibleStringCount("aaaa"));     // Output: 4
    }

    public int possibleStringCount(String word) {
        int n = word.length();
        int result = 1; // original word is always valid

        for (int i = 0; i < n; ) {
            int j = i;

            // find group of same characters
            while (j < n && word.charAt(j) == word.charAt(i)) {
                j++;
            }
            int len = j - i;

            // if group is longer than 1, we can shorten it in (len - 1) ways
            if (len > 1) {
                result += (len - 1);
            }

            i = j;
        }

        return result;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and may press a
// key for too long, resulting in a character being typed multiple times.
//Although Alice tried to focus on her typing, she is aware that she may still have done this at most once.
//You are given a string word, which represents the final output displayed on Alice's screen.
//Return the total number of possible original strings that Alice might have intended to type.

//Example 1:
//Input: word = "abbcccc"
//Output: 5
//Explanation:
//The possible strings are: "abbcccc", "abbccc", "abbcc", "abbc", and "abcccc".

//Example 2:
//Input: word = "abcd"
//Output: 1
//Explanation:
//The only possible string is "abcd".

//Example 3:
//Input: word = "aaaa"
//Output: 4

//Constraints:
//1 <= word.length <= 100
//word consists only of lowercase English letters.
