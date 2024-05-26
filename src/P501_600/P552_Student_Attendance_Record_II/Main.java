package P501_600.P552_Student_Attendance_Record_II;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.checkRecord(2));   // Output: 8
        System.out.println(solution.checkRecord(1));   // Output: 3
        System.out.println(solution.checkRecord(10101)); // Output: 183236316
    }

    private static final int MOD = 1_000_000_007;

    public int checkRecord(int n) {
        long[][][] dp = new long[n + 1][2][3];

        // Base case
        dp[0][0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int a = 0; a < 2; a++) {
                for (int l = 0; l < 3; l++) {
                    // Ending with P
                    dp[i][a][0] = (dp[i][a][0] + dp[i - 1][a][l]) % MOD;

                    // Ending with A
                    if (a > 0) {
                        dp[i][a][0] = (dp[i][a][0] + dp[i - 1][a - 1][l]) % MOD;
                    }

                    // Ending with L
                    if (l > 0) {
                        dp[i][a][l] = (dp[i][a][l] + dp[i - 1][a][l - 1]) % MOD;
                    }
                }
            }
        }

        long result = 0;
        for (int a = 0; a < 2; a++) {
            for (int l = 0; l < 3; l++) {
                result = (result + dp[n][a][l]) % MOD;
            }
        }

        return (int) result;
    }
}

//Задача полягає в тому, щоб визначити кількість можливих записів відвідувань довжиною
//n, які відповідають певним критеріям. Конкретно, студент може отримати нагороду за відвідування, якщо:
//Він був відсутній ('A') строго менше 2 днів.
//Він ніколи не запізнювався ('L') 3 або більше днів поспіль.
//Для розв'язання цієї задачі використовується метод динамічного програмування, оскільки кількість можливих
// варіантів швидко зростає з збільшенням n, і потрібно знайти ефективний спосіб обчислення.
//Динамічне програмування
//Таблиця станів (dp):
//Ми використовуємо тривимірну таблицю dp[i][a][l], де:
//i – довжина послідовності.
//a – кількість відсутностей (0 або 1, оскільки більше 1 відсутності робить послідовність невалідною).
//l – кількість поспіль запізнень (0, 1 або 2, оскільки 3 або більше запізнень поспіль роблять послідовність невалідною).
//Базовий випадок:
//dp[0][0][0] = 1: Існує один спосіб мати порожню послідовність, яка автоматично є валідною.
//Перехід:
//Для кожного дня i:
//Якщо останній символ 'P' (присутній): можна додати 'P' до будь-якої валідної послідовності, яка закінчується
// будь-яким станом.
//Якщо останній символ 'A' (відсутній): можна додати 'A' лише якщо у нас ще немає 'A'.
//Якщо останній символ 'L' (запізнився): можна додати 'L', якщо послідовність не закінчується двома 'L' підряд.
//Підрахунок результату:
//Ми сумуємо всі валідні послідовності довжини n для всіх можливих станів (тобто з урахуванням всіх комбінацій
// відсутностей та запізнень).
//Висновок
//З допомогою динамічного програмування ми заповнюємо таблицю станів, враховуючи всі можливі валідні послідовності
// для кожної довжини від 0 до n. Це дозволяє нам ефективно і коректно обчислити кількість валідних записів
// відвідувань для заданої довжини n.

//An attendance record for a student can be represented as a string where each character signifies whether the
// student was absent, late, or present on that day. The record only contains the following three characters:
//'A': Absent.
//'L': Late.
//'P': Present.
//Any student is eligible for an attendance award if they meet both of the following criteria:
//The student was absent ('A') for strictly fewer than 2 days total.
//The student was never late ('L') for 3 or more consecutive days.
//Given an integer n, return the number of possible attendance records of length n that make a student eligible
// for an attendance award. The answer may be very large, so return it modulo 109 + 7.
//
//Example 1:
//Input: n = 2
//Output: 8
//Explanation: There are 8 records with length 2 that are eligible for an award:
//"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).

//Example 2:
//Input: n = 1
//Output: 3

//Example 3:
//Input: n = 10101
//Output: 183236316
//
//Constraints:
//1 <= n <= 105
