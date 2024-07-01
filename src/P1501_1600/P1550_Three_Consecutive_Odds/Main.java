package P1501_1600.P1550_Three_Consecutive_Odds;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {2, 6, 4, 1};
        int[] arr2 = {1, 2, 34, 3, 4, 5, 7, 23, 12};

        System.out.println(solution.threeConsecutiveOdds(arr1)); // Output: false
        System.out.println(solution.threeConsecutiveOdds(arr2)); // Output: true
    }

    public boolean threeConsecutiveOdds(int[] arr) {
        // Loop through the array until the third to last element
        for (int i = 0; i <= arr.length - 3; i++) {
            // Check if the current element and the next two elements are all odd
            if (arr[i] % 2 != 0 && arr[i + 1] % 2 != 0 && arr[i + 2] % 2 != 0) {
                return true; // Found three consecutive odd numbers
            }
        }
        return false; // No three consecutive odd numbers found
    }
}

//Explanation:
//Loop through the array: We iterate through the array from the start to the third-to-last
// element (i <= arr.length - 3) because we need to check three consecutive elements each time.
//Check for consecutive odd numbers: For each element, check if it and the next two elements are odd using
// the modulus operator (% 2 != 0). If they are all odd, return true.
//Return false if no three consecutive odds are found: If the loop completes without finding three consecutive
// odd numbers, return false.
//This approach ensures that we check every possible set of three consecutive elements in the array.


//Given an integer array arr, return true if there are three consecutive odd numbers in the
// array. Otherwise, return false.
//
//Example 1:
//Input: arr = [2,6,4,1]
//Output: false
//Explanation: There are no three consecutive odds.

//Example 2:
//Input: arr = [1,2,34,3,4,5,7,23,12]
//Output: true
//Explanation: [5,7,23] are three consecutive odds.
//
//Constraints:
//1 <= arr.length <= 1000
//1 <= arr[i] <= 1000
