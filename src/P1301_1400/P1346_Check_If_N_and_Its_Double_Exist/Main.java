package P1301_1400.P1346_Check_If_N_and_Its_Double_Exist;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {10, 2, 5, 3};
        System.out.println(solution.checkIfExist(arr1)); // Output: true

        int[] arr2 = {3, 1, 7, 11};
        System.out.println(solution.checkIfExist(arr2)); // Output: false
    }

    public boolean checkIfExist(int[] arr) {
        HashSet<Integer> seen = new HashSet<>();

        for (int num : arr) {
            if (seen.contains(num * 2) || (num % 2 == 0 && seen.contains(num / 2))) {
                return true;
            }

            seen.add(num);
        }

        return false;
    }
}

//Explanation of the Code:
//HashSet: The HashSet is used to store the numbers we have seen so far as we iterate through the array. This
// allows us to check for conditions in O(1) time.
//Condition Checks:
//2 * num: Checks if the current number is half of any previously seen number.
//num / 2: Checks if the current number is twice any previously seen number (only if it's even, as odd numbers
// cannot be halved to form integers).
//Early Exit: If a match is found, the function immediately returns true, making it efficient.
//Complexity:
//Time Complexity: O(n), where n is the size of the array, as we perform a single pass and
//O(1) operations for each element.
//Space Complexity:
//O(n), for storing elements in the HashSet.


//Given an array arr of integers, check if there exist two indices i and j such that :
//i != j
//0 <= i, j < arr.length
//arr[i] == 2 * arr[j]
//
//Example 1:
//Input: arr = [10,2,5,3]
//Output: true
//Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]

//Example 2:
//Input: arr = [3,1,7,11]
//Output: false
//Explanation: There is no i and j that satisfy the conditions.
//
//Constraints:
//2 <= arr.length <= 500
//-103 <= arr[i] <= 103
