package P1_100.P34_Find_First_and_Last_Position_of_Element_In_Sorted_Array;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {5,7,7,8,8,8,10};
        int target1 = 8;

        System.out.println(Arrays.toString(searchRange(nums1, target1)));
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2]; //create array for result

        ArrayList<Integer> list = new ArrayList<>(); //list for first and last element to result

        for (int i = 0; i < nums.length; i++) { //iterate all array
            if (nums[i] == target) { //looking duplicates
                list.add(i); //added element positions
            }
        }

        if (list.isEmpty()) {
            result[0] = -1;
            result[1] = -1;
        } else {
            //get first element
            result[0] = list.get(0);
            //get last element
            result[1] = list.get(list.size() - 1);
        }

        return result; //congratulation
    }
}

//Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a
// given target value.
//If target is not found in the array, return [-1, -1].
//You must write an algorithm with O(log n) runtime complexity.

//Example 1:
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4]

//Example 2:
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1]

//Example 3:
//Input: nums = [], target = 0
//Output: [-1,-1]

//Constraints:
//0 <= nums.length <= 105
//-109 <= nums[i] <= 109
//nums is a non-decreasing array.
//-109 <= target <= 109
