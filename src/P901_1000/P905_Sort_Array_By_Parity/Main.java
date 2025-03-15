package P901_1000.P905_Sort_Array_By_Parity;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        int[] nums1 = {3,1,2,4};
        System.out.println(solution.sortArrayByParity(nums1));
    }

    public String sortArrayByParity(int[] nums) {
        int[] result = new int[nums.length];
        int evenIdx = 0; //first position for even
        int oddIdx = nums.length - 1; //first position for odd

        for (int num : nums) {
            if (num % 2 == 0) {
                result[evenIdx] = num;
                evenIdx++; //next position for even
            } else {
                result[oddIdx] = num;
                oddIdx--; //previous position for odd
            }
        }

        return Arrays.toString(result); //bingo
    }

}

//Given an integer array nums, move all the even integers at the beginning of the array followed by all
// the odd integers.
//Return any array that satisfies this condition.

//Example 1:
//Input: nums = [3,1,2,4]
//Output: [2,4,3,1]
//Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

//Example 2:
//Input: nums = [0]
//Output: [0]

//Constraints:
//1 <= nums.length <= 5000
//0 <= nums[i] <= 5000
