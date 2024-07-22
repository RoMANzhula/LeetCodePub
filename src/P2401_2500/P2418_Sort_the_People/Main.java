package P2401_2500.P2418_Sort_the_People;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] names1 = {"Mary", "John", "Emma"};
        int[] heights1 = {180, 165, 170};
        System.out.println(Arrays.toString(solution.sortPeople(names1, heights1))); // Output: ["Mary", "Emma", "John"]

        String[] names2 = {"Alice", "Bob", "Bob"};
        int[] heights2 = {155, 185, 150};
        System.out.println(Arrays.toString(solution.sortPeople(names2, heights2))); // Output: ["Bob", "Alice", "Bob"]
    }

    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Person[] people = new Person[n];

        for (int i = 0; i < n; i++) {
            people[i] = new Person(names[i], heights[i]);
        }

        Arrays.sort(people, (a, b) -> b.height - a.height);

        String[] sortedNames = new String[n];
        for (int i = 0; i < n; i++) {
            sortedNames[i] = people[i].name;
        }

        return sortedNames;
    }

}

class Person {
    String name;
    int height;

    Person(String name, int height) {
        this.name = name;
        this.height = height;
    }
}

//Пояснення:
//Метод sortPeople:
//Створює масив Person об'єктів з заданими іменами та висотами.
//Сортує масив Person на основі висот у порядку спадання за допомогою Arrays.sort з кастомним компаратором.
//Створює масив String, що містить імена у відсортованому порядку та повертає його.
//Клас Person: Представляє людину з ім'ям і висотою.
//Метод main: Для тестування методу sortPeople з наданими прикладами.
//Цей підхід забезпечує сортування імен відповідно до висот у порядку спадання, як це вимагається.


//You are given an array of strings names, and an array heights that consists of distinct positive
// integers. Both arrays are of length n.
//For each index i, names[i] and heights[i] denote the name and height of the ith person.
//Return names sorted in descending order by the people's heights.
//
//Example 1:
//Input: names = ["Mary","John","Emma"], heights = [180,165,170]
//Output: ["Mary","Emma","John"]
//Explanation: Mary is the tallest, followed by Emma and John.

//Example 2:
//Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
//Output: ["Bob","Alice","Bob"]
//Explanation: The first Bob is the tallest, followed by Alice and the second Bob.
//
//Constraints:
//n == names.length == heights.length
//1 <= n <= 103
//1 <= names[i].length <= 20
//1 <= heights[i] <= 105
//names[i] consists of lower and upper case English letters.
//All the values of heights are distinct.
