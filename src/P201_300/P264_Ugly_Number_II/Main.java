package P201_300.P264_Ugly_Number_II;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n = 10;
        System.out.println("The " + n + "th ugly number is: " + solution.nthUglyNumber(n));  // Output: 12
    }

    public int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int nextMultipleOf2 = 2;
        int nextMultipleOf3 = 3;
        int nextMultipleOf5 = 5;
        int nextUglyNumber = 1;

        for (int i = 1; i < n; i++) {
            nextUglyNumber = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));
            uglyNumbers[i] = nextUglyNumber;

            if (nextUglyNumber == nextMultipleOf2) {
                i2++;
                nextMultipleOf2 = uglyNumbers[i2] * 2;
            }
            if (nextUglyNumber == nextMultipleOf3) {
                i3++;
                nextMultipleOf3 = uglyNumbers[i3] * 3;
            }
            if (nextUglyNumber == nextMultipleOf5) {
                i5++;
                nextMultipleOf5 = uglyNumbers[i5] * 5;
            }
        }

        return uglyNumbers[n - 1];
    }
}

//Explanation:
//uglyNumbers[]: This array stores the sequence of ugly numbers up to the nth ugly number.
//i2, i3, i5: These are indices to keep track of the next multiples of 2, 3, and 5.
//nextMultipleOf2, nextMultipleOf3, nextMultipleOf5: These variables store the next potential ugly numbers that
// could be obtained by multiplying the previous ugly numbers by 2, 3, and 5 respectively.
//nextUglyNumber: This stores the next ugly number in the sequence.
//Steps:
//Initialize the first ugly number as 1.
//Iterate from 1 to n-1, computing the next ugly number as the minimum of the next multiples of 2, 3, and 5.
//Update the respective index (i2, i3, i5) when the next ugly number is equal to the corresponding multiple.
//Continue this until the nth ugly number is found.
//This approach efficiently computes the nth ugly number with a time complexity of O(n).


//An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
//Given an integer n, return the nth ugly number.
//
//Example 1:
//Input: n = 10
//Output: 12
//Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.

//Example 2:
//Input: n = 1
//Output: 1
//Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
//
//Constraints:
//1 <= n <= 1690
