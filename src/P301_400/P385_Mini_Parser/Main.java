package P301_400.P385_Mini_Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String input1 = "324";
        System.out.println(solution.deserialize(input1)); // Output: 324

        String input2 = "[123,[456,[789]]]";
        System.out.println(solution.deserialize(input2)); // Output: [123,[456,[789]]]
    }

    public NestedInteger deserialize(String s) {
        if (s == null || s.isEmpty()) return null;

        // if it’s a single integer, just return it
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger current = null;
        int num = 0;
        boolean negative = false;
        boolean hasNumber = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '-') {
                negative = true;
            } else if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
                hasNumber = true;
            } else if (ch == '[') {

                if (current != null) {
                    stack.push(current);
                }

                current = new NestedInteger();
            } else if (ch == ',' || ch == ']') {

                if (hasNumber) {
                    current.add(new NestedInteger(negative ? -num : num));
                    num = 0;
                    negative = false;
                    hasNumber = false;
                }

                if (ch == ']' && !stack.isEmpty()) {
                    NestedInteger parent = stack.pop();
                    parent.add(current);
                    current = parent;
                }
            }
        }

        return current;
    }

}

// This is the interface provided, you don’t implement this
class NestedInteger {
    private Integer value;
    private List<NestedInteger> list;

    // Constructor initializes an empty nested list.
    public NestedInteger() {
        list = new ArrayList<>();
    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        this.value = value;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return value != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    public Integer getInteger() {
        return value;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        this.value = value;
        this.list = null;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        if (list == null) list = new ArrayList<>();
        list.add(ni);
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    public List<NestedInteger> getList() {
        return list;
    }

    @Override
    public String toString() {
        return isInteger() ? String.valueOf(value) : list.toString();
    }
}

//Explanation:
//The parser uses a stack to handle nested brackets ([ ]):
//  When you see '[': Start a new NestedInteger and push the current one onto the stack.
//  When you see digits: Parse the integer (handle - too).
//  When you see ',' or ']': If there's a number, wrap it into a NestedInteger and add to the current list.
//  When ']': Pop from the stack and add the finished list to its parent.
//If the input is a plain number (no brackets), just return a single NestedInteger(int).
//Complexity:
//time and space - O(n)


//Given a string s represents the serialization of a nested list, implement a parser to deserialize it and
// return the deserialized NestedInteger.
//Each element is either an integer or a list whose elements may also be integers or other lists.

//Example 1:
//Input: s = "324"
//Output: 324
//Explanation: You should return a NestedInteger object which contains a single integer 324.

//Example 2:
//Input: s = "[123,[456,[789]]]"
//Output: [123,[456,[789]]]
//Explanation: Return a NestedInteger object containing a nested list with 2 elements:
//1. An integer containing value 123.
//2. A nested list containing two elements:
//    i.  An integer containing value 456.
//    ii. A nested list with one element:
//         a. An integer containing value 789

//Constraints:
//1 <= s.length <= 5 * 104
//s consists of digits, square brackets "[]", negative sign '-', and commas ','.
//s is the serialization of valid NestedInteger.
//All the values in the input are in the range [-106, 106].
