package P3001_3100.P3020_Find_the_Maximum_Number_of_Elements_in_Subset;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumLength(new int[]{5, 4, 1, 2, 2})); // 3
        System.out.println(solution.maximumLength(new int[]{1, 3, 2, 4}));    // 1
        System.out.println(solution.maximumLength(new int[]{2, 2, 4, 4, 16})); // 5
        System.out.println(solution.maximumLength(new int[]{1, 1, 1, 1, 1})); // 5
    }

    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        // special handling for value 1
        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);
            ans = Math.max(ans, cnt % 2 == 0 ? cnt - 1 : cnt);
        }

        for (long start : freq.keySet()) {

            if (start == 1) continue;

            long cur = start;
            int len = 0;

            while (true) {
                Integer cnt = freq.get(cur);

                if (cnt == null) {
                    break;
                }

                if (cnt >= 2) {
                    len += 2;

                    if (cur > 31622) {
                        break;
                    }

                    cur *= cur;
                } else {
                    len++;
                    break;
                }
            }

            if ((len & 1) == 0) {
                len--;
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }

}

//Complexity:
// time and space - O(n)


//You are given an array of positive integers nums.
//You need to select a subset of nums which satisfies the following condition:
//You can place the selected elements in a 0-indexed array such that it follows the pattern:
// [x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x] (Note that k can be be any non-negative power of 2). For
// example, [2, 4, 16, 4, 2] and [3, 9, 3] follow the pattern while [2, 4, 8, 4, 2] does not.
//Return the maximum number of elements in a subset that satisfies these conditions.

//Example 1:
//Input: nums = [5,4,1,2,2]
//Output: 3
//Explanation: We can select the subset {4,2,2}, which can be placed in the array as [2,4,2] which follows the
// pattern and 22 == 4. Hence the answer is 3.

//Example 2:
//Input: nums = [1,3,2,4]
//Output: 1
//Explanation: We can select the subset {1}, which can be placed in the array as [1] which follows the pattern. Hence
// the answer is 1. Note that we could have also selected the subsets {2}, {3}, or {4}, there may be multiple subsets
// which provide the same answer.

//Constraints:
//2 <= nums.length <= 105
//1 <= nums[i] <= 109
