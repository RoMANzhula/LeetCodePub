package P2101_2200.P2109_Adding_Spaces_to_a_String;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "LeetcodeHelpsMeLearn";
        int[] spaces1 = {8, 13, 15};
        System.out.println(solution.addSpaces(s1, spaces1)); // Output: "Leetcode Helps Me Learn"

        // Example 2
        String s2 = "icodeinpython";
        int[] spaces2 = {1, 5, 7, 9};
        System.out.println(solution.addSpaces(s2, spaces2)); // Output: "i code in py thon"

        // Example 3
        String s3 = "spacing";
        int[] spaces3 = {0, 1, 2, 3, 4, 5, 6};
        System.out.println(solution.addSpaces(s3, spaces3)); // Output: " s p a c i n g"
    }

    public String addSpaces(String s, int[] spaces) {
        StringBuilder result = new StringBuilder();
        int spaceIndex = 0; // Index in the spaces array
        int n = spaces.length;

        for (int i = 0; i < s.length(); i++) {
            // If the current index matches the next index in the spaces array, add a space
            if (spaceIndex < n && i == spaces[spaceIndex]) {
                result.append(" ");
                spaceIndex++;
            }
            // Append the current character
            result.append(s.charAt(i));
        }

        return result.toString();
    }

}


//You are given a 0-indexed string s and a 0-indexed integer array spaces that describes the indices in the
// original string where spaces will be added. Each space should be inserted before the character at the given index.
//For example, given s = "EnjoyYourCoffee" and spaces = [5, 9], we place spaces before 'Y' and 'C', which
// are at indices 5 and 9 respectively. Thus, we obtain "Enjoy Your Coffee".
//Return the modified string after the spaces have been added.
//
//Example 1:
//Input: s = "LeetcodeHelpsMeLearn", spaces = [8,13,15]
//Output: "Leetcode Helps Me Learn"
//Explanation:
//The indices 8, 13, and 15 correspond to the underlined characters in "LeetcodeHelpsMeLearn".
//We then place spaces before those characters.

//Example 2:
//Input: s = "icodeinpython", spaces = [1,5,7,9]
//Output: "i code in py thon"
//Explanation:
//The indices 1, 5, 7, and 9 correspond to the underlined characters in "icodeinpython".
//We then place spaces before those characters.

//Example 3:
//Input: s = "spacing", spaces = [0,1,2,3,4,5,6]
//Output: " s p a c i n g"
//Explanation:
//We are also able to place spaces before the first character of the string.
//
//Constraints:
//1 <= s.length <= 3 * 105
//s consists only of lowercase and uppercase English letters.
//1 <= spaces.length <= 3 * 105
//0 <= spaces[i] <= s.length - 1
//All the values of spaces are strictly increasing.