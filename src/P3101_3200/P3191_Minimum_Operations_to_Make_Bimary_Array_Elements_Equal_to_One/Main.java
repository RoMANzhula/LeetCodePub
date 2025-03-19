package P3101_3200.P3191_Minimum_Operations_to_Make_Bimary_Array_Elements_Equal_to_One;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {0, 1, 1, 1, 0, 0};
        System.out.println(solution.minOperations(nums1)); // Output: 3

        int[] nums2 = {0, 1, 1, 1};
        System.out.println(solution.minOperations(nums2)); // Output: -1
    }

    public int minOperations(int[] nums) {
        int len = nums.length;
        int operations = 0;

        for (int i = 0; i <= len - 3; i++) {
            if (nums[i] == 0) {
                // flip the three consecutive elements
                nums[i] ^= 1;
                nums[i + 1] ^= 1;
                nums[i + 2] ^= 1;

                operations++;
            }
        }

        // check if the last two elements are 1 (if not, it is impossible)
        if (nums[len - 1] == 0 || nums[len - 2] == 0) {
            return -1;
        }

        return operations;
    }

}

//Explanation of the Code
//We iterate through nums, checking for 0 at every index i.
//If nums[i] is 0, we flip it along with nums[i+1] and nums[i+2] using the XOR (^= 1) operation.
//We keep track of the number of operations performed.
//After iterating, we check if the last two elements are 1. If not, return -1.
//Time Complexity
//Since we only traverse the array once (O(n)) and modify elements in constant time, the solution
// runs in O(n) time complexity.

//You are given a binary array nums.
//You can do the following operation on the array any number of times (possibly zero):
//Choose any 3 consecutive elements from the array and flip all of them.
//Flipping an element means changing its value from 0 to 1, and from 1 to 0.
//Return the minimum number of operations required to make all elements in nums equal to 1. If it is
// impossible, return -1.

//Example 1:
//Input: nums = [0,1,1,1,0,0]
//Output: 3
//Explanation:
//We can do the following operations:
//Choose the elements at indices 0, 1 and 2. The resulting array is nums = [1,0,0,1,0,0].
//Choose the elements at indices 1, 2 and 3. The resulting array is nums = [1,1,1,0,0,0].
//Choose the elements at indices 3, 4 and 5. The resulting array is nums = [1,1,1,1,1,1].

//Example 2:
//Input: nums = [0,1,1,1]
//Output: -1
//Explanation:
//It is impossible to make all elements equal to 1.

//Constraints:
//3 <= nums.length <= 105
//0 <= nums[i] <= 1
