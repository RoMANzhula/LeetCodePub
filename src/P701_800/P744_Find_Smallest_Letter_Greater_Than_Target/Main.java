package P701_800.P744_Find_Smallest_Letter_Greater_Than_Target;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        char[] letters1 = {'c', 'f', 'j'};
        System.out.println(solution.nextGreatestLetter(letters1, 'a')); // c
        System.out.println(solution.nextGreatestLetter(letters1, 'c')); // f

        char[] letters2 = {'x', 'x', 'y', 'y'};
        System.out.println(solution.nextGreatestLetter(letters2, 'z')); // x
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // wrap around using modulo
        return letters[left % letters.length];
    }

}

//Complexity:
// time - O(log n)
// space - O(1)


//You are given an array of characters letters that is sorted in non-decreasing order, and a character target. There
// are at least two different characters in letters.
//Return the smallest character in letters that is lexicographically greater than target. If such a character does
// not exist, return the first character in letters.

//Example 1:
//Input: letters = ["c","f","j"], target = "a"
//Output: "c"
//Explanation: The smallest character that is lexicographically greater than 'a' in letters is 'c'.

//Example 2:
//Input: letters = ["c","f","j"], target = "c"
//Output: "f"
//Explanation: The smallest character that is lexicographically greater than 'c' in letters is 'f'.

//Example 3:
//Input: letters = ["x","x","y","y"], target = "z"
//Output: "x"
//Explanation: There are no characters in letters that is lexicographically greater than 'z' so we return letters[0].

//Constraints:
//2 <= letters.length <= 104
//letters[i] is a lowercase English letter.
//letters is sorted in non-decreasing order.
//letters contains at least two different characters.
//target is a lowercase English letter.
