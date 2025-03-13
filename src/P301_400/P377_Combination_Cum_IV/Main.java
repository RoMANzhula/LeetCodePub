package P301_400.P377_Combination_Cum_IV;

public class Main {

    public int combinationSum4(int[] nums, int target) {
        int[] numbersOfCombinationsForAllNumber = new int[target + 1];
        numbersOfCombinationsForAllNumber[0] = 1;  //there is one way to make a sum of 0 (by not selecting any number)

        for (int i = 1; i <= target; i++) { //loop from 1 to target
            for (int num : nums) { //for-each element from nums-array
                if (i - num >= 0) { //i >= num
                    numbersOfCombinationsForAllNumber[i] += numbersOfCombinationsForAllNumber[i - num]; //add combinations
                    //from the numbers that were considered
                }
            }
        }

        return numbersOfCombinationsForAllNumber[target]; //combinations which were calculate
    }

    public static void main(String[] args) {
        Main solution = new Main();
        int[] nums1 = {1, 2, 3};
        int target1 = 4;
        System.out.println(solution.combinationSum4(nums1, target1));  // Output: 7

        int[] nums2 = {9};
        int target2 = 3;
        System.out.println(solution.combinationSum4(nums2, target2));  // Output: 0
    }
}

//Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
//The test cases are generated so that the answer can fit in a 32-bit integer.

//Example 1:
//Input: nums = [1,2,3], target = 4
//Output: 7
//Explanation:
//The possible combination ways are:
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//Note that different sequences are counted as different combinations.

//Example 2:
//Input: nums = [9], target = 3
//Output: 0

//Constraints:
//1 <= nums.length <= 200
//1 <= nums[i] <= 1000
//All the elements of nums are unique.
//1 <= target <= 1000
