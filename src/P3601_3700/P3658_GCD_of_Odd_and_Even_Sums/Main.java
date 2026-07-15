package P3601_3700.P3658_GCD_of_Odd_and_Even_Sums;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.gcdOfOddEvenSums(4));
        System.out.println(solution.gcdOfOddEvenSums(5));
    }

//    public int gcdOfOddEvenSums(int n) {
//        int oddSum = 1, evenSum = 2, result = 0;
//
//        while (n > 0){
//            oddSum += 2;
//            evenSum += 2;
//            result += (evenSum - oddSum);
//
//            n--;
//        }
//
//        return result;
//    }

    // faster solution
    public int gcdOfOddEvenSums(int n) {
           return n;
    }

    // and another one
    // public int gcdOfSums(int n) {
    //        int sumOdd = n * n;
    //        int sumEven = n * (n + 1);
    //        return gcd(sumOdd, sumEven);
    //    }
    //
    //    private int gcd(int a, int b) {
    //        while (b != 0) {
    //            int temp = a % b;
    //            a = b;
    //            b = temp;
    //        }
    //        return a;
    //    }
}

//Complexity:
// time and space - O(1)


//You are given an integer n. Your task is to compute the GCD (greatest common divisor) of two values:
//sumOdd: the sum of the smallest n positive odd numbers.
//sumEven: the sum of the smallest n positive even numbers.
//Return the GCD of sumOdd and sumEven.

//Example 1:
//Input: n = 4
//Output: 4
//Explanation:
//Sum of the first 4 odd numbers sumOdd = 1 + 3 + 5 + 7 = 16
//Sum of the first 4 even numbers sumEven = 2 + 4 + 6 + 8 = 20
//Hence, GCD(sumOdd, sumEven) = GCD(16, 20) = 4.

//Example 2:
//Input: n = 5
//Output: 5

//Explanation:
//Sum of the first 5 odd numbers sumOdd = 1 + 3 + 5 + 7 + 9 = 25
//Sum of the first 5 even numbers sumEven = 2 + 4 + 6 + 8 + 10 = 30
//Hence, GCD(sumOdd, sumEven) = GCD(25, 30) = 5.

//Constraints:
//1 <= n <= 10​​​​​​​00
