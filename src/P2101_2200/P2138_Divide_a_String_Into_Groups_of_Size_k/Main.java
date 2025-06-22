package P2101_2200.P2138_Divide_a_String_Into_Groups_of_Size_k;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "abcdefghi";
        int k1 = 3;
        char fill1 = 'x';
        String[] result1 = solution.divideString(s1, k1, fill1);
        System.out.println(java.util.Arrays.toString(result1)); // Output: [abc, def, ghi]

        String s2 = "abcdefghij";
        int k2 = 3;
        char fill2 = 'x';
        String[] result2 = solution.divideString(s2, k2, fill2);
        System.out.println(java.util.Arrays.toString(result2)); // Output: [abc, def, ghi, jxx]
    }

    public String[] divideString(String s, int k, char fill) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i += k) {
            // get the end index for substring, but not beyond string length
            int end = Math.min(i + k, s.length());
            String group = s.substring(i, end);

            // if this group is shorter than k, fill with the 'fill' character
            if (group.length() < k) {
                StringBuilder sb = new StringBuilder(group);

                while (sb.length() < k) {
                    sb.append(fill);
                }

                group = sb.toString();
            }

            result.add(group);
        }

        // convert List to String Array and return
        return result.toArray(new String[0]);
    }

}

//Explanation:
//Iterate over string s in chunks of size k.
//For each chunk: if chunk size is less than k, fill it using the fill character.
//Store each chunk in a list.
//Return the list converted to an array.
//Complexity:
// time and space - O(n)


//A string s can be partitioned into groups of size k using the following procedure:
//The first group consists of the first k characters of the string, the second group consists of
// the next k characters of the string, and so on. Each element can be a part of exactly one group.
//For the last group, if the string does not have k characters remaining, a character fill is used to
// complete the group.
//Note that the partition is done so that after removing the fill character from the last group (if it exists) and
// concatenating all the groups in order, the resultant string should be s.
//Given the string s, the size of each group k and the character fill, return a string array denoting the
// composition of every group s has been divided into, using the above procedure.

//Example 1:
//Input: s = "abcdefghi", k = 3, fill = "x"
//Output: ["abc","def","ghi"]
//Explanation:
//The first 3 characters "abc" form the first group.
//The next 3 characters "def" form the second group.
//The last 3 characters "ghi" form the third group.
//Since all groups can be completely filled by characters from the string, we do not need to use fill.
//Thus, the groups formed are "abc", "def", and "ghi".

//Example 2:
//Input: s = "abcdefghij", k = 3, fill = "x"
//Output: ["abc","def","ghi","jxx"]
//Explanation:
//Similar to the previous example, we are forming the first three groups "abc", "def", and "ghi".
//For the last group, we can only use the character 'j' from the string. To complete this group, we add 'x' twice.
//Thus, the 4 groups formed are "abc", "def", "ghi", and "jxx".

//Constraints:
//1 <= s.length <= 100
//s consists of lowercase English letters only.
//1 <= k <= 100
//fill is a lowercase English letter.
