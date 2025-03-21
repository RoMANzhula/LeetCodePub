package P1801_1900.P1887_Reduction_Operations_to_Make_the_Array_Elements_Equal;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {5, 1, 3};
        System.out.println(minOperations(nums1)); // Output: 3

        int[] nums2 = {1, 1, 1};
        System.out.println(minOperations(nums2)); // Output: 0

        int[] nums3 = {1, 1, 2, 2, 3};
        System.out.println(minOperations(nums3)); // Output: 4
    }

//    public static int minOperations(int[] nums) {
//        int operations = 0;
//
//        while (true) {
//            //find the largest value and its index
//            int maxIndex = 0;
//            for (int i = 1; i < nums.length; i++) {
//                if (nums[i] > nums[maxIndex]) {
//                    maxIndex = i;
//                }
//            }
//
//            int largest = nums[maxIndex];
//
//            //check if all elements are equal
//            boolean allEqual = Arrays.stream(nums).allMatch(num -> num == largest);
//            if (allEqual) {
//                break;
//            }
//
//            //find the next largest value
//            int nextLargest = Integer.MIN_VALUE;
//            for (int i = 0; i < nums.length; i++) {
//                if (nums[i] < largest && nums[i] > nextLargest) {
//                    nextLargest = nums[i];
//                }
//            }
//
//            //reduce nums[maxIndex] to nextLargest
//            nums[maxIndex] = nextLargest;
//
//            //increment the operations count
//            operations++;
//        }
//
//        return operations; //bingo
//    }

    public static int minOperations(int[] nums) {
        int operations = 0;

        //sort the array in descending order
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = n - 1; i > 0; i--) {
            //if the current element is not equal to the previous element, it means we need to perform an
            //operation to make them equal
            if (nums[i] != nums[i - 1]) {
                //calculate the number of operations needed to make all elements equal
                operations += (n - i);
            }
        }

        return operations; //bingo
    }

}

//Given an integer array nums, your goal is to make all elements in nums equal. To complete one operation,
// follow these steps:
//Find the largest value in nums. Let its index be i (0-indexed) and its value be largest. If there are multiple
// elements with the largest value, pick the smallest i.
//Find the next largest value in nums strictly smaller than largest. Let its value be nextLargest.
//Reduce nums[i] to nextLargest.
//Return the number of operations to make all elements in nums equal.

//Example 1:
//Input: nums = [5,1,3]
//Output: 3
//Explanation: It takes 3 operations to make all elements in nums equal:
//1. largest = 5 at index 0. nextLargest = 3. Reduce nums[0] to 3. nums = [3,1,3].
//2. largest = 3 at index 0. nextLargest = 1. Reduce nums[0] to 1. nums = [1,1,3].
//3. largest = 3 at index 2. nextLargest = 1. Reduce nums[2] to 1. nums = [1,1,1].

//Example 2:
//Input: nums = [1,1,1]
//Output: 0
//Explanation: All elements in nums are already equal.

//Example 3:
//Input: nums = [1,1,2,2,3]
//Output: 4
//Explanation: It takes 4 operations to make all elements in nums equal:
//1. largest = 3 at index 4. nextLargest = 2. Reduce nums[4] to 2. nums = [1,1,2,2,2].
//2. largest = 2 at index 2. nextLargest = 1. Reduce nums[2] to 1. nums = [1,1,1,2,2].
//3. largest = 2 at index 3. nextLargest = 1. Reduce nums[3] to 1. nums = [1,1,1,1,2].
//4. largest = 2 at index 4. nextLargest = 1. Reduce nums[4] to 1. nums = [1,1,1,1,1].

//Constraints:
//1 <= nums.length <= 5 * 104
//1 <= nums[i] <= 5 * 104
