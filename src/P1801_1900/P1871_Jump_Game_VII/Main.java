package P1801_1900.P1871_Jump_Game_VII;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.canReach("011010", 2, 3)); // true
        System.out.println(solution.canReach("01101110", 2, 3)); // false
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        int len = s.length();
        int farthest = 1;
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int start = Math.max(current + minJump, farthest);
            int end = Math.min(current + maxJump, len - 1);

            for (int i = start; i <= end; i++) {
                if (s.charAt(i) == '0') {
                    if (i == len - 1) return true;

                    queue.offer(i);
                }
            }

            farthest = end + 1;
        }

        return len == 1;
    }

}

//Complexity:
// time and space - O(n)


//You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are
// standing at index 0, which is equal to '0'. You can move from index i to index j if the following
// conditions are fulfilled:
//i + minJump <= j <= min(i + maxJump, s.length - 1), and
//s[j] == '0'.
//Return true if you can reach index s.length - 1 in s, or false otherwise.

//Example 1:
//Input: s = "011010", minJump = 2, maxJump = 3
//Output: true
//Explanation:
//In the first step, move from index 0 to index 3.
//In the second step, move from index 3 to index 5.

//Example 2:
//Input: s = "01101110", minJump = 2, maxJump = 3
//Output: false

//Constraints:
//2 <= s.length <= 105
//s[i] is either '0' or '1'.
//s[0] == '0'
//1 <= minJump <= maxJump < s.length
