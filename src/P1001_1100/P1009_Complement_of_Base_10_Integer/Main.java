package P1001_1100.P1009_Complement_of_Base_10_Integer;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 5;
        int n2 = 7;
        int n3 = 10;

        System.out.println(solution.bitwiseComplement(n1)); // 2
        System.out.println(solution.bitwiseComplement(n2)); // 0
        System.out.println(solution.bitwiseComplement(n3)); // 5
    }

    public int bitwiseComplement(int n) {
        if (n == 0) return 1;

        int mask = 0;
        int temp = n;

        // create mask like 111...111 (same length as n)
        while (temp > 0) {
            mask = (mask << 1) | 1;
            temp >>= 1;
        }

        return n ^ mask;
    }

}

//Complexity:
// time - O(log n)
// space - O(1)


//The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its
// binary representation.
//For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
//Given an integer n, return its complement.

//Example 1:
//Input: n = 5
//Output: 2
//Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.

//Example 2:
//Input: n = 7
//Output: 0
//Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.

//Example 3:
//Input: n = 10
//Output: 5
//Explanation: 10 is "1010" in binary, with complement "0101" in binary, which is 5 in base-10.

//Constraints:
//0 <= n < 109
