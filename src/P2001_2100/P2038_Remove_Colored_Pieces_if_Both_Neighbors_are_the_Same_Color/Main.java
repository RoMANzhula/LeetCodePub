package P2001_2100.P2038_Remove_Colored_Pieces_if_Both_Neighbors_are_the_Same_Color;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        String str1 = "AAABABB";
        String str2 = "AA";
        String str3 = "ABBBBBBBAAA";
        String str4 = "AAAABBBB";

        System.out.println(solution.winnerOfGame(str1)); //true
        System.out.println(solution.winnerOfGame(str2)); //false
        System.out.println(solution.winnerOfGame(str3)); //false
        System.out.println(solution.winnerOfGame(str4)); //false
    }

    public boolean winnerOfGame(String colors) {
        int n = colors.length();
        int aliceCount = 0;
        int bobCount = 0;

        //iterate through the string to count consecutive 'A' and 'B' segments.
        for (int i = 0; i < n; i++) {
            char currentChar = colors.charAt(i);
            int consecutiveCount = 0;

            while (i < n && colors.charAt(i) == currentChar) {
                consecutiveCount++;
                i++;
            }

            if (currentChar == 'A') {
                aliceCount += Math.max(0, consecutiveCount - 2);
            } else {
                bobCount += Math.max(0, consecutiveCount - 2);
            }

            i--; //Adjust i to account for the end of the current segment.
        }

        return aliceCount > bobCount;
    }

//    public boolean winnerOfGame(String colors) {
//        int n = colors.length();
//        int aliceSegments = 0;
//        int bobSegments = 0;
//        int countA = 0;
//        int countB = 0;
//
//        //count segments of 'A' and 'B'
//        for (int i = 0; i < n; i++) {
//            char color = colors.charAt(i);
//            int count = 0;
//            if (color == 'A') {
//                while (i < n && colors.charAt(i) == 'A') {
//                    count++;
//                    i++;
//
//                }
//                countA++;
//                aliceSegments += Math.max(0, count - 2); //Alice can remove 'A' from segments of length >= 3
//            } else {
//                while (i < n && colors.charAt(i) == 'B') {
//                    count++;
//                    i++;
//
//                }
//                countB++;
//                bobSegments += Math.max(0, count - 2); //Bob can remove 'B' from segments of length >= 3
//            }
//
//        }
//        if (countA == countB) return false;
//
//        return aliceSegments > bobSegments; //Alice wins if she has more removable segments
//    }


//    public boolean winnerOfGame(String colors) {
//        char[] chars = colors.toCharArray();
//        if (colors.length() < 3) return false;
//
//        for (int i = 1; i < chars.length - 1; i++) {
//            char left = chars[i - 1];
//            char right = chars[i + 1];
//            char center = chars[i];
//
//            if (left == center && center == right && center != chars[i + 2]) return true;
//
//            int countA = 0;
//            int countB = 0;
//            while (i < colors.length() && chars[i] == 'A') {
//                countA++;
//                i++;
//            }
//            while (i < colors.length() && chars[i] == 'B') {
//                countB++;
//                i++;
//            }
//            if (countA > 3) return false;
//            if (countB > 3) return false;
//        }
//
//        return false;
//    }
}

//There are n pieces arranged in a line, and each piece is colored either by 'A' or by 'B'. You are given a string
// colors of length n where colors[i] is the color of the ith piece.
//Alice and Bob are playing a game where they take alternating turns removing pieces from the line. In this game,
// Alice moves first.
//Alice is only allowed to remove a piece colored 'A' if both its neighbors are also colored 'A'. She is not allowed
// to remove pieces that are colored 'B'.
//Bob is only allowed to remove a piece colored 'B' if both its neighbors are also colored 'B'. He is not allowed to
// remove pieces that are colored 'A'.
//Alice and Bob cannot remove pieces from the edge of the line.
//If a player cannot make a move on their turn, that player loses and the other player wins.
//Assuming Alice and Bob play optimally, return true if Alice wins, or return false if Bob wins.

//Example 1:
//Input: colors = "AAABABB"
//Output: true
//Explanation:
//AAABABB -> AABABB
//Alice moves first.
//She removes the second 'A' from the left since that is the only 'A' whose neighbors are both 'A'.
//Now it's Bob's turn.
//Bob cannot make a move on his turn since there are no 'B's whose neighbors are both 'B'.
//Thus, Alice wins, so return true.

//Example 2:
//Input: colors = "AA"
//Output: false
//Explanation:
//Alice has her turn first.
//There are only two 'A's and both are on the edge of the line, so she cannot move on her turn.
//Thus, Bob wins, so return false.

//Example 3:
//Input: colors = "ABBBBBBBAAA"
//Output: false
//Explanation:
//ABBBBBBBAAA -> ABBBBBBBAA
//Alice moves first.
//Her only option is to remove the second to last 'A' from the right.
//ABBBBBBBAA -> ABBBBBBAA
//Next is Bob's turn.
//He has many options for which 'B' piece to remove. He can pick any.
//On Alice's second turn, she has no more pieces that she can remove.
//Thus, Bob wins, so return false.

//Constraints:
//1 <= colors.length <= 105
//colors consists of only the letters 'A' and 'B'
