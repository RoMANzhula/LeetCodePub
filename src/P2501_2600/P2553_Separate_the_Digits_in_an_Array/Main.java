package P2501_2600.P2553_Separate_the_Digits_in_an_Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {13,25,83,77};
        int[] nums2 = {7,1,3,9};

        System.out.println(Arrays.toString(solution.separateDigits(nums1))); // 1,3,2,5,8,3,7,7
        System.out.println(Arrays.toString(solution.separateDigits(nums2))); // 7,1,3,9
    }

    public int[] separateDigits(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            for (char c : String.valueOf(num).toCharArray()) {
                result.add(c - '0');
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

}

//Complexity:
// time and space (n * numOfDigits)


//Given an array of positive integers nums, return an array answer that consists of the digits of each integer
// in nums after separating them in the same order they appear in nums.
//To separate the digits of an integer is to get all the digits it has in the same order.
//For example, for the integer 10921, the separation of its digits is [1,0,9,2,1].

//Example 1:
//Input: nums = [13,25,83,77]
//Output: [1,3,2,5,8,3,7,7]
//Explanation:
//- The separation of 13 is [1,3].
//- The separation of 25 is [2,5].
//- The separation of 83 is [8,3].
//- The separation of 77 is [7,7].
//answer = [1,3,2,5,8,3,7,7]. Note that answer contains the separations in the same order.

//Example 2:
//Input: nums = [7,1,3,9]
//Output: [7,1,3,9]
//Explanation: The separation of each integer in nums is itself.
//answer = [7,1,3,9].

//Constraints:
//1 <= nums.length <= 1000
//1 <= nums[i] <= 105
