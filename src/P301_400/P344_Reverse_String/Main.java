package P301_400.P344_Reverse_String;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        solution.reverseString(s1);
        System.out.println(Arrays.toString(s1));
    }

    public void reverseString(char[] s) {
        int decrement = s.length;
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[decrement - 1 - i];
            s[decrement - 1 - i] = temp;
        }
    }

//    public reverseString(char[] s) {
//        int left = 0;
//        int right = s.length - 1;
//
//        while (left < right) {
//            // Swap the characters at left and right indices
//            char temp = s[left];
//            s[left] = s[right];
//            s[right] = temp;
//
//            // Move the pointers towards the center
//            left++;
//            right--;
//        }
//    }

}

//Write a function that reverses a string. The input string is given as an array of characters s.
//You must do this by modifying the input array in-place with O(1) extra memory.
//
//Example 1:
//Input: s = ["h","e","l","l","o"]
//Output: ["o","l","l","e","h"]

//Example 2:
//Input: s = ["H","a","n","n","a","h"]
//Output: ["h","a","n","n","a","H"]
//
//Constraints:
//1 <= s.length <= 105
//s[i] is a printable ascii character.
