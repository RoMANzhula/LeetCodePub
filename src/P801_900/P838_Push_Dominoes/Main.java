package P801_900.P838_Push_Dominoes;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.pushDominoes("RR.L"));             // Output: "RR.L"
        System.out.println(solution.pushDominoes(".L.R...LR..L.."));   // Output: "LL.RR.LLRRLL.."
    }

    public String pushDominoes(String dominoes) {
        char[] d = dominoes.toCharArray();
        int n = d.length;
        int[] forces = new int[n];

        // sweep from left to right
        int force = 0;
        for (int i = 0; i < n; i++) {
            if (d[i] == 'R') {
                force = n;
            } else if (d[i] == 'L') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] += force;
        }

        // sweep from right to left
        force = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (d[i] == 'L') {
                force = n;
            } else if (d[i] == 'R') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] -= force;
        }

        // build the final result
        StringBuilder result = new StringBuilder();
        for (int f : forces) {
            if (f > 0) {
                result.append('R');
            } else if (f < 0) {
                result.append('L');
            } else {
                result.append('.');
            }
        }

        return result.toString();
    }

}

//Explanation:
//We simulate the force coming from the right (R) and left (L).
//Force diminishes as it travels over time (or distance).
//At the end, if:
//-net force > 0 → 'R'
//-net force < 0 → 'L'
//-net force = 0 → '.' (balanced)
//Complexity:
//time and space: O(n)


//There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously
// push some of the dominoes either to the left or to the right.
//After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the
// dominoes falling to the right push their adjacent dominoes standing on the right.
//When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
//For the purposes of this question, we will consider that a falling domino expends no additional force to a
// falling or already fallen domino.

//You are given a string dominoes representing the initial state where:
//dominoes[i] = 'L', if the ith domino has been pushed to the left,
//dominoes[i] = 'R', if the ith domino has been pushed to the right, and
//dominoes[i] = '.', if the ith domino has not been pushed.
//Return a string representing the final state.

//Example 1:
//Input: dominoes = "RR.L"
//Output: "RR.L"
//Explanation: The first domino expends no additional force on the second domino.

//Example 2:
//Input: dominoes = ".L.R...LR..L.."
//Output: "LL.RR.LLRRLL.."

//Constraints:
//n == dominoes.length
//1 <= n <= 105
//dominoes[i] is either 'L', 'R', or '.'.
