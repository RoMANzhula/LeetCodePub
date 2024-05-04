package P801_900.P881_Boats_to_Save_People;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] people1 = {1, 2};
        int limit1 = 3;
        System.out.println("Example 1: " + solution.numRescueBoats(people1, limit1)); // output 1

        int[] people2 = {3, 2, 2, 1};
        int limit2 = 3;
        System.out.println("Example 2: " + solution.numRescueBoats(people2, limit2)); // output 3

        int[] people3 = {3, 5, 3, 4};
        int limit3 = 5;
        System.out.println("Example 3: " + solution.numRescueBoats(people3, limit3)); // output 4
    }

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        int boats = 0;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }

            right--;
            boats++;
        }

        return boats;
    }
}

//Ця задача полягає в тому, щоб розрахувати мінімальну кількість човнів, необхідних для транспортування всіх людей з
// масами, які зазначені у вхідному масиві, де кожен човен може взяти максимум двох людей та має обмеження на
// загальну масу, яку він може перевозити.
//Наш підхід полягає у впорядкуванні мас людей за зростанням. Потім ми використовуємо два вказівника: один, який
// починається з початку відсортованого масиву, інший, який починається з кінця. Ми будемо рухати перший вказівник
// вправо і другий вказівник вліво, враховуючи, що першій вказівник може брати з собою другий, якщо сума їхніх мас
// не перевищує ліміт.
//Кожного разу, коли ми виконуємо цей крок, ми збільшуємо кількість човнів, аж до тих пір, поки вони не
// перетнуться. Коли вони перетнуться, це означатиме, що ми використовуємо човен для перевезення однієї людини,
// яка не може бути разом з іншою, і ми зменшуємо кількість доступних човнів.
//Ми повертаємо кількість човнів, які нам знадобляться для перевезення всіх людей.

//You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats
// where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time,
// provided the sum of the weight of those people is at most limit.
//Return the minimum number of boats to carry every given person.
//
//Example 1:
//Input: people = [1,2], limit = 3
//Output: 1
//Explanation: 1 boat (1, 2)

//Example 2:
//Input: people = [3,2,2,1], limit = 3
//Output: 3
//Explanation: 3 boats (1, 2), (2) and (3)

//Example 3:
//Input: people = [3,5,3,4], limit = 5
//Output: 4
//Explanation: 4 boats (3), (3), (4), (5)
//
//Constraints:
//1 <= people.length <= 5 * 104
//1 <= people[i] <= limit <= 3 * 104
