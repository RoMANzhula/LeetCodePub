package P3801_3900.P3876_Construct_Uniform_Parity_Array_II;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 4, 7};

        System.out.println(solution.uniformArray(nums1));
    }

    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;

        Arrays.sort(nums1);

        int targetParity = nums1[0] % 2;

        boolean hasOdd = false;

        for (int i = 0; i < n; i++) {
            int parity = nums1[i] % 2;

            // if x already has the desired parity, choose x
            if (parity == targetParity) {
                // nothing to do
            } else {

                if (parity == 0) {
                    // x is even, target is odd - need a smaller odd number
                    if (!hasOdd) {
                        return false;
                    }
                } else {
                    // x is odd, target is even - need a smaller odd number
                    if (!hasOdd) {
                        return false;
                    }
                }
            }

            if (parity == 0) {
                // nothing to do
            } else {
                hasOdd = true;
            }
        }

        return true;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given an array nums1 of n distinct integers.
//You want to construct another array nums2 of length n such that the elements in nums2 are either all odd or all even.
//For each index i, you must choose exactly one of the following (in any order):
//nums2[i] = nums1[i]
//nums2[i] = nums1[i] - nums1[j], for an index j != i, such that nums1[i] - nums1[j] >= 1
//Return true if it is possible to construct such an array, otherwise return false.

//Example 1:
//Input: nums1 = [1,4,7]
//Output: true
//Explanation:
//Set nums2[0] = nums1[0] = 1.
//Set nums2[1] = nums1[1] - nums1[0] = 4 - 1 = 3.
//Set nums2[2] = nums1[2] = 7.
//nums2 = [1, 3, 7], and all elements are odd. Thus, the answer is true.

//Example 2:
//Input: nums1 = [2,3]
//Output: false
//Explanation:
//It is not possible to construct nums2 such that all elements have the same parity. Thus, the answer is false.

//Example 3:
//Input: nums1 = [4,6]
//Output: true
//Explanation:
//Set nums2[0] = nums1[0] = 4.
//Set nums2[1] = nums1[1] = 6.
//nums2 = [4, 6], and all elements are even. Thus, the answer is true.

//Constraints:
//1 <= n == nums1.length <= 105
//1 <= nums1[i] <= 109
//nums1 consists of distinct integers.
