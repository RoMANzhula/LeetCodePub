package P201_300.P229_Majority_Element_II;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        int[] nums1 = {3,2,3};
        int[] nums2 = {2};
        int[] nums3 = {1,2};
        int[] nums4 = {2,2};
        int[] nums5 = {1,2,3};
        int[] nums6 = {4,3,2,3};

        System.out.println(solution.majorityElement(nums1));
        System.out.println(solution.majorityElement(nums2));
        System.out.println(solution.majorityElement(nums3));
        System.out.println(solution.majorityElement(nums4));
        System.out.println(solution.majorityElement(nums5));
        System.out.println(solution.majorityElement(nums6));
    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            result.add(candidate2);
        }

        return result;
    }

}

//Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

//Example 1:
//Input: nums = [3,2,3]
//Output: [3]

//Example 2:
//Input: nums = [1]
//Output: [1]

//Example 3:
//Input: nums = [1,2]
//Output: [1,2]

//Constraints:
//1 <= nums.length <= 5 * 104
//-109 <= nums[i] <= 109
