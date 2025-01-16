package P2401_2500.P2425Bitwise_XOR_of_All_Pairings;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 1, 3};
        int[] nums2 = {10, 2, 5, 0};
        System.out.println(solution.xorAllNums(nums1, nums2)); // Output: 13

        int[] nums1b = {1, 2};
        int[] nums2b = {3, 4};
        System.out.println(solution.xorAllNums(nums1b, nums2b)); // Output: 0
    }

    public int xorAllNums(int[] nums1, int[] nums2) {
        int xorNums1 = 0;
        int xorNums2 = 0;

        // compute XOR of all elements in nums1
        for (int num : nums1) {
            xorNums1 ^= num;
        }

        // compute XOR of all elements in nums2
        for (int num : nums2) {
            xorNums2 ^= num;
        }

        // compute the final XOR result based on parity
        int result = 0;
        if (nums1.length % 2 != 0) { // nums1 has odd length
            result ^= xorNums2;
        }
        if (nums2.length % 2 != 0) { // nums2 has odd length
            result ^= xorNums1;
        }

        return result;
    }
}

//Explanation of the Fix:
//If nums1.length is odd:
//-Every element in nums2 contributes once to the XOR result because it pairs with all elements in nums1.
//If nums2.length is odd:
//-Every element in nums1 contributes once to the XOR result because it pairs with all elements in nums2.
//If both lengths are even:
//-All contributions cancel out, resulting in 0.
//Complexity:
//Time Complexity: O(n+m), where n=nums1.length and m=nums2.length.
//Space Complexity: O(1).


//You are given two 0-indexed arrays, nums1 and nums2, consisting of non-negative integers. There exists
// another array, nums3, which contains the bitwise XOR of all pairings of integers between nums1 and
// nums2 (every integer in nums1 is paired with every integer in nums2 exactly once).
//Return the bitwise XOR of all integers in nums3.
//
//Example 1:
//Input: nums1 = [2,1,3], nums2 = [10,2,5,0]
//Output: 13
//Explanation:
//A possible nums3 array is [8,0,7,2,11,3,4,1,9,1,6,3].
//The bitwise XOR of all these numbers is 13, so we return 13.

//Example 2:
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 0
//Explanation:
//All possible pairs of bitwise XORs are nums1[0] ^ nums2[0], nums1[0] ^ nums2[1], nums1[1] ^ nums2[0],
//and nums1[1] ^ nums2[1].
//Thus, one possible nums3 array is [2,5,1,6].
//2 ^ 5 ^ 1 ^ 6 = 0, so we return 0.
//
//Constraints:
//1 <= nums1.length, nums2.length <= 105
//0 <= nums1[i], nums2[j] <= 109
