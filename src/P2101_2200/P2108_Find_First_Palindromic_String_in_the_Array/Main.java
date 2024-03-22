package P2101_2200.P2108_Find_First_Palindromic_String_in_the_Array;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"abc","car","ada","racecar","cool"};
        String[] words2 = {"notapalindrome","racecar"};
        String[] words3 = {"def","ghi"};

        System.out.println(solution.firstPalindrome(words1)); // output: "ada"
        System.out.println(solution.firstPalindrome(words2)); // output: "racecar"
        System.out.println(solution.firstPalindrome(words3)); // output: ""


    }

//    public String firstPalindrome(String[] words) {
//        for (String word : words) {
//            if (word.equals(reverseString(word))) {
//                return word;
//            }
//        }
//
//        return "";
//    }
//
//    public static String reverseString(String str) {
//        return new StringBuilder(str).reverse().toString();
//    }

    // faster solve
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word)) {
                return word;
            }
        }
        return "";
    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}

//Given an array of strings words, return the first palindromic string in the array. If there is no such string,
// return an empty string "".
//A string is palindromic if it reads the same forward and backward.

//Example 1:
///Input: words = ["abc","car","ada","racecar","cool"]
//Output: "ada"
//Explanation: The first string that is palindromic is "ada".
//Note that "racecar" is also palindromic, but it is not the first.

//Example 2:
//Input: words = ["notapalindrome","racecar"]
//Output: "racecar"
//Explanation: The first and only string that is palindromic is "racecar".

//Example 3:
//Input: words = ["def","ghi"]
//Output: ""
//Explanation: There are no palindromic strings, so the empty string is returned.
//
//Constraints:
//1 <= words.length <= 100
//1 <= words[i].length <= 100
//words[i] consists only of lowercase English letters.
