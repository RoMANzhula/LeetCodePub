package P3001_3100.P3100_Water_Bottles_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxBottlesDrunk(13, 6)); // Output: 15
        System.out.println(solution.maxBottlesDrunk(10, 3)); // Output: 13
    }

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int result = numBottles; // count all initial bottles drunk
        int empty = numBottles;  // after drinking, they become empty

        while (empty >= numExchange) {
            // exchange empty bottles for 1 full bottle
            empty -= numExchange;
            numExchange++;        // after each exchange, requirement increases
            result++;             // drink that new bottle
            empty++;              // after drinking, it becomes empty again
        }

        return result;
    }

}

//Complexity:
// time - O(numBottles)
// space - O(1)


//You are given two integers numBottles and numExchange.
//numBottles represents the number of full water bottles that you initially have. In one operation, you can
// perform one of the following operations:
//Drink any number of full water bottles turning them into empty bottles.
//Exchange numExchange empty bottles with one full water bottle. Then, increase numExchange by one.
//Note that you cannot exchange multiple batches of empty bottles for the same value of numExchange. For example,
// if numBottles == 3 and numExchange == 1, you cannot exchange 3 empty water bottles for 3 full bottles.
//Return the maximum number of water bottles you can drink.

//Example 1:
//Input: numBottles = 13, numExchange = 6
//Output: 15
//Explanation: The table above shows the number of full water bottles, empty water bottles, the value of numExchange,
// and the number of bottles drunk.

//Example 2:
//Input: numBottles = 10, numExchange = 3
//Output: 13
//Explanation: The table above shows the number of full water bottles, empty water bottles, the value of numExchange,
// and the number of bottles drunk.

//Constraints:
//1 <= numBottles <= 100
//1 <= numExchange <= 100
