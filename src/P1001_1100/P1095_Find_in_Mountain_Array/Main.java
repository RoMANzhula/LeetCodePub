package P1001_1100.P1095_Find_in_Mountain_Array;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        MountainArray mountainArray1 = new MountainArrayImpl(new int[]{1, 2, 3, 4, 5, 3, 1});
        int target1 = 3;
        System.out.println("Example 1 Output: " + solution.findInMountainArray(target1, mountainArray1)); // Expected output: 2

        MountainArray mountainArray2 = new MountainArrayImpl(new int[]{0, 1, 2, 4, 2, 1});
        int target2 = 3;
        System.out.println("Example 2 Output: " + solution.findInMountainArray(target2, mountainArray2)); // Expected output: -1
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();

        //find the peak element's index using binary search
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int peakIndex = left;

        //searc in the increasing part of the mountain array
        left = 0;
        right = peakIndex;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = mountainArr.get(mid);
            if (midValue == target) {
                return mid;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        //search in the decreasing part of the mountain array
        left = peakIndex;
        right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = mountainArr.get(mid);
            if (midValue == target) {
                return mid;
            } else if (midValue < target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1; //target not found in the mountain array
    }

}

interface MountainArray {
    public default int get(int index) {
        return index;
    }

    public default int length() {
        return length();
    }
}

class MountainArrayImpl implements MountainArray {
    private final int[] arr;

    public MountainArrayImpl(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int get(int index) {
        return arr[index];
    }

    @Override
    public int length() {
        return arr.length;
    }
}


//(This problem is an interactive problem.)
//You may recall that an array arr is a mountain array if and only if:
//arr.length >= 3
//There exists some i with 0 < i < arr.length - 1 such that:
//arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
//arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
//Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such
// an index does not exist, return -1.
//You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
//MountainArray.get(k) returns the element of the array at index k (0-indexed).
//MountainArray.length() returns the length of the array.
//Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that
// attempt to circumvent the judge will result in disqualification.

//Example 1:
//Input: mountainArr = [1,2,3,4,5,3,1], target = 3
//Output: 2
//Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
//Example 2:

//Input: mountainArr = [0,1,2,4,2,1], target = 3
//Output: -1
//Explanation: 3 does not exist in the array, so we return -1.

//Constraints:
//3 <= mountainArr.length() <= 104
//0 <= target <= 109
//0 <= mountainArr.get(index) <= 109
