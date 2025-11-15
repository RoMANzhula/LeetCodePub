package P3201_3300.P3234_Count_the_Number_of_Substrings_With_Dominant_One;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "00011";
        System.out.println(solution.numberOfSubstrings(s1));  // Expected: 5

        String s2 = "101101";
        System.out.println(solution.numberOfSubstrings(s2));  // Expected: 16
    }

    public int numberOfSubstrings(String s) {
        int n = s.length();
        int ans = 0;

        // iiterate through all possible number of zeros
        for (int zero = 0; zero + zero * zero <= n; zero++) {
            int lastInvalidPos = -1;
            int[] count = new int[2]; // count[0] = zeros, count[1] = ones

            int l = 0;

            for (int r = 0; r < n; r++) {
                count[s.charAt(r) - '0']++;

                // try to shrink the window
                while (l < r) {
                    if (s.charAt(l) == '0' && count[0] > zero) {
                        count[0]--;          // remove a zero
                        lastInvalidPos = l;  // this l is invalid start
                        l++;
                    } else if (s.charAt(l) == '1' && count[1] - 1 >= zero * zero) {
                        count[1]--;          // remove a one
                        l++;
                    } else {
                        break; // cannot shrink anymore
                    }
                }

                if (count[0] == zero && count[1] >= zero * zero) {
                    // valid substrings ending at r:
                    // [lastInvalidPos+1 .. r], [lastInvalidPos+2 .. r], ... [l .. r]
                    ans += (l - lastInvalidPos);
                }
            }
        }

        return ans;
    }

}

//Complexity:
// time - O(n * sqrt(n))
// space - O(1)


//You are given a binary string s.
//Return the number of substrings with dominant ones.
//A string has dominant ones if the number of ones in the string is greater than or equal to the square of the
// number of zeros in the string.

//Example 1:
//Input: s = "00011"
//Output: 5
//Explanation:
//The substrings with dominant ones are shown in the table below.
//  i	j	s[i..j]	Number of Zeros	Number of Ones
//  3	3	    1	       0	            1
//  4	4	    1	       0	            1
//  2	3	    01	       1	            1
//  3	4	    11	       0	            2
//  2	4	    011	       1	            2

//Example 2:
//Input: s = "101101"
//Output: 16
//Explanation:
//The substrings with non-dominant ones are shown in the table below.
//Since there are 21 substrings total and 5 of them have non-dominant ones, it follows that there are 16 substrings
// with dominant ones.
//   i	j	s[i..j]	Number of Zeros	 Number of Ones
//   1	1	  0	           1	            0
//   4	4	  0	           1	            0
//   1	4	  0110	       2	            2
//   0	4	  10110	       2	            3
//   1	5	  01101	       2	            3

//Constraints:
//1 <= s.length <= 4 * 104
//s consists only of characters '0' and '1'.
