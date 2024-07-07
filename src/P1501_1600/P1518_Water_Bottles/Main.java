package P1501_1600.P1518_Water_Bottles;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int numBottles1 = 9;
        int numExchange1 = 3;
        System.out.println(solution.numWaterBottles(numBottles1, numExchange1)); // Output: 13

        // Example 2
        int numBottles2 = 15;
        int numExchange2 = 4;
        System.out.println(solution.numWaterBottles(numBottles2, numExchange2)); // Output: 19
    }

    public int numWaterBottles(int numBottles, int numExchange) {
        int totalDrunk = 0;
        int emptyBottles = 0;

        while (numBottles > 0) {
            totalDrunk += numBottles;
            emptyBottles += numBottles;
            numBottles = emptyBottles / numExchange;
            emptyBottles = emptyBottles % numExchange;
        }

        return totalDrunk;
    }
}

//Explanation of the Code:
//Initialization:
//totalDrunk keeps track of the total number of bottles drunk.
//emptyBottles keeps track of the current number of empty bottles.
//While Loop:
//As long as there are full bottles to drink (numBottles > 0):
//Add the number of full bottles to totalDrunk.
//Convert the full bottles to empty bottles by adding numBottles to emptyBottles.
//Calculate the new number of full bottles by exchanging the empty bottles (emptyBottles / numExchange).
//Calculate the remaining empty bottles after the exchange (emptyBottles % numExchange).
//Main Method:
//The main method tests the function with the provided examples to verify correctness.
//This approach ensures we accurately count the maximum number of bottles that can be drunk by iteratively
// simulating the drinking and exchanging process.


//There are numBottles water bottles that are initially full of water. You can exchange numExchange empty water
// bottles from the market with one full water bottle.
//The operation of drinking a full water bottle turns it into an empty bottle.
//Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.
//
//Example 1:
//Input: numBottles = 9, numExchange = 3
//Output: 13
//Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
//Number of water bottles you can drink: 9 + 3 + 1 = 13.

//Example 2:
//Input: numBottles = 15, numExchange = 4
//Output: 19
//Explanation: You can exchange 4 empty bottles to get 1 full water bottle.
//Number of water bottles you can drink: 15 + 3 + 1 = 19.
//
//Constraints:
//1 <= numBottles <= 100
//2 <= numExchange <= 100