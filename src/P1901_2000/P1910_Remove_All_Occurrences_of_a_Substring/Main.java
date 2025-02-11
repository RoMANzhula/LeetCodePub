package P1901_2000.P1910_Remove_All_Occurrences_of_a_Substring;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "daabcbaabcbc";
        String part1 = "abc";
        System.out.println(solution.removeOccurrences(s1, part1)); // Output: "dab"

        String s2 = "axxxxyyyyb";
        String part2 = "xy";
        System.out.println(solution.removeOccurrences(s2, part2)); // Output "ab"
    }

    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        int index;

        while ((index = sb.indexOf(part)) != -1) {
            sb.delete(index, index + part.length());
        }

        return sb.toString();
    }

}

//Complexity: O(n) (worst case O(n*m))


//Given two strings s and part, perform the following operation on s until all occurrences of the substring part
// are removed:
//Find the leftmost occurrence of the substring part and remove it from s.
//Return s after removing all occurrences of part.
//A substring is a contiguous sequence of characters in a string.
//
//Example 1:
//Input: s = "daabcbaabcbc", part = "abc"
//Output: "dab"
//Explanation: The following operations are done:
//- s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
//- s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
//- s = "dababc", remove "abc" starting at index 3, so s = "dab".
//Now s has no occurrences of "abc".

//Example 2:
//Input: s = "axxxxyyyyb", part = "xy"
//Output: "ab"
//Explanation: The following operations are done:
//- s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
//- s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
//- s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
//- s = "axyb", remove "xy" starting at index 1 so s = "ab".
//Now s has no occurrences of "xy".
//
//Constraints:
//1 <= s.length <= 1000
//1 <= part.length <= 1000
//s​​​​​​ and part consists of lowercase English letters.
