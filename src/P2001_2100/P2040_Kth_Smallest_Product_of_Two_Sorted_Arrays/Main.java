package P2001_2100.P2040_Kth_Smallest_Product_of_Two_Sorted_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.kthSmallestProduct(new int[]{2, 5}, new int[]{3, 4}, 2)); // Output: 8
        System.out.println(solution.kthSmallestProduct(new int[]{-4, -2, 0, 3}, new int[]{2, 4}, 6)); // Output: 0
        System.out.println(solution.kthSmallestProduct(new int[]{-2, -1, 0, 1, 2}, new int[]{-3, -1, 2, 4, 5}, 3)); // Output: -6
    }

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        List<Long> A1 = new ArrayList<>();
        List<Long> A2 = new ArrayList<>();
        List<Long> B1 = new ArrayList<>();
        List<Long> B2 = new ArrayList<>();

        separate(nums1, A1, A2);
        separate(nums2, B1, B2);

        long negCount = (long) A1.size() * B2.size() + (long) A2.size() * B1.size();
        int sign = 1;

        if (k > negCount) {
            k -= negCount;  // find positive product
        } else {
            k = negCount - k + 1;  // find negative product (in absolute)
            sign = -1;
            List<Long> temp = B1;
            B1 = B2;
            B2 = temp;
        }

        long l = 0, r = (long) 1e10;

        while (l < r) {
            long m = (l + r) / 2;
            if (numProductNoGreaterThan(A1, B1, m) + numProductNoGreaterThan(A2, B2, m) >= k) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return sign * l;
    }

    private void separate(int[] arr, List<Long> neg, List<Long> pos) {
        for (int a : arr) {
            if (a < 0)
                neg.add(-(long)a);  // convert to positive for easier math
            else
                pos.add((long)a);
        }

        Collections.reverse(neg);  // important: sort ascending
    }

    private long numProductNoGreaterThan(List<Long> A, List<Long> B, long m) {
        long count = 0;
        int j = B.size() - 1;

        for (long a : A) {
            while (j >= 0 && a * B.get(j) > m) {
                j--;
            }
            count += (j + 1L);
        }
        return count;
    }

}

//Complexity:
// time - O((n + m) × log(1e10))    (log(1e10) steps - ~60 binary search steps)
// space - O(n + m)


//Given two sorted 0-indexed integer arrays nums1 and nums2 as well as an integer k, return the
// kth (1-based) smallest product of nums1[i] * nums2[j] where 0 <= i < nums1.length and 0 <= j < nums2.length.

//Example 1:
//Input: nums1 = [2,5], nums2 = [3,4], k = 2
//Output: 8
//Explanation: The 2 smallest products are:
//- nums1[0] * nums2[0] = 2 * 3 = 6
//- nums1[0] * nums2[1] = 2 * 4 = 8
//The 2nd smallest product is 8.

//Example 2:
//Input: nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
//Output: 0
//Explanation: The 6 smallest products are:
//- nums1[0] * nums2[1] = (-4) * 4 = -16
//- nums1[0] * nums2[0] = (-4) * 2 = -8
//- nums1[1] * nums2[1] = (-2) * 4 = -8
//- nums1[1] * nums2[0] = (-2) * 2 = -4
//- nums1[2] * nums2[0] = 0 * 2 = 0
//- nums1[2] * nums2[1] = 0 * 4 = 0
//The 6th smallest product is 0.

//Example 3:
//Input: nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
//Output: -6
//Explanation: The 3 smallest products are:
//- nums1[0] * nums2[4] = (-2) * 5 = -10
//- nums1[0] * nums2[3] = (-2) * 4 = -8
//- nums1[4] * nums2[0] = 2 * (-3) = -6
//The 3rd smallest product is -6.

//Constraints:
//1 <= nums1.length, nums2.length <= 5 * 104
//-105 <= nums1[i], nums2[j] <= 105
//1 <= k <= nums1.length * nums2.length
//nums1 and nums2 are sorted.
