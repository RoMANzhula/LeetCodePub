package P1401_1500.P1497_Check_If_Array_Pairs_Are_Divisible_by_k;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {1, 2, 3, 4, 5, 10, 6, 7, 8, 9};
        int k1 = 5;
        System.out.println(solution.canArrange(arr1, k1)); // Output: true

        int[] arr2 = {1, 2, 3, 4, 5, 6};
        int k2 = 7;
        System.out.println(solution.canArrange(arr2, k2)); // Output: true

        int[] arr3 = {1, 2, 3, 4, 5, 6};
        int k3 = 10;
        System.out.println(solution.canArrange(arr3, k3)); // Output: false
    }

    public boolean canArrange(int[] arr, int k) {
        // Frequency array to store count of remainders
        int[] remainderCount = new int[k];

        // Populate the remainderCount array
        for (int num : arr) {
            int remainder = num % k;
            // Handle negative remainders by converting them to positive
            if (remainder < 0) {
                remainder += k;
            }
            remainderCount[remainder]++;
        }

        // Now, check if we can form valid pairs
        // Case 1: For remainder 0, the count must be even
        if (remainderCount[0] % 2 != 0) {
            return false;
        }

        // Case 2: For all other remainders, count[rem] must be equal to count[k - rem]
        for (int i = 1; i <= k / 2; i++) {
            if (remainderCount[i] != remainderCount[k - i]) {
                return false;
            }
        }

        // If all checks pass, return true
        return true;
    }
}

//Explanation:
//Remainder Calculation: For each element in arr, compute the remainder when divided by k. If the remainder is
// negative, we add k to make it positive.
//Remainder Matching:
//For remainder 0, the count must be even because each 0 remainder must pair with another 0 remainder.
//For each remainder r, the number of elements with remainder r must match the number of elements with remainder k - r.
//Edge Cases: If k is even, when r == k / 2, the count of such elements must also be even, because each such
// element can only pair with another element having the same remainder.
//Time Complexity:
//O(n) where n is the length of the array. We iterate through the array once to count remainders and once
// more to check the conditions.
//Space Complexity:
//O(k) for the remainder frequency array.


//Given an array of integers arr of even length n and an integer k.
//We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
//Return true If you can find a way to do that or false otherwise.
//
//Example 1:
//Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
//Output: true
//Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).

//Example 2:
//Input: arr = [1,2,3,4,5,6], k = 7
//Output: true
//Explanation: Pairs are (1,6),(2,5) and(3,4).

//Example 3:
//Input: arr = [1,2,3,4,5,6], k = 10
//Output: false
//Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each
// with sum divisible by 10.
//
//Constraints:
//arr.length == n
//1 <= n <= 105
//n is even.
//-109 <= arr[i] <= 109
//1 <= k <= 105
