package P1701_1800.P1784_Check_if_Binary_String_Has_at_Most_One_Segment_of_Ones;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "1001";
        String s2 = "110";

        System.out.println(solution.checkOnesSegment(s1)); // false
        System.out.println(solution.checkOnesSegment(s2)); // true
    }

    public boolean checkOnesSegment(String s) {
        int segments = 0;

        for (int i = 0; i < s.length(); i++) {
            //start of a new segment of ones
            if (s.charAt(i) == '1' && (i == 0 || s.charAt(i - 1) == '0')) {
                segments++;
                if (segments > 1) {
                    return false;
                }
            }
        }

        return true;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a binary string s without leading zeros, return true if s contains at most one contiguous segment of
// ones. Otherwise, return false.

//Example 1:
//Input: s = "1001"
//Output: false
//Explanation: The ones do not form a contiguous segment.

//Example 2:
//Input: s = "110"
//Output: true

//Constraints:
//1 <= s.length <= 100
//s[i]​​​​ is either '0' or '1'.
//s[0] is '1'.
