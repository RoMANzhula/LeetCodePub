package P1801_1900.P1807_Evaluate_the_Bracket_Pairs_of_a_String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "(name)is(age)yearsold";
        List<List<String>> knowledge1 = Arrays.asList(
                Arrays.asList("name", "bob"),
                Arrays.asList("age", "two")
        );
        System.out.println(solution.evaluate(s1, knowledge1)); // bobistwoyearsold

        String s2 = "hi(name)";
        List<List<String>> knowledge2 = Arrays.asList(
                Arrays.asList("a", "b")
        );
        System.out.println(solution.evaluate(s2, knowledge2)); // hi?

        String s3 = "(a)(a)(a)aaa";
        List<List<String>> knowledge3 = Arrays.asList(
                Arrays.asList("a", "yes")
        );
        System.out.println(solution.evaluate(s3, knowledge3)); // yesyesyesaaa
    }

    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                int j = i + 1;

                while (s.charAt(j) != ')') {
                    j++;
                }

                String key = s.substring(i + 1, j);

                result.append(map.getOrDefault(key, "?"));

                i = j + 1;
            } else {
                result.append(s.charAt(i));
                i++;
            }
        }

        return result.toString();
    }

}

//Complexity:
// time and space - O(s.length() + knowledge.length)


//You are given a string s that contains some bracket pairs, with each pair containing a non-empty key.
//For example, in the string "(name)is(age)yearsold", there are two bracket pairs that contain the keys "name" and "age".
//You know the values of a wide range of keys. This is represented by a 2D string array knowledge where
// each knowledge[i] = [keyi, valuei] indicates that key keyi has a value of valuei.
//You are tasked to evaluate all of the bracket pairs. When you evaluate a bracket pair that contains some key keyi,
// you will:
//Replace keyi and the bracket pair with the key's corresponding valuei.
//If you do not know the value of the key, you will replace keyi and the bracket pair with a question mark "?" (without
// the quotation marks).
//Each key will appear at most once in your knowledge. There will not be any nested brackets in s.
//Return the resulting string after evaluating all of the bracket pairs.

//Example 1:
//Input: s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
//Output: "bobistwoyearsold"
//Explanation:
//The key "name" has a value of "bob", so replace "(name)" with "bob".
//The key "age" has a value of "two", so replace "(age)" with "two".

//Example 2:
//Input: s = "hi(name)", knowledge = [["a","b"]]
//Output: "hi?"
//Explanation: As you do not know the value of the key "name", replace "(name)" with "?".

//Example 3:
//Input: s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
//Output: "yesyesyesaaa"
//Explanation: The same key can appear multiple times.
//The key "a" has a value of "yes", so replace all occurrences of "(a)" with "yes".
//Notice that the "a"s not in a bracket pair are not evaluated.

//Constraints:
//1 <= s.length <= 105
//0 <= knowledge.length <= 105
//knowledge[i].length == 2
//1 <= keyi.length, valuei.length <= 10
//s consists of lowercase English letters and round brackets '(' and ')'.
//Every open bracket '(' in s will have a corresponding close bracket ')'.
//The key in each bracket pair of s will be non-empty.
//There will not be any nested bracket pairs in s.
//keyi and valuei consist of lowercase English letters.
//Each keyi in knowledge is unique.
