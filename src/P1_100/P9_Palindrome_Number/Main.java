package P1_100.P9_Palindrome_Number;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.isPalindrome(121)); // true
        System.out.println(solution.isPalindrome(-121)); // false
        System.out.println(solution.isPalindrome(10)); // false
    }

//    public boolean isPalindrome(int x) {
//        if (x < 0) return false;
//
//        String toStr = String.valueOf(x);
//        int len = toStr.length();
//
//        int left = 0, right = len - 1;
//
//        while (left < right) {
//            if (toStr.charAt(left) != toStr.charAt(right)) return false;
//
//            left++;
//            right--;
//        }
//
//        return true;
//    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;

        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        return x == reversedHalf || x == reversedHalf / 10;
    }

}

//Complexity:
// time - O(log x)
// space - O(1)


//Given an integer x, return true if x is a palindrome, and false otherwise.

//Example 1:
//Input: x = 121
//Output: true
//Explanation: 121 reads as 121 from left to right and from right to left.

//Example 2:
//Input: x = -121
//Output: false
//Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

//Example 3:
//Input: x = 10
//Output: false
//Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

//Constraints:
//-231 <= x <= 231 - 1
