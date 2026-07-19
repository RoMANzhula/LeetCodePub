package P1001_1100.P1081_Smallest_Subsequence_of_Distinct_Characters;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.smallestSubsequence("bcabc"));      // abc
        System.out.println(solution.smallestSubsequence("cbacdcbc"));   // acdb
        System.out.println(solution.smallestSubsequence("cdadabcc"));   // adbc
        System.out.println(solution.smallestSubsequence("abcd"));       // abcd
        System.out.println(solution.smallestSubsequence("ecbacba"));    // eacb
    }

    public String smallestSubsequence(String s) {
        int[] last = new int[26];

        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        boolean[] visited = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            int index = current - 'a';

            if (visited[index]) {
                continue;
            }

            while (!stack.isEmpty()
                    && stack.peekLast() > current
                    && last[stack.peekLast() - 'a'] > i) {

                visited[stack.removeLast() - 'a'] = false;
            }

            stack.addLast(current);
            visited[index] = true;
        }

        StringBuilder answer = new StringBuilder();

        while (!stack.isEmpty()) {
            answer.append(stack.removeFirst());
        }

        return answer.toString();
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct
// characters of s exactly once.

//Example 1:
//Input: s = "bcabc"
//Output: "abc"

//Example 2:
//Input: s = "cbacdcbc"
//Output: "acdb"

//Constraints:
//1 <= s.length <= 1000
//s consists of lowercase English letters.
