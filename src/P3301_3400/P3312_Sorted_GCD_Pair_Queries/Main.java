package P3301_3400.P3312_Sorted_GCD_Pair_Queries;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(Arrays.toString(
                solution.gcdValues(
                        new int[]{2,3,4},
                        new long[]{0,2,2}
                )
        ));

        System.out.println(Arrays.toString(
                solution.gcdValues(
                        new int[]{4,4,2,1},
                        new long[]{5,3,1,0}
                )
        ));

        System.out.println(Arrays.toString(
                solution.gcdValues(
                        new int[]{2,2},
                        new long[]{0,0}
                )
        ));
    }

    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;

        for (int x : nums) {
            max = Math.max(max, x);
        }

        int[] freq = new int[max + 1];

        for (int x : nums) {
            freq[x]++;
        }

        long[] exact = new long[max + 1];

        for (int g = max; g >= 1; g--) {

            long count = 0;

            for (int multiple = g; multiple <= max; multiple += g) {
                count += freq[multiple];
            }

            long pairs = count * (count - 1) / 2;

            for (int multiple = g + g; multiple <= max; multiple += g) {
                pairs -= exact[multiple];
            }

            exact[g] = pairs;
        }

        long[] prefix = new long[max + 1];

        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long target = queries[i] + 1;

            int left = 1;
            int right = max;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (prefix[mid] >= target)
                    right = mid;
                else
                    left = mid + 1;
            }

            answer[i] = left;
        }

        return answer;
    }

}

//Complexity:
// time - O(n + M log M + q Log M)
// space - O(M + q)
// M - max(nums), q - queries


//You are given an integer array nums of length n and an integer array queries.
//Let gcdPairs denote an array obtained by calculating the GCD of all possible pairs (nums[i], nums[j]), where
// 0 <= i < j < n, and then sorting these values in ascending order.
//For each query queries[i], you need to find the element at index queries[i] in gcdPairs.
//Return an integer array answer, where answer[i] is the value at gcdPairs[queries[i]] for each query.
//The term gcd(a, b) denotes the greatest common divisor of a and b.

//Example 1:
//Input: nums = [2,3,4], queries = [0,2,2]
//Output: [1,2,2]
//Explanation:
//gcdPairs = [gcd(nums[0], nums[1]), gcd(nums[0], nums[2]), gcd(nums[1], nums[2])] = [1, 2, 1].
//After sorting in ascending order, gcdPairs = [1, 1, 2].
//So, the answer is [gcdPairs[queries[0]], gcdPairs[queries[1]], gcdPairs[queries[2]]] = [1, 2, 2].

//Example 2:
//Input: nums = [4,4,2,1], queries = [5,3,1,0]
//Output: [4,2,1,1]
//Explanation:
//gcdPairs sorted in ascending order is [1, 1, 1, 2, 2, 4].

//Example 3:
//Input: nums = [2,2], queries = [0,0]
//Output: [2,2]
//Explanation:
//gcdPairs = [2].

//Constraints:
//2 <= n == nums.length <= 105
//1 <= nums[i] <= 5 * 104
//1 <= queries.length <= 105
//0 <= queries[i] < n * (n - 1) / 2
