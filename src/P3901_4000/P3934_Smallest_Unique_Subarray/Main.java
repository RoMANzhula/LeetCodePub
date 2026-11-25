package P3901_4000.P3934_Smallest_Unique_Subarray;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 3, 3};
        System.out.println(solution.smallestUniqueSubarray(nums1)); // 3

        int[] nums2 = {2, 1, 2, 3, 3};
        System.out.println(solution.smallestUniqueSubarray(nums2)); // 1

        int[] nums3 = {1, 1, 2, 2, 1};
        System.out.println(solution.smallestUniqueSubarray(nums3)); //2
    }

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        Integer[] sa = new Integer[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        int[] rank = nums.clone();
        int[] newRank = new int[n];
        final int[] initialRank = rank;

        Arrays.sort(sa, (a, b) ->
                Integer.compare(initialRank[a], initialRank[b]));

        int currentRank = 0;
        newRank[sa[0]] = 0;

        for (int i = 1; i < n; i++) {
            if (rank[sa[i]] != rank[sa[i - 1]]) {
                currentRank++;
            }

            newRank[sa[i]] = currentRank;
        }

        rank = newRank.clone();

        for (int k = 1; k < n; k *= 2) {
            final int len = k;
            final int[] r = rank;

            Arrays.sort(sa, (a, b) -> {
                if (r[a] != r[b]) {
                    return Integer.compare(r[a], r[b]);
                }

                int ra = a + len < n ? r[a + len] : -1;
                int rb = b + len < n ? r[b + len] : -1;

                return Integer.compare(ra, rb);
            });

            newRank[sa[0]] = 0;
            currentRank = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                int aNext = a + k < n ? rank[a + k] : -1;
                int bNext = b + k < n ? rank[b + k] : -1;

                if (rank[a] != rank[b] || aNext != bNext) {
                    currentRank++;
                }

                newRank[b] = currentRank;
            }

            int[] temp = rank;
            rank = newRank;
            newRank = temp;

            if (currentRank == n - 1) {
                break;
            }
        }

        int[] lcp = new int[n];
        int[] inverse = new int[n];

        for (int i = 0; i < n; i++) {
            inverse[sa[i]] = i;
        }

        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = inverse[i];

            if (r == 0) {
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n
                    && nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        int answer = n;

        for (int i = 0; i < n; i++) {
            int maxLcp = 0;

            if (i > 0) {
                maxLcp = Math.max(maxLcp, lcp[i]);
            }

            if (i + 1 < n) {
                maxLcp = Math.max(maxLcp, lcp[i + 1]);
            }

            int uniqueLength = maxLcp + 1;

            if (uniqueLength <= n - sa[i]) {
                answer = Math.min(answer, uniqueLength);
            }
        }

        return answer;
    }

}

//Complexity:
// time - O(n log^2 n)
// space - O(n)


//You are given an integer array nums.
//Find the minimum length of a subarray that is not identical to any other subarray in nums.
//Return an integer denoting the minimum possible length of such a subarray.
//Two subarrays are considered identical if they have the same length and the same elements in corresponding positions.

//Example 1:
//Input: nums = [3,3,3]
//Output: 3
//Explanation:
//Subarrays of length 1: [3] → appears 3 times
//Subarrays of length 2: [3, 3] → appears 2 times
//Subarrays of length 3: [3, 3, 3] → appears once
//The subarray [3, 3, 3] is unique, so the smallest unique subarray length is 3.

//Example 2:
//Input: nums = [2,1,2,3,3]
//Output: 1
//Explanation:
//Subarrays of length 1:
//[2] → appears 2 times
//[1] → appears once
//[3] → appears 2 times
//The subarray [1] is unique, so the smallest unique subarray length is 1.

//Example 3:
//Input: nums = [1,1,2,2,1]
//Output: 2
//Explanation:
//Subarrays of length 1:
//[1] → appears 3 times
//[2] → appears 2 times
//Subarrays of length 2:
//[1, 1] → appears once
//[1, 2] → appears once
//[2, 2] → appears once
//[2, 1] → appears once
//There is at least one subarray of length 2 that is unique, so the smallest unique subarray length is 2.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
