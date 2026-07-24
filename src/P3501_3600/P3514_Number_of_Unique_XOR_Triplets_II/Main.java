package P3501_3600.P3514_Number_of_Unique_XOR_Triplets_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.uniqueXorTriplets(new int[]{1, 3}));       // 2
        System.out.println(solution.uniqueXorTriplets(new int[]{6, 7, 8, 9})); // 4
    }

    int uniqueXorTriplets(int[] nums) {
        final int MAX_XOR = 2048;

        boolean[][] dp = new boolean[4][MAX_XOR];
        dp[0][0] = true;

        for (int v : nums) {
            boolean[][] next = new boolean[4][MAX_XOR];

            //don`t use this index
            for (int c = 0; c <= 3; c++) {
                System.arraycopy(dp[c], 0, next[c], 0, MAX_XOR);
            }

            for (int chosen = 0; chosen <= 3; chosen++) {
                for (int xor = 0; xor < MAX_XOR; xor++) {
                    if (!dp[chosen][xor]) {
                        continue;
                    }

                    // take this index once
                    if (chosen + 1 <= 3) {
                        next[chosen + 1][xor ^ v] = true;
                    }

                    // take this index twice
                    if (chosen + 2 <= 3) {
                        next[chosen + 2][xor] = true;
                    }

                    // take this index three times
                    if (chosen + 3 <= 3) {
                        next[chosen + 3][xor ^ v] = true;
                    }
                }
            }

            dp = next;
        }

        int answer = 0;
        for (boolean exists : dp[3]) {
            if (exists) {
                answer++;
            }
        }

        return answer;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given an integer array nums.
//A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.
//Return the number of unique XOR triplet values from all possible triplets (i, j, k).

//Example 1:
//Input: nums = [1,3]
//Output: 2
//Explanation:
//The possible XOR triplet values are:
//(0, 0, 0) → 1 XOR 1 XOR 1 = 1
//(0, 0, 1) → 1 XOR 1 XOR 3 = 3
//(0, 1, 1) → 1 XOR 3 XOR 3 = 1
//(1, 1, 1) → 3 XOR 3 XOR 3 = 3
//The unique XOR values are {1, 3}. Thus, the output is 2.

//Example 2:
//Input: nums = [6,7,8,9]
//Output: 4
//Explanation:
//The possible XOR triplet values are {6, 7, 8, 9}. Thus, the output is 4.

//Constraints:
//1 <= nums.length <= 1500
//1 <= nums[i] <= 1500
