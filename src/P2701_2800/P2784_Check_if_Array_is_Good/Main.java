package P2701_2800.P2784_Check_if_Array_is_Good;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 1, 3};
        int[] nums2 = {1, 3, 3, 2};
        int[] nums3 = {1, 1};
        int[] nums4 = {3, 4, 4, 1, 2, 1};

        System.out.println(solution.isGood(nums1)); // false
        System.out.println(solution.isGood(nums2)); // true
        System.out.println(solution.isGood(nums3)); // true
        System.out.println(solution.isGood(nums4)); // false
    }

    public boolean isGood(int[] nums) {
        int max = 0;

        // mind maximum element
        for (int num : nums) {
            max = Math.max(max, num);
        }

        // length must be n + 1
        if (nums.length != max + 1) {
            return false;
        }

        int[] freq = new int[max + 1];

        // count frequency
        for (int num : nums) {
            if (num > max) return false;
            freq[num]++;
        }

        // check 1 to n-1 appear once
        for (int i = 1; i < max; i++) {
            if (freq[i] != 1) {
                return false;
            }
        }

        // check n appears twice
        return freq[max] == 2;
    }

}

//Complexity:
// time and space - O(n)


//You are given an integer array nums. We consider an array good if it is a permutation of an array base[n].
//base[n] = [1, 2, ..., n - 1, n, n] (in other words, it is an array of length n + 1 which contains 1 to n - 1 exactly
// once, plus two occurrences of n). For example, base[1] = [1, 1] and base[3] = [1, 2, 3, 3].
//Return true if the given array is good, otherwise return false.
//Note: A permutation of integers represents an arrangement of these numbers.

//Example 1:
//Input: nums = [2, 1, 3]
//Output: false
//Explanation: Since the maximum element of the array is 3, the only candidate n for which this array could be a
// permutation of base[n], is n = 3. However, base[3] has four elements but array nums has three. Therefore, it can
// not be a permutation of base[3] = [1, 2, 3, 3]. So the answer is false.

//Example 2:
//Input: nums = [1, 3, 3, 2]
//Output: true
//Explanation: Since the maximum element of the array is 3, the only candidate n for which this array could be a
// permutation of base[n], is n = 3. It can be seen that nums is a permutation of base[3] = [1, 2, 3, 3] (by swapping
// the second and fourth elements in nums, we reach base[3]). Therefore, the answer is true.

//Example 3:
//Input: nums = [1, 1]
//Output: true
//Explanation: Since the maximum element of the array is 1, the only candidate n for which this array could be a
// permutation of base[n], is n = 1. It can be seen that nums is a permutation of base[1] = [1, 1]. Therefore, the
// answer is true.

//Example 4:
//Input: nums = [3, 4, 4, 1, 2, 1]
//Output: false
//Explanation: Since the maximum element of the array is 4, the only candidate n for which this array could be a
// permutation of base[n], is n = 4. However, base[4] has five elements but array nums has six. Therefore, it can
// not be a permutation of base[4] = [1, 2, 3, 4, 4]. So the answer is false.

//Constraints:
//1 <= nums.length <= 100
//1 <= num[i] <= 200
