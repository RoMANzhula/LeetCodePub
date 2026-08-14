package P3001_3100.P3090_Maximum_Length_Substring_With_Two_Occurrences;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumLengthSubstring("bcbbbcba")); // 4
        System.out.println(solution.maximumLengthSubstring("aaaa")); // 2
    }

    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right) - 'a';
            count[index]++;

            // shrink the window if a character appears more than twice
            while (count[index] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each
// character.

//Example 1:
//Input: s = "bcbbbcba"
//Output: 4
//Explanation:
//The following substring has a length of 4 and contains at most two occurrences of each character: "bcbbbcba".

//Example 2:
//Input: s = "aaaa"
//Output: 2
//Explanation:
//The following substring has a length of 2 and contains at most two occurrences of each character: "aaaa".

//Constraints:
//2 <= s.length <= 100
//s consists only of lowercase English letters.
