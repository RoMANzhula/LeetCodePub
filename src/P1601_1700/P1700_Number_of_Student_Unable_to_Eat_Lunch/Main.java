package P1601_1700.P1700_Number_of_Student_Unable_to_Eat_Lunch;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] students1 = {1,1,0,0};
        int[] sandwiches1 = {0,1,0,1};
        System.out.println("Example 1 Output: " + solution.countStudents(students1, sandwiches1)); // Output: 0

        int[] students2 = {1,1,1,0,0,1};
        int[] sandwiches2 = {1,0,0,0,1,1};
        System.out.println("Example 2 Output: " + solution.countStudents(students2, sandwiches2)); // Output: 3
    }

    public int countStudents(int[] students, int[] sandwiches) {
        int[] count = new int[2]; // count of students who prefer circular (0) and square (1) sandwiches
        for (int student : students) {
            count[student]++;
        }

        for (int sandwich : sandwiches) {
            if (count[sandwich] == 0) {
                break; // no more students who prefer this type of sandwich
            }
            count[sandwich]--;
        }

        return count[0] + count[1];
    }
}

//Задача полягає у тому, що потрібно визначити кількість студентів, які не зможуть поїсти сендвічі. У шкільній їдальні
// є два типи сендвічів: круглі і квадратні, їх позначають числами 0 і 1 відповідно. Усі учні стоять у черзі. Кожен
// учень вибирає, який сендвіч він хоче спробувати.
//Число сендвічів у їдальні дорівнює числу учнів. Сендвічі розміщені в стопці. На кожному кроці:
//Якщо учень на початку черги вибирає сендвіч з верху стопки і йому подобається цей тип сендвіча, він бере його і
// залишає чергу.
//Інакше він залишає сендвіч і йде в кінець черги.
//Цей процес триває до тих пір, поки ніякі учні не залишаться без сендвічів або не зможуть поїсти.
//Для вирішення цієї задачі ми скористаємося підрахунком кількості учнів, які бажають кожен з типів сендвічів, і
// порівняємо їх з наявною кількістю сендвічів. У випадку, якщо для певного типу сендвіча кількість учнів, які цей
// тип вибирають, перевищує кількість доступних сендвічів цього типу, ці учні не зможуть поїсти. Таким чином, ми
// просто підраховуємо кількість учнів, які залишаться без сендвічів для обох типів і повертаємо суму цих кількостей
// як результат.

//The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1
// respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.
//The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a
// stack. At each step:
//If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and
// leave the queue.
//Otherwise, they will leave it and go to the queue's end.
//This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
//
//You are given two integer arrays students and sandwiches where sandwiches[i] is the type of
// the i​​​​​​th sandwich in the stack (i = 0 is the top of the stack) and students[j] is the
// preference of the j​​​​​​th student in the initial queue (j = 0 is the front of the queue). Return
// the number of students that are unable to eat.
//
//Example 1:
//Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
//Output: 0
//Explanation:
//- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
//- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
//- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
//- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
//- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
//- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
//- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
//- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
//Hence all students are able to eat.

//Example 2:
//Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
//Output: 3
//
//Constraints:
//1 <= students.length, sandwiches.length <= 100
//students.length == sandwiches.length
//sandwiches[i] is 0 or 1.
//students[i] is 0 or 1.
