package P501_600.P557_Reverse_Words_in_a_String_III;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String str1 = "Hello world!";

        System.out.println(solution.reverseWords(str1));
    }

    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        String[] wordsArray = s.split(" "); //s.split("\\s+");

        for (String word : wordsArray) {
            StringBuilder temporary = new StringBuilder(word);
            temporary.reverse();
            result.append(temporary).append(" ");
        }

        return  result.toString().trim(); //cut last space
    }
}

//Given a string s, reverse the order of characters in each word within a sentence while still preserving
// whitespace and initial word order.

//Example 1:
//Input: s = "Let's take LeetCode contest"
//Output: "s'teL ekat edoCteeL tsetnoc"

//Example 2:
//Input: s = "God Ding"
//Output: "doG gniD"

//Constraints:
//1 <= s.length <= 5 * 104
//s contains printable ASCII characters.
//s does not contain any leading or trailing spaces.
//There is at least one word in s.
//All the words in s are separated by a single space.