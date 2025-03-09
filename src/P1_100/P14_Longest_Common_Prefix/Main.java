package P1_100.P14_Longest_Common_Prefix;

public class Main {

    public static void main(String[] args) {
        String[] arrayStr1 = {"dog","racecar","car"};
        String[] arrayStr2 = {"flower","flow","flight"};

        System.out.println(longestCommonPrefix(arrayStr1));
        System.out.println(longestCommonPrefix(arrayStr2));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) { //перевірка на порожність
            return "";
        }

        String prefix = strs[0]; //початкове значення префіксу - перше слово в масиві
        for (int i = 1; i < strs.length; i++) { //ітерація по решті слів у масиві
            while (strs[i].indexOf(prefix) != 0) { //перевіряємо, чи поточне слово починається з поточного префіксу

                prefix = prefix.substring(0, prefix.length() - 1); //якщо не починається, обрізаємо префікс на один символ

                if (prefix.isEmpty()) { //якщо префікс стає пустим - значить слова не мають спільного префіксу
                    return "";
                }
            }
        }

        return prefix; // Повертаємо знайдений спільний префікс
    }
}

//Write a function to find the longest common prefix string amongst an array of strings.
//If there is no common prefix, return an empty string "".

//Example 1:
//Input: strs = ["flower","flow","flight"]
//Output: "fl"

//Example 2:
//Input: strs = ["dog","racecar","car"]
//Output: ""
//Explanation: There is no common prefix among the input strings.

//Constraints:
//1 <= strs.length <= 200
//0 <= strs[i].length <= 200
//strs[i] consists of only lowercase English letters.
