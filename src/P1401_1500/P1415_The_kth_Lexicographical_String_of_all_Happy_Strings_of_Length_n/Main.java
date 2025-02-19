package P1401_1500.P1415_The_kth_Lexicographical_String_of_all_Happy_Strings_of_Length_n;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.getHappyString(1, 3));  // Output: "c"
        System.out.println(solution.getHappyString(1, 4));  // Output: ""
        System.out.println(solution.getHappyString(3, 9));  // Output: "cab"
    }

    public String getHappyString(int n, int k) {
        List<String> happyStrings = new ArrayList<>();

        generateHappyStrings(n, "", happyStrings);

        // if "k" is greater than the total number of happy strings, return an empty string
        return k > happyStrings.size() ? "" : happyStrings.get(k - 1);
    }

    private void generateHappyStrings(int n, String current, List<String> happyStrings) {
        int len = current.length();

        if (len == n) {
            happyStrings.add(current);
            return;
        }

        for (char c : new char[]{'a', 'b', 'c'}) {
            if (current.isEmpty() || current.charAt(len - 1) != c) {
                generateHappyStrings(n, current + c, happyStrings);
            }
        }
    }

}

//Explanation:
//generateHappyStrings(n, current, happyStrings)
//Uses recursion to generate all valid happy strings of length n.
//Ensures that no two consecutive characters are the same.
//Adds valid strings to the list in lexicographical order.
//getHappyString(n, k)
//Calls the recursive function to generate happy strings.
//Returns the k-th string if available, otherwise returns an empty string.
//Complexity: O(n^2)


//A happy string is a string that:
//consists only of letters of the set ['a', 'b', 'c'].
//s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
//For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and
// "ababbc" are not happy strings.
//Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
//Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
//
//Example 1:
//Input: n = 1, k = 3
//Output: "c"
//Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".

//Example 2:
//Input: n = 1, k = 4
//Output: ""
//Explanation: There are only 3 happy strings of length 1.

//Example 3:
//Input: n = 3, k = 9
//Output: "cab"
//Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca",
// "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
//
//Constraints:
//1 <= n <= 10
//1 <= k <= 100
