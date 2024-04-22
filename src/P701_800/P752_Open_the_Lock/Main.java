package P701_800.P752_Open_the_Lock;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] deadends1 = {"0201","0101","0102","1212","2002"};
        String target1 = "0202";
        System.out.println("Example 1: " + solution.openLock(deadends1, target1)); // Output: 6

        String[] deadends2 = {"8888"};
        String target2 = "0009";
        System.out.println("Example 2: " + solution.openLock(deadends2, target2)); // Output: 1

        String[] deadends3 = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target3 = "8888";
        System.out.println("Example 3: " + solution.openLock(deadends3, target3)); // Output: -1

    }

    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains("0000")) return -1; // starting point is a dead end

        Set<String> visited = new HashSet<>();
        visited.add("0000");

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(target)) return level;

                if (deadSet.contains(current)) continue; // skip dead ends

                // generate next possible combinations by rotating each wheel
                for (int j = 0; j < 4; j++) {
                    for (int d = -1; d <= 1; d += 2) {
                        int digit = (current.charAt(j) - '0' + d + 10) % 10;
                        String next = current.substring(0, j) + (char)(digit + '0') + current.substring(j + 1);

                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }

            level++;
        }

        return -1; //target not reachable
    }
}

//Ця програма моделює гру замка з чотирма дисковими цифровими комбінаціями. Завдання полягає в тому, щоб знайти
// найменшу кількість кроків, які потрібно зробити, щоб дістатися від початкового стану "0000" до цільового стану,
// який задається параметром "target".
//Цей код використовує алгоритм пошуку в ширину (BFS) для ефективного перебору можливих комбінацій та знаходження
// найкоротшого шляху до цільового стану.
//Основні кроки цього алгоритму:
//Створення множини deadSet, яка містить всі "мертві точки" (комбінації, які недосяжні або неприйнятні).
//Перевірка, чи не є початковий стан "0000" "мертвою точкою". Якщо так, повертається -1, оскільки цільовий стан не
// може бути досягнутий.
//Створення черги queue, яка починається зі стартового стану "0000".
//Виконання циклу, поки черга не стане порожньою:
//Для кожного стану в черзі перевіряється, чи не є він цільовим станом. Якщо так, повертається поточний
// рівень (кількість кроків).
//Якщо стан є "мертвою точкою", він пропускається.
//Для кожного диска (цифри) в комбінації генеруються наступні можливі комбінації, обертаючи диск у двох напрямках.
//Нові стани, які ще не були відвідані, додаються до черги та множини visited.
//Якщо цикл закінчився, та черга стала порожньою, означає, що цільовий стан не досяжний, тому повертається -1.
//Цей алгоритм ефективно шукає найкоротший шлях до цілі, оскільки він перебирає всі можливі комбінації замка,
// використовуючи мінімальну кількість кроків.

//You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots:
// '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we
// can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
//The lock initially starts at '0000', a string representing the state of the 4 wheels.
//You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the
// lock will stop turning and you will be unable to open it.
//Given a target representing the value of the wheels that will unlock the lock, return the minimum total number
// of turns required to open the lock, or -1 if it is impossible.
//
//Example 1:
//Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//Output: 6
//Explanation:
//A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
//Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
//because the wheels of the lock become stuck after the display becomes the dead end "0102".

//Example 2:
//Input: deadends = ["8888"], target = "0009"
//Output: 1
//Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".

//Example 3:
//Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
//Output: -1
//Explanation: We cannot reach the target without getting stuck.
//
//Constraints:
//1 <= deadends.length <= 500
//deadends[i].length == 4
//target.length == 4
//target will not be in the list deadends.
//target and deadends[i] consist of digits only.