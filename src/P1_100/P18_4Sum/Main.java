package P1_100.P18_4Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] test1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        int[] test2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        int[] test3 = {1000000000, 1000000000, 1000000000, 1000000000};
        int target3 = -294967296;
        System.out.println(fourSum(test1, target1));
        System.out.println(fourSum(test2, target2));
        System.out.println(fourSum(test3, target3));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        if (target >= Math.pow(-10, 9) && target <= Math.pow(10, 9)) {
            for (int i = 0; i < nums.length - 3; i++) {
                if (nums[i] < 999999999) {
                    if (i > 0 && nums[i] == nums[i - 1]) {
                        continue; // Skip duplicates
                    }

                    for (int j = i + 1; j < nums.length - 2; j++) {
                        if (j > i + 1 && nums[j] == nums[j - 1]) {
                            continue; //skip duplicates
                        }

                        int left = j + 1;
                        int right = nums.length - 1;

                        while (left < right) {
                            int currentSum = nums[i] + nums[j] + nums[left] + nums[right];

                            if (currentSum == target) {
                                result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                                while (left < right && nums[left] == nums[left + 1]) {
                                    left++; //skip duplicates
                                }
                                while (left < right && nums[right] == nums[right - 1]) {
                                    right--; //skip duplicates
                                }

                                left++;
                                right--;
                            } else if (currentSum < target) {
                                left++;
                            } else {
                                right--;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }
}
//Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c],
// nums[d]] such that:
//0 <= a, b, c, d < n
//a, b, c, and d are distinct.
//nums[a] + nums[b] + nums[c] + nums[d] == target
//You may return the answer in any order.

//Example 1:
//Input: nums = [1,0,-1,0,-2,2], target = 0
//Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

//Example 2:
//Input: nums = [2,2,2,2,2], target = 8
//Output: [[2,2,2,2]]

//Constraints:
//1 <= nums.length <= 200
//-10 in 9 <= nums[i] <= 10 in 9
//-10 in 9 <= target <= 10 in 9
