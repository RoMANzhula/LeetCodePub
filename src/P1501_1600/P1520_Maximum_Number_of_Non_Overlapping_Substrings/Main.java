package P1501_1600.P1520_Maximum_Number_of_Non_Overlapping_Substrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "adefaddaccc";
        System.out.println(solution.maxNumOfSubstrings(s1)); // [e, f, ccc]

        String s2 = "abbaccd";
        System.out.println(solution.maxNumOfSubstrings(s2)); // [bb, cc, d]
    }

    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {
            if (last[c] == -1) {
                continue;
            }

            int start = first[c];
            int end = last[c];
            boolean valid = true;

            for (int i = start; i <= end; i++) {
                int current = s.charAt(i) - 'a';

                if (first[current] < start) {
                    valid = false;
                    break;
                }

                end = Math.max(end, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        intervals.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(b[0], a[0]);
        });

        List<String> result = new ArrayList<>();

        int previousEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > previousEnd) {
                result.add(s.substring(start, end + 1));
                previousEnd = end;
            }
        }

        return result;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet
// the following conditions:
//The substrings do not overlap, that is for any two substrings s[i..j] and s[x..y], either j < x or i > y is true.
//A substring that contains a certain character c must also contain all occurrences of c.
//Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same
// number of substrings, return the one with minimum total length. It can be shown that there exists a unique
// solution of minimum total length.
//Notice that you can return the substrings in any order.

//Example 1:
//Input: s = "adefaddaccc"
//Output: ["e","f","ccc"]
//Explanation: The following are all the possible substrings that meet the conditions:
//[
//  "adefaddaccc"
//  "adefadda",
//  "ef",
//  "e",
//  "f",
//  "ccc",
//]
//If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are
// left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not
// optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which
// gives us 3 substrings. No other solution of the same number of substrings exist.

//Example 2:
//Input: s = "abbaccd"
//Output: ["d","bb","cc"]
//Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect
// since it has larger total length.

//Constraints:
//1 <= s.length <= 105
//s contains only lowercase English letters.
