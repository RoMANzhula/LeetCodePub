package P3901_4000.P3942_Minimum_Operations_to_Sort_a_Permutation;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {0, 2, 1};
        int[] nums2 = {1, 0, 2};
        int[] nums3 = {2, 0, 1, 3};

        System.out.println(solution.minOperations(nums1));
        System.out.println(solution.minOperations(nums2));
        System.out.println(solution.minOperations(nums3));
    }

    public int minOperations(int[] nums) {
        int n = nums.length;

        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (check(nums, zero, 1)) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n - zero + 2);
        }

        if (check(nums, zero, -1)) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private boolean check(int[] nums, int zero, int step) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int prev = (zero + (i - 1) * step + n) % n;
            int curr = (zero + i * step + n) % n;

            if (nums[prev] > nums[curr]) {
                return false;
            }
        }

        return true;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given an integer array nums of length n, where nums is a permutation of the integers from 0 to n - 1.
//You may perform only the following operations:
//Reverse the entire array.
//Rotate Left by One: Move the first element to the end of the array, and rest elements to left by one position.
//Return an integer denoting the minimum number of operations required to sort the array in increasing order. If it
// is not possible to sort the array using only the given operations, return -1.

//Example 1:
//Input: nums = [0,2,1]
//Output: 2
//Explanation:
//Rotate Left by one: [2, 1, 0]
//Reverse the array: [0, 1, 2]
//The array becomes sorted in 2 operations, which is minimal

//Example 2:
//Input: nums = [1,0,2]
//Output: 2
//Explanation:
//Reverse the array: [2, 0, 1]
//Rotate Left by one: [0, 1, 2]
//The array becomes sorted in 2 operations, which is minimal.

//Example 3:
//Input: nums = [2,0,1,3]
//Output: -1
//Explanation:
//It is impossible to reach [2, 0, 1, 3]. Thus, the answer is -1.

//Constraints:
//1 <= n == nums.length <= 105
//0 <= nums[i] <= n - 1
//nums is a permutation of integers from 0 to n - 1.
