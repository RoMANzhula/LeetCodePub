package P501_600.P564_Find_the_Closest_Palindrome;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.nearestPalindromic("123")); // Output: "121"
        System.out.println(solution.nearestPalindromic("1"));   // Output: "0"
    }

    public String nearestPalindromic(String n) {
        int len = n.length();
        long num = Long.parseLong(n);
        long high, low;

        // Edge cases
        if (n.equals("1")) {
            return "0";
        }
        if (n.equals("10") || n.equals("11")) {
            return "9";
        }

        // Generate base palindromes
        long base = (long) Math.pow(10, len - 1);
        high = base * 10 + 1;
        low = base - 1;

        // Generate palindromes by mirroring
        String firstHalf = n.substring(0, (len + 1) / 2);
        long firstHalfNum = Long.parseLong(firstHalf);

        long pal1 = createPalindrome(firstHalfNum, len % 2 == 0);
        long pal2 = createPalindrome(firstHalfNum - 1, len % 2 == 0);
        long pal3 = createPalindrome(firstHalfNum + 1, len % 2 == 0);

        List<Long> candidates = Arrays.asList(pal1, pal2, pal3, high, low);
        long closest = -1;

        // Find the closest palindrome
        for (long candidate : candidates) {
            if (candidate == num) {
                continue;
            }
            if (closest == -1 || Math.abs(candidate - num) < Math.abs(closest - num) ||
                    (Math.abs(candidate - num) == Math.abs(closest - num) && candidate < closest)) {
                closest = candidate;
            }
        }

        return String.valueOf(closest);
    }

    private long createPalindrome(long half, boolean isEvenLength) {
        String halfStr = String.valueOf(half);
        String reverse = new StringBuilder(halfStr).reverse().toString();
        if (isEvenLength) {
            return Long.parseLong(halfStr + reverse);
        } else {
            return Long.parseLong(halfStr + reverse.substring(1));
        }
    }
}

//Explanation:
//Base Palindromes: We consider simple base cases such as 999...999, 100...001, 9...9, etc.
//Mirroring: For creating palindromes, we mirror the first half of the string. We check palindromes formed by
// leaving the middle unchanged, increasing it, and decreasing it.
//Comparisons: We then compare all potential palindromes, excluding the original number itself, and return the one
// with the smallest difference. In case of a tie, we return the smaller numeric value.
//This approach is efficient and handles the constraints well, ensuring that even for the maximum possible length
// of n, the function performs adequately.


//Given a string n representing an integer, return the closest integer (not including itself), which is a
// palindrome. If there is a tie, return the smaller one.
//The closest is defined as the absolute difference minimized between two integers.
//
//Example 1:
//Input: n = "123"
//Output: "121"

//Example 2:
//Input: n = "1"
//Output: "0"
//Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
//
//Constraints:
//1 <= n.length <= 18
//n consists of only digits.
//n does not have leading zeros.
//n is representing an integer in the range [1, 1018 - 1].
