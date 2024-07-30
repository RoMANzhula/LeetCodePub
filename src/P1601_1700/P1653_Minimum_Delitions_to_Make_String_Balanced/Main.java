package P1601_1700.P1653_Minimum_Delitions_to_Make_String_Balanced;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minimumDeletions("aababbab")); // Output: 2
        System.out.println(solution.minimumDeletions("bbaaaaabb")); // Output: 2
    }

    public int minimumDeletions(String s) {
        int n = s.length();
        int[] bCount = new int[n];
        int[] aCount = new int[n];

        // Fill bCount array, bCount[i] means number of 'b's from the beginning to i
        bCount[0] = s.charAt(0) == 'b' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            bCount[i] = bCount[i - 1] + (s.charAt(i) == 'b' ? 1 : 0);
        }

        // Fill aCount array, aCount[i] means number of 'a's from i to the end
        aCount[n - 1] = s.charAt(n - 1) == 'a' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            aCount[i] = aCount[i + 1] + (s.charAt(i) == 'a' ? 1 : 0);
        }

        // Calculate minimum deletions
        int minDeletions = Math.min(aCount[0], bCount[n - 1]); // All 'a's or all 'b's
        for (int i = 0; i < n - 1; i++) {
            minDeletions = Math.min(minDeletions, bCount[i] + aCount[i + 1]);
        }

        return minDeletions;
    }
}

//Explanation:
//bCount Array: This array keeps track of the number of 'b's from the start of the string up to the current index.
//aCount Array: This array keeps track of the number of 'a's from the current index to the end of the string.
//Calculate Minimum Deletions: We iterate through the string and at each index, we calculate the sum of 'b's before and
// including the current index and 'a's after the current index. This gives the total deletions needed to make the
// string balanced if we separate the string at the current index.
//The solution runs in O(n) time complexity, making it efficient for large strings up to the length of
//10 to the 5th degree


//You are given a string s consisting only of characters 'a' and 'b'​​​​.
//You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of
// indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
//Return the minimum number of deletions needed to make s balanced.
//
//Example 1:
//Input: s = "aababbab"
//Output: 2
//Explanation: You can either:
//Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
//Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").

//Example 2:
//Input: s = "bbaaaaabb"
//Output: 2
//Explanation: The only solution is to delete the first two characters.
//
//Constraints:
//1 <= s.length <= 105
//s[i] is 'a' or 'b'​​.