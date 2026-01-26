package P1101_1200.P1200_Minimum_Absolute_Difference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {4, 2, 1, 3};
        int[] arr2 = {1, 3, 6, 10, 15};
        int[] arr3 = {3, 8, -10, 23, 19, -4, -14, 27};

        System.out.println(solution.minimumAbsDifference(arr1)); // [[1, 2], [2, 3], [3, 4]]
        System.out.println(solution.minimumAbsDifference(arr2)); // [[1, 3]]
        System.out.println(solution.minimumAbsDifference(arr3)); // [[-14, -10], [19, 23], [23, 27]]
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);

        int minDiff = Integer.MAX_VALUE;

        //find minimum difference
        for (int i = 1; i < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
        }

        // collect pairs with minimum difference
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == minDiff) {
                result.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }

        return result;
    }

}

//Complexity:
// time - O(n log n)
// space - O(1)


//Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of
// any two elements.
//Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
//a, b are from arr
//a < b
//b - a equals to the minimum absolute difference of any two elements in arr

//Example 1:
//Input: arr = [4,2,1,3]
//Output: [[1,2],[2,3],[3,4]]
//Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.

//Example 2:
//Input: arr = [1,3,6,10,15]
//Output: [[1,3]]

//Example 3:
//Input: arr = [3,8,-10,23,19,-4,-14,27]
//Output: [[-14,-10],[19,23],[23,27]]

//Constraints:
//2 <= arr.length <= 105
//-106 <= arr[i] <= 106
