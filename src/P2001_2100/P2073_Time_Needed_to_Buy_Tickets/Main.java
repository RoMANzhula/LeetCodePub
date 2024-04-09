package P2001_2100.P2073_Time_Needed_to_Buy_Tickets;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] tickets1 = {2, 3, 2};
        int k1 = 2;
        System.out.println("Example 1 Output: " + solution.timeRequiredToBuy(tickets1, k1)); // Output: 6

        int[] tickets2 = {5, 1, 1, 1};
        int k2 = 0;
        System.out.println("Example 2 Output: " + solution.timeRequiredToBuy(tickets2, k2)); // Output: 8
    }

    public int timeRequiredToBuy(int[] tickets, int k) {
        int n = tickets.length;
        int[] queue = new int[n];
        System.arraycopy(tickets, 0, queue, 0, n);

        int time = 0;
        while (queue[k] > 0) {
            for (int i = 0; i < n; i++) {
                if (queue[i] > 0) {
                    queue[i]--;
                    time++;
                    if (i == k && queue[k] == 0) {
                        return time;
                    }
                }
            }
        }
        return time;
    }
}

//Задача полягає в тому, щоб визначити час, який займе покупка квитків для людини, яка стоїть на певній позиції в черзі.
//Для вирішення цієї задачі ми можемо симулювати процес покупки квитків. Ми ітеруємо через чергу людей до тих пір,
// поки людина на позиції k не закінчить купувати квитки. На кожній ітерації ми віднімаємо 1 від кількості квитків,
// яку хоче купити людина на передньому місці черги, і переміщаємо її назад у кінець черги, якщо вона все ще хоче
// купити більше квитків.
//Отже, ми просто проходимо через чергу людей, зменшуючи кількість їхніх квитків до тих пір, поки людина на позиції
// k не закінчить купувати квитки, і підраховуємо час, який це займе. Коли людина на позиції k купить всі свої квитки,
// ми повертаємо час, який це зайняло.
//Це симулює процес покупки квитків у черзі і допомагає знайти час, який займе покупка квитків для людини на певній
// позиції в черзі.

//There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and
// the (n - 1)th person is at the back of the line.
//You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would
// like to buy is tickets[i].
//Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back
// to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have
// any tickets left to buy, the person will leave the line.
//Return the time taken for the person at position k (0-indexed) to finish buying tickets.
//
//Example 1:
//Input: tickets = [2,3,2], k = 2
//Output: 6
//Explanation:
//- In the first pass, everyone in the line buys a ticket and the line becomes [1, 2, 1].
//- In the second pass, everyone in the line buys a ticket and the line becomes [0, 1, 0].
//The person at position 2 has successfully bought 2 tickets and it took 3 + 3 = 6 seconds.

//Example 2:
//Input: tickets = [5,1,1,1], k = 0
//Output: 8
//Explanation:
//- In the first pass, everyone in the line buys a ticket and the line becomes [4, 0, 0, 0].
//- In the next 4 passes, only the person in position 0 is buying tickets.
//The person at position 0 has successfully bought 5 tickets and it took 4 + 1 + 1 + 1 + 1 = 8 seconds.
//
//Constraints:
//n == tickets.length
//1 <= n <= 100
//1 <= tickets[i] <= 100
//0 <= k < n
