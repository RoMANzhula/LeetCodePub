package P3701_3800.P3714_Longest_Balanced_Substring_II;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.longestBalanced("abbac")); // 4
        System.out.println(solution.longestBalanced("aabcc")); // 3
        System.out.println(solution.longestBalanced("aba"));   // 2
    }

    //for single letters
    private int calc1(String s) {
        int n = s.length();
        int res = 0;
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            res = Math.max(res, j - i);
            i = j;
        }
        return res;
    }

    // for two letters
    private int calc2(String s, char a, char b) {
        int n = s.length();
        int res = 0;
        int i = 0;

        while (i < n) {
            // skip letters not in (a,b)
            while (i < n && s.charAt(i) != a && s.charAt(i) != b) {
                i++;
            }
            Map<Integer, Integer> pos = new HashMap<>();
            pos.put(0, i - 1);
            int diff = 0;

            while (i < n && (s.charAt(i) == a || s.charAt(i) == b)) {
                diff += s.charAt(i) == a ? 1 : -1;
                if (pos.containsKey(diff)) {
                    res = Math.max(res, i - pos.get(diff));
                } else {
                    pos.put(diff, i);
                }
                i++;
            }
        }

        return res;
    }

    // for three letters
    private int calc3(String s) {
        int n = s.length();
        Map<String, Integer> pos = new HashMap<>();
        pos.put("0#0", -1);
        int res = 0;
        int a = 0, b = 0, c = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') a++;
            else if (ch == 'b') b++;
            else c++;

            String key = (a - b) + "#" + (b - c);
            if (pos.containsKey(key)) {
                res = Math.max(res, i - pos.get(key));
            } else {
                pos.put(key, i);
            }
        }

        return res;
    }

    public int longestBalanced(String s) {
        int x = calc1(s);
        int y = Math.max(calc2(s, 'a', 'b'), Math.max(calc2(s, 'b', 'c'), calc2(s, 'a', 'c')));
        int z = calc3(s);
        return Math.max(x, Math.max(y, z));
    }

}

//Complexity:
// time and space - O(n)


//You are given a string s consisting only of the characters 'a', 'b', and 'c'.
//A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
//Return the length of the longest balanced substring of s.

//Example 1:
//Input: s = "abbac"
//Output: 4
//Explanation:
//The longest balanced substring is "abba" because both distinct characters 'a' and 'b' each appear exactly 2 times.

//Example 2:
//Input: s = "aabcc"
//Output: 3
//Explanation:
//The longest balanced substring is "abc" because all distinct characters 'a', 'b' and 'c' each appear exactly 1 time.

//Example 3:
//Input: s = "aba"
//Output: 2
//Explanation:
//One of the longest balanced substrings is "ab" because both distinct characters 'a' and 'b' each appear
// exactly 1 time. Another longest balanced substring is "ba".

//Constraints:
//1 <= s.length <= 105
//s contains only the characters 'a', 'b', and 'c'.
