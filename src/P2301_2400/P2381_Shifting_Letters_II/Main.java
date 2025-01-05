package P2301_2400.P2381_Shifting_Letters_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "abc";
        int[][] shifts1 = {{0, 1, 0}, {1, 2, 1}, {0, 2, 1}};
        System.out.println(solution.shiftingLetters(s1, shifts1)); // Output: "ace"

        String s2 = "dztz";
        int[][] shifts2 = {{0, 0, 0}, {1, 1, 1}};
        System.out.println(solution.shiftingLetters(s2, shifts2)); // Output: "catz"
    }

    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] shift = new int[n + 1]; // use n+1 to handle range increments easily

        // Apply the difference array logic
        for (int[] sh : shifts) {
            int start = sh[0];
            int end = sh[1];
            int direction = sh[2];
            shift[start] += (direction == 1 ? 1 : -1);
            shift[end + 1] -= (direction == 1 ? 1 : -1);
        }

        // compute the prefix sum to get the net shifts
        int[] netShift = new int[n];
        int cumulativeShift = 0;
        for (int i = 0; i < n; i++) {
            cumulativeShift += shift[i];
            netShift[i] = cumulativeShift;
        }

        // apply the shifts to the string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char originalChar = s.charAt(i);
            int shiftAmount = netShift[i];
            // Calculate the new character with wrapping
            int newChar = (originalChar - 'a' + shiftAmount) % 26;
            if (newChar < 0) newChar += 26; // Handle negative wrapping
            result.append((char) ('a' + newChar));
        }

        return result.toString();
    }
}

//Explanation:
//Difference Array:
//-shift[start] is incremented or decremented based on the direction.
//-shift[end + 1] is updated to cancel out the increment/decrement beyond the range.
//Prefix Sum:
//-The netShift array accumulates the cumulative effect of all ranges on each index.
//Shift Calculation:
//-The formula (originalChar - 'a' + shiftAmount) % 26 calculates the new character.
//-If newChar is negative, wrap it around using newChar += 26.
//Efficiency:
//The solution runs in O(n+m), where n is the length of the string and m is the number of shift operations.


//You are given a string s of lowercase English letters and a 2D integer array shifts where
// shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s from the index starti to
// the index endi (inclusive) forward if directioni = 1, or shift the characters backward if directioni = 0.
//Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so
// that 'z' becomes 'a'). Similarly, shifting a character backward means replacing it with the previous letter
// in the alphabet (wrapping around so that 'a' becomes 'z').
//Return the final string after all such shifts to s are applied.
//
//Example 1:
//Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
//Output: "ace"
//Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
//Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
//Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".

//Example 2:
//Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
//Output: "catz"
//Explanation: Firstly, shift the characters from index 0 to index 0 backward. Now s = "cztz".
//Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".
//
//Constraints:
//1 <= s.length, shifts.length <= 5 * 104
//shifts[i].length == 3
//0 <= starti <= endi < s.length
//0 <= directioni <= 1
//s consists of lowercase English letters.
