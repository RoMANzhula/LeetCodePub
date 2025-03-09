package P1_100.P17_Letter_Combinations_of_a_Phone_Number;

import java.util.*;

public class Main {

    private static final Map<Character, String> phoneMap = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz"
    );

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>(); // create an empty list to store the found combinations
        if (digits == null || digits.isEmpty()) {
            return result; // return an empty list
        }
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    // recursive method to generate all possible letter combinations
    private void backtrack(List<String> result, StringBuilder combination, String digits, int index) {
        if (combination.length() == digits.length()) { // if the lengths match
            result.add(combination.toString()); // add the combination to the result
            return;
        }
        char digit = digits.charAt(index); // get the current digit by index
        String letters = phoneMap.get(digit); // get the string of letters corresponding to the current digit using phoneMap
        for (char letter : letters.toCharArray()) {
            combination.append(letter);
            backtrack(result, combination, digits, index + 1); // call backtrack for the next digit with an increased index
            combination.deleteCharAt(combination.length() - 1); // after using the current letter for the combination,
            // remove it from combination to prepare for adding the next letter
        }
    }


    public static void main(String[] args) {
        Main solution = new Main();
        String digits1 = "23";
        String digits2 = "";
        String digits3 = "2";
        System.out.println(solution.letterCombinations(digits1)); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(solution.letterCombinations(digits2)); // []
        System.out.println(solution.letterCombinations(digits3)); // ["a","b","c"]
    }

}

//Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number
// could represent. Return the answer in any order.
//A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map
// to any letters.
//2-abc, 3-def, 4-ghi, 5-jkl, 6-mno, 7-pqrs, 8-tuv, 9-wxyz

//Example 1:
//Input: digits = "23"
//Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

//Example 2:
//Input: digits = ""
//Output: []

//Example 3:
//Input: digits = "2"
//Output: ["a","b","c"]

//Constraints:
//0 <= digits.length <= 4
//digits[i] is a digit in the range ['2', '9'].
