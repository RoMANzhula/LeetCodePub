package P3101_3200.P3186_Maximum_Total_Damage_With_Spell_Casting;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumTotalDamage(new int[]{1, 1, 3, 4})); // 6
        System.out.println(solution.maximumTotalDamage(new int[]{7, 1, 6, 6})); // 13
        System.out.println(solution.maximumTotalDamage(new int[]{5,9,2,10,2,7,10,9,3,8})); // 31
    }

    public long maximumTotalDamage(int[] power) {
        // sum totals by damage value
        Map<Integer, Long> map = new HashMap<>();
        for (int p : power) {
            map.put(p, map.getOrDefault(p, 0L) + p);
        }

        // sort distinct damage values
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        int n = keys.size();

        int[] vals = new int[n];
        long[] totals = new long[n];
        for (int i = 0; i < n; i++) {
            vals[i] = keys.get(i);
            totals[i] = map.get(vals[i]);
        }

        // dp[i] = best total using values[0..i]
        long[] dp = new long[n];
        dp[0] = totals[0];

        for (int i = 1; i < n; i++) {
            // find largest j < i with vals[j] <= vals[i] - 3
            int target = vals[i] - 3;
            int l = 0, r = i - 1, j = -1;
            while (l <= r) {
                int mid = (l + r) >>> 1;
                if (vals[mid] <= target) {
                    j = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

            long take = totals[i] + (j == -1 ? 0 : dp[j]);
            dp[i] = Math.max(dp[i - 1], take);
        }

        return dp[n - 1];
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//A magician has various spells.
//You are given an array power, where each element represents the damage of a spell. Multiple spells can have the
// same damage value.
//It is a known fact that if a magician decides to cast a spell with a damage of power[i], they cannot cast any
// spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.
//Each spell can be cast only once.
//Return the maximum possible total damage that a magician can cast.

//Example 1:
//Input: power = [1,1,3,4]
//Output: 6
//Explanation:
//The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.

//Example 2:
//Input: power = [7,1,6,6]
//Output: 13
//Explanation:
//The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.

//Constraints:
//1 <= power.length <= 105
//1 <= power[i] <= 109
