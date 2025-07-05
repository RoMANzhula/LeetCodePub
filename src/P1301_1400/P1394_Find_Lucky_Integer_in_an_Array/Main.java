package P1301_1400.P1394_Find_Lucky_Integer_in_an_Array;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {2, 2, 3, 4};
        int[] arr2 = {1, 2, 2, 3, 3, 3};
        int[] arr3 = {2, 2, 2, 3, 3};

        System.out.println(solution.findLucky(arr1)); // Output: 2
        System.out.println(solution.findLucky(arr2)); // Output: 3
        System.out.println(solution.findLucky(arr3)); // Output: -1
    }

    public int findLucky(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        // count frequencies
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxLucky = -1;

        // find lucky integers and track the max
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            if (num == freq) {
                maxLucky = Math.max(maxLucky, num);
            }
        }

        return maxLucky;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
//Return the largest lucky integer in the array. If there is no lucky integer return -1.

//Example 1:
//Input: arr = [2,2,3,4]
//Output: 2
//Explanation: The only lucky number in the array is 2 because frequency[2] == 2.

//Example 2:
//Input: arr = [1,2,2,3,3,3]
//Output: 3
//Explanation: 1, 2 and 3 are all lucky numbers, return the largest of them.

//Example 3:
//Input: arr = [2,2,2,3,3]
//Output: -1
//Explanation: There are no lucky numbers in the array.

//Constraints:
//1 <= arr.length <= 500
//1 <= arr[i] <= 500
