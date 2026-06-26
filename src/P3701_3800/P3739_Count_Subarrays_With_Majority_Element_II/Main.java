package P3701_3800.P3739_Count_Subarrays_With_Majority_Element_II;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countMajoritySubarrays(new int[]{1,2,2,3}, 2)); // 5
        System.out.println(solution.countMajoritySubarrays(new int[]{1,1,1,1}, 1)); // 10
        System.out.println(solution.countMajoritySubarrays(new int[]{1,2,3}, 4));   // 0
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        // build prefix sums of transformed array
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (nums[i] == target ? 1 : -1);
        }

        // coordinate compression
        int[] sorted = pref.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rank = new HashMap<>();
        int id = 1;
        for (int v : sorted) {
            if (!rank.containsKey(v)) {
                rank.put(v, id++);
            }
        }

        // fenwick tree
        Fenwick bit = new Fenwick(id + 2);

        long ans = 0;

        // count pairs pref[j] > pref[i]
        for (int x : pref) {
            int r = rank.get(x);

            // count how many previous < current
            ans += bit.sum(r - 1);

            bit.add(r, 1);
        }

        return ans;
    }

    class Fenwick {
        long[] bit;

        Fenwick(int n) {
            bit = new long[n + 2];
        }

        void add(int i, long val) {
            while (i < bit.length) {
                bit[i] += val;
                i += i & -i;
            }
        }

        long sum(int i) {
            long res = 0;
            while (i > 0) {
                res += bit[i];
                i -= i & -i;
            }
            return res;
        }
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given an integer array nums and an integer target.
//Return the number of subarrays of nums in which target is the majority element.
//The majority element of a subarray is the element that appears strictly more than half of the times in that subarray.

//Example 1:
//Input: nums = [1,2,2,3], target = 2
//Output: 5
//Explanation:
//Valid subarrays with target = 2 as the majority element:
//nums[1..1] = [2]
//nums[2..2] = [2]
//nums[1..2] = [2,2]
//nums[0..2] = [1,2,2]
//nums[1..3] = [2,2,3]
//So there are 5 such subarrays.

//Example 2:
//Input: nums = [1,1,1,1], target = 1
//Output: 10
//Explanation:
//All 10 subarrays have 1 as the majority element.

//Example 3:
//Input: nums = [1,2,3], target = 4
//Output: 0
//Explanation:
//target = 4 does not appear in nums at all. Therefore, there cannot be any subarray where 4 is the majority element.
// Hence the answer is 0.

//Constraints:
//1 <= nums.length <= 10
//1 <= nums[i] <= 10
//1 <= target <= 109
