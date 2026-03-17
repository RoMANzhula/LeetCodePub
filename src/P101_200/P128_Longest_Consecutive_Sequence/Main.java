package P101_200.P128_Longest_Consecutive_Sequence;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {100,4,200,1,3,2};
        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
        int[] nums3 = {1,0,1,2};

        System.out.println(solution.longestConsecutive(nums1)); // 4
        System.out.println(solution.longestConsecutive(nums2)); // 9
        System.out.println(solution.longestConsecutive(nums3)); // 3
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();

        // put all numbers
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {

            // if it is the start of a sequence
            if (!set.contains(num - 1)) {

                int currentNum = num;
                int currentLength = 1;

                // expand the sequence
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }

                longest = Math.max(longest, currentLength);
            }
        }

        return longest;
    }

}

//Complexity:
// time and space - O(n)


//Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//You must write an algorithm that runs in O(n) time.

//Example 1:
//Input: nums = [100,4,200,1,3,2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

//Example 2:
//Input: nums = [0,3,7,2,5,8,4,6,0,1]
//Output: 9

//Example 3:
//Input: nums = [1,0,1,2]
//Output: 3

//Constraints:
//0 <= nums.length <= 105
//-109 <= nums[i] <= 109
