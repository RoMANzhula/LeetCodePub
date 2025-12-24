package P3001_3100.P3974_Apple_Redistribution_into_Boxes;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] apple1 = {1, 3, 2};
        int[] capacity1 = {4, 3, 1, 5, 2};
        System.out.println(solution.minimumBoxes(apple1, capacity1)); // Output: 2

        int[] apple2 = {5, 5, 5};
        int[] capacity2 = {2, 4, 2, 7};
        System.out.println(solution.minimumBoxes(apple2, capacity2)); // Output: 4
    }

    public int minimumBoxes(int[] apple, int[] capacity) {
        // calculate total apples
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }

        // sort capacities in descending order
        Arrays.sort(capacity);

        // pick boxes greedily from largest to smallest
        int currentCapacity = 0;
        int boxesUsed = 0;

        for (int i = capacity.length - 1; i >= 0; i--) {
            currentCapacity += capacity[i];
            boxesUsed++;

            if (currentCapacity >= totalApples) {
                return boxesUsed;
            }
        }

        return boxesUsed; // guaranteed solution exists
    }

}


//Complexity:
// time - O(m log m)
// space - O(1)


//You are given an array apple of size n and an array capacity of size m.
//There are n packs where the ith pack contains apple[i] apples. There are m boxes as well, and the ith box has a
// capacity of capacity[i] apples.
//Return the minimum number of boxes you need to select to redistribute these n packs of apples into boxes.
//Note that, apples from the same pack can be distributed into different boxes.

//Example 1:
//Input: apple = [1,3,2], capacity = [4,3,1,5,2]
//Output: 2
//Explanation: We will use boxes with capacities 4 and 5.
//It is possible to distribute the apples as the total capacity is greater than or equal to the total number of apples.

//Example 2:
//Input: apple = [5,5,5], capacity = [2,4,2,7]
//Output: 4
//Explanation: We will need to use all the boxes.

//Constraints:
//1 <= n == apple.length <= 50
//1 <= m == capacity.length <= 50
//1 <= apple[i], capacity[i] <= 50
//The input is generated such that it's possible to redistribute packs of apples into boxes.
