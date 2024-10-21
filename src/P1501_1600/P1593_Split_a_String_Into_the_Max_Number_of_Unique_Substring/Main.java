package P1501_1600.P1593_Split_a_String_Into_the_Max_Number_of_Unique_Substring;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxUniqueSplit("ababccc"));  // Output: 5
        System.out.println(solution.maxUniqueSplit("aba"));      // Output: 2
        System.out.println(solution.maxUniqueSplit("aa"));       // Output: 1
    }

    public int maxUniqueSplit(String s) {
        return backtrack(s, 0, new HashSet<>());
    }

    // Backtracking function to find the maximum number of unique substrings
    private int backtrack(String s, int start, Set<String> seen) {
        if (start == s.length()) {
            return 0;  // Reached the end, no more substrings to split
        }

        int maxSplits = 0;

        // Try all possible splits starting from 'start'
        for (int end = start + 1; end <= s.length(); end++) {
            String substring = s.substring(start, end);

            if (!seen.contains(substring)) {
                // If the substring is unique in this path, add it to the set
                seen.add(substring);

                // Continue splitting the remaining part of the string
                maxSplits = Math.max(maxSplits, 1 + backtrack(s, end, seen));

                // Backtrack: remove the substring and try other splits
                seen.remove(substring);
            }
        }

        return maxSplits;
    }

}

//Explanation:
//backtrack function:
//Takes three parameters: the string s, the starting index start from which we're trying to split, and a
// Set<String> called seen to track the substrings we've used so far.
//If start reaches the end of the string, we return 0 because there are no more characters left to split.
//We try splitting the string from start to end, where end goes from start + 1 to the length of the string. If
// the current substring (from start to end) is not in the set seen, we add it and recursively call backtrack
// for the rest of the string.
//After processing, we remove the substring from the set (seen) to try other possible splits.
//main function:
// Tests the method with sample inputs provided in the problem.
//Time Complexity:
//Since we are using a backtracking approach that tries every possible substring, the time complexity is
// approximately O(2^n), where n is the length of the string. This is because, in the worst case, every
// character might generate a split or not, leading to an exponential number of possibilities.
//However, given the constraint 1 <= s.length <= 16, this approach will work efficiently within the limits.


//Given a string s, return the maximum number of unique substrings that the given string can be split into.
//You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms
// the original string. However, you must split the substrings such that all of them are unique.
//A substring is a contiguous sequence of characters within a string.
//
//Example 1:
//Input: s = "ababccc"
//Output: 5
//Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting
// like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.

//Example 2:
//Input: s = "aba"
//Output: 2
//Explanation: One way to split maximally is ['a', 'ba'].

//Example 3:
//Input: s = "aa"
//Output: 1
//Explanation: It is impossible to split the string any further.
//
//Constraints:
//1 <= s.length <= 16
//s contains only lower case English letters.
