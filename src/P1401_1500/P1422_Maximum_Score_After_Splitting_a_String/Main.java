package P1401_1500.P1422_Maximum_Score_After_Splitting_a_String;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxScore("011101")); // Output: 5
        System.out.println(solution.maxScore("00111"));  // Output: 5
        System.out.println(solution.maxScore("1111"));   // Output: 3
    }

    public int maxScore(String s) {
        int maxScore = 0;
        int zerosLeft = 0;
        int onesRight = countOnes(s);

        // traverse the string and calculate scores for each split
        for (int i = 0; i < s.length() - 1; i++) { // avoid splitting at the last character
            if (s.charAt(i) == '0') zerosLeft++;
            else onesRight--;

            // calculate the score for the current split
            int currentScore = zerosLeft + onesRight;
            maxScore = Math.max(maxScore, currentScore);
        }

        return maxScore;
    }

    // count total ones in the string
    private int countOnes(String s) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') count++;
        }

        return count;
    }

}

//Explanation:
//Count Total 1s: We first calculate the total number of 1s in the string, which is needed to calculate the
// right substring score.
//Traverse the String: For each character in the string (except the last character, since both
// substrings must be non-empty):
//Increment leftZeros if the character is 0.
//Decrement rightOnes if the character is 1.
//Calculate the current score as leftZeros + rightOnes and update the maxScore.
//Return the Maximum Score: After the traversal, maxScore contains the highest score achievable.
//This solution runs in O(n), where n is the length of the string. It efficiently calculates the maximum score by
// using a single pass to calculate the scores for all possible splits.


//Given a string s of zeros and ones, return the maximum score after splitting the string into two
// non-empty substrings (i.e. left substring and right substring).
//
//The score after splitting a string is the number of zeros in the left substring plus the number of ones
// in the right substring.

//Example 1:
//Input: s = "011101"
//Output: 5
//Explanation:
//All possible ways of splitting s into two non-empty substrings are:
//left = "0" and right = "11101", score = 1 + 4 = 5
//left = "01" and right = "1101", score = 1 + 3 = 4
//left = "011" and right = "101", score = 1 + 2 = 3
//left = "0111" and right = "01", score = 1 + 1 = 2
//left = "01110" and right = "1", score = 2 + 1 = 3

//Example 2:
//Input: s = "00111"
//Output: 5
//Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5

//Example 3:
//Input: s = "1111"
//Output: 3
//
//Constraints:
//2 <= s.length <= 500
//The string s consists of characters '0' and '1' only.
