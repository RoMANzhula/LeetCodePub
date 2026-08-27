package P3701_3800.P3720_Lexicographicaly_Smallest_Permutation_Greater_Than_Target;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.lexGreaterPermutation("abc", "bba")); // bca

        System.out.println(solution.lexGreaterPermutation("leet", "code")); // eelt

        System.out.println(solution.lexGreaterPermutation("baba", "bbaa")); // ""
    }

    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int[][] remaining = new int[n][26];

        for (int i = 0; i < n; i++) {

            remaining[i] = count.clone();

            int c = target.charAt(i) - 'a';

            if (count[c] == 0) {
                break;
            }

            count[c]--;
        }


        for (int i = n - 1; i >= 0; i--) {

            if (!prefixCanBeBuilt(s, target, i)) {
                continue;
            }

            int[] cnt = remaining[i].clone();

            int targetChar = target.charAt(i) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (cnt[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    ans.append(target, 0, i);

                    ans.append((char) ('a' + c));

                    cnt[c]--;

                    for (int x = 0; x < 26; x++) {
                        while (cnt[x] > 0) {
                            ans.append((char) ('a' + x));
                            cnt[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }


    private boolean prefixCanBeBuilt(
            String s,
            String target,
            int length
    ) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (int i = 0; i < length; i++) {
            int c = target.charAt(i) - 'a';

            if (count[c] == 0) {
                return false;
            }

            count[c]--;
        }

        return true;
    }

}

//Complexity:
// time


//You are given two strings s and target, both having length n, consisting of lowercase English letters.
//Return the lexicographically smallest permutation of s that is strictly greater than target. If no permutation
// of s is lexicographically strictly greater than target, return an empty string.
//A string a is lexicographically strictly greater than a string b (of the same length) if in the first position
// where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b.

//Example 1:
//Input: s = "abc", target = "bba"
//Output: "bca"
//Explanation:
//The permutations of s (in lexicographical order) are "abc", "acb", "bac", "bca", "cab", and "cba".
//The lexicographically smallest permutation that is strictly greater than target is "bca".

//Example 2:
//Input: s = "leet", target = "code"
//Output: "eelt"
//Explanation:
//The permutations of s (in lexicographical order) are "eelt", "eetl", "elet", "elte", "etel", "etle", "leet",
// "lete", "ltee", "teel", "tele", and "tlee".
//The lexicographically smallest permutation that is strictly greater than target is "eelt".

//Example 3:
//Input: s = "baba", target = "bbaa"
//Output: ""
//Explanation:
//The permutations of s (in lexicographical order) are "aabb", "abab", "abba", "baab", "baba", and "bbaa".
//None of them is lexicographically strictly greater than target. Therefore, the answer is "".

//Constraints:
//1 <= s.length == target.length <= 300
//s and target consist of only lowercase English letters.
