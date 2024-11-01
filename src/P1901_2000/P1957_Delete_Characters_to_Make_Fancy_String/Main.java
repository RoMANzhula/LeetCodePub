package P1901_2000.P1957_Delete_Characters_to_Make_Fancy_String;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "leeetcode";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + solution.makeFancyString(s1)); // Expected output: "leetcode"

        // Example 2
        String s2 = "aaabaaaa";
        System.out.println("Input: " + s2);
        System.out.println("Output: " + solution.makeFancyString(s2)); // Expected output: "aabaa"

        // Example 3
        String s3 = "aab";
        System.out.println("Input: " + s3);
        System.out.println("Output: " + solution.makeFancyString(s3)); // Expected output: "aab"
    }

    public String makeFancyString(String s) {
        StringBuilder result = new StringBuilder();
        int count = 1;  // Track consecutive identical characters

        // Add the first character to result
        result.append(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;  // Increment count if current char is the same as previous
            } else {
                count = 1;  // Reset count if different character
            }

            // Only add the character if count is less than 3
            if (count < 3) {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }

}

//Explanation
//Initialize Variables:
//StringBuilder result: To build the resulting "fancy" string without three consecutive identical characters.
//count: To keep track of consecutive identical characters.
//Iterate through the String:
//Start from the second character.
//If the current character is the same as the previous one, increment count.
//If not, reset count to 1.
//Add Character to Result:
//Only add the character if count is less than 3, which prevents three consecutive identical characters.
//Complexity
//Time Complexity:
//O(n), where n is the length of the string, as we iterate through each character once.
//Space Complexity:
//O(n), due to the usage of StringBuilder.


//A fancy string is a string where no three consecutive characters are equal.
//Given a string s, delete the minimum possible number of characters from s to make it fancy.
//Return the final string after the deletion. It can be shown that the answer will always be unique.

//Example 1:
//Input: s = "leeetcode"
//Output: "leetcode"
//Explanation:
//Remove an 'e' from the first group of 'e's to create "leetcode".
//No three consecutive characters are equal, so return "leetcode".

//Example 2:
//Input: s = "aaabaaaa"
//Output: "aabaa"
//Explanation:
//Remove an 'a' from the first group of 'a's to create "aabaaaa".
//Remove two 'a's from the second group of 'a's to create "aabaa".
//No three consecutive characters are equal, so return "aabaa".

//Example 3:
//Input: s = "aab"
//Output: "aab"
//Explanation: No three consecutive characters are equal, so return "aab".
//
//Constraints:
//1 <= s.length <= 105
//s consists only of lowercase English letters.