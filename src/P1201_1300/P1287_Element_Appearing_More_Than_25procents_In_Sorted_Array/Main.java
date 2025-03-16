package P1201_1300.P1287_Element_Appearing_More_Than_25procents_In_Sorted_Array;

public class Main {

    public static void main(String[] args) {
        int[] arr1 = {1,2,2,6,6,6,6,7,10};
        int[] arr2 = {1,1,1,1,1,1,2,3,4,5,6,7,8,9,10,11,12,12,12,12};

        System.out.println(findSpecialInteger(arr1));
        System.out.println(findSpecialInteger(arr2));
    }

//    public static int findSpecialInteger(int[] arr) {
//        int count = 0;
//        int result = 0;
//
//        if (arr.length == 1) {
//            result = arr[0];
//        }
//
//
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int element : arr) {
//                if (arr[i] == element) {
//                    count++;
//
//                    if (count > arr.length / 4) {
//                        result = arr[i];
//
//                    }
//                } else {
//                    count = 0;
//                }
//            }
//        }
//
//        return result;
//    }

    //somewhat faster solution
    public static int findSpecialInteger(int[] arr) {
        int blockSize = arr.length / 4;

        for (int i = 0; i < arr.length - blockSize; i++) {
            if (arr[i] == arr[i + blockSize]) {
                return arr[i];
            }
        }

        return -1; //if element not found
    }

}

//Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that
// occurs more than 25% of the time, return that integer.

//Example 1:
//Input: arr = [1,2,2,6,6,6,6,7,10]
//Output: 6

//Example 2:
//Input: arr = [1,1]
//Output: 1

//Constraints:
//1 <= arr.length <= 104
//0 <= arr[i] <= 105