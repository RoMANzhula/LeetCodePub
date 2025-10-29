package P3301_3400.P3370_Smallest_Number_With_All_Set_Bits;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.smallestNumber(5));  // Output: 7
        System.out.println(solution.smallestNumber(10)); // Output: 15
        System.out.println(solution.smallestNumber(3));  // Output: 3
    }

    public int smallestNumber(int n) {
        int k = 1;

        while ((1 << k) - 1 < n) { // whilw 2^k - 1 < 0
            k++;
        }

        return (1 << k) - 1; // return 2^k - 1
    }

}


//You are given a positive number n.
//Return the smallest number x greater than or equal to n, such that the binary representation of x contains only
// set bits

//Example 1:
//Input: n = 5
//Output: 7
//Explanation:
//The binary representation of 7 is "111".

//Example 2:
//Input: n = 10
//Output: 15
//Explanation:
//The binary representation of 15 is "1111".

//Example 3:
//Input: n = 3
//Output: 3
//Explanation:
//The binary representation of 3 is "11".

//Constraints:
//1 <= n <= 1000
