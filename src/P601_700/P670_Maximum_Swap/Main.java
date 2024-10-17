package P601_700.P670_Maximum_Swap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumSwap(2736)); // Output: 7236
        System.out.println(solution.maximumSwap(9973)); // Output: 9973
    }

    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray(); // Convert the number to a char array of digits

        // To store the last occurrence index of each digit (0-9)
        int[] last = new int[10];

        // Fill the 'last' array to record the last occurrence of each digit
        for (int i = 0; i < digits.length; i++) {
            last[digits[i] - '0'] = i;
        }

        // Try to find the first pair of digits to swap
        for (int i = 0; i < digits.length; i++) {
            // Try to find a larger digit than digits[i] that occurs later in the number
            for (int d = 9; d > digits[i] - '0'; d--) {
                if (last[d] > i) {
                    // Swap digits[i] with digits[last[d]]
                    char temp = digits[i];
                    digits[i] = digits[last[d]];
                    digits[last[d]] = temp;

                    // Convert the modified digits array back to a number and return
                    return Integer.parseInt(new String(digits));
                }
            }
        }

        // If no swap was made, return the original number
        return num;
    }

}


//You are given an integer num. You can swap two digits at most once to get the maximum valued number.
//Return the maximum valued number you can get.
//
//Example 1:
//Input: num = 2736
//Output: 7236
//Explanation: Swap the number 2 and the number 7.

//Example 2:
//Input: num = 9973
//Output: 9973
//Explanation: No swap.
//
//Constraints:
//0 <= num <= 108
