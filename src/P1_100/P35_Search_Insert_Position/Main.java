package P1_100.P35_Search_Insert_Position;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1,3,5,6};
        int target1 = 5;
        System.out.println(solution.searchInsert(nums1, target1)); // 2

        int[] nums2 = {1,3,5,6};
        int target2 = 2;
        System.out.println(solution.searchInsert(nums2, target2)); // 1
    }

    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);

            if (nums[middle] == target) return middle;

            if (nums[middle] < target) left = middle + 1;
            else right = middle - 1;
        }

        return left;
    }

}

//Complexity:
// time - O(log n)
// space - O(1)


//Given a sorted array of distinct integers and a target value, return the index if the target is found. If not,
// return the index where it would be if it were inserted in order.
//You must write an algorithm with O(log n) runtime complexity.

//Example 1:
//Input: nums = [1,3,5,6], target = 5
//Output: 2

//Example 2:
//Input: nums = [1,3,5,6], target = 2
//Output: 1

//Example 3:
//Input: nums = [1,3,5,6], target = 7
//Output: 4

//Constraints:
//1 <= nums.length <= 104
//-104 <= nums[i] <= 104
//nums contains distinct values sorted in ascending order.
//-104 <= target <= 104
