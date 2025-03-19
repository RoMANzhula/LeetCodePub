package P1601_1700.P1662_Check_If_Two_Strings_Arrays_are_Equivalent;

public class Main {

    public static void main(String[] args) {
        String[] word1 = {"ab", "c"};
        String[] word2 = {"a", "bc"};

        System.out.println(arrayStringsAreEqual(word1, word2));  // Виведе "acb"
    }

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder stringBuilder1 = new StringBuilder();
        for (String word : word1) {
            stringBuilder1.append(word);
        }

        StringBuilder stringBuilder2 = new StringBuilder();
        for (String word : word2) {
            stringBuilder2.append(word);
        }

        return (stringBuilder1.toString()).contentEquals(stringBuilder2);
    }

}

//Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
//A string is represented by an array if the array elements concatenated in order forms the string.

//Example 1:
//Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
//Output: true
//Explanation:
//word1 represents string "ab" + "c" -> "abc"
//word2 represents string "a" + "bc" -> "abc"
//The strings are the same, so return true.

//Example 2:
//Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
//Output: false

//Example 3:
//Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
//Output: true

//Constraints:
//1 <= word1.length, word2.length <= 103
//1 <= word1[i].length, word2[i].length <= 103
//1 <= sum(word1[i].length), sum(word2[i].length) <= 103
//word1[i] and word2[i] consist of lowercase letters.
