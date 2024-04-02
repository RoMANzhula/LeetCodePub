package P201_300.P205_Isomorphic_Strings;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "egg", t1 = "add";
        System.out.println("Output for Example 1: " + solution.isIsomorphic(s1, t1)); //true

        String s2 = "foo", t2 = "bar";
        System.out.println("Output for Example 2: " + solution.isIsomorphic(s2, t2)); //false

        String s3 = "paper", t3 = "title";
        System.out.println("Output for Example 3: " + solution.isIsomorphic(s3, t3)); //true
    }

//    public boolean isIsomorphic(String s, String t) {
//        if (s.length() != t.length()) {
//            return false;
//        }
//
//        Map<Character, Character> sToT = new HashMap<>();
//        Map<Character, Character> tToS = new HashMap<>();
//
//        for (int i = 0; i < s.length(); i++) {
//            char charS = s.charAt(i);
//            char charT = t.charAt(i);
//
//            // check if charS already maps to charT, or charT maps to charS
//            if (sToT.containsKey(charS) && sToT.get(charS) != charT ||
//                    tToS.containsKey(charT) && tToS.get(charT) != charS) {
//                return false;
//            }
//
//            // add mapping from charS to charT and charT to charS
//            sToT.put(charS, charT);
//            tToS.put(charT, charS);
//        }
//
//        return true;
//    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> mapping = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // сheck if charS already maps to charT or charT is already mapped to another char
            if (mapping.containsKey(charS)) {
                if (mapping.get(charS) != charT) {
                    return false;
                }
            } else {
                // сheck if charT is already mapped to by another char in s
                if (mapping.containsValue(charT)) {
                    return false;
                }
                // add mapping from charS to charT
                mapping.put(charS, charT);
            }
        }

        return true;
    }

}

//Задача полягає в тому, щоб перевірити, чи можна замінити символи у першому рядку (s) так, щоб вони утворювали
// другий рядок (t), зберігаючи порядок символів.
//Одним з швидких способів вирішення цієї задачі є використання одного мапу для відображення символів з рядка s на
// символи з рядка t. Під час ітерації по обом рядкам перевіряємо, чи вже існує відображення для символу з рядка s.
// Якщо таке відображення вже є, перевіряємо, чи відображає він на той самий символ у рядку t. Якщо так, то
// ми продовжуємо. Якщо символ з рядка s ще не відображений, перевіряємо, чи вже існує відображення для символу
// з рядка t. Якщо таке відображення існує, то це означає, що два символи з рядка s відображаються на один символ
// з рядка t, що не є допустимою умовою для ізоморфізму. Якщо такого відображення немає, то ми додаємо це нове
// відображення до мапу.
//Якщо ми успішно пройшли всі символи без порушення умов і не маємо жодного випадку неоднозначного відображення, ми
// можемо вважати рядки s та t ізоморфними. Якщо хоча б один з перевірок виявиться невірним, ми визначаємо, що
// рядки не є ізоморфними.
//Цей підхід дає нам розв'язок з часовою складністю O(n), де n - довжина рядків s та t.

//Given two strings s and t, determine if they are isomorphic.
//Two strings s and t are isomorphic if the characters in s can be replaced to get t.
//All occurrences of a character must be replaced with another character while preserving the order of
// characters. No two characters may map to the same character, but a character may map to itself.
//
//Example 1:
//Input: s = "egg", t = "add"
//Output: true

//Example 2:
//Input: s = "foo", t = "bar"
//Output: false

//Example 3:
//Input: s = "paper", t = "title"
//Output: true
//
//Constraints:
//1 <= s.length <= 5 * 104
//t.length == s.length
//s and t consist of any valid ascii character.
