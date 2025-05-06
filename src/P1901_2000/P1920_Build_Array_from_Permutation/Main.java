package P1901_2000.P1920_Build_Array_from_Permutation;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {0, 2, 1, 5, 3, 4};
        int[] result1 = solution.buildArray(nums1);
        printArray(result1); // Output: [0, 1, 2, 4, 5, 3]

        int[] nums2 = {5, 0, 1, 2, 3, 4};
        int[] result2 = solution.buildArray(nums2);
        printArray(result2); // Output: [4, 5, 0, 1, 2, 3]
    }

    private static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }

        System.out.println();
    }

    public int[] buildArray(int[] nums) {
        int len = nums.length;

        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = nums[nums[i]];
        }

        return result;
    }

}

//Explanation
//Loop through each index i
//Set ans[i] = nums[nums[i]]
//That's it! Since the input is guaranteed to be a valid permutation, we don't need to worry about invalid indices.
//Complexity:
// time and space: O(n)


//Given a zero-based permutation nums (0-indexed), build an array ans of the same length where
// ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
//A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).

//Example 1:
//Input: nums = [0,2,1,5,3,4]
//Output: [0,1,2,4,5,3]
//Explanation: The array ans is built as follows:
//ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
//    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
//    = [0,1,2,4,5,3]

//Example 2:
//Input: nums = [5,0,1,2,3,4]
//Output: [4,5,0,1,2,3]
//Explanation: The array ans is built as follows:
//ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
//    = [nums[5], nums[0], nums[1], nums[2], nums[3], nums[4]]
//    = [4,5,0,1,2,3]

//Constraints:
//1 <= nums.length <= 1000
//0 <= nums[i] < nums.length
//The elements in nums are distinct.
