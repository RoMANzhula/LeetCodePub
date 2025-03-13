package P301_400.P342_Power_of_Four;

public class Main {
    public static void main(String[] args) {
        System.out.println(isPowerOfFour(32));
        System.out.println(isPowerOfFour(64));
        System.out.println(isPowerOfFour(4));
    }

    public static boolean isPowerOfFour(int n) {
        if (n == 0) return false;
        if (n == 1) return true;

        int pair = 1;

        for (int i = 0; i < 231; i++) {
            pair *= 4;
            if (n == pair) return true;
        }

        return false;

    }
}


//Given an integer n, return true if it is a power of four. Otherwise, return false.
//An integer n is a power of four, if there exists an integer x such that n == 4x.

//Example 1:
//Input: n = 16
//Output: true

//Example 2:
//Input: n = 5
//Output: false

//Example 3:
//Input: n = 1
//Output: true

//Constraints:
//-231 <= n <= 231 - 1
