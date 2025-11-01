package P1_100.P81_Search_in_Rotated_Sorted_Array_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();


        int[] nums1 = {2,5,6,0,0,1,2};
        int[] nums2 = {2,5,6,0,0,1,2};
        System.out.println(solution.search(nums1, 0));  // true
        System.out.println(solution.search(nums2, 3));  // false
    }

    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // found target
            if (nums[mid] == target) {
                return true;
            }

            // handle duplicates
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            }
            // left side is sorted
            else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // right side is sorted
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }

}

//Complexity:
// time - O(log n)
// space - O(1)


//There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
//Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such
// that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For
// example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
//Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it
// is not in nums.
//You must decrease the overall operation steps as much as possible.

//Example 1:
//Input: nums = [2,5,6,0,0,1,2], target = 0
//Output: true

//Example 2:
//Input: nums = [2,5,6,0,0,1,2], target = 3
//Output: false

//Constraints:
//1 <= nums.length <= 5000
//-104 <= nums[i] <= 104
//nums is guaranteed to be rotated at some pivot.
//-104 <= target <= 104
