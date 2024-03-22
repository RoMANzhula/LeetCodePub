package P701_800.P791_Custom_String_Sort;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        String order1 = "cba";
        String s1 = "abcd";
        System.out.println(solution.customSortString(order1, s1)); // Output: "cbad"

        // Example 2
        String order2 = "bcafg";
        String s2 = "abcd";
        System.out.println(solution.customSortString(order2, s2)); // Output: "bcad"
    }

//    public String customSortString(String order, String s) {
//        // Step 1: Create a map to store the index of each character in order
//        Map<Character, Integer> charMap = new HashMap<>();
//        for (int i = 0; i < order.length(); i++) {
//            charMap.put(order.charAt(i), i);
//        }
//
//        // Step 2: Sort the characters in s based on the custom order
//        Character[] charArray = new Character[s.length()];
//        for (int i = 0; i < s.length(); i++) {
//            charArray[i] = s.charAt(i);
//        }
//        // Custom sort based on the order defined in charMap
//        Arrays.sort(charArray, (a, b) -> {
//            int indexA = charMap.getOrDefault(a, order.length());
//            int indexB = charMap.getOrDefault(b, order.length());
//            return indexA - indexB;
//        });
//
//        // Step 3: Construct the sorted string
//        StringBuilder sortedString = new StringBuilder();
//        for (char c : charArray) {
//            sortedString.append(c);
//        }
//
//        return sortedString.toString();
//    }

    // faster solve
    public String customSortString(String order, String s) {
        // Step 1: Create an array to store the index of each character in order
        int[] charIndex = new int[26]; // Assuming lowercase English letters
        for (int i = 0; i < order.length(); i++) {
            charIndex[order.charAt(i) - 'a'] = i + 1; // Add 1 to avoid 0 indexing
        }

        // Step 2: Count sort characters in s based on the custom order
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Construct the sorted string
        StringBuilder sortedString = new StringBuilder();
        for (char c : order.toCharArray()) {
            int index = c - 'a';
            while (count[index] > 0) {
                sortedString.append(c);
                count[index]--;
            }
        }

        // Append remaining characters not present in order
        for (char c = 'a'; c <= 'z'; c++) {
            while (count[c - 'a'] > 0) {
                sortedString.append(c);
                count[c - 'a']--;
            }
        }

        return sortedString.toString();
    }
}

//Створення Масиву для Зберігання Індексів: На першому кроці ми створюємо масив charIndex, в якому кожному
// символу алфавіту відповідає індекс у порядку order. Наприклад, якщо order = "cba", тоді charIndex['c' - 'a'] = 0,
// charIndex['b' - 'a'] = 1, charIndex['a' - 'a'] = 2. Це дозволяє нам ефективно знаходити порядок символів.
//Кількісне Сортування: На другому кроці ми використовуємо кількісне сортування для впорядкування символів у
// рядку s. Ми перебираємо всі символи у s, збільшуючи лічильник для кожного символу. Після цього ми будуємо
// відсортований рядок, перебираючи символи в order і додаючи їх відповідно до кількості згадок цього символу у s.
//Формування Відсортованого Рядка: Нарешті, ми формуємо відсортований рядок. Спочатку ми додаємо символи, які
// зустрічаються у порядку order, в порядку, який вони вказані у order. Потім ми додаємо залишкові символи,
// які не зустрічаються у order, у будь-якому порядку.
//Це рішення ефективно працює з обмеженнями, зазначеними у завданні, та надає відсортовану рядок, яка відповідає
// вимогам задачі.

//You are given two strings order and s. All the characters of order are unique and were sorted in some custom
// order previously.
//Permute the characters of s so that they match the order that order was sorted. More specifically, if a character
// x occurs before a character y in order, then x should occur before y in the permuted string.
//Return any permutation of s that satisfies this property.
//
//Example 1:
//Input:  order = "cba", s = "abcd"
//Output:  "cbad"
//Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
//Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
//
//Example 2:
//Input:  order = "bcafg", s = "abcd"
//Output:  "bcad"
//Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character
// "d" in s does not appear in order, so its position is flexible.
//Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can
// be placed at any position since it's not in order. The output "bcad" correctly follows this rule. Other
// arrangements like "bacd" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.
//
//Constraints:
//1 <= order.length <= 26
//1 <= s.length <= 200
//order and s consist of lowercase English letters.
//All the characters of order are unique.
