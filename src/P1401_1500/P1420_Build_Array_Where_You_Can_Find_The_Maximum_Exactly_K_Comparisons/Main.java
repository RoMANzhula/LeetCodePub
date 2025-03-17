package P1401_1500.P1420_Build_Array_Where_You_Can_Find_The_Maximum_Exactly_K_Comparisons;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 2;
        int m1 = 3;
        int k1 = 1;

        System.out.println(solution.numOfArrays(n1, m1, k1)); //expected 6
    }

    public int numOfArrays(int n, int m, int k) {
        int mod = 1000000007;

        //initialize the dp array
        long[][][] dp = new long[n + 1][m + 1][k + 1];

        //initialize base cases
        for (int j = 1; j <= m; j++) {
            dp[1][j][1] = 1;
        }

        //fill in the dp array
        for (int len = 2; len <= n; len++) {
            for (int max = 1; max <= m; max++) {
                for (int cost = 1; cost <= k; cost++) {
                    for (int prevMax = 1; prevMax < max; prevMax++) {
                        dp[len][max][cost] = (dp[len][max][cost] + dp[len - 1][prevMax][cost - 1]) % mod;
                    }
                    dp[len][max][cost] = (dp[len][max][cost] + dp[len - 1][max][cost] * max) % mod;
                }
            }
        }

        //calculate the total number of ways
        long result = 0;
        for (int max = 1; max <= m; max++) {
            result = (result + dp[n][max][k]) % mod;
        }

        return (int) result;
    }

}


//You are given three integers n, m and k. Consider the following algorithm to find the maximum element of an array
// of positive integers:
//maximum_values = -1;
//maximum_index = -1;
//search_cost = 0;
//n = arr.length
//for (int i = 0; i < n; i++) {
//  if (maximum_value < arr[i]) {
//      maximum_value = arr[i];
//      maximum_index = i;
//      search_cost += 1;
//      }
//  }
//  return maximum_index;

//You should build the array arr which has the following properties:
//arr has exactly n integers.
//1 <= arr[i] <= m where (0 <= i < n).
//After applying the mentioned algorithm to arr, the value search_cost is equal to k.
//Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large, the
// answer must be computed modulo 109 + 7.

//Example 1:
//Input: n = 2, m = 3, k = 1
//Output: 6
//Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]

//Example 2:
//Input: n = 5, m = 2, k = 3
//Output: 0
//Explanation: There are no possible arrays that satisfies the mentioned conditions.

//Example 3:
//Input: n = 9, m = 1, k = 1
//Output: 1
//Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]

//Constraints:
//1 <= n <= 50
//1 <= m <= 100
//0 <= k <= n
