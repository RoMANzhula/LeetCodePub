package P2353_Design_a_Food_Rating_System;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


class FoodRatings {
    // {cuisine: {(rating, food)}}
    private final Map<String, TreeSet<Pair<Integer, String>>> cuisineToRatingAndFoods = new HashMap<>();
    private final Map<String, String> foodToCuisine = new HashMap<>();
    private final Map<String, Integer> foodToRating = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; ++i) {
            cuisineToRatingAndFoods.computeIfAbsent(
                    cuisines[i],
                    key -> new TreeSet<>((a, b) ->
                            a.getKey().equals(b.getKey()) ? a.getValue().compareTo(b.getValue()) : b.getKey() - a.getKey()
                    )
            );
            cuisineToRatingAndFoods.get(cuisines[i]).add(new Pair<>(ratings[i], foods[i]));
            foodToCuisine.put(foods[i], cuisines[i]);
            foodToRating.put(foods[i], ratings[i]);
        }
    }

    public void changeRating(String food, int newRating) {
        final String cuisine = foodToCuisine.get(food);
        final int oldRating = foodToRating.get(food);
        TreeSet<Pair<Integer, String>> ratingAndFoods = cuisineToRatingAndFoods.get(cuisine);
        ratingAndFoods.remove(new Pair<>(oldRating, food));
        ratingAndFoods.add(new Pair<>(newRating, food));
        foodToRating.put(food, newRating);
    }

    public String highestRated(String cuisine) {
        return cuisineToRatingAndFoods.get(cuisine).first().getValue();
    }

    public static void main(String[] args) {
        String[] foods = {"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"};
        String[] cuisines = {"korean", "japanese", "japanese", "greek", "japanese", "korean"};
        int[] ratings = {9, 12, 8, 15, 14, 7};

        FoodRatings foodRatings = new FoodRatings(foods, cuisines, ratings);
        System.out.println(foodRatings.highestRated("korean")); // Output: kimchi
        System.out.println(foodRatings.highestRated("japanese")); // Output: ramen
        foodRatings.changeRating("sushi", 16);
        System.out.println(foodRatings.highestRated("japanese")); // Output: sushi
        foodRatings.changeRating("ramen", 16);
        System.out.println(foodRatings.highestRated("japanese")); // Output: ramen
    }
}

class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}


//Задача полягає в розробці системи оцінки їжі, яка здатна виконувати три операції: ініціалізацію системи,
// зміну оцінки для конкретного продукту та знаходження найвищооціненого продукту для певного типу кухні.
//
//Опис рішення:
//
//Ініціалізація системи:
//
//Використовується клас FoodRatings.
//Під час ініціалізації передаються масиви з іменами їжі, типами кухні та початковими оцінками.
//Для збереження даних використовуються три структури даних: cuisineToRatingAndFoods, foodToCuisine, foodToRating.
//cuisineToRatingAndFoods - це Map, де ключем є тип кухні, а значенням TreeSet, який зберігає пари (оцінка, їжа)
// для даного типу кухні.
//foodToCuisine - це Map, яка зберігає зв'язок між іменем їжі та типом кухні.
//foodToRating - це Map, яка зберігає зв'язок між іменем їжі та її оцінкою.
//Зміна оцінки продукту:
//
//По імені продукту знаходимо його тип кухні.
//Оновлюємо оцінку продукту в foodToRating.
//Видаляємо пару (стара оцінка, їжа) з cuisineToRatingAndFoods та додаємо нову пару (нова оцінка, їжа).
//Знаходження найвищооціненого продукту для певного типу кухні:
//
//Знаходимо TreeSet для даного типу кухні в cuisineToRatingAndFoods.
//Перевіряємо, чи множина не є порожньою.
//Якщо не порожня, повертаємо перший елемент TreeSet, тобто найвищооцінений продукт.
//Оптимізація:
//
//Використовується computeIfAbsent для автоматичного створення TreeSet для нового типу кухні.
//Замість TreeSet тепер використовується Pair<Integer, String>, щоб краще виражати пари оцінок та їжі.
//При зміні оцінки використовується TreeSet для швидкого оновлення найвищооцінених продуктів.
//Це рішення ефективно та коректно виконує вказані операції оцінювання їжі та враховує вимоги завдання.


//Design a food rating system that can do the following:
//
//Modify the rating of a food item listed in the system.
//Return the highest-rated food item for a type of cuisine in the system.
//Implement the FoodRatings class:
//
//FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are
// described by foods, cuisines and ratings, all of which have a length of n.
//foods[i] is the name of the ith food,
//cuisines[i] is the type of cuisine of the ith food, and
//ratings[i] is the initial rating of the ith food.
//void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
//String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the
// given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
//Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that
// is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before
// y[i] in alphabetic order.
//
//
//
//Example 1:
//
//Input
//["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
//[[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek",
// "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"],
// ["ramen", 16], ["japanese"]]
//Output
//[null, "kimchi", "ramen", null, "sushi", null, "ramen"]
//
//Explanation
//FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"],
// ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
//foodRatings.highestRated("korean"); // return "kimchi"
//                                    // "kimchi" is the highest rated korean food with a rating of 9.
//foodRatings.highestRated("japanese"); // return "ramen"
//                                      // "ramen" is the highest rated japanese food with a rating of 14.
//foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
//foodRatings.highestRated("japanese"); // return "sushi"
//                                      // "sushi" is the highest rated japanese food with a rating of 16.
//foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
//foodRatings.highestRated("japanese"); // return "ramen"
//                                      // Both "sushi" and "ramen" have a rating of 16.
//                                      // However, "ramen" is lexicographically smaller than "sushi".
//
//
//Constraints:
//
//1 <= n <= 2 * 104
//n == foods.length == cuisines.length == ratings.length
//1 <= foods[i].length, cuisines[i].length <= 10
//foods[i], cuisines[i] consist of lowercase English letters.
//1 <= ratings[i] <= 108
//All the strings in foods are distinct.
//food will be the name of a food item in the system across all calls to changeRating.
//cuisine will be a type of cuisine of at least one food item in the system across all calls to highestRated.
//At most 2 * 104 calls in total will be made to changeRating and highestRated.
