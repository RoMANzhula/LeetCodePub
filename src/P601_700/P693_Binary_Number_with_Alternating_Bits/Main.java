package P601_700.P693_Binary_Number_with_Alternating_Bits;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.hasAlternatingBits(5));  // true
        System.out.println(solution.hasAlternatingBits(7));  // false
        System.out.println(solution.hasAlternatingBits(11)); // false
    }

    public boolean hasAlternatingBits(int n) {
        int x = n ^ (n >> 1);

        return (x & (x + 1)) == 0;
    }

}

//Complexity:
// time and space - O(1)


//Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always
// have different values.

//Example 1:
//Input: n = 5
//Output: true
//Explanation: The binary representation of 5 is: 101

//Example 2:
//Input: n = 7
//Output: false
//Explanation: The binary representation of 7 is: 111.

//Example 3:
//Input: n = 11
//Output: false
//Explanation: The binary representation of 11 is: 1011.

//Constraints:
//1 <= n <= 231 - 1
