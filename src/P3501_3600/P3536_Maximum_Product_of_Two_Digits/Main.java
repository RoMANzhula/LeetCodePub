package P3501_3600.P3536_Maximum_Product_of_Two_Digits;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxProduct(31)); // 3
        System.out.println(solution.maxProduct(22)); // 4
        System.out.println(solution.maxProduct(124)); // 8
        System.out.println(solution.maxProduct(267)); // 42
    }

    public int maxProduct(int n) {
        int max1 = -1;
        int max2 = -1;

        while (n > 0) {
            int digit = n % 10;

            if (digit >= max1) {
                max2 = max1;
                max1 = digit;
            } else if (digit > max2) {
                max2 = digit;
            }

            n /= 10;
        }

        return max1 * max2;
    }

}

//Complexity:
// time - O(d)      d - num of digits (<=10)
// space - O(1)


//You are given a positive integer n.
//Return the maximum product of any two digits in n.
//Note: You may use the same digit twice if it appears more than once in n.

//Example 1:
//Input: n = 31
//Output: 3
//Explanation:
//The digits of n are [3, 1].
//The possible products of any two digits are: 3 * 1 = 3.
//The maximum product is 3.

//Example 2:
//Input: n = 22
//Output: 4
//Explanation:
//The digits of n are [2, 2].
//The possible products of any two digits are: 2 * 2 = 4.
//The maximum product is 4.

//Example 3:
//Input: n = 124
//Output: 8
//Explanation:
//The digits of n are [1, 2, 4].
//The possible products of any two digits are: 1 * 2 = 2, 1 * 4 = 4, 2 * 4 = 8.
//The maximum product is 8.

//Constraints:
//10 <= n <= 109
