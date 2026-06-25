package P3301_3400.P3737_Count_Subarrays_With_Majority_Element_I;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countMajoritySubarrays(new int[]{1, 2, 2, 3}, 2)); // 5
        System.out.println(solution.countMajoritySubarrays(new int[]{1, 1, 1, 1}, 1)); // 10
        System.out.println(solution.countMajoritySubarrays(new int[]{1, 2, 3}, 4)); // 0
    }

    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        // coordinate compression
        int[] values = prefix.clone();
        Arrays.sort(values);

        Map<Integer, Integer> rank = new HashMap<>();
        int idx = 1;

        for (int v : values) {
            if (!rank.containsKey(v)) {
                rank.put(v, idx++);
            }
        }

        Fenwick bit = new Fenwick(idx);

        int answer = 0;

        for (int p : prefix) {
            int r = rank.get(p);

            // count previous prefix sums strictly smaller than current
            answer += bit.sum(r - 1);

            bit.add(r, 1);
        }

        return answer;
    }

    class Fenwick {
        private final long[] tree;

        Fenwick(int n) {
            tree = new long[n + 1];
        }

        void add(int index, long value) {
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        long sum(int index) {
            long res = 0;

            while (index > 0) {
                res += tree[index];
                index -= index & -index;
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
//target = 4 does not appear in nums at all. Therefore, there cannot be any subarray where 4 is the majority
// element. Hence the answer is 0.

//Constraints:
//1 <= nums.length <= 1000
//1 <= nums[i] <= 10​​​​​​​9
//1 <= target <= 109
