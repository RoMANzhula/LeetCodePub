package P701_800.P726_Number_of_Atoms;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countOfAtoms("H2O")); // Output: H2O
        System.out.println(solution.countOfAtoms("Mg(OH)2")); // Output: H2MgO2
        System.out.println(solution.countOfAtoms("K4(ON(SO3)2)2")); // Output: K4N2O14S4
    }

    public String countOfAtoms(String formula) {
        Deque<Map<String, Integer>> stack = new ArrayDeque<>();
        stack.push(new HashMap<>());
        int i = 0, n = formula.length();

        while (i < n) {
            char c = formula.charAt(i);

            if (c == '(') {
                stack.push(new HashMap<>());
                i++;
            } else if (c == ')') {
                Map<String, Integer> top = stack.pop();
                i++;
                int start = i;
                while (i < n && Character.isDigit(formula.charAt(i))) i++;
                int multiplier = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;

                Map<String, Integer> current = stack.peek();
                for (String key : top.keySet()) {
                    current.put(key, current.getOrDefault(key, 0) + top.get(key) * multiplier);
                }
            } else {
                int start = i;
                i++;
                while (i < n && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(start, i);

                start = i;
                while (i < n && Character.isDigit(formula.charAt(i))) i++;
                int count = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;

                Map<String, Integer> current = stack.peek();
                current.put(name, current.getOrDefault(name, 0) + count);
            }
        }

        Map<String, Integer> result = stack.pop();
        TreeMap<String, Integer> sortedResult = new TreeMap<>(result);
        StringBuilder sb = new StringBuilder();
        for (String key : sortedResult.keySet()) {
            sb.append(key);
            int count = sortedResult.get(key);
            if (count > 1) sb.append(count);
        }

        return sb.toString();
    }
}

//Explanation:
//Parsing Characters:
//We use a stack to manage nested structures. When encountering '(', we push a new map onto the stack.
//When encountering ')', we pop from the stack, apply the multiplier, and merge the counts back into the previous level.
//For element names, we extract the name and its count (defaulting to 1 if no count is specified) and
// update the current map on the stack.
//Combining Results:
//After processing the entire formula, the stack's top contains the combined counts.
//We use a TreeMap to sort the elements by name and construct the final result string.
//This approach ensures we handle nested formulas and multipliers correctly, and the use of a stack helps
// manage the hierarchical structure effectively.


//Given a string formula representing a chemical formula, return the count of each atom.
//The atomic element always starts with an uppercase character, then zero or more lowercase letters,
// representing the name.
//One or more digits representing that element's count may follow if the count is greater than 1. If the count
// is 1, no digits will follow.
//For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
//Two formulas are concatenated together to produce another formula.
//For example, "H2O2He3Mg4" is also a formula.
//A formula placed in parentheses, and a count (optionally added) is also a formula.
//For example, "(H2O2)" and "(H2O2)3" are formulas.
//Return the count of all elements as a string in the following form: the first name (in sorted order), followed by
// its count (if that count is more than 1), followed by the second name (in sorted order), followed by its
// count (if that count is more than 1), and so on.
//The test cases are generated so that all the values in the output fit in a 32-bit integer.
//
//Example 1:
//Input: formula = "H2O"
//Output: "H2O"
//Explanation: The count of elements are {'H': 2, 'O': 1}.

//Example 2:
//Input: formula = "Mg(OH)2"
//Output: "H2MgO2"
//Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.

//Example 3:
//Input: formula = "K4(ON(SO3)2)2"
//Output: "K4N2O14S4"
//Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
//
//Constraints:
//1 <= formula.length <= 1000
//formula consists of English letters, digits, '(', and ')'.
//formula is always valid.