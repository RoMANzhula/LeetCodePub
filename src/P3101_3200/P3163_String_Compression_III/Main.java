package P3101_3200.P3163_String_Compression_III;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.compressedString("abcde")); // Output: "1a1b1c1d1e"
        System.out.println(solution.compressedString("aaaaaaaaaaaaaabb")); // Output: "9a5a2b"
    }

    public String compressedString(String word) {
        StringBuilder comp = new StringBuilder();
        int i = 0;
        int n = word.length();

        while (i < n) {
            char currentChar = word.charAt(i);
            int count = 0;

            // Count consecutive characters, up to a maximum of 9
            while (i < n && word.charAt(i) == currentChar && count < 9) {
                count++;
                i++;
            }

            // Append the count and the character to the compressed string
            comp.append(count).append(currentChar);
        }

        return comp.toString();
    }

}

//Explanation of the Code:
//Loop Logic: The while (i < n) loop goes through each character in the word. For each character, we
// count consecutive repetitions up to a maximum of 9.
//Inner Loop: The inner while loop counts how many times the current character repeats consecutively.
//Appending Results: After counting, count and currentChar are appended to comp.
//Incrementing i: We move i forward by the number of counted characters, so each character or character
// block is only processed once.
//Complexity
//Time Complexity:
//O(n), where n is the length of word, since we process each character at most once.
//Space Complexity:
//O(n) for storing the output in comp.


//Given a string word, compress it using the following algorithm:
//Begin with an empty string comp. While word is not empty, use the following operation:
//Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
//Append the length of the prefix followed by c to comp.
//Return the string comp.
//
//Example 1:
//Input: word = "abcde"
//Output: "1a1b1c1d1e"
//Explanation:
//Initially, comp = "". Apply the operation 5 times, choosing "a", "b", "c", "d", and "e" as the
// prefix in each operation.
//For each prefix, append "1" followed by the character to comp.
//
//Example 2:
//Input: word = "aaaaaaaaaaaaaabb"
//Output: "9a5a2b"
//Explanation:
//Initially, comp = "". Apply the operation 3 times, choosing "aaaaaaaaa", "aaaaa", and "bb" as the
// prefix in each operation.
//For prefix "aaaaaaaaa", append "9" followed by "a" to comp.
//For prefix "aaaaa", append "5" followed by "a" to comp.
//For prefix "bb", append "2" followed by "b" to comp.
//
//Constraints:
//1 <= word.length <= 2 * 105
//word consists only of lowercase English letters.
