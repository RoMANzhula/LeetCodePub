package P201_300.P258_Add_Digits;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();


        System.out.println(solution.addDigits(38)); // Output: 2
        System.out.println(solution.addDigits(0));  // Output: 0
    }

    public int addDigits(int num) {
        if (num == 0) return 0;

        return 1 + (num - 1) % 9;
    }

}

//Complexity:
// time and space - O(1)

//Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

//Example 1:
//Input: num = 38
//Output: 2
//Explanation: The process is
//38 --> 3 + 8 --> 11
//11 --> 1 + 1 --> 2
//Since 2 has only one digit, return it.

//Example 2:
//Input: num = 0
//Output: 0

//Constraints:
//0 <= num <= 231 - 1
