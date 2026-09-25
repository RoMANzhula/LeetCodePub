package P1001_1100.P1096_Brace_Expansion_II;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String expression1 = "{a,b}{c,{d,e}}";
        System.out.println(solution.braceExpansionII(expression1));

        String expression2 = "{{a,z},a{b,c},{ab,z}}";
        System.out.println(solution.braceExpansionII(expression2));
    }

    private static int index = 0;

    public List<String> braceExpansionII(String expression) {
        index = 0;

        Set<String> result = parse(expression);
        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> parse(String expression) {
        Set<String> result = new HashSet<>();

        while (index < expression.length() && expression.charAt(index) != '}') {

            Set<String> current;

            if (expression.charAt(index) == '{') {
                index++;
                current = parse(expression);
                index++;
            } else {
                current = new HashSet<>();
                current.add(String.valueOf(expression.charAt(index)));
                index++;
            }

            if (result.isEmpty()) {
                result.addAll(current);
            } else {
                result = concatenate(result, current);
            }

            if (index < expression.length() && expression.charAt(index) == ',') {
                index++;
                result.addAll(parse(expression));
                break;
            }
        }

        return result;
    }

    private Set<String> concatenate(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>();

        for (String a : first) {
            for (String b : second) {
                result.add(a + b);
            }
        }

        return result;
    }

}

//Complexity:
// time and space - O(N * L)
// N - the number of distinct words produced
// L - the maximum word length


//Under the grammar given below, strings can represent a set of lowercase words. Let R(expr) denote the set of words
// the expression represents.
//The grammar can best be understood through simple examples:
//Single letters represent a singleton set containing that word.
//R("a") = {"a"}
//R("w") = {"w"}
//When we take a comma-delimited list of two or more expressions, we take the union of possibilities.
//R("{a,b,c}") = {"a","b","c"}
//R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
//When we concatenate two expressions, we take the set of possible concatenations between two words where the first
// word comes from the first expression and the second word comes from the second expression.
//R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
//R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
//Formally, the three rules for our grammar:
//For every lowercase letter x, we have R(x) = {x}.
//For expressions e1, e2, ... , ek with k >= 2, we have R({e1, e2, ...}) = R(e1) ∪ R(e2) ∪ ...
//For expressions e1 and e2, we have R(e1 + e2) = {a + b for (a, b) in R(e1) × R(e2)}, where + denotes concatenation,
// and × denotes the cartesian product.
//Given an expression representing a set of words under the given grammar, return the sorted list of words that the
// expression represents.

//Example 1:
//Input: expression = "{a,b}{c,{d,e}}"
//Output: ["ac","ad","ae","bc","bd","be"]

//Example 2:
//Input: expression = "{{a,z},a{b,c},{ab,z}}"
//Output: ["a","ab","ac","z"]
//Explanation: Each distinct word is written only once in the final answer.

//Constraints:
//1 <= expression.length <= 60
//expression[i] consists of '{', '}', ','or lowercase English letters.
//The given expression represents a set of words based on the grammar given in the description.
