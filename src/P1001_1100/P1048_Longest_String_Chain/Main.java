package P1001_1100.P1048_Longest_String_Chain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public int longestStrChain(String[] words) {
        //sort the words by their length
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        //create a map to store the length of the longest word chain ending with each word
        Map<String, Integer> wordChainLength = new HashMap<>();
        int maxChainLength = 1;

        for (String word : words) {
            int currentChainLength = 1;

            //try removing one character from the word and check if it exists in the map
            for (int i = 0; i < word.length(); i++) {
                StringBuilder predecessor = new StringBuilder(word);
                predecessor.deleteCharAt(i);
                String pred = predecessor.toString();

                if (wordChainLength.containsKey(pred)) {
                    currentChainLength = Math.max(currentChainLength, wordChainLength.get(pred) + 1);
                }
            }

            wordChainLength.put(word, currentChainLength);
            maxChainLength = Math.max(maxChainLength, currentChainLength);
        }

        return maxChainLength;
    }

    public static void main(String[] args) {
        Main solution = new Main();
        String[] words1 = {"a","b","ba","bca","bda","bdca"};
        System.out.println(solution.longestStrChain(words1)); // Output: 4

        String[] words2 = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        System.out.println(solution.longestStrChain(words2)); // Output: 5

        String[] words3 = {"abcd","dbqca"};
        System.out.println(solution.longestStrChain(words3)); // Output: 1
    }

}

//You are given an array of words where each word consists of lowercase English letters.
//wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without
// changing the order of the other characters to make it equal to wordB.
//For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
//A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor
// of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
//Return the length of the longest possible word chain with words chosen from the given list of words.

//Example 1:
//Input: words = ["a","b","ba","bca","bda","bdca"]
//Output: 4
//Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

//Example 2:
//Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
//Output: 5
//Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

//Example 3:
//Input: words = ["abcd","dbqca"]
//Output: 1
//Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
//["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.

//Constraints:
//1 <= words.length <= 1000
//1 <= words[i].length <= 16
//words[i] only consists of lowercase English letters.

//Розв'язок:
//Для вирішення цієї задачі ми можемо використовувати динамічне програмування. Ось:
//Спочатку відсортуйте слова за їхніми довжинами в порядку зростання. Це допоможе при обробці слів,
// оскільки перед обробкою кожного слова всі його можливі попередники вже будуть відомі.
//Створіть словник (wordChainLength), в якому ви будете відстежувати довжину найдовшого ланцюжка слів, що
// закінчується кожним словом. Ініціалізуйте це значенням 1, оскільки кожне слово може бути ланцюжком
// довжиною 1 за замовчуванням.
//Пройдіться по відсортованим словам. Для кожного слова перевірте, чи є у нього попередник, видаляючи одну
// літеру за раз і дивлячись, чи це слово вже зустрічалось в словнику. Якщо так, оновіть довжину поточного
// ланцюжка слова, додавши 1 до довжини попереднього слова.
//Зберігайте максимальну знайдену довжину ланцюжка слов під час ітерації.
//Поверніть максимальну довжину ланцюжка як відповідь.
