package P2001_2100.P2009_Minimum_Number_of_Operations_to_Make_Array_Continuous;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {4,2,5,3}; //0
        int[] nums2 = {1,2,3,5,6}; //1
        int[] nums3 = {1, 10, 100, 1000}; //3
        int[] nums4 = {8,5,9,9,8,4}; //2
        int[] nums5 = {41,33,29,33,35,26,47,24,18,28}; //5 {18,19,20,21,22,23,24,25,26,27}

        System.out.println(solution.minOperations(nums1));
        System.out.println(solution.minOperations(nums2));
        System.out.println(solution.minOperations(nums3));
        System.out.println(solution.minOperations(nums4));
        System.out.println(solution.minOperations(nums5));
    }

    public int minOperations(int[] nums) {
        int result = 0;
        int[] forCompare = new int[nums.length];

        int min = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        for (int i = 0; i < forCompare.length; i++) {
            forCompare[i] = min + i;
        }

        for (int num : forCompare) {
            boolean found = false;
            for (int num2 : nums) {
                if (num == num2) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result++;
            }
        }

        return result;

    }

}

//You are given an integer array nums. In one operation, you can replace any element in nums with any integer.
//nums is considered continuous if both of the following conditions are fulfilled:
//All elements in nums are unique.
//The difference between the maximum element and the minimum element in nums equals nums.length - 1.
//For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.
//Return the minimum number of operations to make nums continuous.

//Example 1:
//Input: nums = [4,2,5,3]
//Output: 0
//Explanation: nums is already continuous.

//Example 2:
//Input: nums = [1,2,3,5,6]
//Output: 1
//Explanation: One possible solution is to change the last element to 4.
//The resulting array is [1,2,3,5,4], which is continuous.

//Example 3:
//Input: nums = [1,10,100,1000]
//Output: 3
//Explanation: One possible solution is to:
//- Change the second element to 2.
//- Change the third element to 3.
//- Change the fourth element to 4.
//The resulting array is [1,2,3,4], which is continuous.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
