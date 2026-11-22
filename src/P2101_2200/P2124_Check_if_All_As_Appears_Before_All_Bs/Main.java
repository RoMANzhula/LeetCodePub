package P2101_2200.P2124_Check_if_All_As_Appears_Before_All_Bs;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.checkString("aaabbb")); // true
        System.out.println(solution.checkString("abab"));   // false
        System.out.println(solution.checkString("bbb"));    // true
        System.out.println(solution.checkString("aaa"));    // true
        System.out.println(solution.checkString("ba"));     // false
    }

    public boolean checkString(String s) {
        boolean foundB = false;

        for (char c : s.toCharArray()) {
            if (c == 'b') {
                foundB = true;
            } else if (foundB) {
                return false;
            }
        }

        return true;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a string s consisting of only the characters 'a' and 'b', return true if every 'a' appears before every 'b' in
// the string. Otherwise, return false.

//Example 1:
//Input: s = "aaabbb"
//Output: true
//Explanation:
//The 'a's are at indices 0, 1, and 2, while the 'b's are at indices 3, 4, and 5.
//Hence, every 'a' appears before every 'b' and we return true.

//Example 2:
//Input: s = "abab"
//Output: false
//Explanation:
//There is an 'a' at index 2 and a 'b' at index 1.
//Hence, not every 'a' appears before every 'b' and we return false.

//Example 3:
//Input: s = "bbb"
//Output: true
//Explanation:
//There are no 'a's, hence, every 'a' appears before every 'b' and we return true.

//Constraints:
//1 <= s.length <= 100
//s[i] is either 'a' or 'b'.