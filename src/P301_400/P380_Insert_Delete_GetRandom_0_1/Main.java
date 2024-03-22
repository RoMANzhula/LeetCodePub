package P301_400.P380_Insert_Delete_GetRandom_0_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        RandomizedSet randomizedSet = new RandomizedSet();
//        System.out.println(randomizedSet.insert(1)); // true
//        System.out.println(randomizedSet.remove(2)); // false
//        System.out.println(randomizedSet.insert(2)); // true
//        System.out.println(randomizedSet.getRandom()); // 1 or 2
//        System.out.println(randomizedSet.remove(1)); // true
//        System.out.println(randomizedSet.insert(2)); // false
//        System.out.println(randomizedSet.getRandom()); // 2

        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(3)); // true
        System.out.println(randomizedSet.insert(-2)); // true
        System.out.println(randomizedSet.remove(2)); // false
        System.out.println(randomizedSet.insert(1)); // true
        System.out.println(randomizedSet.insert(-3)); // true
        System.out.println(randomizedSet.insert(-2)); // false
        System.out.println(randomizedSet.remove(-2)); // true
        System.out.println(randomizedSet.remove(-2)); // true
        System.out.println(randomizedSet.insert(3)); // true
        System.out.println(randomizedSet.remove(-1)); // true
        System.out.println(randomizedSet.insert(-3)); // true
        System.out.println(randomizedSet.insert(1)); // false
        System.out.println(randomizedSet.insert(-2)); // true
        System.out.println(randomizedSet.insert(-2)); // false
        System.out.println(randomizedSet.insert(-2)); // false
        System.out.println(randomizedSet.getRandom()); // -2 or -3
        System.out.println(randomizedSet.insert(-2)); // true
        System.out.println(randomizedSet.remove(0)); // false
        System.out.println(randomizedSet.insert(-3)); // true
        System.out.println(randomizedSet.insert(1)); // true
    }
}

class RandomizedSet {
    private List<Integer> list;
    private HashMap<Integer, Integer> indexMap;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
        indexMap = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (indexMap.containsKey(val)) {
            return false;
        }

        list.add(val);
        indexMap.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) {
            return false;
        }

        int indexToRemove = indexMap.get(val);
        int lastElement = list.get(list.size() - 1);

        // Swap the element to remove with the last element
        if (indexToRemove != list.size() - 1) {
            list.set(indexToRemove, lastElement);
            indexMap.put(lastElement, indexToRemove);
        }

        // Remove the last element and its mapping
        list.remove(list.size() - 1);
        indexMap.remove(val);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


//Ми звернулися до структури даних, що називається HashMap або HashSet, разом із ArrayList або Array для забезпечення
// оптимального часу вставки та видалення, а також для забезпечення швидкого отримання випадкового елементу.
//
//В даному розв'язку:
//
//ArrayList використовується для зберігання елементів, а HashMap використовується для відображення значень на
// їхні індекси в ArrayList.
//insert: Перевіряємо, чи вже елемент є в HashMap. Якщо так, повертаємо false, оскільки він вже існує. Якщо
// елементу немає, додаємо його в кінець ArrayList та відображаємо його індекс в HashMap. Повертаємо true.
//remove: Перевіряємо, чи елемент існує в HashMap. Якщо так, отримуємо його індекс з HashMap, індекс останнього
// елемента з ArrayList і сам елемент. Переміщуємо останній елемент на позицію елемента, який потрібно видалити,
// видаляємо останній елемент, видаляємо відображення для видаленого елемента. Повертаємо true.
//getRandom: Генеруємо випадковий індекс в межах розміру ArrayList та повертаємо елемент за цим індексом.
//Цей підхід дозволяє виконувати операції вставки, видалення та отримання випадкового елемента в середньому за O(1) часу.


//Implement the RandomizedSet class:
//
//RandomizedSet() Initializes the RandomizedSet object.
//bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not
// present, false otherwise.
//bool remove(int val) Removes an item val from the set if present. Returns true if the item was present,
// false otherwise.
//int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least
// one element exists when this method is called). Each element must have the same probability of being returned.
//You must implement the functions of the class such that each function works in average O(1) time complexity.
//
//Example 1:
//Input
//["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
//[[], [1], [2], [2], [], [1], [2], []]
//Output
//[null, true, false, true, 2, true, false, 2]
//
//Explanation
//RandomizedSet randomizedSet = new RandomizedSet();
//randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
//randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
//randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
//randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
//randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
//randomizedSet.insert(2); // 2 was already in the set, so return false.
//randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.

//Constraints:
//-231 <= val <= 231 - 1
//At most 2 * 105 calls will be made to insert, remove, and getRandom.
//There will be at least one element in the data structure when getRandom is called.