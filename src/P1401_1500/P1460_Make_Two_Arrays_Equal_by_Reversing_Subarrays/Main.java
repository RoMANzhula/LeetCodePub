package P1401_1500.P1460_Make_Two_Arrays_Equal_by_Reversing_Subarrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] target1 = {1, 2, 3, 4};
        int[] arr1 = {2, 4, 1, 3};
        System.out.println(solution.canBeEqual(target1, arr1)); // Output: true

        int[] target2 = {7};
        int[] arr2 = {7};
        System.out.println(solution.canBeEqual(target2, arr2)); // Output: true

        int[] target3 = {3, 7, 9};
        int[] arr3 = {3, 7, 11};
        System.out.println(solution.canBeEqual(target3, arr3)); // Output: false
    }

//    public boolean canBeEqual(int[] target, int[] arr) {
//        // Sort both arrays
//        Arrays.sort(target);
//        Arrays.sort(arr);
//
//        // Check if sorted arrays are equal
//        return Arrays.equals(target, arr);
//    }

    //faster solution
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] countTarget = new int[1001];
        int[] countArr = new int[1001];

        // Count frequencies of elements in target
        for (int num : target) {
            countTarget[num]++;
        }

        // Count frequencies of elements in arr
        for (int num : arr) {
            countArr[num]++;
        }

        // Compare frequency counts
        for (int i = 1; i <= 1000; i++) {
            if (countTarget[i] != countArr[i]) {
                return false;
            }
        }

        return true;
    }

}

//Explanation:
//Frequency Counting:
//countTarget[num]++; increments the count of num in the countTarget array.
//countArr[num]++; increments the count of num in the countArr array.
//Comparison:
//The loop for (int i = 1; i <= 1000; i++) checks if the frequency counts of all possible elements (from 1 to 1000)
// are the same in both arrays.
//This approach ensures that we only pass through each array once to build the frequency arrays and then perform a
// linear comparison, resulting in an efficient O(n) solution.


//You are given two integer arrays of equal length target and arr. In one step, you can select any non-empty
// subarray of arr and reverse it. You are allowed to make any number of steps.
//Return true if you can make arr equal to target or false otherwise.
//
//Example 1:
//Input: target = [1,2,3,4], arr = [2,4,1,3]
//Output: true
//Explanation: You can follow the next steps to convert arr to target:
//1- Reverse subarray [2,4,1], arr becomes [1,4,2,3]
//2- Reverse subarray [4,2], arr becomes [1,2,4,3]
//3- Reverse subarray [4,3], arr becomes [1,2,3,4]
//There are multiple ways to convert arr to target, this is not the only way to do so.

//Example 2:
//Input: target = [7], arr = [7]
//Output: true
//Explanation: arr is equal to target without any reverses.

//Example 3:
//Input: target = [3,7,9], arr = [3,7,11]
//Output: false
//Explanation: arr does not have value 9 and it can never be converted to target.
//
//Constraints:
//target.length == arr.length
//1 <= target.length <= 1000
//1 <= target[i] <= 1000
//1 <= arr[i] <= 1000