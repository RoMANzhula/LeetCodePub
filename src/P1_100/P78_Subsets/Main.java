package P1_100.P78_Subsets;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3};
        int[] nums2 = {0};

        System.out.println(solution.subsets(nums1)); //Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
        System.out.println(solution.subsets(nums2)); //output: [[], [0]]
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentList, int[] nums, int start) {
        result.add(new ArrayList<>(currentList)); // Add the current subset to the result
        for (int i = start; i < nums.length; i++) {
            currentList.add(nums[i]); // Include nums[i] in the current subset
            backtrack(result, currentList, nums, i + 1); // Move to the next element
            currentList.remove(currentList.size() - 1); // Exclude nums[i] from the current subset
        }
    }

}

//Крок 1: Визначення функції
//Визначимо функцію subsets, яка приймає масив цілих чисел nums і повертає список всіх можливих підмножин.
//Крок 2: Допоміжна функція
//Використаємо допоміжну функцію backtrack для генерування підмножин. Ця функція приймає параметри:
//result: список, що містить всі підмножини.
//currentList: поточний список, який представляє підмножину, що будується.
//nums: оригінальний масив.
//start: початковий індекс для побудови підмножини.
//Крок 3: Метод "backtracking" (відкату)
//Основна ідея полягає у використанні методу відкату для дослідження всіх можливих підмножин. Для кожного елемента
// можна або включити його до поточної підмножини, або виключити його. Це створює дерево рішень, де кожна вершина
// представляє рішення (включити або виключити елемент).
//Крок 4: Базовий випадок
//Коли досягаємо кінця масиву, додаємо поточну підмножину до списку всіх підмножин.
//Крок 5: Рекурсивний випадок
//Для кожного елемента, починаючи з поточного індексу, включаємо його до поточної підмножини і рекурсивно викликаємо
// допоміжну функцію для наступного індексу. Після рекурсивного виклику видаляємо елемент з поточної підмножини, щоб
// дослідити шлях, де цей елемент виключений.

//Given an integer array nums of unique elements, return all possible
//subsets (the power set).
//The solution set must not contain duplicate subsets. Return the solution in any order.
//
//Example 1:
//Input: nums = [1,2,3]
//Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

//Example 2:
//Input: nums = [0]
//Output: [[],[0]]
//
//Constraints:
//1 <= nums.length <= 10
//-10 <= nums[i] <= 10
//All the numbers of nums are unique.