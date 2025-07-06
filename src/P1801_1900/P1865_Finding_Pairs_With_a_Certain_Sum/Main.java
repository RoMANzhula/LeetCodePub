package P1801_1900.P1865_Finding_Pairs_With_a_Certain_Sum;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Main obj = new Main(
                new int[]{1, 1, 2, 2, 2, 3},
                new int[]{1, 4, 5, 2, 5, 4}
        );
        System.out.println(obj.count(7)); // Output: 8
        obj.add(3, 2);
        System.out.println(obj.count(8)); // Output: 2
        System.out.println(obj.count(4)); // Output: 1
        obj.add(0, 1);
        obj.add(1, 1);
        System.out.println(obj.count(7)); // Output: 11
    }

    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> freqMap; // frequency map for nums2 values

    public Main(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.freqMap = new HashMap<>();

        // populate frequency map for nums2
        for (int num : nums2) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
    }

    public void add(int index, int val) {
        // remove old value count
        int oldVal = nums2[index];
        freqMap.put(oldVal, freqMap.get(oldVal) - 1);
        if (freqMap.get(oldVal) == 0) {
            freqMap.remove(oldVal);
        }

        //update value in nums2
        nums2[index] += val;

        // add new value count
        int newVal = nums2[index];
        freqMap.put(newVal, freqMap.getOrDefault(newVal, 0) + 1);
    }

    public int count(int tot) {
        int count = 0;

        for (int n1 : nums1) {
            int complement = tot - n1;
            count += freqMap.getOrDefault(complement, 0);
        }

        return count;
    }

}

//Complexity:
// n1 - numns1.length (max 1_000)
// n2 - nums2.length (10^5)
// time - O(n1)
// space - O(n1 + n2)


//You are given two integer arrays nums1 and nums2. You are tasked to implement a data structure that supports
// queries of two types:
//Add a positive integer to an element of a given index in the array nums2.
//Count the number of pairs (i, j) such that nums1[i] + nums2[j] equals a given value (0 <= i < nums1.length and
// 0 <= j < nums2.length).
//Implement the FindSumPairs class:
//FindSumPairs(int[] nums1, int[] nums2) Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
//void add(int index, int val) Adds val to nums2[index], i.e., apply nums2[index] += val.
//int count(int tot) Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.

//Example 1:
//Input
//["FindSumPairs", "count", "add", "count", "count", "add", "add", "count"]
//[[[1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]], [7], [3, 2], [8], [4], [0, 1], [1, 1], [7]]
//Output
//[null, 8, null, 2, 1, null, null, 11]
//Explanation
//FindSumPairs findSumPairs = new FindSumPairs([1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]);
//findSumPairs.count(7);  // return 8; pairs (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) make 2 + 5 and
// pairs (5,1), (5,5) make 3 + 4
//findSumPairs.add(3, 2); // now nums2 = [1,4,5,4,5,4]
//findSumPairs.count(8);  // return 2; pairs (5,2), (5,4) make 3 + 5
//findSumPairs.count(4);  // return 1; pair (5,0) makes 3 + 1
//findSumPairs.add(0, 1); // now nums2 = [2,4,5,4,5,4]
//findSumPairs.add(1, 1); // now nums2 = [2,5,5,4,5,4]
//findSumPairs.count(7);  // return 11; pairs (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) make
// 2 + 5 and pairs (5,3), (5,5) make 3 + 4

//Constraints:
//1 <= nums1.length <= 1000
//1 <= nums2.length <= 105
//1 <= nums1[i] <= 109
//1 <= nums2[i] <= 105
//0 <= index < nums2.length
//1 <= val <= 105
//1 <= tot <= 109
//At most 1000 calls are made to add and count each.
