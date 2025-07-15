package P3101_3200.P3136_Valid_Word;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.isValid("234Adas")); // true
        System.out.println(solution.isValid("b3"));      // false
        System.out.println(solution.isValid("a3$e"));    // false
        System.out.println(solution.isValid("AE1"));     // false
        System.out.println(solution.isValid("e2B"));     // true
    }

    public boolean isValid(String word) {
        if (word.length() < 3) return false;

        boolean hasVowel = false;
        boolean hasConsonant = false;

        for (char ch : word.toCharArray()) {
            if (!Character.isLetterOrDigit(ch)) {
                return false; // invalid character
            }

            if (isVowel(ch)) {
                hasVowel = true;
            } else if (Character.isLetter(ch)) {
                hasConsonant = true;
            }
        }

        return hasVowel && hasConsonant;
    }

    private static boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) != -1;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//A word is considered valid if:
//It contains a minimum of 3 characters.
//It contains only digits (0-9), and English letters (uppercase and lowercase).
//It includes at least one vowel.
//It includes at least one consonant.
//You are given a string word.
//Return true if word is valid, otherwise, return false.

//Notes:
//'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
//A consonant is an English letter that is not a vowel.

//Example 1:
//Input: word = "234Adas"
//Output: true
//Explanation:
//This word satisfies the conditions.

//Example 2:
//Input: word = "b3"
//Output: false
//Explanation:
//The length of this word is fewer than 3, and does not have a vowel.

//Example 3:
//Input: word = "a3$e"
//Output: false
//Explanation:
//This word contains a '$' character and does not have a consonant.

//Constraints:
//1 <= word.length <= 20
//word consists of English uppercase and lowercase letters, digits, '@', '#', and '$'.
