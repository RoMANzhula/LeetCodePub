package P1701_1800.P1704_Determine_if_String_Halves_Are_Alike;

public class Main {
    public static void main(String[] args) {
        System.out.println(halvesAlike("book"));       // true
        System.out.println(halvesAlike("textbook"));   // false
    }

    public static boolean halvesAlike(String s) {
        // Convert the string to lowercase to simplify comparisons
        s = s.toLowerCase();

        // Split the string into two halves
        String a = s.substring(0, s.length() / 2);
        String b = s.substring(s.length() / 2);

        // Count vowels in each half
        int countA = countVowels(a);
        int countB = countVowels(b);

        // Check if the counts are equal
        return countA == countB;
    }

    private static int countVowels(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (isVowel(c)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}

//You are given a string s of even length. Split this string into two halves of equal lengths, and let
// a be the first half and b be the second half.
//
//Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').
// Notice that s contains uppercase and lowercase letters.
//
//Return true if a and b are alike. Otherwise, return false.
//Example 1:
//Input: s = "book"
//Output: true
//Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
//Example 2:
//Input: s = "textbook"
//Output: false
//Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
//Notice that the vowel o is counted twice.

//Constraints:
//2 <= s.length <= 1000
//s.length is even.
//s consists of uppercase and lowercase letters.
