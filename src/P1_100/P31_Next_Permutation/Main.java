package P1_100.P31_Next_Permutation;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums = {1, 2, 3};
        solution.nextPermutation(nums);
        System.out.println("Next permutation: " + Arrays.toString(nums)); // Output: [1, 3, 2]

        int[] nums2 = {3, 2, 1};
        solution.nextPermutation(nums2);
        System.out.println("Next permutation: " + Arrays.toString(nums2)); // Output: [1, 2, 3]

        int[] nums3 = {1, 1, 5};
        solution.nextPermutation(nums3);
        System.out.println("Next permutation: " + Arrays.toString(nums3)); // Output: [1, 5, 1]
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;

        // find the first index from the right where nums[i] < nums[i + 1]
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // if such index is found, find the element just larger than nums[i] to swap with
        if (i >= 0) {
            int j = nums.length - 1;

            while (nums[j] <= nums[i]) {
                j--;
            }

            swap(nums, i, j);
        }

        // reverse the subarray from i + 1 to the end
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

}

//Explanation:
//Find the pivot: Traverse from right to left to find the first i such that nums[i] < nums[i + 1].
//Find the successor: If such an index exists, find the smallest number on the right of i that's larger
// than nums[i], and swap them.
//Reverse the tail: Finally, reverse the subarray to the right of index i to get the next smallest
// lexicographical order.
//Complexity:
// timr - O(n)
// space - O(1)


//A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
//For example, for arr = [1,2,3], the following are all the permutations of
// arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
//The next permutation of an array of integers is the next lexicographically greater permutation of its
// integer. More formally, if all the permutations of the array are sorted in one container according to their
// lexicographical order, then the next permutation of that array is the permutation that follows it in the
// sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible
// order (i.e., sorted in ascending order).
//For example, the next permutation of arr = [1,2,3] is [1,3,2].
//Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
//While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger
// rearrangement.
//Given an array of integers nums, find the next permutation of nums.
//The replacement must be in place and use only constant extra memory.

//Example 1:
//Input: nums = [1,2,3]
//Output: [1,3,2]

//Example 2:
//Input: nums = [3,2,1]
//Output: [1,2,3]

//Example 3:
//Input: nums = [1,1,5]
//Output: [1,5,1]

//Constraints:
//1 <= nums.length <= 100
//0 <= nums[i] <= 100
