package P101_200.P140_Word_Break_II;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "catsanddog";
        List<String> wordDict1 = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(solution.wordBreak(s1, wordDict1)); // Output: ["cats and dog", "cat sand dog"]

        String s2 = "pineapplepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(solution.wordBreak(s2, wordDict2)); // Output: ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]

        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(solution.wordBreak(s3, wordDict3)); // Output: []
    }

//    public List<String> wordBreak(String s, List<String> wordDict) {
//        // Convert wordDict to a HashSet for O(1) lookup time.
//        Set<String> wordSet = new HashSet<>(wordDict);
//        // Use a HashMap to memoize results for subproblems.
//        return backtrack(s, wordSet, new HashMap<>());
//    }
//
//    private static List<String> backtrack(String s, Set<String> wordSet, Map<String, List<String>> memo) {
//        // If the result for the current substring is already computed, return it.
//        if (memo.containsKey(s)) {
//            return memo.get(s);
//        }
//
//        List<String> result = new ArrayList<>();
//
//        // If the entire substring is a valid word, add it to the result.
//        if (wordSet.contains(s)) {
//            result.add(s);
//        }
//
//        // Try every possible prefix of the substring.
//        for (int i = 1; i < s.length(); i++) {
//            String prefix = s.substring(0, i);
//            String suffix = s.substring(i);
//
//            // If the prefix is a valid word, recursively process the suffix.
//            if (wordSet.contains(prefix)) {
//                List<String> suffixBreaks = backtrack(suffix, wordSet, memo);
//                for (String suffixBreak : suffixBreaks) {
//                    result.add(prefix + " " + suffixBreak);
//                }
//            }
//        }
//
//        // Memoize the result for the current substring.
//        memo.put(s, result);
//        return result;
//    }

    //little faster solution
    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        // Масив для зберігання мемоізованих результатів для кожного індексу
        List<String>[] memo = new List[s.length() + 1];
        return backtrack(s, 0, wordSet, memo);
    }

    private static List<String> backtrack(String s, int start, Set<String> wordSet, List<String>[] memo) {
        // Якщо результат для поточного індексу вже обчислений, повернути його
        if (memo[start] != null) {
            return memo[start];
        }

        List<String> result = new ArrayList<>();

        // Якщо весь залишок строки є словом, додати його до результату
        if (wordSet.contains(s.substring(start))) {
            result.add(s.substring(start));
        }

        // Перебрати всі можливі префікси підрядка, що починаються з індексу start
        for (int end = start + 1; end < s.length(); end++) {
            String prefix = s.substring(start, end);

            // Якщо префікс є словом, рекурсивно обробити суфікс
            if (wordSet.contains(prefix)) {
                List<String> suffixBreaks = backtrack(s, end, wordSet, memo);
                for (String suffixBreak : suffixBreaks) {
                    result.add(prefix + " " + suffixBreak);
                }
            }
        }

        // Мемоізувати результат для поточного індексу
        memo[start] = result;
        return result;
    }
}

//Опис проблеми
//Ми маємо рядок s і словник слів wordDict. Потрібно знайти всі можливі розбиття рядка s, такі що кожне
// слово розбиття міститься у словнику wordDict.
//Підхід до вирішення
//Перетворення словника в множину:
//Щоб прискорити перевірку наявності слів у словнику, перетворюємо wordDict у множину wordSet.
//Рекурсивна функція з мемоізацією:
//Використовуємо рекурсивну функцію для розбиття рядка.
//Щоб уникнути обчислень для однакових підрядків кілька разів, використовуємо мемоізацію. Мемоізація зберігає
// результати обчислень для вже оброблених підрядків, що значно прискорює алгоритм.
//Рекурсивний підхід:
//Для кожного індексу start у рядку s, перевіряємо всі можливі префікси (підрядки, що починаються з start і
// закінчуються раніше кінця рядка).
//Якщо префікс є словом зі словника, викликаємо рекурсію для суфікса (залишкової частини рядка, що починається
// після префікса).
//Об’єднуємо префікс із результатами розбиття суфікса, щоб утворити повні речення.
//Базовий випадок рекурсії:
//Якщо весь рядок або його частина (починаючи з start) є словом зі словника, додаємо цей рядок до результату.
//Об'єднання результатів:
//Для кожного валідного префікса додаємо його до всіх валідних розбиттів суфікса.
//Результати зберігаємо в мемоізованому масиві або словнику для поточного індексу start.
//Повернення результату:
//Після обробки всіх можливих префіксів для кожного індексу start, повертаємо всі можливі речення, які можна
// утворити з початкового рядка s.
//Висновок
//Цей підхід ефективно знаходить всі можливі речення, що можна утворити з рядка s за допомогою слів зі
// словника. Використання мемоізації дозволяє значно зменшити кількість обчислень, що робить алгоритм швидким
// навіть для довгих рядків і великих словників.


//Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each
// word is a valid dictionary word. Return all such possible sentences in any order.
//Note that the same word in the dictionary may be reused multiple times in the segmentation.
//
//Example 1:
//Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
//Output: ["cats and dog","cat sand dog"]

//Example 2:
//Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
//Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
//Explanation: Note that you are allowed to reuse a dictionary word.

//Example 3:
//Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//Output: []
//
//Constraints:
//1 <= s.length <= 20
//1 <= wordDict.length <= 1000
//1 <= wordDict[i].length <= 10
//s and wordDict[i] consist of only lowercase English letters.
//All the strings of wordDict are unique.
//Input is generated in a way that the length of the answer doesn't exceed 105.