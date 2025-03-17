package P1401_1500.P1436_Destination_City;

import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<List<String>> paths1 = List.of(
                List.of("London", "New York"),
                List.of("New York", "Lima"),
                List.of("Lima", "Sao Paulo")
        );
        System.out.println(destCity(paths1)); // Output: "Sao Paulo"

        List<List<String>> paths2 = List.of(
                List.of("B", "C"),
                List.of("D", "B"),
                List.of("C", "A")
        );
        System.out.println(destCity(paths2)); // Output: "A"

        List<List<String>> paths3 = List.of(
                List.of("A", "Z")
        );
        System.out.println(destCity(paths3)); // Output: "Z"
    }

    public static String destCity(List<List<String>> paths) {
        HashSet<String> startingCities = new HashSet<>();

        // add all starting cities to the HashSet
        for (List<String> path : paths) {
            startingCities.add(path.get(0));
        }

        // iterate through paths to find the destination city
        for (List<String> path : paths) {
            String destinationCity = path.get(1);
            if (!startingCities.contains(destinationCity)) {
                return destinationCity;
            }
        }

        // if no destination city is found
        return null;
    }

}

//Задача полягає в тому, щоб знайти місто-призначення, тобто те місто, з якого не виходить жодного напрямку до
// іншого міста. Оскільки граф шляхів утворює лінію без циклів, таке місто-призначення завжди існує, і воно єдине.
//Алгоритм використовує HashSet для зберігання всіх міст, які є початковими точками шляхів. Спочатку додаються
// всі початкові міста до HashSet. Потім проходиться по усіх шляхах і знаходиться місто-призначення, яке не
// є початковим містом.
//Ось пояснення коду:
//Створюємо HashSet з назвами початкових міст (startingCities).
//Додаємо всі початкові міста до HashSet.
//Проходимося по всіх шляхах і шукаємо місто-призначення, яке не є початковим містом.
//Повертаємо знайдене місто-призначення.
//Наприклад, для вхідних даних [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]], ми додаємо
// "London", "New York" і "Lima" до HashSet. Потім, переглядаючи усі шляхи, знаходимо, що "Sao Paulo" не є
// початковим містом і повертаємо його як місто-призначення.
//Цей підхід гарантує знаходження єдиного міста-призначення в графі шляхів без циклів.

//You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going
// from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.
//It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one
// destination city.

//Example 1:
//Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
//Output: "Sao Paulo"
//Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your
// trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".

//Example 2:
//Input: paths = [["B","C"],["D","B"],["C","A"]]
//Output: "A"
//Explanation: All possible trips are:
//"D" -> "B" -> "C" -> "A".
//"B" -> "C" -> "A".
//"C" -> "A".
//"A".
//Clearly the destination city is "A".

//Example 3:
//Input: paths = [["A","Z"]]
//Output: "Z"

//Constraints:
//1 <= paths.length <= 100
//paths[i].length == 2
//1 <= cityAi.length, cityBi.length <= 10
//cityAi != cityBi
//All strings consist of lowercase and uppercase English letters and the space character.
