package P3701_3800.P3713_Longest_Balanced_Substring_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "abbac";
        String s2 = "zzabccy";
        String s3 = "aba";

        System.out.println("Input: " + s1);
        System.out.println("Output: " + solution.longestBalanced(s1)); // 4
        System.out.println();

        System.out.println("Input: " + s2);
        System.out.println("Output: " + solution.longestBalanced(s2)); // 4
        System.out.println();

        System.out.println("Input: " + s3);
        System.out.println("Output: " + solution.longestBalanced(s3)); // 2
    }

    public int longestBalanced(String s) {
        int n = s.length();
        int answer = 1; // at least 1 character is always balanced

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            int maxFreq = 0;
            int distinct = 0;

            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';

                if (freq[idx] == 0) {
                    distinct++;
                }

                freq[idx]++;
                maxFreq = Math.max(maxFreq, freq[idx]);

                int length = j - i + 1;

                //check balance condition
                if (maxFreq * distinct == length) {
                    answer = Math.max(answer, length);
                }
            }
        }

        return answer;
    }

}

//Complexity:
// time - O(n^2)
// space - O(const) ~ O(26)


//You are given a string s consisting of lowercase English letters.
//A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
//Return the length of the longest balanced substring of s.

//Example 1:
//Input: s = "abbac"
//Output: 4
//Explanation:
//The longest balanced substring is "abba" because both distinct characters 'a' and 'b' each appear exactly 2 times.

//Example 2:
//Input: s = "zzabccy"
//Output: 4
//Explanation:
//The longest balanced substring is "zabc" because the distinct characters 'z', 'a', 'b', and 'c' each appear
// exactly 1 time.​​​​​​​

//Example 3:
//Input: s = "aba"
//Output: 2
//Explanation:
//One of the longest balanced substrings is "ab" because both distinct characters 'a' and 'b' each appear
// exactly 1 time. Another longest balanced substring is "ba".

//Constraints:
//1 <= s.length <= 1000
//s consists of lowercase English letters.
