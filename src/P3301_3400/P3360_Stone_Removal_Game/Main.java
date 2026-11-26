package P3301_3400.P3360_Stone_Removal_Game;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.canAliceWin(12)); // true
        System.out.println(solution.canAliceWin(1)); // false
    }

    public boolean canAliceWin(int n) {
        int stones = 10;
        boolean aliceTurn = true;

        while (n >= stones) {
            n -= stones;
            stones--;
            aliceTurn = !aliceTurn;
        }

        return !aliceTurn;
    }

}

//Complexity:
// time and space - O(1)


//Alice and Bob are playing a game where they take turns removing stones from a pile, with Alice going first.
//Alice starts by removing exactly 10 stones on her first turn.
//For each subsequent turn, each player removes exactly 1 fewer stone than the previous opponent.
//The player who cannot make a move loses the game.
//Given a positive integer n, return true if Alice wins the game and false otherwise.

//Example 1:
//Input: n = 12
//Output: true
//Explanation:
//Alice removes 10 stones on her first turn, leaving 2 stones for Bob.
//Bob cannot remove 9 stones, so Alice wins.

//Example 2:
//Input: n = 1
//Output: false
//Explanation:
//Alice cannot remove 10 stones, so Alice loses.

//Constraints:
//1 <= n <= 50
