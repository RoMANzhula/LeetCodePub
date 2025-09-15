package P1901_2000.P1935_Maximum_Number_of_Words_You_Can_Type;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.canBeTypedWords("hello world", "ad")); // Output: 1
        System.out.println(solution.canBeTypedWords("leet code", "lt"));   // Output: 1
        System.out.println(solution.canBeTypedWords("leet code", "e"));    // Output: 0
    }

    public int canBeTypedWords(String text, String brokenLetters) {
        // convert broken letters into a set for fast lookup
        boolean[] broken = new boolean[26];

        for (char c : brokenLetters.toCharArray()) {
            broken[c - 'a'] = true;
        }

        int count = 0;
        String[] words = text.split(" ");

        // check each word
        for (String word : words) {
            boolean canType = true;

            for (char c : word.toCharArray()) {
                if (broken[c - 'a']) {
                    canType = false;
                    break;
                }
            }

            if (canType) count++;
        }

        return count;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.
//Given a string text of words separated by a single space (no leading or trailing spaces) and a string
// brokenLetters of all distinct letter keys that are broken, return the number of words in text you can
// fully type using this keyboard.

//Example 1:
//Input: text = "hello world", brokenLetters = "ad"
//Output: 1
//Explanation: We cannot type "world" because the 'd' key is broken.

//Example 2:
//Input: text = "leet code", brokenLetters = "lt"
//Output: 1
//Explanation: We cannot type "leet" because the 'l' and 't' keys are broken.

//Example 3:
//Input: text = "leet code", brokenLetters = "e"
//Output: 0
//Explanation: We cannot type either word because the 'e' key is broken.

//Constraints:
//1 <= text.length <= 104
//0 <= brokenLetters.length <= 26
//text consists of words separated by a single space without any leading or trailing spaces.
//Each word only consists of lowercase English letters.
//brokenLetters consists of distinct lowercase English letters.
