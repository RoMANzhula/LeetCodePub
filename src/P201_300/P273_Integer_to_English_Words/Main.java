package P201_300.P273_Integer_to_English_Words;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numberToWords(123));        // Output: "One Hundred Twenty Three"
        System.out.println(solution.numberToWords(12345));      // Output: "Twelve Thousand Three Hundred Forty Five"
        System.out.println(solution.numberToWords(1234567));    // Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
        System.out.println(solution.numberToWords(0));          // Output: "Zero"

    }

    // Arrays to store words for single digits, tens and above, and special cases
    private static final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero"; // Handle the case where the number is zero

        int i = 0;
        String words = "";

        // Process the number in chunks of 1000
        while (num > 0) {
            if (num % 1000 != 0) {
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            }
            num /= 1000;
            i++;
        }

        return words.trim(); // Remove any leading/trailing spaces
    }

    private String helper(int num) {
        if (num == 0) return "";
        else if (num < 20) return LESS_THAN_20[num] + " ";
        else if (num < 100) return TENS[num / 10] + " " + helper(num % 10);
        else return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }
}

//Explanation:
//Arrays for Word Representation:
//LESS_THAN_20 array holds words for numbers from 0 to 19.
//TENS array holds words for multiples of ten from 20 to 90.
//THOUSANDS array holds the words for thousands, millions, and billions.
//Main Conversion Function (numberToWords):
//If the input num is zero, it returns "Zero".
//It iterates over the number, processing it in chunks of 1000.
//For each chunk, it converts the chunk to words and appends the appropriate thousand/million/billion label.
//Helper Function (helper):
//Converts numbers less than 1000 into words.
//Handles numbers less than 20, less than 100, and hundreds separately to form the correct English words.
//Main Method for Testing:
//The main method tests the function with different examples to ensure it works as expected.
//This solution ensures that all edge cases and different segments of the number are handled correctly, providing
// the accurate English words representation for the given integer.


//Convert a non-negative integer num to its English words representation.
//
//Example 1:
//Input: num = 123
//Output: "One Hundred Twenty Three"

//Example 2:
//Input: num = 12345
//Output: "Twelve Thousand Three Hundred Forty Five"

//Example 3:
//Input: num = 1234567
//Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
//
//Constraints:
//0 <= num <= 231 - 1