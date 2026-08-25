package P3701_3800.P3718_Smallest_Missing_Multiple_of_K;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {8, 2, 3, 4, 6};
        int k1 = 2;

        System.out.println(solution.missingMultiple(nums1, k1)); // 10


        int[] nums2 = {1, 4, 7, 10, 15};
        int k2 = 5;

        System.out.println(solution.missingMultiple(nums2, k2)); // 5
    }

    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        // store all numbers from nums
        for (int num : nums) {
            set.add(num);
        }

        // check k, 2*k, 3*k ...
        int multiple = k;

        while (set.contains(multiple)) {
            multiple += k;
        }

        return multiple;
    }

}

//Complexity:
// time - O(n)
// space - O(n + m)         m - number of multiples checked


//Given an integer array nums and an integer k, return the smallest positive multiple of k that is missing from nums.
//A multiple of k is any positive integer divisible by k.

//Example 1:
//Input: nums = [8,2,3,4,6], k = 2
//Output: 10
//Explanation:
//The multiples of k = 2 are 2, 4, 6, 8, 10, 12... and the smallest multiple missing from nums is 10.

//Example 2:
//Input: nums = [1,4,7,10,15], k = 5
//Output: 5
//Explanation:
//The multiples of k = 5 are 5, 10, 15, 20... and the smallest multiple missing from nums is 5.

//Constraints:
//1 <= nums.length <= 100
//1 <= nums[i] <= 100
//1 <= k <= 100
