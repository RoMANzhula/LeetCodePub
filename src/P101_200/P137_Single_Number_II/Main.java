package P101_200.P137_Single_Number_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 2, 3, 2};
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};

        System.out.println(solution.singleNumber(nums1)); // 3
        System.out.println(solution.singleNumber(nums2)); // 99
    }

    public int singleNumber(int[] nums) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            int sum = 0;

            for (int num : nums) {
                sum += (num >> i) & 1;
            }

            if (sum % 3 != 0) {
                result |= (1 << i);
            }
        }

        return result;
    }

    // faster:
    //    public int singleNumber(int[] nums) {
    //        int ones = 0, twos = 0;
    //
    //        for (int num : nums) {
    //            ones = (ones ^ num) & ~twos;
    //            twos = (twos ^ num) & ~ones;
    //        }
    //
    //        return ones;
    //    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given an integer array nums where every element appears three times except for one, which appears exactly once. Find
// the single element and return it.
//You must implement a solution with a linear runtime complexity and use only constant extra space.

//Example 1:
//Input: nums = [2,2,3,2]
//Output: 3

//Example 2:
//Input: nums = [0,1,0,1,0,1,99]
//Output: 99

//Constraints:
//1 <= nums.length <= 3 * 104
//-231 <= nums[i] <= 231 - 1
//Each element in nums appears exactly three times except for one element which appears once.
