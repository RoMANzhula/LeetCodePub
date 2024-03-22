package P1_100.P49_Group_Anagrams;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(solution.groupAnagrams(strs1)); //Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

        String[] strs2 = {""};
        System.out.println(solution.groupAnagrams(strs2)); // Output: [[""]]

        String[] strs3 = {"a"};
        System.out.println(solution.groupAnagrams(strs3)); //Output: [["a"]]
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = String.valueOf(charArray);

            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(str);
        }

        return new ArrayList<>(map.values());
    }
}

//У цьому розв'язку ми створюємо клас AnagramGrouping, в якому є метод groupAnagrams, що приймає на
// вхід масив рядків strs і повертає список списків, де кожен внутрішній список містить анаграми,
// згруповані разом. Спочатку ми сортуємо символи кожного рядка, щоб ідентифікувати анаграми, а
// потім використовуємо HashMap для їх групування. Нарешті, ми повертаємо значення (списки анаграм)
// з мапи. Метод main демонструє використання цього розв'язку з наданими прикладами.

//Given an array of strings strs, group the anagrams together. You can return the answer in any order.
//An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
// typically using all the original letters exactly once.

//Example 1:
//Input: strs = ["eat","tea","tan","ate","nat","bat"]
//Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

//Example 2:
//Input: strs = [""]
//Output: [[""]]

//Example 3:
//Input: strs = ["a"]
//Output: [["a"]]

//Constraints:
//1 <= strs.length <= 104
//0 <= strs[i].length <= 100
//strs[i] consists of lowercase English letters.
