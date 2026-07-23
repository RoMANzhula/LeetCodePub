package P3501_3600.P3513_Number_of_Unique_XOR_Triplets_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2};
        System.out.println(solution.uniqueXorTriplets(nums1)); // 2

        int[] nums2 = {3, 1, 2};
        System.out.println(solution.uniqueXorTriplets(nums2)); // 4

        int[] nums3 = {1, 2, 3, 4};
        System.out.println(solution.uniqueXorTriplets(nums3)); // 8

        int[] nums4 = {1, 2, 3, 4, 5};
        System.out.println(solution.uniqueXorTriplets(nums4)); // 8

        int[] nums5 = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(solution.uniqueXorTriplets(nums5)); // 16
    }

    public int uniqueXorTriplets(int[] nums) {
        int len = nums.length;

        if (len <= 2) return len;

        int bits = 32 - Integer.numberOfLeadingZeros(len);

        return 1 << bits;
    }

}

//Complexity:
// time and space - O(1)


//You are given an integer array nums of length n, where nums is a permutation of the numbers in the range [1, n].
//A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.
//Return the number of unique XOR triplet values from all possible triplets (i, j, k).

//Example 1:
//Input: nums = [1,2]
//Output: 2
//Explanation:
//The possible XOR triplet values are:
//(0, 0, 0) → 1 XOR 1 XOR 1 = 1
//(0, 0, 1) → 1 XOR 1 XOR 2 = 2
//(0, 1, 1) → 1 XOR 2 XOR 2 = 1
//(1, 1, 1) → 2 XOR 2 XOR 2 = 2
//The unique XOR values are {1, 2}, so the output is 2.

//Example 2:
//Input: nums = [3,1,2]
//Output: 4
//Explanation:
//The possible XOR triplet values include:
//(0, 0, 0) → 3 XOR 3 XOR 3 = 3
//(0, 0, 1) → 3 XOR 3 XOR 1 = 1
//(0, 0, 2) → 3 XOR 3 XOR 2 = 2
//(0, 1, 2) → 3 XOR 1 XOR 2 = 0
//The unique XOR values are {0, 1, 2, 3}, so the output is 4.

//Constraints:
//1 <= n == nums.length <= 105
//1 <= nums[i] <= n
//nums is a permutation of integers from 1 to n.
