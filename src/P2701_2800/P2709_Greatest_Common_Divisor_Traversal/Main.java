package P2701_2800.P2709_Greatest_Common_Divisor_Traversal;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {2, 3, 6};
        int[] nums2 = {3, 9, 5};
        int[] nums3 = {4, 3, 12, 8};

        Main solution = new Main();

        System.out.println("Output for nums1: " + solution.canTraverseAllPairs(nums1)); // Output: true
        System.out.println("Output for nums2: " + solution.canTraverseAllPairs(nums2)); // Output: false
        System.out.println("Output for nums3: " + solution.canTraverseAllPairs(nums3)); // Output: true
    }

    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        int maxNum = Arrays.stream(nums).max().getAsInt();
        List<Integer> maxPrimeFactor = sieveEratosthenes(maxNum + 1);
        Map<Integer, Integer> primeToFirstIndex = new HashMap<>();
        FinalFind uf = new FinalFind(n);

        for (int i = 0; i < n; i++) {
            for (int primeFactor : getPrimeFactors(nums[i], maxPrimeFactor)) {
                if (primeToFirstIndex.containsKey(primeFactor)) {
                    uf.unionBySize(primeToFirstIndex.get(primeFactor), i);
                } else {
                    primeToFirstIndex.put(primeFactor, i);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (uf.getSize(i) == n) return true;
        }
        return false;
    }

    private List<Integer> sieveEratosthenes(int n) {
        List<Integer> minPrimeFactors = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            minPrimeFactors.add(i);
        }
        for (int i = 2; i * i <= n; i++) {
            if (minPrimeFactors.get(i) == i) {
                for (int j = i * i; j <= n; j += i) {
                    minPrimeFactors.set(j, Math.min(minPrimeFactors.get(j), i));
                }
            }
        }
        return minPrimeFactors;
    }

    private List<Integer> getPrimeFactors(int num, List<Integer> minPrimeFactors) {
        List<Integer> primeFactors = new ArrayList<>();
        while (num > 1) {
            int divisor = minPrimeFactors.get(num);
            primeFactors.add(divisor);
            while (num % divisor == 0) {
                num /= divisor;
            }
        }
        return primeFactors;
    }
}

class FinalFind {
    private int[] id;
    private int[] sz;

    public FinalFind(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public void unionBySize(int u, int v) {
        int i = find(u);
        int j = find(v);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            sz[j] += sz[i];
            id[i] = j;
        } else {
            sz[i] += sz[j];
            id[j] = i;
        }
    }

    public int getSize(int i) {
        return sz[i];
    }

    private int find(int u) {
        if (id[u] != u) id[u] = find(id[u]);
        return id[u];
    }
}

//UnionFind (Об'єднання знаходження): Це клас для реалізації структури даних "об'єднання-знаходження", яка
// використовується для об'єднання множин та визначення зв'язності між елементами. Ця структура даних
// використовується для знаходження компонент зв'язності в графі.
//Метод unionBySize: Цей метод виконує об'єднання двох компонент за розміром. Він перевіряє розмір кожної
// компоненти і об'єднує меншу компоненту з більшою.
//Метод getSize: Цей метод повертає розмір компоненти, до якої належить заданий елемент.
//Клас Solution: Це основний клас, який містить метод canTraverseAllPairs.
//Метод canTraverseAllPairs: Цей метод приймає масив чисел nums і повертає true, якщо існує можливість
// пройти між всіма парами індексів у масиві nums. Щоб це зробити, він використовує алгоритм розкладу
// на множники (функція _getPrimeFactors) для отримання простих множників кожного числа. Після цього
// він використовує клас UnionFind для визначення зв'язності між індексами, де два індекси вважаються
// зв'язаними, якщо між числами, які вони представляють, є спільний простий множник.
//Метод _sieveEratosthenes: Цей метод використовується для знаходження мінімальних простих множників чисел від 2 до n.
//Метод _getPrimeFactors: Цей метод використовується для знаходження простих множників заданого числа,
// використовуючи мінімальні прості множники, обчислені методом _sieveEratosthenes.
//Усі ці елементи дозволяють ефективно вирішити задачу, використовуючи алгоритм розкладу чисел на множники
// та структуру даних "об'єднання-знаходження" для визначення зв'язності між елементами.

//You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can
// traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is
// the greatest common divisor.
//Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a
// sequence of traversals that can take us from i to j.
//Return true if it is possible to traverse between all such pairs of indices, or false otherwise.

//Example 1:
//Input: nums = [2,3,6]
//Output: true
//Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
//To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from
// index 0 to index 2 because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to
// index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
//To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise,
// to go from index 1 to index 2, we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1

//Example 2:
//Input: nums = [3,9,5]
//Output: false
//Explanation: No sequence of traversals can take us from index 0 to index 2 in this example. So, we return false.

//Example 3:
//Input: nums = [4,3,12,8]
//Output: true
//Explanation: There are 6 possible pairs of indices to traverse between: (0, 1), (0, 2), (0, 3), (1, 2), (1, 3),
// and (2, 3). A valid sequence of traversals exists for each pair, so we return true.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
