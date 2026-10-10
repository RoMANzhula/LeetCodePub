package P2301_2400.P2333_Minimum_Sum_of_Squared_Difference;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {2, 10, 20, 19};
        System.out.println(solution.minSumSquareDiff(nums1, nums2, 0, 0)); // 579

        int[] nums3 = {1, 4, 10, 12};
        int[] nums4 = {5, 8, 6, 9};
        System.out.println(solution.minSumSquareDiff(nums3, nums4, 1, 1)); // 43
    }

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long operations = (long) k1 + k2;
        int[] freq = new int[100001];
        long totalDiff = 0;

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            totalDiff += diff;
        }

        if (operations >= totalDiff) {
            return 0L;
        }

        for (int d = 100000; d > 0 && operations > 0; d--) {
            if (freq[d] == 0) {
                continue;
            }

            long move = Math.min(operations, freq[d]);

            freq[d] -= (int) move;
            freq[d - 1] += (int) move;
            operations -= move;
        }

        long result = 0;

        for (int d = 1; d < freq.length; d++) {
            result += (long) d * d * freq[d];
        }

        return result;
    }

}

//Complexity:
// time - O(n + D)   D is the maximum absolute difference ~ D <= 10^5
// space - O(D)


//You are given two positive 0-indexed integer arrays nums1 and nums2, both of length n.
//The sum of squared difference of arrays nums1 and nums2 is defined as the sum of (nums1[i] - nums2[i])2 for
// each 0 <= i < n.
//You are also given two positive integers k1 and k2. You can modify any of the elements of nums1 by +1 or -1 at
// most k1 times. Similarly, you can modify any of the elements of nums2 by +1 or -1 at most k2 times.
//Return the minimum sum of squared difference after modifying array nums1 at most k1 times and modifying array
// nums2 at most k2 times.
//Note: You are allowed to modify the array elements to become negative integers.

//Example 1:
//Input: nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
//Output: 579
//Explanation: The elements in nums1 and nums2 cannot be modified because k1 = 0 and k2 = 0.
//The sum of square difference will be: (1 - 2)2 + (2 - 10)2 + (3 - 20)2 + (4 - 19)2 = 579.

//Example 2:
//Input: nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
//Output: 43
//Explanation: One way to obtain the minimum sum of square difference is:
//- Increase nums1[0] once.
//- Increase nums2[2] once.
//The minimum of the sum of square difference will be:
//(2 - 5)2 + (4 - 8)2 + (10 - 7)2 + (12 - 9)2 = 43.
//Note that, there are other ways to obtain the minimum of the sum of square difference, but there is no way to
// obtain a sum smaller than 43.

//Constraints:
//n == nums1.length == nums2.length
//1 <= n <= 105
//0 <= nums1[i], nums2[i] <= 105
//0 <= k1, k2 <= 109
