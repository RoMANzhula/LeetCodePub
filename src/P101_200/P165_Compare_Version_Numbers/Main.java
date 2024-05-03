package P101_200.P165_Compare_Version_Numbers;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String version1 = "1.01";
        String version2 = "1.001";
        System.out.println(solution.compareVersion(version1, version2)); // Output: 0

        version1 = "1.0";
        version2 = "1.0.0";
        System.out.println(solution.compareVersion(version1, version2)); // Output: 0

        version1 = "0.1";
        version2 = "1.1";
        System.out.println(solution.compareVersion(version1, version2)); // Output: -1
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int maxLength = Math.max(v1.length, v2.length);
        for (int i = 0; i < maxLength; i++) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }

        return 0;
    }

}

//порівняння двох версій програмного забезпечення, поданих у вигляді рядків. Версії програм можуть мати будь-яку
// кількість "ревізій", які розділені крапкою. Наприклад, "1.0", "2.5.33", "0.1" - це допустимі версійні номери.
//Програма порівнює ревізії починаючи з першої і до останньої. Вони перетворюються в цілі числа, і якщо числа не
// рівні, то одна версія вважається більшою або меншою за іншу, залежно від порівняння чисел. Якщо числа однакові,
// порівняння переходить до наступної ревізії. Якщо одна версія має менше ревізій, то для недостаючих ревізій
// приймається значення 0.
//На виході програма повертає:
//-1, якщо версія version1 менша за версію version2.
//1, якщо версія version1 більша за версію version2.
//0, якщо версії рівні.
//Наприклад:
//Для "1.01" і "1.001" обидва числа рівні, тому повертається 0.
//Для "1.0" і "1.0.0" так само, обидва числа рівні, повертається 0.
//Для "0.1" і "1.1" перша версія менша за другу, тому повертається -1.

//Given two version numbers, version1 and version2, compare them.
//Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may
// contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right,
// with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and
// 0.1 are valid version numbers.
//To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their
// integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version
// number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less
// than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.

//Return the following:
//If version1 < version2, return -1.
//If version1 > version2, return 1.
//Otherwise, return 0.
//
//Example 1:
//Input: version1 = "1.01", version2 = "1.001"
//Output: 0
//Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".

//Example 2:
//Input: version1 = "1.0", version2 = "1.0.0"
//Output: 0
//Explanation: version1 does not specify revision 2, which means it is treated as "0".

//Example 3:
//Input: version1 = "0.1", version2 = "1.1"
//Output: -1
//Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
//
//Constraints:
//1 <= version1.length, version2.length <= 500
//version1 and version2 only contain digits and '.'.
//version1 and version2 are valid version numbers.
//All the given revisions in version1 and version2 can be stored in a 32-bit integer.
