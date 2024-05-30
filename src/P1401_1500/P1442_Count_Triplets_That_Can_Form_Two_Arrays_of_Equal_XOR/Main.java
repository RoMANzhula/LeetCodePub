package P1401_1500.P1442_Count_Triplets_That_Can_Form_Two_Arrays_of_Equal_XOR;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {2, 3, 1, 6, 7};
        System.out.println(solution.countTriplets(arr1));  // Output: 4

        int[] arr2 = {1, 1, 1, 1, 1};
        System.out.println(solution.countTriplets(arr2));  // Output: 10
    }

    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] prefixXOR = new int[n + 1];

        // Compute the prefix XOR
        for (int i = 0; i < n; i++) {
            prefixXOR[i + 1] = prefixXOR[i] ^ arr[i];
        }

        int count = 0;

        // Iterate through all pairs (i, k) and check if prefixXOR[i] == prefixXOR[k + 1]
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (prefixXOR[i] == prefixXOR[k + 1]) {
                    // If they are equal, all j in the range (i+1, k+1) are valid
                    count += (k - i);
                }
            }
        }

        return count;
    }
}

//Щоб знайти кількість трійок (i, j, k) для яких виконується умова a == b, нам потрібно використати властивості
// операції XOR. Головна ідея полягає в тому, що для будь-якого сегменту масиву, якщо XOR підмасиву від i до j-1
// дорівнює XOR підмасиву від j до k, тоді XOR усього підмасиву від i до k повинен дорівнювати нулю.
//Кроки рішення:
//Вирахування префіксного XOR:
//Спочатку обчислимо префіксний XOR для масиву. Префіксний XOR для індексу i - це XOR усіх елементів від початку
// масиву до індексу i-1. Це допоможе нам швидко визначати XOR будь-якого підмасиву.
//Перебір всіх можливих пар (i, k):
//Далі ми перебираємо всі можливі пари індексів (i, k) в масиві. Для кожної пари перевіряємо, чи дорівнюють
// префіксні XOR для індексів i та k+1. Якщо вони рівні, це означає, що XOR підмасиву від i до k дорівнює нулю.
//Підрахунок кількості трійок:
//Для кожної пари (i, k), де префіксні XOR рівні, існує k - i можливих значень для j (оскільки i < j <= k). Ми
// збільшуємо лічильник на k - i для кожної пари, яка задовольняє умову.
//Пояснення через приклад:
//Розглянемо приклад масиву [2, 3, 1, 6, 7]:
//Спочатку обчислюємо префіксний XOR для всіх індексів масиву.
//Далі перевіряємо всі пари індексів (i, k) і дивимося, чи рівні їх префіксні XOR.
//Наприклад, для пари (0, 2), префіксні XOR однакові, отже, існує 2 можливих значення для j (1 і 2), що
// задовольняють умову.
//Цей підхід дозволяє ефективно визначити кількість трійок, які відповідають умовам задачі, використовуючи
// властивості операції XOR і префіксні обчислення.

//Given an array of integers arr.
//We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
//Let's define a and b as follows:
//a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
//b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
//Note that ^ denotes the bitwise-xor operation.
//Return the number of triplets (i, j and k) Where a == b.
//
//Example 1:
//Input: arr = [2,3,1,6,7]
//Output: 4
//Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)

//Example 2:
//Input: arr = [1,1,1,1,1]
//Output: 10
//
//Constraints:
//1 <= arr.length <= 300
//1 <= arr[i] <= 108