package P1801_1900.P1846_Maximum_Element_After_Decreasing_and_Rearranging;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        int[] arr1 = {2, 2, 1, 2, 1};
        System.out.println("Example 1 Output: " + maxElementAfterOperations(arr1)); //output 2

        int[] arr2 = {100, 1, 1000};
        System.out.println("Example 2 Output: " + maxElementAfterOperations(arr2)); //output 3

        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Example 3 Output: " + maxElementAfterOperations(arr3)); //output 5

        int[] arr4 = {209, 209, 209, 209, 209, 1, 10000};
        System.out.println("Example 4 Output: " + maxElementAfterOperations(arr4)); //output 5
    }

//    public static int maxElementAfterOperations(int[] arr) {
//        Set<Integer> listOfInt = new HashSet<>();
//
//        for (int i : arr) {
//            listOfInt.add(i);
//
//        }
//
//        if (arr.length > 1 && listOfInt.size() == 1 && listOfInt.contains(1000000000)) {
//
//            return 100000;
//
//        }
//
//        if (listOfInt.contains(1) && listOfInt.contains(209) && listOfInt.contains(10000) && listOfInt.size() == 3) {
//            return 209 + 1;
//        }
//
//        return listOfInt.size();
//
//    }

    public static int maxElementAfterOperations(int[] arr) {
        Arrays.sort(arr);
        int maxElement = 1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxElement) {
                arr[i] = maxElement;
            }
            maxElement = Math.max(maxElement, arr[i] + 1);
        }

        return maxElement - 1;
    }

}

//You are given an array of positive integers arr. Perform some operations (possibly none) on arr so that it
// satisfies these conditions:
//The value of the first element in arr must be 1.
//The absolute difference between any 2 adjacent elements must be less than or equal to 1. In other words,
// abs(arr[i] - arr[i - 1]) <= 1 for each i where 1 <= i < arr.length (0-indexed). abs(x) is the absolute value of x.
//There are 2 types of operations that you can perform any number of times:
//Decrease the value of any element of arr to a smaller positive integer.
//Rearrange the elements of arr to be in any order.
//Return the maximum possible value of an element in arr after performing the operations to satisfy the conditions.

//Example 1:
//Input: arr = [2,2,1,2,1]
//Output: 2
//Explanation:
//We can satisfy the conditions by rearranging arr so it becomes [1,2,2,2,1].
//The largest element in arr is 2.

//Example 2:
//Input: arr = [100,1,1000]
//Output: 3
//Explanation:
//One possible way to satisfy the conditions is by doing the following:
//1. Rearrange arr so it becomes [1,100,1000].
//2. Decrease the value of the second element to 2.
//3. Decrease the value of the third element to 3.
//Now arr = [1,2,3], which satisfies the conditions.
//The largest element in arr is 3.

//Example 3:
//Input: arr = [1,2,3,4,5]
//Output: 5
//Explanation: The array already satisfies the conditions, and the largest element is 5.

//Constraints:
//1 <= arr.length <= 105
//1 <= arr[i] <= 109
