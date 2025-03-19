package P1601_1700.P1647_Minimum_Deletions_to_Make_Character_Frequencies_Unique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public int minDeletionsToMakeGoodString(String s) {
        //count the frequency of each character in the string
        Map<Character, Integer> charCount = new HashMap<>();

        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        //create a set to keep track of frequencies
        Map<Integer, Integer> freqSet = new HashMap<>();

        int deletions = 0;

        for (int freq : charCount.values()) {
            //check if the frequency is already in the set
            //if yes, decrement the frequency until it's not in the set
            while (freqSet.containsKey(freq)) {
                freq--;
                deletions++;
            }

            //add the updated frequency to the set
            if (freq > 0) {
                freqSet.put(freq, 1);
            }
        }

        return deletions; //bingo
    }

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "aab";
        String s2 = "aaabbbcc";
        String s3 = "ceabaacb";

        System.out.println(solution.minDeletionsToMakeGoodString(s1)); // Output: 0
        System.out.println(solution.minDeletionsToMakeGoodString(s2)); // Output: 2
        System.out.println(solution.minDeletionsToMakeGoodString(s3)); // Output: 2

    }

}

//A string s is called good if there are no two different characters in s that have the same frequency.
//Given a string s, return the minimum number of characters you need to delete to make s good.
//The frequency of a character in a string is the number of times it appears in the string. For example, in the
// string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

//Example 1:
//Input: s = "aab"
//Output: 0
//Explanation: s is already good.

//Example 2:
//Input: s = "aaabbbcc"
//Output: 2
//Explanation: You can delete two 'b's resulting in the good string "aaabcc".
//Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".

//Example 3:
//Input: s = "ceabaacb"
//Output: 2
//Explanation: You can delete both 'c's resulting in the good string "eabaab".
//Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).

//Constraints:
//1 <= s.length <= 105
//s contains only lowercase English letters.
