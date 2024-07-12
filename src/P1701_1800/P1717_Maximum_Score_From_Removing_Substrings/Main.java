package P1701_1800.P1717_Maximum_Score_From_Removing_Substrings;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumGain("cdbcbbaaabab", 4, 5));  // Example 1, expected output 19
        System.out.println(solution.maximumGain("aabbaaxybbaabb", 5, 4)); // Example 2, expected output 20
    }

    public int maximumGain(String s, int x, int y) {
        if (x > y) {
            return calculateMaxPoints(s, 'a', 'b', x, y);
        } else {
            return calculateMaxPoints(s, 'b', 'a', y, x);
        }
    }

    private static int calculateMaxPoints(String s, char first, char second, int highPoints, int lowPoints) {
        int totalPoints = 0;
        StringBuilder stack = new StringBuilder();

        // First remove the higher point substring
        for (char c : s.toCharArray()) {
            if (stack.length() > 0 && stack.charAt(stack.length() - 1) == first && c == second) {
                stack.deleteCharAt(stack.length() - 1);
                totalPoints += highPoints;
            } else {
                stack.append(c);
            }
        }

        // Convert the remaining stack to a string for the second pass
        s = stack.toString();
        stack.setLength(0);

        // Now remove the lower point substring
        for (char c : s.toCharArray()) {
            if (stack.length() > 0 && stack.charAt(stack.length() - 1) == second && c == first) {
                stack.deleteCharAt(stack.length() - 1);
                totalPoints += lowPoints;
            } else {
                stack.append(c);
            }
        }

        return totalPoints;
    }
}

//Explanation:
//maxPoints function:
//
//It determines which substring removal gives more points and prioritizes that removal first.
//It calls calculateMaxPoints with appropriate characters and points.
//calculateMaxPoints function:
//It uses a StringBuilder to act as a stack to process the string.
//It first processes the higher point substring removal.
//After processing the first pass, it converts the remaining characters in the stack to a string and processes
// the lower point substring removal in the second pass.
//The function keeps track of the total points.
//Test Cases:
//The function will now correctly handle the examples provided and return the expected outputs:
//Input: "cdbcbbaaabab", x = 4, y = 5, Expected output: 19
//Input: "aabbaaxybbaabb", x = 5, y = 4, Expected output: 20
//This approach ensures that we correctly prioritize the removals to maximize the points, fixing the previous issue.


//You are given a string s and two integers x and y. You can perform two types of operations any number of times.
//Remove substring "ab" and gain x points.
//For example, when removing "ab" from "cabxbae" it becomes "cxbae".
//Remove substring "ba" and gain y points.
//For example, when removing "ba" from "cabxbae" it becomes "cabxe".
//Return the maximum points you can gain after applying the above operations on s.
//
//Example 1:
//Input: s = "cdbcbbaaabab", x = 4, y = 5
//Output: 19
//Explanation:
//- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
//- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
//- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
//- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
//Total score = 5 + 4 + 5 + 5 = 19.

//Example 2:
//Input: s = "aabbaaxybbaabb", x = 5, y = 4
//Output: 20
//
//Constraints:
//1 <= s.length <= 105
//1 <= x, y <= 104
//s consists of lowercase English letters.