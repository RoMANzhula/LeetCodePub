package P1301_1400.P1331_Rank_Transform_of_an_Array;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {40, 10, 20, 30};
        int[] arr2 = {100, 100, 100};
        int[] arr3 = {37, 12, 28, 9, 100, 56, 80, 5, 12};

        System.out.println(Arrays.toString(solution.arrayRankTransform(arr1))); // Output: [4, 1, 2, 3]
        System.out.println(Arrays.toString(solution.arrayRankTransform(arr2))); // Output: [1, 1, 1]
        System.out.println(Arrays.toString(solution.arrayRankTransform(arr3))); // Output: [5, 3, 4, 2, 8, 6, 7, 1, 3]
    }

    public int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0) {
            return new int[0]; // Return an empty array if input is empty
        }

        // Create a copy of the original array and sort it
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);

        // Create a map to store the rank of each unique element
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;

        for (int num : sortedArr) {
            // Only assign a rank if the number is not already in the map
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank++);
            }
        }

        // Replace each element in the original array with its rank
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rankMap.get(arr[i]);
        }

        return arr;
    }
}

//Explanation:
//Sorting: The original array is sorted to determine the order of the elements. This helps us assign ranks
// based on their size.
//Rank Mapping: A HashMap is used to map each unique value to its rank. The rank starts at 1 and increases as
// we encounter larger values.
//Final Replacement: We iterate through the original array, replacing each element with its rank from the map.
//Complexity:
//Time Complexity: O(n log n) due to the sorting step, where n is the length of the array.
//Space Complexity: O(n) for the HashMap and the sorted array.


//Given an array of integers arr, replace each element with its rank.
//The rank represents how large the element is. The rank has the following rules:
//Rank is an integer starting from 1.
//The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
//Rank should be as small as possible.
//
//Example 1:
//Input: arr = [40,10,20,30]
//Output: [4,1,2,3]
//Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.

//Example 2:
//Input: arr = [100,100,100]
//Output: [1,1,1]
//Explanation: Same elements share the same rank.

//Example 3:
//Input: arr = [37,12,28,9,100,56,80,5,12]
//Output: [5,3,4,2,8,6,7,1,3]
//
//Constraints:
//0 <= arr.length <= 105
//-109 <= arr[i] <= 109
