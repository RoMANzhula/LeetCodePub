package P1_100.P88_Merge_Sorted_Array;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1a = {1,2,3,0,0,0};
        int m1 = 3;
        int[] nums2a = {2,5,6};
        int n1 = 3;
        solution.merge(nums1a, m1, nums2a, n1);
        System.out.println("Example 1 Output: " + Arrays.toString(nums1a)); // Output: [1, 2, 2, 3, 5, 6]

        int[] nums1b = {1};
        int m2 = 1;
        int[] nums2b = {};
        int n2 = 0;
        solution.merge(nums1b, m2, nums2b, n2);
        System.out.println("Example 2 Output: " + Arrays.toString(nums1b)); // Output: [1]

        int[] nums1c = {0};
        int m3 = 0;
        int[] nums2c = {1};
        int n3 = 1;
        solution.merge(nums1c, m3, nums2c, n3);
        System.out.println("Example 3 Output: " + Arrays.toString(nums1c)); // Output: [1]
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1; // last element in nums1
        int p2 = n - 1; // last element in nums2
        int p = m + n - 1; // last position in nums1

        // merge from the back to the front
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }

        // if there are leftover elements in nums2, copy them to nums1
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }

}

//Explanation:
//Start from the back: We compare nums1[m-1] and nums2[n-1] and place the larger one at nums1[m+n-1].
//Move backwards: Continue the process until all elements in nums2 are merged.
//Handle leftovers in nums2: If nums1 is exhausted but nums2 still has elements, copy them.
//Time & Space Complexity:
//Time Complexity: bO(m+n) (Each element is processed once)
//Space Complexity: O(1) (In-place merging, no extra space used)


//You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
// representing the number of elements in nums1 and nums2 respectively.
//Merge nums1 and nums2 into a single array sorted in non-decreasing order.
//The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To
// accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be
// merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
//
//Example 1:
//Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//Output: [1,2,2,3,5,6]
//Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
//The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.

//Example 2:
//Input: nums1 = [1], m = 1, nums2 = [], n = 0
//Output: [1]
//Explanation: The arrays we are merging are [1] and [].
//The result of the merge is [1].

//Example 3:
//Input: nums1 = [0], m = 0, nums2 = [1], n = 1
//Output: [1]
//Explanation: The arrays we are merging are [] and [1].
//The result of the merge is [1].
//Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge
// result can fit in nums1.
//
//Constraints:
//nums1.length == m + n
//nums2.length == n
//0 <= m, n <= 200
//1 <= m + n <= 200
//-109 <= nums1[i], nums2[j] <= 109
