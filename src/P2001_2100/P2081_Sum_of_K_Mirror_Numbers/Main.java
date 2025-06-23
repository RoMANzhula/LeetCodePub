package P2001_2100.P2081_Sum_of_K_Mirror_Numbers;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.kMirror(2, 5)); // Output: 25
        System.out.println(solution.kMirror(3, 7)); // Output: 499
        System.out.println(solution.kMirror(7, 17)); // Output: 20379000
    }

    public long kMirror(int k, int n) {
        List<Long> result = new ArrayList<>();

        // start with 1-digit palindromic numbers and grow
        for (int length = 1; result.size() < n; length++) {
            generatePalindromes(length, k, result, n);
        }

        long sum = 0;

        for (long num : result) {
            sum += num;
        }

        return sum;
    }

    private static void generatePalindromes(int length, int k, List<Long> result, int n) {
        int half = (length + 1) / 2;
        long start = (length == 1) ? 1 : (long) Math.pow(10, half - 1);
        long end = (long) Math.pow(10, half);

        for (long i = start; i < end && result.size() < n; i++) {
            long num = createPalindrome(i, length % 2 == 1);
            if (isPalindrome(toBaseK(num, k))) {
                result.add(num);
            }
        }
    }

    // create palindrome based on the half part
    private static long createPalindrome(long half, boolean oddLength) {
        String str = Long.toString(half);
        StringBuilder sb = new StringBuilder(str);

        if (oddLength) sb.deleteCharAt(sb.length() - 1);

        sb.reverse();

        return Long.parseLong(str + sb.toString());
    }

    // convert number to base-k string
    private static String toBaseK(long num, int k) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            sb.append(num % k);
            num /= k;
        }

        return sb.toString();
    }

    // check if string is palindrome
    private static boolean isPalindrome(String str) {
        int l = 0, r = str.length() - 1;

        while (l < r) {
            if (str.charAt(l++) != str.charAt(r--)) return false;
        }

        return true;
    }

}

// Explanation:
//Palindrome Generator: We generate numbers that are palindromic in base 10 by constructing them from "half parts".
//Check Base-k Palindrome: Convert this number to base-k and check if that string is also palindromic.
//Continue until n numbers found.
//Complexity:
// time - O(n * L) L = O(log (num))
// space - O(n + L)


//A k-mirror number is a positive integer without leading zeros that reads the same both forward and backward in
// base-10 as well as in base-k.
//For example, 9 is a 2-mirror number. The representation of 9 in base-10 and base-2 are 9 and 1001 respectively,
// which read the same both forward and backward.
//On the contrary, 4 is not a 2-mirror number. The representation of 4 in base-2 is 100, which does not read the
// same both forward and backward.
//Given the base k and the number n, return the sum of the n smallest k-mirror numbers.

//Example 1:
//Input: k = 2, n = 5
//Output: 25
//Explanation:
//The 5 smallest 2-mirror numbers and their representations in base-2 are listed as follows:
//  base-10    base-2
//    1          1
//    3          11
//    5          101
//    7          111
//    9          1001
//Their sum = 1 + 3 + 5 + 7 + 9 = 25.

//Example 2:
//Input: k = 3, n = 7
//Output: 499
//Explanation:
//The 7 smallest 3-mirror numbers are and their representations in base-3 are listed as follows:
//  base-10    base-3
//    1          1
//    2          2
//    4          11
//    8          22
//    121        11111
//    151        12121
//    212        21212
//Their sum = 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499.

//Example 3:
//Input: k = 7, n = 17
//Output: 20379000
//Explanation: The 17 smallest 7-mirror numbers are:
//1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596

//Constraints:
//2 <= k <= 9
//1 <= n <= 30
