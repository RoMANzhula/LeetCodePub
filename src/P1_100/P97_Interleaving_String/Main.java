package P1_100.P97_Interleaving_String;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbcbcac"));  // Output: true
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbbaccc"));  // Output: false
        System.out.println(solution.isInterleave("", "", ""));                      // Output: true
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int lengthFirstString = s1.length();
        int lengthSecondString = s2.length();

        if (lengthFirstString + lengthSecondString != s3.length()) { //main checking on lengths
            return false;
        }

        boolean[][] forResult = new boolean[lengthFirstString + 1][lengthSecondString + 1]; //create two-dimensional array
        forResult[0][0] = true; //if all strings are empty -> true

        //we iterate over the array for all combinations
        for (int i = 0; i <= lengthFirstString; i++) {
            for (int j = 0; j <= lengthSecondString; j++) {
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)) { //if first(next) char s1 == first(next) char s3, we
                    //go to next step
                    forResult[i][j] |= forResult[i - 1][j]; // (bitwise or with assignment) forming pair chars for s3
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1)) { //such as s1...
                    forResult[i][j] |= forResult[i][j - 1];// (bitwise or with assignment) forming pair chars for s3
                }
            }
        }

        return forResult[lengthFirstString][lengthSecondString]; //had result
    }

}

//Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
//An interleaving of two strings s and t is a configuration where s and t are divided into n and m
//substrings
// respectively, such that:
//s = s1 + s2 + ... + sn
//t = t1 + t2 + ... + tm
//|n - m| <= 1
//The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
//Note: a + b is the concatenation of strings a and b.
//
//Example 1:
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//Output: true
//Explanation: One way to obtain s3 is:
//Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
//Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
//Since s3 can be obtained by interleaving s1 and s2, we return true.

//Example 2:
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//Output: false
//Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.

//Example 3:
//Input: s1 = "", s2 = "", s3 = ""
//Output: true
//
//Constraints:
//0 <= s1.length, s2.length <= 100
//0 <= s3.length <= 200
//s1, s2, and s3 consist of lowercase English letters.
