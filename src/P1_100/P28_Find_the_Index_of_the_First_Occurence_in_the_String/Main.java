package P1_100.P28_Find_the_Index_of_the_First_Occurence_in_the_String;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String haystack1 = "sadbutsad", needle1 = "sad";
        System.out.println(solution.strStr(haystack1, needle1)); // 0

        String haystack2 = "leetcode", needle2 = "sleetoad";
        System.out.println(solution.strStr(haystack2, needle2)); // -1
    }

    public int strStr(String haystack, String needle) {
        int result = haystack.indexOf(needle);

        return result;
    }

}

//Complexity:
// time - O(haystack.length() + needle.length())
// space - O(1)


//Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if
// needle is not part of haystack.

//Example 1:
//Input: haystack = "sadbutsad", needle = "sad"
//Output: 0
//Explanation: "sad" occurs at index 0 and 6.
//The first occurrence is at index 0, so we return 0.

//Example 2:
//Input: haystack = "leetcode", needle = "leeto"
//Output: -1
//Explanation: "leeto" did not occur in "leetcode", so we return -1.

//Constraints:
//1 <= haystack.length, needle.length <= 104
//haystack and needle consist of only lowercase English characters.
