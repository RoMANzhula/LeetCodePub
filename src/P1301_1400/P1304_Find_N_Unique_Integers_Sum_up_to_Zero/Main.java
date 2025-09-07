package P1301_1400.P1304_Find_N_Unique_Integers_Sum_up_to_Zero;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();


        int n1 = 5;
        int n2 = 3;
        int n3 = 1;

        System.out.println("n = " + n1 + " → " + Arrays.toString(solution.sumZero(n1))); // [-1, 1, -2, 2, 0]
        System.out.println("n = " + n2 + " → " + Arrays.toString(solution.sumZero(n2))); // [-1, 1, 0]
        System.out.println("n = " + n3 + " → " + Arrays.toString(solution.sumZero(n3))); // [0]
    }

    public int[] sumZero(int n) {
        int[] result = new int[n];
        int index = 0;

        // fill with pairs (-i, i)
        for (int i = 1; i <= n / 2; i++) {
            result[index++] = -i;
            result[index++] = i;
        }

        // if n is odd, add 0
        if (n % 2 == 1) {
            result[index] = 0;
        }

        return result;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given an integer n, return any array containing n unique integers such that they add up to 0.

//Example 1:
//Input: n = 5
//Output: [-7,-1,1,3,4]
//Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].

//Example 2:
//Input: n = 3
//Output: [-1,0,1]

//Example 3:
//Input: n = 1
//Output: [0]

//Constraints:
//1 <= n <= 1000
