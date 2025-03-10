package P1_100.P4_Median_of_Two_Sorted_Arrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] sum1 = {1, 3}; //output 2.0
        int[] sum2 = {2};

        System.out.println(solution.findMedianSortedArrays(sum1, sum2));

        System.out.println(5 / 2);

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        int totalLength = nums1.length + nums2.length;
        int[] united = new int[totalLength];

        for (int i = 0; i < nums1.length; i++) {
            united[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            united[nums1.length + i] = nums2[i];
        }

        Arrays.sort(united);

        int center = united.length / 2;

        if (united.length % 2 != 0) {
            result = united[center];
        } else {
            result = (united[center - 1] + united[center]) / 2.0;
        }

        return result;
    }
}


//Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//The overall run time complexity should be O(log (m+n)).

//Example 1:
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.

//Example 2:
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

//Constraints:
//nums1.length == m
//nums2.length == n
//0 <= m <= 1000
//0 <= n <= 1000
//1 <= m + n <= 2000
//-106 <= nums1[i], nums2[i] <= 106