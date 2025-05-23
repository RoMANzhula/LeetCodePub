package P1201_1300.P1269_Number_of_Ways_to_Stay_in_the_Same_Place_After_Some_Steps;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        System.out.println(solution.numWays(3, 2)); //output 4
        System.out.println(solution.numWays(2, 4)); //output 2
        System.out.println(solution.numWays(4, 2)); //output 8
    }

    public int numWays(int steps, int arrLen) {
        int mod = 1000000007; //max value
        int maxPos = Math.min(steps / 2, arrLen - 1); //not to move outside the array boundaries
        int[][] numberOfWays = new int[steps + 1][maxPos + 1];

        numberOfWays[0][0] = 1; //only one way - to stay in "0" position

        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxPos; j++) {
                numberOfWays[i][j] = numberOfWays[i - 1][j];
                if (j > 0) {
                    numberOfWays[i][j] = (numberOfWays[i][j] + numberOfWays[i - 1][j - 1]) % mod;
                }
                if (j < maxPos) {
                    numberOfWays[i][j] = (numberOfWays[i][j] + numberOfWays[i - 1][j + 1]) % mod;
                }
            }
        }

        return numberOfWays[steps][0]; //bingo
    }

}

//You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left,
// 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the
// array at any time).
//Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after
// exactly steps steps. Since the answer may be too large, return it modulo 109 + 7.

//Example 1:
//Input: steps = 3, arrLen = 2
//Output: 4
//Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
//Right, Left, Stay
//Stay, Right, Left
//Right, Stay, Left
//Stay, Stay, Stay

//Example 2:
//Input: steps = 2, arrLen = 4
//Output: 2
//Explanation: There are 2 differents ways to stay at index 0 after 2 steps
//Right, Left
//Stay, Stay

//Example 3:
//Input: steps = 4, arrLen = 2
//Output: 8

//Constraints:
//1 <= steps <= 500
//1 <= arrLen <= 106
