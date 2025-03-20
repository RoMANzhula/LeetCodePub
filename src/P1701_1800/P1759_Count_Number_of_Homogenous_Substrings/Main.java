package P1701_1800.P1759_Count_Number_of_Homogenous_Substrings;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        System.out.println(solution.countHomogenous("abbcccaa")); //output: 13
        System.out.println(solution.countHomogenous("xy")); //output: 2
        System.out.println(solution.countHomogenous("zzzzz")); //output: 15
    }

//    public int countHomogenous(String s) {
//        final int MOD = 1_000_000_007;
//        int count = 1; //at least one homogeneous substring, which is the character itself
//        long result = 0;
//
//        for (int i = 1; i < s.length(); i++) {
//            if (s.charAt(i) == s.charAt(i - 1)) {
//                count++;
//            } else {
//                //calculate the number of homogeneous substrings ending at the current character and add it to the result
//                result = (result + ((long)count * (count + 1) / 2) % MOD) % MOD;
//                count = 1;
//            }
//        }
//
//        //calculate the number of homogeneous substrings ending at the last character
//        result = (result + ((long) count * (count + 1) / 2) % MOD) % MOD;
//
//        return (int)result;
//    }

    //faster method
    public int countHomogenous(String s) {
        final int MOD = 1_000_000_007;
        int count = 1; // At least one homogeneous substring, which is the character itself
        long result = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                // Calculate the number of homogeneous substrings ending at the current character
                long substrCount = (long)count * (count + 1) / 2;
                result = (result + substrCount) % MOD;
                count = 1;
            }
        }

        // Calculate the number of homogeneous substrings ending at the last character
        long substrCount = (long)count * (count + 1) / 2;
        result = (result + substrCount) % MOD;

        return (int)result;
    }

}


//Given a string s, return the number of homogenous substrings of s. Since the answer may be too large,
// return it modulo 109 + 7.
//A string is homogenous if all the characters of the string are the same.
//A substring is a contiguous sequence of characters within a string.

//Example 1:
//Input: s = "abbcccaa"
//Output: 13
//Explanation: The homogenous substrings are listed as below:
//"a"   appears 3 times.
//"aa"  appears 1 time.
//"b"   appears 2 times.
//"bb"  appears 1 time.
//"c"   appears 3 times.
//"cc"  appears 2 times.
//"ccc" appears 1 time.
//3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.

//Example 2:
//
//Input: s = "xy"
//Output: 2
//Explanation: The homogenous substrings are "x" and "y".

//Example 3:
//Input: s = "zzzzz"
//Output: 15

//Constraints:
//1 <= s.length <= 105
//s consists of lowercase letters.
