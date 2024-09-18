package P101_200.P179_Largest_Number;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {10, 2};
        System.out.println(solution.largestNumber(nums1));  // Output: "210"

        int[] nums2 = {3, 30, 34, 5, 9};
        System.out.println(solution.largestNumber(nums2));  // Output: "9534330"
    }

    public String largestNumber(int[] nums) {
        // Convert the array of numbers to an array of strings
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }

        // Sort the strings with a custom comparator
        Arrays.sort(strNums, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                // Compare two numbers by concatenating them in different orders
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1);  // Sort in descending order
            }
        });

        // If the largest number is "0", return "0" (to handle the case of multiple zeros)
        if (strNums[0].equals("0")) {
            return "0";
        }

        // Concatenate all strings to form the largest number
        StringBuilder largestNumber = new StringBuilder();
        for (String str : strNums) {
            largestNumber.append(str);
        }

        return largestNumber.toString();
    }
}

//Explanation:
//String Conversion: We first convert all integers to strings because we need to concatenate them for comparison.
//Custom Sorting: The custom comparator sorts two strings a and b by comparing a + b and b + a. This ensures
// that the combination of strings in the correct order results in the largest possible number.
//Handling Edge Cases: If the first number in the sorted list is "0", it means that all numbers are zeros,
// and we simply return "0" to avoid cases like "0000".
//Concatenation: We concatenate all strings from the sorted array to form the final result.
//Time Complexity:
//Sorting takes O(n log n), where n is the length of the input array. The comparison between two strings takes O(k),
// where k is the average number of digits in the numbers.
//Overall, the time complexity is O(n log n).


//Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
//Since the result may be very large, so you need to return a string instead of an integer.
//
//Example 1:
//Input: nums = [10,2]
//Output: "210"

//Example 2:
//Input: nums = [3,30,34,5,9]
//Output: "9534330"
//
//Constraints:
//1 <= nums.length <= 100
//0 <= nums[i] <= 109
