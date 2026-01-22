package P3501_3600.P3507_Minimum_Pair_Removal_to_Sort_Array_I;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {5, 2, 3, 1};
        int[] nums2 = {1, 2, 2};

        System.out.println(solution.minimumPairRemoval(nums1)); // Output: 2
        System.out.println(solution.minimumPairRemoval(nums2)); // output: 0
    }

    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        int operations = 0;

        while (!isNonDecreasing(list)) {
            int minSum = Integer.MAX_VALUE;
            int index = 0;

            //find leftmost adjacent pair with minimum sum
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);

                if (sum < minSum) {
                    minSum = sum;
                    index = i;
                }
            }

            // replace the pair with their sum
            list.remove(index + 1);
            list.set(index, minSum);

            operations++;
        }

        return operations;
    }

    private static boolean isNonDecreasing(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

}

//Complexity:
// time - O(n^2)
// space - O(n)


//Given an array nums, you can perform the following operation any number of times:
//Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
//Replace the pair with their sum.
//Return the minimum number of operations needed to make the array non-decreasing.
//An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).

//Example 1:
//Input: nums = [5,2,3,1]
//Output: 2
//Explanation:
//The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
//The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
//The array nums became non-decreasing in two operations.

//Example 2:
//Input: nums = [1,2,2]
//Output: 0
//Explanation:
//The array nums is already sorted.

//Constraints:
//1 <= nums.length <= 50
//-1000 <= nums[i] <= 1000
