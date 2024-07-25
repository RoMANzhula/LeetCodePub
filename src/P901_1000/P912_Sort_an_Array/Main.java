package P901_1000.P912_Sort_an_Array;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {5, 2, 3, 1};
        int[] sortedNums1 = solution.sortArray(nums1);
        System.out.println(Arrays.toString(sortedNums1)); // Output: [1, 2, 3, 5]

        int[] nums2 = {5, 1, 1, 2, 0, 0};
        int[] sortedNums2 = solution.sortArray(nums2);
        System.out.println(Arrays.toString(sortedNums2)); // Output: [0, 0, 1, 1, 2, 5]
    }

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= right) {
            temp[k++] = nums[j++];
        }

        System.arraycopy(temp, 0, nums, left, temp.length);
    }

}

//Explanation:
//sortArray Method: This is the main method that checks for null or single-element arrays and calls the
// mergeSort method to sort the array.
//mergeSort Method: This method recursively divides the array into two halves until the base case of a
// single element or an empty array is reached.
//merge Method: This method merges two sorted halves into a single sorted array. It uses a temporary array to
// store the merged elements and then copies them back to the original array.
//Main Method: This is a simple test to check the functionality of the sortArray method with example inputs.
//The space complexity of this implementation is O(n) due to the use of the temporary array in the merge method. This
// approach ensures the array is sorted with a time complexity of O(n log n).


//Given an array of integers nums, sort the array in ascending order and return it.
//You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the
// smallest space complexity possible.
//
//Example 1:
//Input: nums = [5,2,3,1]
//Output: [1,2,3,5]
//Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3),
// while the positions of other numbers are changed (for example, 1 and 5).

//Example 2:
//Input: nums = [5,1,1,2,0,0]
//Output: [0,0,1,1,2,5]
//Explanation: Note that the values of nums are not necessairly unique.
//
//Constraints:
//1 <= nums.length <= 5 * 104
//-5 * 104 <= nums[i] <= 5 * 104