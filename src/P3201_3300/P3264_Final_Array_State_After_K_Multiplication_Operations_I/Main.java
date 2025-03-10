package P3201_3300.P3264_Final_Array_State_After_K_Multiplication_Operations_I;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 1, 3, 5, 6};
        int k1 = 5;
        int multiplier1 = 2;
        System.out.println("Output: " + Arrays.toString(solution.getFinalState(nums1, k1, multiplier1))); // Output: [8, 4, 6, 5, 6]

        int[] nums2 = {1, 2};
        int k2 = 3;
        int multiplier2 = 4;
        System.out.println("Output: " + Arrays.toString(solution.getFinalState(nums2, k2, multiplier2))); // Output: [16, 8]
    }

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        // Perform k operations
        for (int i = 0; i < k; i++) {
            // Find the index of the minimum value in the array
            int minIndex = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            // Replace the minimum value with its multiplied value
            nums[minIndex] *= multiplier;
        }

        return nums;
    }
}

//Explanation of the Code:
//Find Minimum Value:
//-Loop through the array to find the index of the smallest number.
//-If multiple minimums exist, the first one is chosen due to the left-to-right iteration.
//Replace with Multiplied Value:
//-Multiply the minimum value by the given multiplier and update its position in the array.
//Repeat for k Operations:
//-Each time the operation is performed, the array is updated.


//You are given an integer array nums, an integer k, and an integer multiplier.
//You need to perform k operations on nums. In each operation:
//Find the minimum value x in nums. If there are multiple occurrences of the minimum value, select the
// one that appears first.
//Replace the selected minimum value x with x * multiplier.
//Return an integer array denoting the final state of nums after performing all k operations.
//
//Example 1:
//Input: nums = [2,1,3,5,6], k = 5, multiplier = 2
//Output: [8,4,6,5,6]

//Explanation:
//Operation	Result
//After operation 1	[2, 2, 3, 5, 6]
//After operation 2	[4, 2, 3, 5, 6]
//After operation 3	[4, 4, 3, 5, 6]
//After operation 4	[4, 4, 6, 5, 6]
//After operation 5	[8, 4, 6, 5, 6]

//Example 2:
//Input: nums = [1,2], k = 3, multiplier = 4
//Output: [16,8]
//
//Explanation:
//Operation	Result
//After operation 1	[4, 2]
//After operation 2	[4, 8]
//After operation 3	[16, 8]
//
//Constraints:
//1 <= nums.length <= 100
//1 <= nums[i] <= 100
//1 <= k <= 10
//1 <= multiplier <= 5