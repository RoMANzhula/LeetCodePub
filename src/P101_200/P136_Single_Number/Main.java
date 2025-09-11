package P101_200.P136_Single_Number;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2,2,1};
        System.out.println(solution.singleNumber(nums1)); // output: 1

        int[] nums2 = {4,1,2,1,2};
        System.out.println(solution.singleNumber(nums2)); // output: 4

        int[] nums3 = {1};
        System.out.println(solution.singleNumber(nums3)); // output: 1

    }

    public int singleNumber(int[] nums) {
        int twice = 0;

        if (nums.length == 1) return nums[0];

        for (int i = 0; i < nums.length; i++) {
            twice ^= nums[i];
        }

        return twice;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
//You must implement a solution with a linear runtime complexity and use only constant extra space.

//Example 1:
//Input: nums = [2,2,1]
//Output: 1

//Example 2:
//Input: nums = [4,1,2,1,2]
//Output: 4

//Example 3:
//Input: nums = [1]
//Output: 1

//Constraints:
//1 <= nums.length <= 3 * 104
//-3 * 104 <= nums[i] <= 3 * 104
//Each element in the array appears twice except for one element which appears only once.
