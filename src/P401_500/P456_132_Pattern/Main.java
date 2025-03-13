package P401_500.P456_132_Pattern;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {3, 1, 4, 2};
        int[] nums3 = {-1, 3, 2, 0};
        int[] nums4 = {3, 5, 0, 3, 4};

        System.out.println(solution.find132pattern(nums1)); //false
        System.out.println(solution.find132pattern(nums2)); //true
        System.out.println(solution.find132pattern(nums3)); //true
        System.out.println(solution.find132pattern(nums4)); //true
    }

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int[] min_i = new int[n];
        min_i[0] = nums[0];

        //calculate the minimum value at each position
        for (int i = 1; i < n; i++) {
            min_i[i] = Math.min(min_i[i - 1], nums[i]);
        }

        for (int j = n - 1; j >= 0; j--) {
            if (nums[j] > min_i[j]) {
                while (!stack.isEmpty() && stack.peek() <= min_i[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
            }
        }

        return false;
    }
}


//Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and
// nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
//Return true if there is a 132 pattern in nums, otherwise, return false.

//Example 1:
//Input: nums = [1,2,3,4]
//Output: false
//Explanation: There is no 132 pattern in the sequence.

//Example 2:
//Input: nums = [3,1,4,2]
//Output: true
//Explanation: There is a 132 pattern in the sequence: [1, 4, 2].

//Example 3:
//Input: nums = [-1,3,2,0]
//Output: true
//Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

//Constraints:
//n == nums.length
//1 <= n <= 2 * 105
//-109 <= nums[i] <= 109
