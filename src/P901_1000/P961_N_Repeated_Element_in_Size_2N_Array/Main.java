package P901_1000.P961_N_Repeated_Element_in_Size_2N_Array;

import java.util.HashSet;
import java.util.Set;


public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 3};
        int[] nums2 = {2, 1, 2, 5, 3, 2};
        int[] nums3 = {5, 1, 5, 2, 5, 3, 5, 4};

        System.out.println(solution.repeatedNTimes(nums1)); // 3
        System.out.println(solution.repeatedNTimes(nums2)); // 2
        System.out.println(solution.repeatedNTimes(nums3)); // 5
    }

    public int repeatedNTimes(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (!seen.add(num)) {
                return num; // found the repeated element
            }
        }

        return -1; // never reached due to problem constraints
    }

}

//Complexity:
// time and space - O(n)


//You are given an integer array nums with the following properties:
//nums.length == 2 * n.
//nums contains n + 1 unique elements.
//Exactly one element of nums is repeated n times.
//Return the element that is repeated n times.

//Example 1:
//Input: nums = [1,2,3,3]
//Output: 3

//Example 2:
//Input: nums = [2,1,2,5,3,2]
//Output: 2

//Example 3:
//Input: nums = [5,1,5,2,5,3,5,4]
//Output: 5

//Constraints:
//2 <= n <= 5000
//nums.length == 2 * n
//0 <= nums[i] <= 104
//nums contains n + 1 unique elements and one of them is repeated exactly n times.
